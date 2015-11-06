package game.comp;

/**
 *
 * @author i14033
 */
public class Game {

    private Player p1;
    private Player p2;
    private Board board;
    private Dice dice;
    private boolean versusComputer;
    private boolean gameOver;
    private int lastRoll;
    private Player curPlayer;

    public Game(String p1, String p2) {
        this.p1 = new Player(p1);
        this.p2 = new Player(p2);
        this.board = new Board();
        this.dice = new Dice();
        this.versusComputer = false;
        this.gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
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
        this.p1.setPosition(board.getTile(1));
        this.p2.setPosition(board.getTile(1));
        this.curPlayer = this.p1;
        printBoardString();
    }

    public void play() {
        this.lastRoll = this.dice.roll();
        int position = curPlayer.getPosition() + this.lastRoll;
        this.curPlayer.setPosition(board.getTile(nextPosition(position)));

        printBoardString();
        end();
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

    private void printWinner() {
        System.out.println("CONGRATULATION!!!");
        System.out.println(this.curPlayer + " is the winner!");
    }

    public void printBoardString() {
        Tile[] tiles = this.board.getBoard();
        String r = new String();
        for (int i = 0; i < 10; i++) {
            int puluhan = i * 10;
            if (i % 2 == 0) {//genap
                for (int j = 9; j >= 0; j--) {
                    r = " " + tiles[puluhan + j] + " |" + r;
                }
            } else {//ganjil
                for (int j = 0; j < 10; j++) {
                    r = " " + tiles[puluhan + j] + " |" + r;
                }
            }
            r = "\n|" + r;
        }
        if (this.lastRoll != 0) {
            r += "\nNow is " + this.curPlayer + "'s turn!";
            r += "\nDice Roll " + this.lastRoll;
        }
        r += "\n" + p1 + "'s position : " + p1.getPosition() + "\n";
        r += p2 + "'s position : " + p2.getPosition();
        System.out.println(r);
    }

    private int nextPosition(int value) {
        if (isOverlap(value)) {
            value = 200 - value;
        }
        value = this.board.getTile(value).getTarget();
        return value;
    }

    private boolean isOverlap(int value) {
        return value > 100;
    }
}
