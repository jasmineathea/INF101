package inf101v22.tetris.model;

import inf101v22.grid.Coordinate;
import inf101v22.grid.Grid;

import java.awt.Color;

/**
 * Class representing a board of tiles.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class TetrisBoard extends Grid<Tile> {

    public int rows;
    public int cols;
    public Tile tile;
    public int score = 0;

    public TetrisBoard(int rows, int cols, Tile tile) {
        super(rows, cols);
    }

    public char[][] toCharArray2D() {
        char [][] charBoard = new char[rows][cols];
        for (int row = 0; row < rows; rows++) {
            for (int col = 0; col < cols; cols++) {
                if (this.tile == null) {
                    charBoard[rows][cols] = '-';
                }
                else {
                    charBoard[rows][cols] = this.get(new Coordinate(row, col)).character;
                }
            }
        }
        return charBoard;
    }

    public String charArray2dToString(char[][] charBoard) {
        String stringOutput = "";
        for (int i = 0; i < charBoard.length; i++) {
            for (int j = 0; j < charBoard.length; j++) {
                stringOutput = charBoard.toString();
            }
        }
        return stringOutput;
    }

    /**
     * Method checking whether an element (i.e. null) exists in a row.
     * 
     * @param row
     * @return true if the row is full
     */
    public boolean checkFullRow(int row) {
        for (int col = 0; col < getCols(); col++) {
            if((get(new Coordinate(row, col)).character == '-')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method filling a row with a given value.
     * 
     * @param row
     * @param tile
     */
    public void fillRow(int row, Tile tile) {
        for (int col = 0; col < getCols(); col++) {
            set(new Coordinate(row, col), tile);
        }
    }

    /**
     * Method copying all elements of a row into another row.
     * 
     * @param newRow
     * @param oldRow
     */
    public void copyRowElement(int newRow, int oldRow) {
        for (int col = 0; col < getCols(); col++) {
            Coordinate coord = new Coordinate(oldRow, col);
            Coordinate newCoord = new Coordinate(newRow, col);
            set(newCoord, get(coord));
        }
    }

    /**
     * Method removing all full rows from board.
     * 
     * @return
     */
    public int removeRow() {
        int newRow = this.getRows()-1;
        int oldRow = this.getRows() -1;
        int removedRows = 0;

        while (newRow >= 0) {
            if (oldRow >= 0) {
                while (checkFullRow(oldRow)) {
                    removedRows ++;
                    this.score += (int) Math.pow(removedRows, 2);
                    oldRow --;
                }
                if (oldRow >= 0) {
                    copyRowElement(newRow, oldRow);
                }
                else {
                    fillRow(newRow, new Tile(Color.BLACK, '-'));
                }
            }
            newRow --;
            oldRow --;
        }
        return removedRows;
    }

}
