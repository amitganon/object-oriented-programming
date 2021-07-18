package Ability;

import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Player.Player;

import java.util.List;

public class MonsterAbility extends Ability {
    public MonsterAbility(){
        this.name="HeroicUnit ability";
    }

    @Override
    public void activateAbility(Player play, List<Enemy> enemies, int attackPoints) {

    }
}
