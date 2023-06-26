package no.uib.inf101.sem2.snake;

import static org.junit.Assert.assertEquals;

import no.uib.inf101.sem2.snake.model.snake.Snake;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;

public class SnakeTest {

    private Snake snake = new Snake(new CellPosition(5, 5));

    @Before
    public void setUp() throws Exception {
        snake = new Snake(new CellPosition(5, 5));
    }

    @Test
    public void testGetSnake() {
        GridCell<Character> expectedHead = new GridCell<>(new CellPosition(5, 5), 'S');
        assertEquals(expectedHead, snake.getSnake());
    }

    @Test
    public void testMoveSnake() {
        Snake newSnake = snake.moveSnake(-1, 0);
        GridCell<Character> expectedHead = new GridCell<Character>(new CellPosition(4, 5), 'S');
        assertEquals(expectedHead, newSnake.getSnake());
    }
}

