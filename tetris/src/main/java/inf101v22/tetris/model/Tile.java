package inf101v22.tetris.model;

import java.awt.Color;

/**
 * Class representing a single tile on the board.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class Tile {
    
    public final Color color; 
    public final char character;

    /**
     * Class constructor. 
     * 
     * @param color
     * @param character
     */
    public Tile(Color color, char character) {
        this.color = color;
        this.character = character;
    }

    // added for testing purposes :)
    public Color getColor() {
		return color;
	}

	public char getCharacter() {
		return character;
	}

}
