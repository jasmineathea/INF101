package no.uib.inf101.sem2.snake;

import static org.junit.Assert.*;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.snake.model.Board;
import no.uib.inf101.sem2.snake.model.Direction;

import no.uib.inf101.sem2.snake.model.GameState;
import no.uib.inf101.sem2.snake.model.SnakeModel;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.snake.model.objects.Object;
import no.uib.inf101.sem2.snake.model.snake.Snake;

import java.util.LinkedList;
import java.util.List;

public class SnakeModelTest {
    private SnakeModel model = new SnakeModel(new Board(10,10),new Snake(new CellPosition(1,1)));
    private Snake snake = new Snake(new CellPosition(5,5));

    @Test
    public void testGenerateObject() {
        this.model = new SnakeModel(new Board(10,10),new Snake(new CellPosition(1,1)));
        model.generateObject('A');
        Object object = model.getObject();
        assertTrue(object.getObjectPosition().row() >= 0 && object.getObjectPosition().row() < model.getBoard().rows());
        assertTrue(object.getObjectPosition().col() >= 0 && object.getObjectPosition().col() < model.getBoard().cols());
        assertEquals(model.getBoard().get(object.getObjectPosition()).charValue(), 'A');
    }

    @Test
    public void testGenerateObjectOnOccupiedTile() {
        this.model = new SnakeModel(new Board(10,10),new Snake(new CellPosition(1,1)));
        model.getBoard().set(new CellPosition(0, 0), 'A'); // Place apple at (0, 0)
        model.generateObject('A');
        Object object = model.getObject();
        assertNotEquals(object.getObjectPosition(), new CellPosition(0, 0));
    }

    @Test
    public void testSetDirectionUp() {
        this.model = new SnakeModel(new Board(10,10),new Snake(new CellPosition(1,1)));
        model.setDirection(Direction.UP);
        assertEquals(model.getDirection(), Direction.UP);
    }
    
    @Test
    public void testSetDirectionLeft() {
        this.model = new SnakeModel(new Board(10,10),new Snake(new CellPosition(1,1)));
        model.setDirection(Direction.LEFT);
        assertEquals(model.getDirection(), Direction.LEFT);
    }

    @Test
    public void testSetDirectionDown() {
        this.model = new SnakeModel(new Board(10,10),new Snake(new CellPosition(1,1)));
        model.setDirection(Direction.DOWN);
        assertEquals(model.getDirection(), Direction.DOWN);
    }

    @Test
    public void testSetDirectionCannotChange() {
        this.model = new SnakeModel(new Board(10,10),new Snake(new CellPosition(1,1)));
        model.setDirection(Direction.UP);
        model.setCanChangeDirection(false);
        model.setDirection(Direction.DOWN);
        assertEquals(model.getDirection(), Direction.UP);
    }

    @Test
    public void testMoveSnake() {
        // Test moving the snake up
        snake.moveSnake(-1, 0);
        assertEquals(new CellPosition(4, 5), snake.getSnake().pos());
        
        // Test moving the snake down
        snake.moveSnake(1, 0);
        assertEquals(new CellPosition(5, 5), snake.getSnake().pos());
        
        // Test moving the snake left
        snake.moveSnake(0, -1);
        assertEquals(new CellPosition(5, 4), snake.getSnake().pos());
        
        // Test moving the snake right
        snake.moveSnake(0, 1);
        assertEquals(new CellPosition(5, 5), snake.getSnake().pos());
        
        // Test moving the snake out of bounds
        snake.moveSnake(-1, 0);
        snake.moveSnake(-1, 0);
        snake.moveSnake(-1, 0);
        assertEquals(new CellPosition(0, 5), snake.getSnake().pos());
        assertEquals(GameState.GAME_OVER, model.getGameScreen());
        
        // Test moving the snake into itself
        snake.moveSnake(0, -1);
        snake.moveSnake(1, 0);
        snake.moveSnake(0, 1);
        snake.moveSnake(-1, 0);
        assertEquals(new CellPosition(0, 6), snake.getSnake().pos());
        assertEquals(GameState.GAME_OVER, model.getGameScreen());
    }
}
