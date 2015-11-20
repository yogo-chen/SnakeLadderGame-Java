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

    public Player getP1() {
        return this.p1;
    }

    public Player getP2() {
        return this.p2;
    }

    public Player getCurPlayer() {
        return this.curPlayer;
    }

    public boolean isVersusComputer() {
        return this.versusComputer;
    }

    public int getLastRoll() {
        return this.lastRoll;
    }

    public int getFirstPosition() {
        return this.firstPosition;
    }

    public int getAfterRollPosition() {
        return this.afterRollPosition;
    }

    public int getFinalPosition() {
        return this.finalPosition;
    }

    public boolean isGameOver() {
        return this.gameOver;
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
        if (this.versusComputer) {
            playPVBot();
        } else {
            playPVP();
        }
    }

    private void playPVP() {
        updatePosition();
        printBoardString();
        printStatus();
        endPlay();
    }

    private void playPVBot() {
        printBoardString();
        do {
            updatePosition();
            printStatus();
            endPlay();
        } while (this.curPlayer == this.p2 && !this.gameOver);
    }

    private void endPlay() {
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
        r += "\n" + this.p1 + "'s position : " + this.p1.getPosition() + "\n";
        r += this.p2 + "'s position : " + this.p2.getPosition();
        System.out.println(r);
    }

    private void printWinner() {
        System.out.println("\nCONGRATULATION!!!");
        System.out.println(this.curPlayer + " is the winner!");
    }

    private void updatePosition() {
        roll();
        setFirstPosition();
        setAfterRollPosition();
        setFinalPosition();
        setCurPlayerPosition();
    }

    private void roll() {
        this.lastRoll = this.dice.roll();
    }

    private void setFirstPosition() {
        this.firstPosition = this.curPlayer.getPosition();
    }

    private void setAfterRollPosition() {
        int position = this.firstPosition + this.lastRoll;
        if (position > 100) {
            position = 200 - position;
        }
        this.afterRollPosition = position;
    }

    private void setFinalPosition() {
        this.finalPosition = this.board.getTile(this.afterRollPosition).getTarget();
    }

    private void setCurPlayerPosition() {
        this.curPlayer.setPosition(this.board.getTile(this.finalPosition));
    }
}
