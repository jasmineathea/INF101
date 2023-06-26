package inf101v22.tetris.view;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.TetrisModel.GameScreen;

/**
 * TetrisViewable consists of the methods necessary for viewing.
 * These methods are defined in the classes Coordinate, CoordinateItem, Tile, and GameScreen (in TetrisModel).
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public interface TetrisViewable {

     /**
      * @return number of rows in grid
      */
     int getRows();

     /**
      * @return number of columns in grid
      */
     int getCols();

     /**
      * Iterates over the tiles on board.
      *
      * @return coordinates from tiles on board
      */
     Iterable<CoordinateItem<Tile>> iterableBoard();

     /**
      * Iterates over the tiles in each Tetris-piece.
      *
      * @return coordinates for given piece
      */
     Iterable<CoordinateItem<Tile>> iterableFallingPiece();

     /**
      * Set the color of each tile.
      *
      * @param tileCoordinate The position of the tile
      * @param color
      */
     void set(Coordinate tileCoordinate, Tile color);

     /**
      * Get the coordinate for each tile.
      *
      * @param coord
      * @return tile coordinate
      */
     Tile get(Coordinate coord);

     /**
      * @return state of GameScreen (default is ACTIVE)
      */
     public GameScreen getGameScreen();

     /**
      * Get score once a full row has been removed.
      
      * @return score
      */
     public int getScore();

}
