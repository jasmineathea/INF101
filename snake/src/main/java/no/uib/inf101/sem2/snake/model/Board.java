package no.uib.inf101.sem2.snake.model;

import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.grid.GridCell;

public class Board extends Grid<Character> {
    /**
     * Class constructor.
     * 
     * Initialize all positions in the grid with the value '-'.
     * 
     * @param rows
     * @param cols
     */
    public Board(int rows, int cols) {
        super(rows, cols);
        for (GridCell<Character> snakeBoardTile : this) {
            this.set(snakeBoardTile.pos(), '-');
        }
    }


    /**
     * Method that returns an object that, when iterated over, gives all positions
     * on the board with same symbol.
     *
     * @return Iterable<GridCell<Character>>
     */
    public void clearBoard() {
        for (GridCell<Character> snakeBoardTile : this) {
            this.set(snakeBoardTile.pos(), '-');
        }
    }

}
