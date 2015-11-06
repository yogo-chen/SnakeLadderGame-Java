package game.comp;

/**
 *
 * @author Prayogo Cendra (2014730033)
 */
public class TileNormal extends Tile {

    public TileNormal(int value) {
        super(value);
        this.target = this;
    }

    @Override
    public String toString() {
        return (String.format("%3d -    ", this.value));
    }

}
