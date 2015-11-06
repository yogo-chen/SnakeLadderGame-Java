package game.comp;

/**
 *
 * @author Prayogo Cendra (2014730033)
 */
public abstract class Tile {

    protected final int value;
    protected Tile target;

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public int getTarget() {
        return this.target.getValue();
    }

    @Override
    public abstract String toString();
}
