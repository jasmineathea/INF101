package no.uib.inf101.sem2.snake.model;

import java.util.LinkedList;
import java.util.Random;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.snake.controller.SnakeControllable;
import no.uib.inf101.sem2.snake.model.objects.Object;
import no.uib.inf101.sem2.snake.model.snake.Snake;
import no.uib.inf101.sem2.snake.view.ColorTheme;
import no.uib.inf101.sem2.snake.view.SnakeViewable;

/**
 * Class representing the model of the game.
 * 
 * @author Jasmine Næss
 */
public class SnakeModel implements SnakeViewable, SnakeControllable {

    private GameState state = GameState.START_GAME;
    private Random random = new Random();
    private ColorTheme theme = new ColorTheme();
    private boolean canChangeDirection;

    private int snakeLength = 3;
    private int score;
    private Board board;
    private Snake snake;
    private Object object;

    // If the snake is moving one direction, it cannot directly move in the opposit direction.
    // Thus, we need to keep track of the opposite direction for a given direction.
    private Direction direction;
    private Direction opposite;

    public LinkedList<CellPosition> snakePosition = new LinkedList<>();

    /**
     * Class constructor.
     * 
     */
    public SnakeModel(Board board, Snake snake) {
        this.board = board;
        this.snake = snake;
        this.score = 0;
        this.direction = Direction.LEFT;
        this.opposite = Direction.RIGHT;
        this.canChangeDirection = true;
        this.snakePosition.add(snake.getSnake().pos());
        generateObject('A');
        // generateObject('B'); // kan legge inn feller? eller andre objekter
    }


    /**
     * Get the current game state.
     *
     * @return GameState
     */
    private boolean legalPosition(Snake snake) {
        for (GridCell<Character> snakeHead : snake) {

            if (!board.positionIsOnGrid(snakeHead.pos())) {
                state = GameState.GAME_OVER;
                return false;
            }
            if (board.get(snakeHead.pos()) == 'S') {
                state = GameState.GAME_OVER;
                return false;
            }
        }
        return true;
    }


    /**
     * Move the snake in a given direction.
     * 
     * @param direction
     */
    public void moveSnake(Direction direction) {
        CellPosition headPosition = snake.getSnake().pos();
        if (state == GameState.ACTIVE_GAME) {
            Snake snake = switch (direction) {
                case UP -> this.snake.moveSnake(-1, 0);
                case DOWN -> this.snake.moveSnake(1, 0);
                case LEFT -> this.snake.moveSnake(0, -1);
                case RIGHT -> this.snake.moveSnake(0, 1);
            };

            if (legalPosition(snake)) {
                this.snake = snake;
                checkEaten();
            }
            else {
                state = GameState.GAME_OVER;
            }
            
            board.set(headPosition, 'S'); // Set the head of the snake
            snakePosition.add(headPosition); // Add the head to the list of positions
            updateSnake(); // Update the snake by removing the tail
        }
    }

    /**
     * Spawns a new object on the board.
     * The new object is placed on a random empty tile.
     * The object is defined by a character.
     * 
     * @param objectChar 
     */
     public void generateObject(char objectChar) {
        int x = random.nextInt(board.rows());
        int y = random.nextInt(board.cols());
        CellPosition objectPosition = new CellPosition(x, y);
    
        // If the object is not placed on an empty tile, generate a new position
        while (!board.get(objectPosition).equals('-')) {
            x = random.nextInt(board.rows());
            y = random.nextInt(board.cols());
            objectPosition = new CellPosition(x, y);
        }
        Object apple = new Object('A', objectPosition);
        this.object = apple;
        board.set(objectPosition, objectChar);
    }

    /**
     * Get the current game state.
     *
     * @return GameState
     */
    void checkEaten() {
        if (snake.getSnake().pos().equals(object.getObjectPosition())) {
            snakeLength++;
            score+= 10;
            generateObject('A');
        }
    }
    
    /**
     * Update the snake by removing the tail.
     */
    public void updateSnake() {
        while (snakeLength <= snakePosition.size()) {
            // Set the tail of the snake to empty
            board.set(snakePosition.get(0), '-');
            snakePosition.remove(0);
        }
    }
    
    @Override
    public void setDirection(Direction direction) {
        if (canChangeDirection == false)
        return;

        // We can't change direction if we are moving in the opposite direction
        if (direction != opposite) {
            this.direction = direction;
            // Define the opposite direction(s)
            switch (direction) {
                case UP:
                    opposite = Direction.DOWN;
                    break;
                case DOWN:
                    opposite = Direction.UP;
                    break;
                case LEFT:
                    opposite = Direction.RIGHT;
                    break;
                case RIGHT:
                    opposite = Direction.LEFT;
                    break;
                default:
                    break;
                }
            }
            // We have changed direction, so we can't change again until the next clock tick
            canChangeDirection = false;
    }

    @Override
    public void clockTick() {
        if (state == GameState.ACTIVE_GAME) {
            moveSnake(direction);
        }
        // We have moved, so we can change direction again
        canChangeDirection = true;
    }

    @Override
    public int getDelay() {
        return 180;
    }

    @Override
    public void restart() {
        score = 0;
        snakeLength = 3;
        direction = Direction.LEFT;
        opposite = Direction.RIGHT;
        canChangeDirection = true;
        state = GameState.ACTIVE_GAME;
        snakePosition.clear();
        board.clearBoard();
        snake = new Snake(new CellPosition(7, 7));
        generateObject('A');
        snakePosition.add(snake.getSnake().pos());
        board.set(snake.getSnake().pos(), 'S');
        updateSnake();
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> tileCells() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> snakeCells() {
        return snake;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setGameScreen(GameState gameScreen) {
        this.state = gameScreen;
    }

    @Override
    public GameState getGameScreen() {
        return state;
    }

    public Board getBoard() {
        return board;
    }

    public Object getObject() {
        return object;
    }

    public Enum<Direction> getDirection() {
        return direction;
    }

    public Enum<Direction> getOpposite() {
        return opposite;
    }

    public boolean setCanChangeDirection(boolean canChange) {
        canChangeDirection = canChange;
        return canChange;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnakeLength(int i) {
        snakeLength = i;
    }
}
