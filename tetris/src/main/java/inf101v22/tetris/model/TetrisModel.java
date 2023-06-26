package inf101v22.tetris.model;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.controller.TetrisControllable;
import inf101v22.tetris.model.piece.PositionedPiece;
import inf101v22.tetris.model.piece.PositionedPieceFactory;
import inf101v22.tetris.view.*;
import java.awt.Color;

/**
 * Class representing a complete Tetris-game, including the board,
 * pieces, and the different states of the game (start / game over).
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class TetrisModel implements TetrisViewable, TetrisControllable {

    // Welcome-screen is newly added
    public enum GameScreen {
        WELCOME_SCREEN,
        // PAUSE,
        ACTIVE_GAME,
        GAME_OVER
    }

    // Start with the welcome screen
    public GameScreen gameScreen = GameScreen.WELCOME_SCREEN;

    public int rows = 15;
    public int cols = 10;
    public int pieceCounter = 0;
    
    PositionedPiece fallingPiece;
    PositionedPieceFactory pieceFactory;

    // Custom color for the background tiles
    public static final Color jet = new Color(47, 48, 55);

    public final TetrisBoard board = new TetrisBoard(15, 10, new Tile(jet, '-')); {
        this.rows = board.rows;
        this.cols = board.cols;
    }

    /**
     * Class constructor.
     * 
     */
    public TetrisModel() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 10; col++) {
                this.board.set(new Coordinate(row, col), new Tile(jet, '-'));
            }
        }

        // For testing purposes:
        // this.board.set(new Coordinate(0, 0), new Tile(Color.GREEN, 'g'));
        // this.board.set(new Coordinate(board.getRows() - 1, 0), new Tile(Color.RED, 'r'));
        // this.board.set(new Coordinate(0, board.getCols() - 1), new Tile(Color.YELLOW, 'y'));
        // this.board.set(new Coordinate(board.getRows() - 1, board.getCols() - 1), new Tile(Color.BLUE, 'b'));

        this.pieceFactory = new PositionedPieceFactory();
        this.pieceFactory.setCenterColumn(board.getCols()/2);
        this.fallingPiece = this.pieceFactory.getPositionedPiece();
    }

    @Override
    public int getRows() {
        return board.getRows();
    }

    @Override
    public int getCols() {
        return board.getCols();
    }

    @Override
    public Iterable<CoordinateItem<Tile>> iterableBoard() {
        return this.board;
    }

    @Override
    public void set(Coordinate tileCoordinate, Tile color) {
        board.set(tileCoordinate, color);
    }

    @Override
    public Tile get(Coordinate tileCoordinate) {
        return board.get(tileCoordinate);
    }

    @Override
    public Iterable<CoordinateItem<Tile>> iterableFallingPiece() {
        return this.fallingPiece;
    }

    @Override
    public boolean moveFallingPiece(int deltaRow, int deltaCol) {
        PositionedPiece nextFallingPiece = fallingPiece.movedPieceCopy(deltaRow, deltaCol);
        if (checkLegalPosition(nextFallingPiece)) {
            this.fallingPiece = nextFallingPiece;
            return true;
        }
        return false;
    }

    @Override
    public boolean checkLegalPosition(PositionedPiece piece) {
        if (piece.coord.row + piece.getHeight() > this.getRows()
        || piece.coord.col + piece.getWidth() > this.getCols()
        || piece.coord.row < 0
        || piece.coord.col < 0) {
            return false;
        }

        for (CoordinateItem<Tile> otherPiece : piece) {
            if (board.get(otherPiece.coordinate).character != '-') {
                return false;
            }
        }
    return true;
    }

    @Override
    public boolean rotatePiece() {
        PositionedPiece rotatePiece = fallingPiece.rotatedPieceCopy(); 
        if (checkLegalPosition(rotatePiece)) {
            fallingPiece = fallingPiece.rotatedPieceCopy();
            return true;
        }
        return false;
    }

    /**
     * Glue a piece to the board, generate a new piece, and remove full rows.
     * 
     */
    public void glueFallingPiece() {
        for (CoordinateItem<Tile> tiles : iterableFallingPiece()) {
            board.set(tiles.coordinate, tiles.item);
        } 
        getNewPiece();
        board.removeRow();
    }

    /**
     * Generate a new piece once a piece has been glued to the board.
     * If the board is full, no new piece will be generated and th game is over.
     * 
     */
    public void getNewPiece() {
        PositionedPiece newPiece = pieceFactory.getPositionedPiece();
        if (checkLegalPosition(newPiece)) {
            this.fallingPiece = newPiece;
            this.pieceCounter++;
        }
        else {
            gameScreen = GameScreen.GAME_OVER;
        }
    }

    @Override
    public void dropPiece() {
        for (int i = 0; i < board.getRows(); i++) {
            if (moveFallingPiece(1, 0)) {
                moveFallingPiece(1, 0);
            } 
            else break;
        }
        glueFallingPiece();
    }

    @Override
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    @Override
    public int getSeconds() {
        int milliseconds = 1000;
        double secondDelay = milliseconds * (Math.pow(0.98, pieceCounter));
        return (int) secondDelay;
    }

    @Override
    public void clockTick() {
        if (moveFallingPiece(1, 0)) {
        }
        else {
            glueFallingPiece();
        }
    }

    @Override
    public int getScore() {
        return board.score;
    }

    @Override
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

}
