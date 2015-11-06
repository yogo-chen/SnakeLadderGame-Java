package game;

import game.comp.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Game game = new Game("Pemain");
        game.start();
        while(!game.isGameOver()){
            game.play();
        }
    }
}
