package no.uib.inf101.sem2.snake.model.objects;

import java.util.Random;

import no.uib.inf101.sem2.grid.CellPosition;

/**
 * Class representing the apple (or other fruit) that the snake eats.
 * Can also be used to represent other objects that can be obstacles.
 * 
 * @author Jasmine Næss
 */
public class Object {

    public char character;
    public CellPosition position;
    private final Random random;

    /**
     * Class constructor.
     * Constructs a new Apple, defined by a character and a coordinate.
     * 
     * @param character
     * @param position
     */
    public Object(char character, CellPosition position) {
        this.character = character;
        this.position = position;
        random = new Random();
    }

    public CellPosition getObjectPosition() {
        return position;
    }

}
