package no.uib.inf101.sem2.snake.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;

/**
 * Class CellPositionToPixelConverter with parameter Rectangle2D, GridDimension
 * and double.
 * 
 * From Tetris v23
 */
public class CellPositionToPixelConverter {

    private Rectangle2D box;
    private GridDimension gd;
    private double margin;

    /**
     * Class constructor.
     * 
     * Takes a Rectangle2D, GridDimension and double as parameters and
     * converts the cell position to pixel.
     * 
     * @param box
     * @param gd
     * @param margin
     */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    /**
     * Method getBoundsForCell will find the bounds for the cell.
     * 
     * @param cells
     * @return a Rectangle2D object
     */
    public Rectangle2D getBoundsForCell(CellPosition cells) { 
        double cellWidth = (box.getWidth() - ((gd.cols() + 1) * margin)) / gd.cols();
        double cellHeight = (box.getHeight() - ((gd.rows() + 1) * margin)) / gd.rows();
        double cellX = box.getX() + ((cells.col() + 1) * margin) + (cells.col() * cellWidth);
        double cellY = box.getY() + ((cells.row() + 1) * margin) + (cells.row() * cellHeight);
        Rectangle2D cell = new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
        return cell;
    }

}
