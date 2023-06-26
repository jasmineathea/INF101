package inf101v22.tetris.model.piece;

import inf101v22.tetris.model.Tile;

import java.awt.Color;

/**
 * Giving each Tetris-piece their shape and color.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class PieceShape {
    
    private Tile tile;
    private boolean[][] shape;

    private PieceShape(Tile tile, boolean[][] shape) {
        this.tile = tile;
        this.shape = shape;
    }

    /**
     * The shape of the piece.
     * 
     * @return shape
     */
    public boolean[][] getShape() {
        return shape;
    }

    /**
     * The width of the piece.
     * 
     * @return width
     */
    public int getWidth() {
        return shape[0].length;
    }
    
    /**
     * The height of the piece.
     * 
     * @return height
     */
    public int getHeight() {
        return shape.length;
    }

    /**
     * The tiles creating each Tetris-piece.
     * 
     * @return shape and color of PieceShape-tiles
     */
    public Tile getTile() {
        return tile;
    }

    // Custom colors for each Tetris-piece
    public static final Color babyblue = new Color(154, 196, 248);
    public static final Color blush = new Color(234, 99, 140);
    public static final Color salmon = new Color(250, 163, 129);
    public static final Color teagreen = new Color(203, 234, 166);
    public static final Color darkseagreen = new Color(121, 183, 145);
    public static final Color lavenderfloral = new Color(162, 136, 227);
    public static final Color persiangreen = new Color(27, 153, 139);

    public static final PieceShape T = new PieceShape (new Tile(babyblue, 't'), new boolean[][] {
        {  true,  true,  true },
        { false,  true, false }
        });

    public static final PieceShape S = new PieceShape (new Tile(blush, 's'), new boolean[][] {
        { false,  true,  true },
        {  true,  true, false }
        });
    
    public static final PieceShape Z = new PieceShape (new Tile(salmon, 'z'), new boolean[][] {
        {  true,  true, false },
        { false,  true,  true }
        });

    public static final PieceShape I = new PieceShape (new Tile(teagreen, 'i'), new boolean[][] {
        { true,  true,  true, true }
        });

    public static final PieceShape J = new PieceShape (new Tile(darkseagreen, 'j'), new boolean[][]{
        { true, false, false },
        { true, true,  true }
        });
    
    public static final PieceShape L = new PieceShape (new Tile(lavenderfloral, 'l'), new boolean[][] {
        { false, false,  true },
        {  true,  true,  true }
        });

    public static final PieceShape O = new PieceShape (new Tile(persiangreen, 'o'), new boolean[][] {
        {  true,  true },
        {  true,  true }
        });

    public static final PieceShape[] STANDARD_TETRIS_PIECES = {T, S, Z, I, J, L, O};
    
    /**
     * Create a copy a given piece which is rotated.
     * 
     * @return a rotated piece
     */
    public PieceShape rotatedCopy() {
        boolean[][] newBool = new boolean[getWidth()][getHeight()];
        for (int newRow = 0; newRow < getWidth(); newRow++) {
            for (int oldRow = 0; oldRow < getHeight(); oldRow++) {
                int oldWidth = getWidth();
                int oldCol = oldWidth - newRow - 1;
                newBool[newRow][oldRow] = shape[oldRow][oldCol];
            }
        }
        PieceShape rotatedPieceShape = new PieceShape(tile, newBool);
        return rotatedPieceShape;
    }
    
}
