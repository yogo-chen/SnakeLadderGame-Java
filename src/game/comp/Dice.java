package game.comp;

import java.util.Random;

/**
 *
 * @author Kresna
 */
public class Dice {

    private Random r = new Random();

    public int roll() {
        return r.nextInt(6) + 1;
    }

}
