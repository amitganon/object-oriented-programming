package Tile.Unit.Player;

import Ability.RougeAbility;
import Other.*;
import Resource.Energy;
import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Unit;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Rogue extends Player{

    final protected int RogueHealthIncreaseOnLevelUp = 0;
    final protected int RogueAttackIncreaseOnLevelUp = 3;
    final protected int RogueDefenceIncreaseOnLevelUp = 0;

    public Rogue(String name, int healthCapacity, int attack, int defense, int abilityCost) {
        super(name, healthCapacity, attack, defense);
        super.resource = new Energy(100, abilityCost);
        super.ability = new RougeAbility();
    }

    @Override
    public String Description() {
        return super.Description() + "\t" + this.resource.toString();
    }

    @Override
    public void CastAbility(List<Enemy> enemies) {
        if(resource.CurrentAmount() >= resource.Cost()) {
            message.send(name+" cast "+ ability.toString());
            List<Enemy> enemiesToAttack = new LinkedList<Enemy>();
            for (Enemy enemy : enemies) {
                if (getPosition().Range(enemy.getPosition()) < 2)
                    enemiesToAttack.add(enemy);
            }
            if (enemiesToAttack.size() != 0) {
                ability.activateAbility(this, enemiesToAttack, attackPoints);
            }
            resource.SetCurrentAmount(resource.CurrentAmount() - resource.Cost());
        }
        else{
            message.send(name+" tried to cast "+ ability.toString() + ", but there was not enough " + resource.toString());
        }
    }


    @Override
    public void interact(Unit unit) {
        unit.accept(this);
    }

    public void LevelUp(){
        int healthIncrease = PlayerHealthIncreaseOnLevelUp+RogueHealthIncreaseOnLevelUp;
        int attackIncrease = PlayerAttackIncreaseOnLevelUp+RogueAttackIncreaseOnLevelUp;
        int defenceIncrease = PlayerDefenceIncreaseOnLevelUp+RogueDefenceIncreaseOnLevelUp;

        this.Experience -= 50 * this.PlayerLevel;
        this.PlayerLevel += 1;
        this.health.SetMaxAmount(this.health.MaxAmount() + (healthIncrease * this.PlayerLevel));
        this.health.SetCurrentAmount(this.health.MaxAmount());
        this.attackPoints += attackIncrease * this.PlayerLevel;
        this.defencePoints += defenceIncrease * this.PlayerLevel;

        this.resource.SetCurrentAmount(100);

        message.send(this.name + " reached level " + this.PlayerLevel + ": +" + healthIncrease +  " Health, +" + attackIncrease + " Attack, +" + defenceIncrease + " Defence");
    }

    @Override
    public void OnGameTick(){
        resource.SetCurrentAmount(Math.min(resource.CurrentAmount() + 10, 100));
    }
}
