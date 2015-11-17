package game.comp;

/**
 *
 * @author i14033
 */
public class Game {

    private Player p1;
    private Player p2;
    private Player curPlayer;
    private Board board;
    private Dice dice;

    private boolean versusComputer;
    private boolean gameOver;
    private int lastRoll;
    private int firstPosition;
    private int afterRollPosition;
    private int finalPosition;

    public Game(String p1, String p2) {
        this.p1 = new Player(p1);
        this.p2 = new Player(p2);
        this.board = new Board();
        this.dice = new Dice();
        this.versusComputer = false;
        this.gameOver = false;
    }

    public Game(String p1) {
        this.p1 = new Player(p1);
        this.p2 = new Player("Bot");
        this.board = new Board();
        this.dice = new Dice();
        this.versusComputer = true;
        this.gameOver = false;
    }

    public void start() {
        Tile zero = new Tile(0) {
            @Override
            public String toString() {
                return "";
            }
        };
        this.p1.setPosition(zero);
        this.p2.setPosition(zero);
        this.curPlayer = this.p1;
        printBoardString();
        printStatus();
    }

    public void play() {
        if (versusComputer) {
            playPVBot();
        } else {
            playPVP();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getCurPlayer() {
        return curPlayer;
    }

    private void playPVP() {
        updatePosition();
        printBoardString();
        printStatus();
        end();
    }

    private void playPVBot() {
        printBoardString();
        do {
            updatePosition();
            printStatus();
            end();
        } while (this.curPlayer == this.p2);
    }

    private void end() {
        if (this.curPlayer.getPosition() == 100) {
            this.gameOver = true;
            printWinner();
        } else if (this.lastRoll != 6) {
            if (this.curPlayer == this.p1) {
                this.curPlayer = this.p2;
            } else {
                this.curPlayer = this.p1;
            }
        }
    }

    private void printBoardString() {
        System.out.println(this.board);
    }

    private void printStatus() {
        String r = new String();
        if (this.lastRoll != 0) {
            r += "\nDice roll " + this.lastRoll;
            r += "\n" + this.curPlayer + " move from " + this.firstPosition + " to " + this.afterRollPosition;
            if (this.board.getTile(this.afterRollPosition) instanceof TileLadder) {
                r += "\nYaay, " + this.curPlayer + " found a ladder to " + this.finalPosition;
            } else if (this.board.getTile(this.afterRollPosition) instanceof TileSnake) {
                r += "\nAwww, " + this.curPlayer + " found a snake to " + this.finalPosition;
            }
        }
        r += "\n" + p1 + "'s position : " + p1.getPosition() + "\n";
        r += p2 + "'s position : " + p2.getPosition();
        System.out.println(r);
    }

    private void printWinner() {
        System.out.println("\nCONGRATULATION!!!");
        System.out.println(this.curPlayer + " is the winner!");
    }

    private void updatePosition() {
        this.lastRoll = this.dice.roll();
        this.firstPosition = this.curPlayer.getPosition();
        int position = this.firstPosition + this.lastRoll;
        nextPosition(position);
        this.curPlayer.setPosition(this.board.getTile(this.finalPosition));
    }

    private void nextPosition(int value) {
        if (isOverlap(value)) {
            value = 200 - value;
        }
        afterRollPosition = value;
        finalPosition = this.board.getTile(value).getTarget();
    }

    private boolean isOverlap(int value) {
        return value > 100;
    }
}
