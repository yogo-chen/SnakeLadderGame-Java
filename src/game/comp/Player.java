package game.comp;

/**
 *
 * @author i14033
 */
public class Player {

    protected Tile position;
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public void setPosition(Tile tile) {
        this.position = tile;
    }

    public int getPosition() {
        return this.position.getValue();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
