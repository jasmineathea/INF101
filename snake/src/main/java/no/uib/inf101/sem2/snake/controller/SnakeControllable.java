package no.uib.inf101.sem2.snake.controller;

import no.uib.inf101.sem2.snake.model.Direction;
import no.uib.inf101.sem2.snake.model.GameState;

public interface SnakeControllable {

    /**
     * Set the game screen.
     * @param gameScreen
     */
    public void setGameScreen(GameState gameScreen);

    /**
     * Get the game screen.
     * @return the current game screen
     */
    public GameState getGameScreen();

    /**
     * Set the direction of the snake.
     * @param direction
     */
    public void setDirection(Direction direction);

    public void clockTick();

    public int getDelay();

    public void restart();
}
