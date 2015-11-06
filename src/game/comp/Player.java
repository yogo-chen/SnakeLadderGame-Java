/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
