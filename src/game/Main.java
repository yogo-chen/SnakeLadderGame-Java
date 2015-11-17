package game;

import game.comp.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Scanner in = new Scanner(System.in);
        String input = new String();
        do {
            System.out.println("ULAR TANGGA");
            System.out.println("1. Player versus Player");
            System.out.println("2. Player versus Computer");
            System.out.print("Option : ");
            input = in.nextLine();
        } while (!checkOption(input));

        Game game;
        if (Integer.parseInt(input) == 1) {
            System.out.print("Player 1 name : ");
            String p1 = in.next();
            System.out.print("Player 2 name : ");
            String p2 = in.next();
            game = new Game(p1, p2);
        } else {
            System.out.print("Player name : ");
            String p1 = in.next();
            game = new Game(p1);
        }

        game.start();
        in.nextLine();

        while (!game.isGameOver()) {
            System.out.print("\nNow is " + game.getCurPlayer() + "'s turn! Play [Y/n] ? ");
            if (in.nextLine().trim().equalsIgnoreCase("n")) {
                break;
            }
            game.play();
        }
    }

    private static boolean checkOption(String pilihan) {
        boolean res = true;
        int value = 0;
        try {
            value = Integer.parseInt(pilihan);
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
