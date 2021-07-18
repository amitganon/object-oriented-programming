package Tile.Unit.Player;
import Ability.Ability;
import Ability.MageAbility;
import Other.*;
import Resource.Mana;
import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Unit;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Mage extends Player{

    final protected int MageHealthIncreaseOnLevelUp = 0;
    final protected int MageAttackIncreaseOnLevelUp = 0;
    final protected int MageDefenceIncreaseOnLevelUp = 0;
    final protected int MageManaIncreaseOnLevelUp = 25;
    final protected int MageSpellPowerIncreaseOnLevelUp = 10;

    private int spellPower;
    private int hitsCount;
    private int range;

    public Mage(String name, int healthCapacity, int attack, int defense, int manaPool, int manaCost, int spellPower, int hitCount, int range){
        super(name,healthCapacity,attack,defense);
        super.resource = new Mana(manaPool, manaCost);
        this.spellPower = spellPower;
        this.hitsCount = hitCount;
        this.range = range;
        super.ability=new MageAbility();
    }

    @Override
    public String Description() {
        return super.Description() + "\t" + this.resource.toString() + "\t Spell Power: " + spellPower;
    }

    @Override
    public void CastAbility(List<Enemy> enemies) {
        if(resource.CurrentAmount() >= resource.Cost()) {
            message.send(name+" cast "+ ability.toString());
            List<Enemy> enemiesToAttack = new LinkedList<Enemy>();
            for (Enemy enemy : enemies) {
                if (getPosition().Range(enemy.getPosition()) < range)
                    enemiesToAttack.add(enemy);
            }
            if (enemiesToAttack.size() != 0) {
                for (int i = 0; i < hitsCount; i++) {
                    ability.activateAbility(this, enemiesToAttack, spellPower);
                }
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
        int healthIncrease = (PlayerHealthIncreaseOnLevelUp+MageHealthIncreaseOnLevelUp) * this.PlayerLevel;
        int attackIncrease = (PlayerAttackIncreaseOnLevelUp+MageAttackIncreaseOnLevelUp) * this.PlayerLevel;
        int defenceIncrease = (PlayerDefenceIncreaseOnLevelUp+MageDefenceIncreaseOnLevelUp) * this.PlayerLevel;
        int manaIncrease = MageManaIncreaseOnLevelUp * this.PlayerLevel;
        int spellPowerIncrease = MageSpellPowerIncreaseOnLevelUp * this.PlayerLevel;

        this.Experience -= 50 * this.PlayerLevel;
        this.PlayerLevel += 1;
        this.health.SetMaxAmount(this.health.MaxAmount() + healthIncrease);
        this.health.SetCurrentAmount(this.health.MaxAmount());
        this.attackPoints += attackIncrease;
        this.defencePoints += defenceIncrease;

        this.resource.SetMaxAmount(this.resource.MaxAmount() + manaIncrease);
        this.resource.SetCurrentAmount(Math.min(this.resource.CurrentAmount()+this.resource.MaxAmount()/4, this.resource.MaxAmount()));
        this.spellPower += spellPowerIncrease;

        message.send(this.name + " reached level " + this.PlayerLevel + ": +" + healthIncrease +  " Health, +" + attackIncrease + " Attack, +" + defenceIncrease + " Defence\n"
        + "+" + manaIncrease +  " maximum mana, +" + spellPowerIncrease + " spell power");
    }

    @Override
    public void OnGameTick() {
        resource.SetCurrentAmount(Math.min(resource.CurrentAmount() + this.PlayerLevel, resource.MaxAmount()));
    }
}
