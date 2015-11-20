package game.comp;

import java.util.Random;

/**
 *
 * @author Kresna
 */
public class Dice {

    private Random r;

    public Dice() {
        this.r = new Random();
    }

    public int roll() {
        return this.r.nextInt(6) + 1;
    }
}
