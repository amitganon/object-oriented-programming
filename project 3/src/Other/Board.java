package Other;

import Tile.*;
import Tile.Unit.*;
import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Player.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.internal.util.xml.impl.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {
    private List<Tile> Tiles;
    private List<Enemy> Enemies;
    private Player ActivePlayer;
    private int height;
    private int width;
    private MessageCallBack messageCallBack;
    private int state;

    public Board(int height, int width, String boardString, Player player, MessageCallBack messageCallBack){
        this.height = height;
        this.width = width;
        this.ActivePlayer = player;
        Tiles = new ArrayList<Tile>();
        Enemies = new ArrayList<Enemy>();
        InitializeTiles(boardString);
        this.messageCallBack = messageCallBack;
        state=1;
    }

    private void InitializeTiles(String boardString){
        TileFactory TileFactory=new TileFactory();
        int x=0,y=0;
        for(char c : boardString.toCharArray()){
            Position p = new Position(x, y);
            Tile t;
            if(c=='@')
                System.out.println();
            switch (c) {
                case '#':
                    t = new Wall();
                    t.initialize(p);
                    Tiles.add(t);
                    break;
                case '.':
                    t = new Empty();
                    t.initialize(p);
                    Tiles.add(t);
                    break;
                case '@':
                    ActivePlayer.initialize(p, (msg) -> messageCallBack.send(msg), () -> playerDeath(),new InputProvider(), (pos) -> toMove(ActivePlayer , pos));
                    Tiles.add(ActivePlayer);
                    break;
                default:
                    Enemy e = TileFactory.getEnemy(c);
                    e.initialize(p, (msg) -> messageCallBack.send(msg), (unit, usedAbility) -> EnemyDeath(unit, usedAbility) ,(pos) -> toMove(e, pos));
                    Tiles.add(e);
                    Enemies.add(e);
            }
            x+=1;
            if(x==width) {
                x = 0;
                y += 1;
            }
            ActivePlayer.initializeAbilityCallback((Player)-> GetEnemies(Player));
        }
    }

    private void GetEnemies(Player player) {
        player.CastAbility(Enemies);
    }

    public String toString(){
        Tiles.sort(new TileComparator());
        StringBuilder result = new StringBuilder();
        for(Tile t : Tiles){
            result.append(t.getPosition().getX() == 0 ? "\n" + t : t.toString());
        }
        return result.toString();
    }

    public int Tick(){
        messageCallBack.send(toString());
        if(state==1) {
            PlayerTurn();
            EnemiesTurn();
            if(state!=-1)
                state = 1;
        }
        if(Enemies.size()==0)
            state = 2;
        return state;
    }

    public void PlayerTurn(){
        messageCallBack.send(ActivePlayer.Description());
        InputProvider.makeMove(ActivePlayer, messageCallBack);
        ActivePlayer.OnGameTick();
    }

    public void EnemiesTurn(){
        for(Enemy enemy : Enemies){
            enemy.Act(ActivePlayer.getPosition(), ActivePlayer);
        }
    }

    public void playerDeath(){
        messageCallBack.send(toString());
        messageCallBack.send(ActivePlayer.Description());
        state = -1;
    }

    public void EnemyDeath(Enemy enemy, boolean usedAbility){
        ActivePlayer.GetExperience(enemy.experienceValue);
        Enemies.remove(enemy);
        int indexTile = enemy.getPosition().getY()*width+enemy.getPosition().getX();
        Empty empty = new Empty();
        empty.initialize(enemy.getPosition());
        Tiles.set(indexTile, empty);

        if (!usedAbility) empty.interact(ActivePlayer);

        if(Enemies.size() == 0) state = 2;
    }

    public void toMove(Unit unit, Position pos){
        Optional<Tile> tile = Tiles.stream().filter(t -> t.getPosition().equals(pos)).findFirst();
        if (tile.isPresent())
            tile.get().interact(unit);
    }
}
