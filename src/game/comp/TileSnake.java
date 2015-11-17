package game.comp;

/**
 *
 * @author Prayogo Cendra (2014730033)
 */
public class TileSnake extends Tile {

    public TileSnake(int value, Tile target) {
        super(value);
        this.target = target;
    }
}
