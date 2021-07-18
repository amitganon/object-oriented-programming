package Ability;

import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Player.Player;

import java.util.List;

public abstract class Ability {
    protected String name;
    public String toString(){
        return name;
    }
    public abstract void activateAbility(Player play, List<Enemy> enemies, int attackPoints);
}
