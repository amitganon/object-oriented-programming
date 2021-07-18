package Test;

import Other.TileFactory;
import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Enemy.Monster;
import Tile.Unit.Player.Player;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class combatTests {

    TileFactory factory=new TileFactory();
    Enemy monster=factory.getEnemy('s');
    Player player=factory.getPlayer(1);

    @BeforeEach
        public void start(){
        Enemy monster=factory.getEnemy('s');
        Player player=factory.getPlayer(1);
        }

    @Test
    public void playerDef(){
        player.Defend(10,monster,false);
        Assertions.assertEquals(1,player.getPlayerLevel());
        Assertions.assertEquals(30,player.getAttackPoints());
        Assertions.assertEquals(4,player.getDefencePoints());
        Assertions.assertEquals(290,player.getHealth());
        Assertions.assertEquals("Jon Snow",player.getName());
    }

    @Test
    public void enemyDef(){
        int health=monster.getHealth();
        monster.Defend(10,player,false);
        Assertions.assertEquals(health-10,monster.getHealth());
    }
}
