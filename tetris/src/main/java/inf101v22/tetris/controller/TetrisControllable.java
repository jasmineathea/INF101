package inf101v22.tetris.controller;

import inf101v22.tetris.model.TetrisModel.GameScreen;
import inf101v22.tetris.model.piece.PositionedPiece;

/**
 * Functionality purposes; controlling piecees, moving pieces, rotating pieces, dropping pieces.
 * Also including methods for timer.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public interface TetrisControllable {

    /**
     * Move a piece.
     * 
     * @param deltaRow new row-value
     * @param deltaCol new col-value
     * @return boolean determining whether the piece is being moved or not
     */
    boolean moveFallingPiece(int deltaRow, int deltaCol);

    /**
     * Check whether the piece is moved to a legal position.
     * 
     * @param positionedPiece
     * @return boolean determining whether the piece is within bounds
     */
    boolean checkLegalPosition (PositionedPiece positionedPiece);

   /**
    * Rotate a piece.
    *
    * @return boolean determining whether the piece is being rotated or not.
    */
    boolean rotatePiece();

    /**
     * Drop a piece.
     * void: no return value
     */
    void dropPiece();

    /**
     * Get the game screen.
     * @return object of type GameScreen
     */
    public GameScreen getGameScreen();

    /**
     * Set the game screen.
     * @param activeGame
     */
    public void setGameScreen(GameScreen gameScreen);

    /**
     * For timer: seconds between every clock tick.
     * 
     * @return seconds
     */
    public int getSeconds();
    
    /**
     * For timer: method being called every time the clock ticks.
     * 
     */
    public void clockTick();
}
