package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A CellPosition consists of a row and a column.
 *
 * @param row the row of the cell
 * @param col the column of the cell
 * @param E   the generic parameter
 */

/**
 * A Grid is a two-dimensional array of objects.
 *
 * @param <E> the generic parameter
 */
public class Grid<E> implements IGrid<E> {

    /**
     * The grid.
     */
    private List<List<E>> grid;

    /**
     * The number of rows.
     */
    int rows;
    /**
     * The number of columns.
     */
    int cols;

    // Konstruktør 1

    /**
     * Create a new grid with the given number of rows and columns.
     *
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public Grid(int rows, int cols) {
        this(rows, cols, null);
    }

    /**
     * Create a new grid with the given number of rows and columns, and fill it with
     * the given value.
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @param e    the value to fill the grid with
     */
    // Konstruktør 2
    public Grid(int rows, int cols, E e) {
        this.grid = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            // Lage en rekke
            ArrayList<E> theRow = new ArrayList<>();

            for (int c = 0; c < cols; c++) {
                theRow.add(e); // Legger til default verdi i theRow som jeg vil ha kolonner
            }

            this.grid.add(theRow);
        }
    }

    /**
     * Create a new grid from the given two-dimensional array.
     *
     * @param grid the two-dimensional array
     */
    @Override
    public void set(CellPosition pos, E value) {
        // Inspirasjon fra koden i lab 4
        List<E> row = this.grid.get(pos.row());
        row.set(pos.col(), value);
    }

    @Override
    public int rows() {
        return this.grid.size();
    }


    @Override
    public int cols() {
        return this.grid.get(0).size();
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        List<GridCell<E>> result = new ArrayList<>(); // Kopiert og modifisert kode fra Lab 4 getCells

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                CellPosition pos = new CellPosition(i, j);

                GridCell<E> posFromGridCell = new GridCell<E>(pos, grid.get(i).get(j));
                result.add(posFromGridCell);

            }
        }
        return result.iterator();
    }

    @Override
    public E get(CellPosition pos) {
        List<E> row = this.grid.get(pos.row());
        return row.get(pos.col());
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        // Grid på 4x7 og pos: (3, 6) -> True
        // (3, 8) -> False
        if (pos.row() < 0)
            return false;
        if (pos.col() < 0)
            return false;
        if (pos.row() >= this.rows())
            return false;
        if (pos.col() >= this.cols())
            return false;
        return true;
    }
}
