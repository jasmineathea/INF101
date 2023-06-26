package inf101v22.tetris.model.piece;

import java.util.Random;

import inf101v22.grid.Coordinate;

/**
 * Get a PositionedPiece-object from the "factory".
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class PositionedPieceFactory {

    private Random random = new Random();
    private int center;
    
    /**
     * Set the ceter column.
     * Used for reference when placing new pieces onto the board.
     * 
     * @param col
     */
    public void setCenterColumn(int col) {
        this.center = col;
    }

    public PositionedPiece getPositionedPiece() {
        PieceShape[] standardPieces = PieceShape.STANDARD_TETRIS_PIECES;
        int randomNumber = random.nextInt(standardPieces.length);
        
        PieceShape shape = standardPieces[randomNumber];
        PositionedPiece positionedPiece = new PositionedPiece(shape, new Coordinate(0, center-shape.getWidth()/2));
        return positionedPiece;
    }

}
