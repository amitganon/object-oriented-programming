import Other.Board;
import Other.InputProvider;
import Other.Supplier;
import Other.TileFactory;
import Tile.Unit.Player.Player;
import Tile.Unit.Player.Warrior;
import Tile.Unit.Unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameManager {
    private List<Board> boards;
    private int currentLevel;

    public GameManager(){
        boards=new LinkedList<Board>();
        currentLevel=1;
    }

    public void BeginGame(){
        Player player = ChoosePlayer();
        LoadBoard(player);
        StartGame(player);
    }

    private void StartGame(Player player)
    {
        Board b = boards.get(currentLevel-1);
        int state = b.Tick();
        while(state == 1){////what happened when finish board, when die or win?
            state = b.Tick();
        }
        if(state == 2){
            File directoryPath = new File("src/levels_dir");
            if(currentLevel < directoryPath.list().length){
                currentLevel = currentLevel + 1;
                LoadBoard(player);
                StartGame(player);
            }
            else{
                winGame();
            }
        }
        else{
            looseGame();
        }
    }

    private Player ChoosePlayer() {
        int choose= InputProvider.choosePlayerString(System.out::println);
        Player player;
        TileFactory TileFactory=new TileFactory();
        player=TileFactory.getPlayer(choose);
        if(player==null)
            player=ChoosePlayer();
        switch(choose){
            case 1:
                System.out.println("You have selected:\n" +
                        "Jon Snow");
                break;
            case 2:
                System.out.println("You have selected:\n" +
                        "The Hound");
                break;
            case 3:
                System.out.println("You have selected:\n" +
                        "Melisandre");
                break;
            case 4:
                System.out.println("You have selected:\n" +
                        "Thoros of Myr");
                break;
            case 5:
                System.out.println("You have selected:\n" +
                        "Arya Stark");
                break;
            case 6:
                System.out.println("You have selected:\n" +
                        "Bronn");
                break;
            default:
                player=ChoosePlayer();
                break;
        }
        return player;
    }

    private void LoadBoard(Player player){
        int width = 0;
        int height = 0;
        try {
            String levelAddress = "src/levels_dir/level" + currentLevel + ".txt";
            File myBoardFile = new File(levelAddress);
            Scanner scan = new Scanner(myBoardFile);
            String boardString = "";
            while (scan.hasNextLine()) {
                if (boardString.equals("")) {
                    boardString = boardString + scan.nextLine();
                    width = boardString.length();
                } else {
                    boardString = boardString + scan.nextLine();
                }
                height++;
            }
            Board b = new Board(height, width, boardString, player, System.out::println);
            boards.add(b);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private void winGame(){
        System.out.println("You won!");
    }
    private void looseGame(){
        System.out.println("Game Over.");
    }
}
