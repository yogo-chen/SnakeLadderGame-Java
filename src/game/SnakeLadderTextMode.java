package game;

import game.comp.*;
import java.util.Scanner;

/**
 *
 * @author Prayogo Cendra (2014730033)
 */
public class SnakeLadderTextMode {

    public void start() {
        Scanner in = new Scanner(System.in);
        String input = new String();
        do {
            System.out.println("ULAR TANGGA");
            System.out.println("1. Player versus Player");
            System.out.println("2. Player versus Computer");
            System.out.print("Option : ");
            input = in.nextLine().trim();
        } while (!checkOption(input));

        Game game;
        if (Integer.parseInt(input) == 1) {
            String p1;
            String p2;
            do {
                System.out.print("Player 1 name : ");
                p1 = in.nextLine().trim();
            } while (p1.equals(""));
            do {
                System.out.print("Player 2 name : ");
                p2 = in.nextLine().trim();
            } while (p2.equals(""));
            game = new Game(p1, p2);
        } else {
            String p1;
            do {
                System.out.print("Player name : ");
                p1 = in.nextLine().trim();
            } while (p1.equals(""));
            game = new Game(p1);
        }
        game.start();

        while (!game.isGameOver()) {
            System.out.print("\nNow is " + game.getCurPlayer() + "'s turn! Press enter to play...");
            in.nextLine();
            game.play();
        }
    }

    private boolean checkOption(String pilihan) {
        boolean res = true;
        try {
            int value = Integer.parseInt(pilihan);
            if (value != 1 && value != 2) {
                res = false;
            }
        } catch (Exception e) {
            res = false;
        }
        if (!res) {
            System.out.println("You can only choose between 1 or 2!\n");
        }
        return res;
    }
}
