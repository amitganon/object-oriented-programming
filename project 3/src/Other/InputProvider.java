package Other;

import Tile.Unit.Player.Player;

import java.util.Scanner;

public class InputProvider {
    static public void makeMove(Player player, MessageCallBack massageCollBack) {
        String strReturn = "Select your move \n w-up\ta-left\ts-down\td-right\te-ability\tq-nothing";
        massageCollBack.send(strReturn);
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        switch (str) {
            case "w":
                player.moveUp();
                break;
            case "a":
                player.moveLeft();
                break;
            case "d":
                player.moveRight();
                break;
            case "s":
                player.moveDown();
                break;
            case "e":
                player.CastAbility();
                break;
            case "q":
                break;
            default:
                makeMove(player, massageCollBack);
                break;
        }
    }

    static public int choosePlayerString(MessageCallBack massageCollBack)
    {
        String str="1. Jon Snow             Health: 300/300         Attack: 30              Defense: 4              Level: 1                Experience: 0/50                Cooldown: 0/3\n" +
                "2. The Hound            Health: 400/400         Attack: 20              Defense: 6              Level: 1                Experience: 0/50                Cooldown: 0/5\n" +
                "3. Melisandre           Health: 100/100         Attack: 5               Defense: 1              Level: 1                Experience: 0/50                Mana: 75/300            Spell Power: 15\n" +
                "4. Thoros of Myr        Health: 250/250         Attack: 25              Defense: 4              Level: 1                Experience: 0/50                Mana: 37/150            Spell Power: 20\n" +
                "5. Arya Stark           Health: 150/150         Attack: 40              Defense: 2              Level: 1                Experience: 0/50                Energy: 100/100\n" +
                "6. Bronn                Health: 250/250         Attack: 35              Defense: 3              Level: 1                Experience: 0/50                Energy: 100/100";

        massageCollBack.send(str);
        Scanner scan = new Scanner(System.in);
        int choose = scan.nextInt();
        if(choose>6 | choose<1)
            choose=choosePlayerString(massageCollBack);
        return choose;
    }
}
