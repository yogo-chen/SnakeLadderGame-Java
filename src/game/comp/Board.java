package game.comp;

import java.util.Random;

/**
 *
 * @author Prayogo Cendra (2014730033)
 */
public class Board {

    private Tile[] tiles;

    public Board() {
        this.tiles = new Tile[100];
        generateTiles(10, 10);
    }

    public Tile getTile(int value) {
        return this.tiles[value - 1];
    }

    @Override
    public String toString() {
        String r = new String();
        for (int i = 0; i < 10; i++) {
            int puluhan = i * 10;
            if (i % 2 == 0) {//genap
                for (int j = 9; j >= 0; j--) {
                    r = this.tiles[puluhan + j] + "|" + r;
                }
            } else {//ganjil
                for (int j = 0; j < 10; j++) {
                    r = this.tiles[puluhan + j] + "|" + r;
                }
            }
            r = "\n|" + r;
        }
        return r;
    }

    private void generateTiles(int snake, int ladder) {
        Random r = new Random();
        generateNormalTiles();
        generateSnakeTiles(r, snake);
        generateLadderTiles(r, ladder);
    }

    private void generateNormalTiles() {
        for (int i = this.tiles.length - 1; i >= 0; i--) {
            this.tiles[i] = new TileNormal(i + 1);
        }
    }

    private void generateSnakeTiles(Random r, int snake) {
        int initial;
        int target;
        while (snake-- > 0) {
            do {
                initial = r.nextInt(79) + 20;
            } while (!(this.tiles[initial] instanceof TileNormal));
            do {
                target = r.nextInt(initial - 10) + 1;
            } while (!(this.tiles[target] instanceof TileNormal));
            this.tiles[initial] = new TileSnake(initial + 1, this.tiles[target]);
        }
    }

    private void generateLadderTiles(Random r, int ladder) {
        int initial;
        int target;
        while (ladder-- > 0) {
            do {
                initial = r.nextInt(79) + 1;
            } while (!(this.tiles[initial] instanceof TileNormal));
            do {
                target = r.nextInt(99 - initial) + initial;
            } while (!(this.tiles[target] instanceof TileNormal));
            this.tiles[initial] = new TileLadder(initial + 1, this.tiles[target]);
        }
    }
}
