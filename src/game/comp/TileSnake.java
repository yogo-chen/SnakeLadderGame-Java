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

    @Override
    public String toString() {
        return (String.format("%3d - %-3d", this.value, this.target.getValue()));
    }

}
