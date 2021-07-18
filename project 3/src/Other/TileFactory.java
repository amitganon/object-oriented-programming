package Other;
import java.util.HashMap;
import java.util.Map;

import Tile.Unit.Enemy.Enemy;
import Tile.Unit.Enemy.Monster;
import Tile.Unit.Enemy.Trap;
import Tile.Unit.Player.Mage;
import Tile.Unit.Player.Player;
import Tile.Unit.Player.Rogue;
import Tile.Unit.Player.Warrior;

public class TileFactory{
    Map<Integer,Supplier<Player>> playerMap;
    Map<Character,Supplier<Enemy>> enemyMap;

    public TileFactory(){
        playerMap= new HashMap<Integer,Supplier<Player>>();
        enemyMap= new HashMap<Character,Supplier<Enemy>>();
        factory();
    }

    public void factory(){
        playerMap.put(1,() -> new Warrior("Jon Snow", 300, 30, 4, 3));
        playerMap.put(2,()-> new Warrior("The Hound", 400, 20, 6, 5));
        playerMap.put(3,()-> new Mage("Melisandre",100,5,1,300,30,15,5,6));
        playerMap.put(4,()-> new Mage("Thoros of Myr",250,25,4,150,20,20,3,4));
        playerMap.put(5,()-> new Rogue("Arya Stark", 150, 40, 2, 20));
        playerMap.put(6,()-> new Rogue("Bronn", 250, 35, 3, 50));

        enemyMap.put('s',()-> new Monster('s',"Lannister Solider",80,8,3,3,25));
        enemyMap.put('k',()-> new Monster('k',"Lannister Knight",200,14,8,4,50));
        enemyMap.put('q',()-> new Monster('q',"Queen’s Guard ",400,20,15,5,100));
        enemyMap.put('z',()-> new Monster('z',"Wright",600,30,15,3,100));
        enemyMap.put('b',()-> new Monster('b',"Bear-Wright",1000,75,30,4,250));
        enemyMap.put('g',()-> new Monster('g',"Giant-Wright",1500,100,40,5,500));
        enemyMap.put('w',()-> new Monster('w',"White Walker",2000,150,50,6,1000));
        enemyMap.put('M',()-> new Monster('M',"The Mountain",1000,60,25,6,500));
        enemyMap.put('C',()-> new Monster('C',"Queen Cersei",100,10,10,1,1000));
        enemyMap.put('K',()-> new Monster('K',"Night’s King",5000,300,150,8,5000));

        enemyMap.put('B',()-> new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10));
        enemyMap.put('Q',()-> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 10));
        enemyMap.put('D',()-> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10));
    }

    public Player getPlayer(Integer c){
        return playerMap.get(c).get();
    }
    public Enemy getEnemy(Character c){
        return enemyMap.get(c).get();
    }
}