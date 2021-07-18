package Ability;

import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Player.Player;

import java.util.List;
import java.util.Random;

public class WarriorAbility extends Ability{
    public WarriorAbility(){
        this.name="Avenger’s Shield";
    }

    public void activateAbility(Player player, List<Enemy> enemies , int healthAmount){
        Random rnd = new Random();
        int c = rnd.nextInt(enemies.size());
        enemies.get(c).Defend(healthAmount/10, player, true);
    }
}
