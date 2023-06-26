package inf101v22.grid;

import java.util.Objects;


/**
 * Representing coordinates for each row and column.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class Coordinate {
    public final int row;
    public final int col;

    /**
     * Class constructor
     * 
     * @param row
     * @param col
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "{" +
            " row='" + row + 
            "', col='" + col +
            "' }";
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return row == coordinate.row && col == coordinate.col;
    }

}
