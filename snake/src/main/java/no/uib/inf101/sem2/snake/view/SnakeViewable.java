package no.uib.inf101.sem2.snake.view;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.snake.model.GameState;

/**
 * SnakeViewable consists of the methods necessary for viewing.
 * These methods are defined in the classes Coordinate, CoordinateItem, Tile, and GameScreen
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public interface SnakeViewable {

     /**
      * Getter for the game screen.

      * @return the current game screen
      */
     GameState getGameScreen();

     /**
     * Returns GridDimension which gives us number of cols and number of rows
     * 
     * @return GridDimension
     */
    GridDimension getDimension();

    /**
     * Method that returns an object that, when iterated over, gives all positions
     * on the board with same symbol.
     * 
     * @return Iterable<GridCell<Character>>
     */
    Iterable<GridCell<Character>> tileCells();

    /**
     * Method that returns an object that, when iterated over, gives all positions
     * of the falling tetromino.
     * 
     * @return Iterable<GridCell<Character>>
     */
    Iterable<GridCell<Character>> snakeCells();

     /**
      * Get score once an apple is eaten.
      
      * @return the current score
      */
     public int getScore();

    void setGameScreen(GameState startGame);
}
