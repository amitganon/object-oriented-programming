package Test;
import Other.TileFactory;
import Tile.Unit.Player.Player;
import Tile.Unit.Unit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class PlayerTests {

    TileFactory tileFactory=new TileFactory();
    Player player= tileFactory.getPlayer(1);
    @BeforeClass
    void main(){
        TileFactory tileFactory=new TileFactory();
    }

    @BeforeEach
    void setUp() {
        Player player= tileFactory.getPlayer(1);
    }

    @Test
    void ToString() {
        Assertions.assertEquals('@',player.toString());
    }


    @Test
    void levelUp(){
        player.LevelUp();
        Assertions.assertEquals(100,player.getExperience());
        Assertions.assertEquals(2,player.getPlayerLevel());
        Assertions.assertEquals(32,player.getAttackPoints());
        Assertions.assertEquals(5,player.getDefencePoints());
        Assertions.assertEquals(305,player.getHealth());
        Assertions.assertEquals("Jon Snow",player.getName());
    }
}
