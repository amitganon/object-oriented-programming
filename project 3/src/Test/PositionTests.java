package Test;

import Other.Position;
import Other.TileFactory;
import Tile.Unit.Player.Player;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class PositionTests {

    Position pos1=new Position(1,2);
    Position pos2=new Position(4,5);

    @BeforeEach
    void setUp() {
        Assertions.assertEquals(false,pos1.CompareTo(pos2)>0);
    }
}
