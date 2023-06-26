package no.uib.inf101.sem2.snake;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.snake.model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static junit.framework.TestCase.assertEquals;


public class BoardTest {

    Board board;
    @Test
    public void testConstructor() {
        // Test that a new board is created with the correct number of rows and columns
        int rows = 5;
        int cols = 10;
        Board board = new Board(rows, cols);
        assertEquals(rows, board.rows());
        assertEquals(cols, board.cols());
    }

}
