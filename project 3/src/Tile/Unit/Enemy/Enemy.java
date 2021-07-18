package Tile.Unit.Enemy;
import Other.EnemyDeathCallBack;
import Other.MessageCallBack;
import Other.MoveCallback;
import Other.Position;
import Tile.Unit.Player.Player;
import Tile.Unit.Unit;

import java.util.Random;

public abstract class Enemy extends Unit {
    public int experienceValue;
    protected EnemyDeathCallBack enemyDeathCallBack;

    public Enemy(char c, String name, int health, int attackPoints, int defencePoints, int experienceValue) {
        super(c,name, health, attackPoints, defencePoints);
        this.tile=c;
        this.experienceValue=experienceValue;
    }

    public Enemy initialize(Position position, MessageCallBack message, EnemyDeathCallBack enemyDeathCallBack, MoveCallback moveCallback){
        super.initialize(position,message,moveCallback);
        this.enemyDeathCallBack = enemyDeathCallBack;
        return this;
    }

    public String Description(){
        return super.Description() + "\t Experience Value: " + experienceValue;
    }

    abstract public void Act (Position p, Player player);

    @Override
    public void accept(Enemy enemy) {}//not need to fill

    @Override
    public void accept(Player player) {
        message.send(name+" engaged in combat with "+ player.Name());
        message.send(this.Description());
        message.send(player.Description());
        Random rnd =new Random();
        int attackDamage=rnd.nextInt(attackPoints+1);
        message.send(name+" rolled "+ attackDamage +" attack points");
        player.Defend(attackDamage, this, false);
    }

    @Override
    public void Die(Unit player, boolean usedAbility) {
        message.send(this.name + " died. " + player.Name() + " gained " + experienceValue + " experience");
        enemyDeathCallBack.call(this, usedAbility);
    }




}
