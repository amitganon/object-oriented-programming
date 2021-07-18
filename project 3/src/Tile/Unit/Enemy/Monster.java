package Tile.Unit.Enemy;

import Other.EnemyDeathCallBack;
import Other.Position;
import Tile.Unit.Player.Player;
import Tile.Unit.Unit;

import java.util.Random;

public class Monster extends Enemy{
    private int visionRange;

    public Monster(char c, String name, int healthPool, int attackPoints, int defencePoints, int visionRange, int experienceValue) {
        super(c,name, healthPool, attackPoints, defencePoints, experienceValue);
        this.visionRange=visionRange;
    }

    public String Description(){
        return super.Description() + "\tVision Range: " + visionRange;
    }

    @Override
    public void Act(Position p, Player player) {
        if(p.Range(this.position)<visionRange){
            int dx=position.getX()-p.getX();
            int dy=position.getY()-p.getY();
            if(Math.abs(dx)>Math.abs(dy)){
                if(dx>0)
                    super.moveLeft();
                else
                    super.moveRight();
            }
            else{
                if(dy>0)
                    super.moveUp();
                else
                    super.moveDown();
            }
        }
        else{
            Random rnd = new Random();
            int random= rnd.nextInt(4);
            switch (random){
                case 0:
                    super.moveDown();
                    break;
                case 1:
                    super.moveLeft();
                    break;
                case 2:
                    super.moveRight();
                    break;
                default:
                    super.moveUp();
                    break;
            }
        }
    }

    @Override
    public void interact(Unit unit) {
        unit.accept(this);
    }
}
