package Ability;

import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Player.Player;

import java.util.List;
import java.util.Random;

public class RougeAbility extends Ability {
    public RougeAbility(){
        this.name="Fan of Knives";
    }

    @Override
    public void activateAbility(Player player, List<Enemy> enemies, int attackPoints) {
        for(Enemy enemy : enemies) {
            enemy.Defend(attackPoints, player, true);
        }
    }
}
