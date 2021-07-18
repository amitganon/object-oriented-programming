package Tile.Unit.Player;

import Ability.WarriorAbility;
import Other.*;
import Resource.Cooldown;
import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Unit;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player{

    final protected int WarriorHealthIncreaseOnLevelUp = 5;
    final protected int WarriorAttackIncreaseOnLevelUp = 2;
    final protected int WarriorDefenceIncreaseOnLevelUp = 1;

    public Warrior(String name, int healthCapacity, int attack, int defense, int abilityCooldown) {
        super(name, healthCapacity, attack, defense);
        super.resource = new Cooldown(abilityCooldown);
        super.ability = new WarriorAbility();
    }

    @Override
    public String Description() {
        return super.Description() + "\t" + this.resource.toString();
    }

    @Override
    public void CastAbility(List<Enemy> enemies) {
        if(resource.CurrentAmount()==resource.MaxAmount()) {
            message.send(name+" used "+ ability.toString() + ", healing for " + (defencePoints*10) + ".");
            List<Enemy> enemiesToAttack = new LinkedList<Enemy>();
            for (Enemy enemy : enemies) {
                if (getPosition().Range(enemy.getPosition()) < 3)
                    enemiesToAttack.add(enemy);
            }
            if (enemiesToAttack.size() != 0) {
                ability.activateAbility(this, enemiesToAttack, health.MaxAmount());
            }
            health.SetCurrentAmount(Math.min(health.CurrentAmount()+(defencePoints*10), health.MaxAmount()));
            resource.SetCurrentAmount(-1);
        }
        else{
            message.send(name+" tried to cast "+ ability.toString() + ", but there is a cooldown: " + resource.CurrentAmount());
        }
    }

    @Override
    public void interact(Unit unit) {
        unit.accept(this);
    }

    public void LevelUp(){
        int healthIncrease = (PlayerHealthIncreaseOnLevelUp+WarriorHealthIncreaseOnLevelUp) * this.PlayerLevel;
        int attackIncrease = (PlayerAttackIncreaseOnLevelUp+WarriorAttackIncreaseOnLevelUp) * this.PlayerLevel;
        int defenceIncrease = (PlayerDefenceIncreaseOnLevelUp+WarriorDefenceIncreaseOnLevelUp) * this.PlayerLevel;

        this.Experience -= 50 * this.PlayerLevel;
        this.PlayerLevel += 1;
        this.health.SetMaxAmount(this.health.MaxAmount() + healthIncrease);
        this.health.SetCurrentAmount(this.health.MaxAmount());
        this.attackPoints += attackIncrease;
        this.defencePoints += defenceIncrease;

        this.resource.SetCurrentAmount(this.resource.MaxAmount());

        message.send(this.name + " reached level " + this.PlayerLevel + ": +" + healthIncrease +  " Health, +" + attackIncrease + " Attack, +" + defenceIncrease + " Defence");
    }

    @Override
    public void OnGameTick() {
        resource.SetCurrentAmount(Math.min(resource.CurrentAmount() + 1, 3));
    }
}
