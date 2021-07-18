package Other;

import Tile.Tile;

import java.util.Comparator;

public class TileComparator implements Comparator<Tile> {
    @Override
    public int compare(Tile o1, Tile o2) {
        Position p1 =o1.getPosition();
        Position p2 =o2.getPosition();
        if (p1.getY()!=p2.getY())
            return p1.getY() - p2.getY();
        return p1.getX() - p2.getX();
    }
}
