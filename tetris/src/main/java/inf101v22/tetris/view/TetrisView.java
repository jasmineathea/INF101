package inf101v22.tetris.view;

import javax.swing.JComponent;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.TetrisModel.GameScreen;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Viewing the graphics of Tetris.
 * TetrisView extends JComponent and uses methods defined in CoordinateItem, Tile, and TetrisModel.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class TetrisView extends JComponent {
    public TetrisViewable view;

    {
        this.setFocusable(true);
    }

    public TetrisView(TetrisViewable model) {
        this.view = model;
    }

    @Override
    public void paint(Graphics canvas) {
        super.paint(canvas);
        int padding = 10;
        Color transparentgray = new Color(0, 0, 0, 128);
        drawTetrisBoard(canvas, padding, padding, this.getWidth() -2 * padding, this.getHeight() -2 * padding, padding/5);
        
        if (view.getGameScreen() == GameScreen.ACTIVE_GAME) {
            this.drawTetrisBoard(canvas, padding, padding, this.getWidth() -2 * padding, this.getHeight() -2 * padding, padding/5);

            // Add score-counter:
            canvas.setColor(transparentgray);
            canvas.fillRect((this.getWidth() - 105), (this.getHeight() - 738), 90, 45);

            canvas.setColor(Color.WHITE);
            Font secondFont = new Font("Arial", Font.BOLD, 18);
            canvas.setFont(secondFont);
            canvas.drawString(
                "Score: " + view.getScore(),
                (this.getWidth() - 95), (this.getHeight() - 705));
        }
        
        if (view.getGameScreen() == GameScreen.GAME_OVER) {
            canvas.setColor(transparentgray);
            canvas.fillRect(padding, padding, this.getWidth() - 2 * padding, this.getHeight() - 2 * padding);

            canvas.setColor(Color.WHITE);
            Font firstFont = new Font("Arial", Font.BOLD, 60);
            canvas.setFont(firstFont);
            GraphicHelperMethods.drawCenteredString(
                canvas, "GAME OVER!",
                20, 125, this.getWidth() - 40, this.getHeight() - 540);

            Font secondFont = new Font("Arial", Font.BOLD, 25);
            canvas.setFont(secondFont);
            GraphicHelperMethods.drawCenteredString(
                canvas, "Your score: " + view.getScore(),
                20, 200, this.getWidth() - 40, this.getHeight() - 540);
            }
        
        // For implementing pause function later :)
        // if (view.getGameScreen() == GameScreen.PAUSE) {
        //     canvas.setColor(transparentgray);
        //     canvas.fillRect(padding, padding, this.getWidth() - 2 * padding, this.getHeight() - 2 * padding);

        //     canvas.setColor(Color.WHITE);
        //     Font firstFont = new Font("Arial", Font.BOLD, 60);
        //     canvas.setFont(firstFont);
        //     GraphicHelperMethods.drawCenteredString(
        //         canvas, "GAME PAUSED",
        //         20, 125, this.getWidth() - 40, this.getHeight() - 540);
            
        //     Font secondFont = new Font("Arial", Font.BOLD, 25);
        //     canvas.setFont(secondFont);
        //     GraphicHelperMethods.drawCenteredString(
        //         canvas, "Press [p] to continue,",
        //         20, 200, this.getWidth() - 40, this.getHeight() - 540);
        //     GraphicHelperMethods.drawCenteredString(
        //         canvas, "or [q] to quit.",
        //         20, 250, this.getWidth() - 40, this.getHeight() - 540);
        //     }
    }

    /**
     * Draw the welcome screen
     *
     * @param canvas
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void drawWelcomeScreen(Graphics canvas, int x, int y, int width, int height) {
        Color jet = new Color(47, 48, 55);
        Color lightpink = new Color(255, 194, 209);
        Color darkpink = new Color(255, 143, 171);
        canvas.setColor(jet);
        canvas.fillRect(x, y, width, height);
        
        canvas.setColor(lightpink);
        Font firstFont = new Font("Arial", Font.BOLD, 60);
        canvas.setFont(firstFont);
        GraphicHelperMethods.drawCenteredString(
            canvas, "WELCOME",
            0, -250, width, height);
        
        Font secondFont = new Font("Arial", Font.BOLD, 25);
        canvas.setFont(secondFont);
        GraphicHelperMethods.drawCenteredString(
            canvas, "INF101 Tetris vol 2",
            0, -200, width, height);

        canvas.setColor(darkpink);
        GraphicHelperMethods.drawCenteredString(
                canvas, "Press [ENTER] to play!",
                0, -110, width, height);

        // Key menu:
        canvas.setColor(darkpink);
        canvas.fillRect(x + 40, y + 510, 400, 170);

        canvas.setColor(jet);
        GraphicHelperMethods.drawCenteredString(
                canvas, "Keys:",
                0, y + 180, width, height);
        GraphicHelperMethods.drawCenteredString(
                canvas, "____________________",
                0, y + 181, width, height);
        GraphicHelperMethods.drawCenteredString(
                canvas, "[space]: drop piece",
                0, y + 220, width, height);
        GraphicHelperMethods.drawCenteredString(
                canvas, "[arrow up]: rotate piece",
                0, y + 250, width, height);
        GraphicHelperMethods.drawCenteredString(
                canvas, "[p]: pause game",
                0, y+280, width, height);
    }

    /**
     * Construct a tile with padding on the right and bottom side.
     * 
     * @param canvas the canvas to be painted
     * @param xCell x-value
     * @param yCell y-value
     * @param cellWidth
     * @param cellHeight
     * @param padding
     * @param color
     */
    public void drawTileWithRightBottomPadding(Graphics canvas, int xCell, int yCell, int cellWidth, int cellHeight, int padding, Color color) {
        canvas.setColor(color);
        canvas.fillRect(xCell, yCell, cellWidth - padding, cellHeight - padding);
    }

    /**
     * Construct a board with padding on the right and bottom side.
     * 
     * @param canvas the canvas to be painted
     * @param xBoard x-value
     * @param yBoard y-value
     * @param boardWidth
     * @param boardHeight
     * @param padding
     */
    public void drawBoardWithRightBottomPadding(Graphics canvas, int xBoard, int yBoard, int boardWidth, int boardHeight, int padding, Iterable<CoordinateItem<Tile>> tilesToPaint) {

        for (CoordinateItem<Tile> coordinateItem : tilesToPaint) {
            Color color = Color.BLACK;
            int row = coordinateItem.coordinate.row;
            int col = coordinateItem.coordinate.col;
            Tile tile = coordinateItem.item;

            if (tile != null) {
                color = tile.color;
            }

            int cols = this.view.getCols();
            int rows = this.view.getRows();

            // Calculate the coordinates of the tile and paint it
            int tileX = xBoard + col * boardWidth / cols;
            int tileY = yBoard + row * boardHeight / rows;
            int nextX = xBoard + (col + 1) * boardWidth / cols;
            int nextY = yBoard + (row + 1) * boardHeight / rows;
            int tileWidth = nextX - tileX;
            int tileHeight = nextY - tileY;

            drawTileWithRightBottomPadding(canvas, tileX, tileY, tileWidth, tileHeight, padding, color);
        }
    }
    
    /**
     * Construct the entire Tetris-board, with padding on all sides.
     * Draw each falling piece onto board.
     * 
     * @param canvas
     * @param x 
     * @param y
     * @param width
     * @param height
     * @param padding
     */
    public void drawTetrisBoard(Graphics canvas, int x, int y, int width, int height, int padding) {
        if (view.getGameScreen() == GameScreen.WELCOME_SCREEN) {
            drawWelcomeScreen(canvas, x, y, width, height);
        }
        else {
            drawBoardWithRightBottomPadding(canvas, x + padding, y + padding, width - padding, height - padding, padding, this.view.iterableBoard());
            drawBoardWithRightBottomPadding(canvas, x + padding, y + padding, width - padding, height - padding, padding, this.view.iterableFallingPiece());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = 500;
        int height = 750;
        return new Dimension(width, height);
    }

}
