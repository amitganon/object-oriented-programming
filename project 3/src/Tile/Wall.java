package Tile;

import Other.Position;
import Tile.Unit.Unit;

public class Wall extends  Tile{
    public static final char tile = '#';
    public Wall() {
        super(tile);
    }

    @Override
    public void interact(Unit unit) {
        unit.accept(this);
    }
}
