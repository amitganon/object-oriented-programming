package Tile.Unit.Player;
import Other.*;
import Resource.AbstractResource;
import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Unit;

import java.util.List;
import java.util.Random;

public abstract class Player extends Unit {
    protected PlayerDeathCallBack playerDeathCallback;
    protected AbilityCallback abilityCallback;
    protected InputProvider inputProvider;
    protected int Experience;
    public int getExperience(){
        return Experience;
    }
    protected int PlayerLevel;
    public int getPlayerLevel(){
        return PlayerLevel;
    }
    protected AbstractResource resource;

    final protected int PlayerHealthIncreaseOnLevelUp = 10;
    final protected int PlayerAttackIncreaseOnLevelUp = 4;
    final protected int PlayerDefenceIncreaseOnLevelUp = 1;

    protected Player(String name, int healthCapacity, int attack, int defense) {
        super('@', name, healthCapacity, attack, defense);
        this.PlayerLevel = 1;
        this.Experience = 0;
    }


    public Player initialize(Position position, MessageCallBack messageCallback, PlayerDeathCallBack playerDeathCallback, InputProvider inputProvider, MoveCallback moveCallback) {
        super.initialize(position, messageCallback, moveCallback);
        this.playerDeathCallback = playerDeathCallback;
        this.inputProvider = inputProvider;
        return this;
    }


    public String Description() {
        return super.Description() + "\t Level: " + PlayerLevel + "\n\tExperience: " + Experience;
    }

    abstract public void LevelUp();

    @Override
    public void accept(Player player) {
    }//not need to fill

    public void accept(Enemy enemy) {
        message.send(name + " engaged in combat with " + enemy.Name());
        message.send(this.Description());
        message.send(enemy.Description());
        Random rnd = new Random();
        int attackDamage = rnd.nextInt(attackPoints + 1);
        message.send(name + " rolled " + attackDamage + " attack points");
        enemy.Defend(attackDamage, this, false);
    }

    public void Die(Unit attackerUnit, boolean usedAbility) {
        message.send(name + " was killed by " + attackerUnit.Name());
        message.send("You lost.");
        super.tile = 'X';
        playerDeathCallback.call();
    }

    public void GetExperience(int experience) {
        this.Experience += experience;
        if(this.Experience >= PlayerLevel*50)
            LevelUp();
    }

    public void initializeAbilityCallback(AbilityCallback abilityCallback) {
        this.abilityCallback = abilityCallback;
    }

    public void CastAbility() {
        abilityCallback.GetEnemies(this);
    }

    public abstract void CastAbility(List<Enemy> listEnemies);

    public abstract void OnGameTick();
}

