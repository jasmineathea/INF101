package inf101v22.tetris.model.piece;

import java.util.ArrayList;
import java.util.Iterator;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;

/**
 * Class representing a falling Tetris-piece, characherized by its shape and coordinates.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class PositionedPiece implements Iterable<CoordinateItem<Tile>> {

    public Coordinate coord;
    private PieceShape shape;

    /**
     * Class constructor.
     * 
     * @param shape
     * @param coord
     */
    PositionedPiece(PieceShape shape, Coordinate coord) {
        this.shape = shape;
        this.coord = coord;
    }

    @Override
    public Iterator<CoordinateItem<Tile>> iterator() {
        ArrayList<CoordinateItem<Tile>> items = new ArrayList<CoordinateItem<Tile>>();
        boolean[][] bool2 = shape.getShape();
        Tile tile = shape.getTile();
        
        for (int col = 0; col < shape.getWidth(); col++) {
            for (int row = 0; row < shape.getHeight(); row++) {
                if (bool2 [row][col]) {
                    Coordinate coord = new Coordinate(this.coord.row + row, this.coord.col + col);
                    items.add(new CoordinateItem<Tile>(coord, tile));
                }
            }
        }
        return items.iterator();
    }

    public int getWidth() {
        return shape.getWidth();
    }

    public int getHeight() {
        return shape.getHeight();
    }

    public Tile getTile() {
        return shape.getTile();
    }
    
    /**
     * Create a moved copy of PositionedPiece.
     * 
     * @param deltaRow new row-value
     * @param deltaCol new col-value
     * @return a moved copy
     */
    public PositionedPiece movedPieceCopy(int deltaRow, int deltaCol) {
        Coordinate newCoord = new Coordinate(this.coord.row + deltaRow, this.coord.col + deltaCol);
        PositionedPiece movedPiece = new PositionedPiece(shape, newCoord);
        return movedPiece;
    }

    /**
     * Method to find the center of each piece.
     * 
     * @return the center coordinate
     */
    public Coordinate findCenter() {
        int centerRow = shape.getHeight()/2;
        int centerCol = shape.getWidth()/2;

        int newRow = shape.rotatedCopy().getHeight()/2;
        int newCol = shape.rotatedCopy().getWidth()/2;

        Coordinate centerCoord = new Coordinate(coord.row + centerRow - newRow, coord.col + centerCol - newCol);
        return centerCoord;
    }

    /**
     * Create a rotated copy of PositionedPiece
     * 
     * @return a rotated copy
     */
    public PositionedPiece rotatedPieceCopy() {
        PositionedPiece rotatedPiece = new PositionedPiece(shape.rotatedCopy(), findCenter());
        return rotatedPiece;
    }

}
