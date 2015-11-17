package game.comp;

/**
 *
 * @author Prayogo Cendra (2014730033)
 */
public class TileLadder extends Tile {

    public TileLadder(int value, Tile target) {
        super(value);
        this.target = target;
    }
}
