package Tile.Unit.Enemy;

import Other.Position;
import Tile.Unit.Player.Player;
import Tile.Unit.Unit;

import java.util.Random;

public class Trap extends Enemy{

    private int VisibilityTime;
    private int InvisibilityTime;
    private int TickCount;
    private boolean Visible;

    public Trap(char c, String name, int healthPool, int attackPoints, int defencePoints, int experienceValue, int visibilityTime, int invisibilityTime){
        super(c,name, healthPool, attackPoints, defencePoints, experienceValue);
        this.VisibilityTime = visibilityTime;
        this.InvisibilityTime = invisibilityTime;
        this.TickCount = 0;
        this.Visible = true;
    }

    @Override
    public void Act(Position pos, Player player) {
        this.Visible = this.TickCount < this.VisibilityTime;

        if (this.TickCount == (this.VisibilityTime + this.InvisibilityTime)) this.TickCount = 0;
        else this.TickCount += 1;

        if (this.position.Range(pos) < 2) {
            accept(player);
        }
    }

    @Override
    public String toString() {
        if(Visible)
            return tile+"";
        else
            return ".";
    }

    @Override
    public void interact(Unit unit) {
        unit.accept(this);
    }
}
