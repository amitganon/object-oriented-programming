package Tile;

import Other.Position;
import Tile.Unit.Unit;

public abstract class Tile {
    protected char tile;
    protected Position position;

    public Tile(char tile)
    {
        this.tile = tile;
    }

    public void initialize(Position position)
    {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }

    public abstract void interact(Unit unit);
}
