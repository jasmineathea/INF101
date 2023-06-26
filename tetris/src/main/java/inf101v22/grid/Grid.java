package inf101v22.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representing a grid.
 * Works with the generic type 'E' and implements the interface IGrid.
 * 
 * Source for constructors, locationToIndex and throwsExceptionWheenCoordinateOffGrid: lab 5
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class Grid<E> implements IGrid<E> {

    public final List<E> grid;
	public final int cols;
	public final int rows;

	/**
	 * First class constructor.
	 * Creates a grid with the given dimensions.
	 * 
	 * @param rows
	 * @param cols
	 */
    public Grid(int rows, int cols) {
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException();
		}

		this.cols = cols;
		this.rows = rows;
		grid = new ArrayList<E>(cols * rows);
		for (int i = 0; i < cols * rows; ++i) {
			grid.add(null);
		}
	}

	/**
	 * Second class constructor.
	 * Creates a grid with the given dimensions.
	 * 
	 * @param rows
	 * @param cols
	 * @param element What the tile should initially hold (possibly null)
	 */
	public Grid(int rows, int cols, E element) {
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException();
		}

		this.cols = cols;
		this.rows = rows;
		grid = new ArrayList<E>(cols * rows);
		for (int i = 0; i < cols * rows; ++i) {
			grid.add(element);
		}
	}
    
    @Override
    public Iterator<CoordinateItem<E>> iterator() {
        ArrayList<CoordinateItem<E>> items = new ArrayList<CoordinateItem<E>>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                E item = get(coordinate);
                CoordinateItem<E> coordinateItem = new CoordinateItem<E>(coordinate, item);
                items.add(coordinateItem);
            }
        }
        return items.iterator();
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public void set(Coordinate coordinate, E value) {
		if (!coordinateIsOnGrid(coordinate)) {
			throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
		}
		else {
			grid.set(locationToIndex(coordinate), value);
		}
    }

    @Override
    public E get(Coordinate coordinate) {
		if (!coordinateIsOnGrid(coordinate)) {
			throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
		}
		else {
			return grid.get(locationToIndex(coordinate));
		}
    }

    @Override
    public boolean coordinateIsOnGrid(Coordinate coordinate) {
        if (coordinate.row < 0 || coordinate.row >= rows) {
			return false;
		}
		return coordinate.col >= 0 && coordinate.col < cols;
    }

	 /**
	 * This method computes which index in the list belongs to a given Location.
	 */
    private int locationToIndex(Coordinate coordinate) {
        return coordinate.row + coordinate.col * rows;
    }

    /**
	 * This method checks if a given Location is within the bounds of this grid.
	 * If it is not, an IndexOutOfBoundsException is thrown.
	 * 
	 * @param coordinate
	 */
	 public void throwsExceptionWheenCoordinateOffGrid(Coordinate coordinate) {
		if (!coordinateIsOnGrid(coordinate)) {
			throw new IndexOutOfBoundsException("Row and column indices must be within bounds");
		}
	}

}
