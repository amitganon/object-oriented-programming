package Ability;

import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Player.Player;

import java.util.List;
import java.util.Random;

public class MageAbility extends Ability{
    public MageAbility(){
        this.name="Blizzard";
    }

    @Override
    public void activateAbility(Player player, List<Enemy> enemies, int spellPower) {
        Random rnd = new Random();
        int c = rnd.nextInt(enemies.size());
        enemies.get(c).Defend(spellPower, player, true);
    }
}
