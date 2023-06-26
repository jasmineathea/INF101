package no.uib.inf101.sem2.grid;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;
import no.uib.inf101.sem2.grid.GridCell;
import org.junit.Test;

import java.util.Iterator;

import static org.testng.Assert.*;


public class GridTest {

    private Grid<Integer> grid;
    @Test
    public void testConstructorAndGet() {
        // Test constructor with default value
        Grid<String> grid = new Grid<>(3, 3);
        assertEquals(null, grid.get(new CellPosition(1, 1)));

        // Test constructor with specific value
        grid = new Grid<>(2, 2, "test");
        assertEquals("test", grid.get(new CellPosition(1, 1)));

        // Test set() and get()
        grid.set(new CellPosition(1, 1), "new value");
        assertEquals("new value", grid.get(new CellPosition(1, 1)));
    }

    @Test
    public void testPositionIsOnGrid() {
        // Test positionIsOnGrid() method
        Grid<String> grid = new Grid<>(3, 3);
        CellPosition pos = new CellPosition(1, 1);
        assertTrue(grid.positionIsOnGrid(pos));
        pos = new CellPosition(3, 3);
        assertFalse(grid.positionIsOnGrid(pos));
        pos = new CellPosition(-1, 0);
        assertFalse(grid.positionIsOnGrid(pos));
    }
}
