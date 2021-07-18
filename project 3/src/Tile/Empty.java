package Tile;

import Other.Position;
import Tile.Unit.Unit;

public class Empty extends Tile {
    public static final char tile = '.';
    public Empty() {
        super(tile);
    }

    @Override
    public void interact(Unit unit) {
        unit.accept(this);
    }
}
