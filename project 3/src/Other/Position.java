package Other;

import Tile.Tile;

public class Position {
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double Range(Position pos){
        return Math.sqrt(Math.pow(x - pos.getX(), 2) + Math.pow(y - pos.getY(),2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int CompareTo(Position p)
    {
        if(y!=p.getY())
            return y-p.getY();
        return x-p.getX();
    }

    public boolean equals(Position pos){
        if(pos.getX()!=x)
            return false;
        if(pos.getY()!=y)
            return false;
        return true;
    }
}
