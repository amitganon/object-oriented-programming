package Tile.Unit;
import Ability.Ability;
import Other.MoveCallback;
import Resource.Health;
import Other.MessageCallBack;
import Other.Position;
import Tile.*;
import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Player.Player;

import java.util.Random;

public abstract class Unit extends Tile {
    protected String name;
    public String getName(){
        return name;
    }
    protected Health health;
    public int getHealth(){
        return health.CurrentAmount();
    }
    protected int attackPoints;
    public int getAttackPoints(){
        return attackPoints;
    }
    protected int defencePoints;
    public int getDefencePoints(){
        return defencePoints;
    }
    protected Ability ability;
    protected MessageCallBack message;
    private MoveCallback moveCallback;


    protected Unit(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile);
        this.name = name;
        this.health = new Health(healthCapacity);
        this.attackPoints = attack;
        this.defencePoints = defense;
    }

    public void initialize(Position position, MessageCallBack messageCallback, MoveCallback moveCallback){
        super.initialize(position);
        this.message = messageCallback;
        this.moveCallback = moveCallback;
    }
    public String Name() {return name;}

    public String Description(){
        return name +"\t\t" + health.toString() + "\t Attack: " + attackPoints + "\t Defence: " + defencePoints;
    }

    public void moveLeft(){
        Position pos = new Position(getPosition().getX()-1, getPosition().getY());
        moveCallback.move(pos);
    }

    public void moveRight(){
        Position pos = new Position(getPosition().getX()+1, getPosition().getY());
        moveCallback.move(pos);
    }

    public void moveUp(){
        Position pos = new Position(getPosition().getX(), getPosition().getY()-1);
        moveCallback.move(pos);
    }

    public void moveDown(){
        Position pos = new Position(getPosition().getX(), getPosition().getY()+1);
        moveCallback.move(pos);
    }

    public void Defend(int attackPoint, Unit attackUnit, boolean usedAbility){
        Random rnd = new Random();
        int random=rnd.nextInt(defencePoints+1);
        message.send(name+" rolled "+ random+" defense points");
        int d = Math.max(attackPoint-random, 0);
        message.send(attackUnit.Name()+" dealt "+ d +" damage to "+ name);
        if(d>0){
            health.SetCurrentAmount(Math.max(0, health.CurrentAmount()-d));
            if(health.CurrentAmount()<=0){
                this.Die(attackUnit, usedAbility);
            }
        }
    }

    abstract public void Die(Unit player, boolean usedAbility);

    public void accept(Wall wall) {}

    public void accept(Empty empty) {
        Position tmp = empty.getPosition();
        empty.setPosition(getPosition());
        setPosition(tmp);
    }

    public abstract void accept(Enemy enemy);

    public abstract void accept(Player player);
}