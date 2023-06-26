package no.uib.inf101.sem2.snake.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.snake.model.GameState;

/**
 * SnakeView class is responsible for viewing the game.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class SnakeView extends JComponent {

    private static final int padding = 10;
    private static final int bottomPadding = 30;
    private ColorTheme theme;
    private SnakeViewable model;

    /**
     * SnakeView retrieves number of rows and cols on grid
     * and which color it should have.
     */
    public SnakeView(SnakeViewable model) {
        this.model = model;
        this.setFocusable(true); // Hentet fra SampleView.java

        this.setPreferredSize(getPreferredSize());

        this.theme = new ColorTheme();
        this.setBackground(theme.boardColor);
    }

    @Override
    public void paintComponent(Graphics canvas) {
        super.paintComponent(canvas);
        Graphics2D g2d = (Graphics2D) canvas;
        drawGame(g2d);

        if (model.getGameScreen() == GameState.START_GAME) {
            drawStartScreen(g2d);
        } else if (model.getGameScreen() == GameState.PAUSE) {
            drawPauseScreen(g2d);
        } else if (model.getGameScreen() == GameState.GAME_OVER) {
            drawGameOverScreen(g2d);
        }
    }

    /**
     * Method for drawing the cells.
     * 
     * @param canvas
     * @param cells
     * @param converter
     * @param getColors
     */
    private static void drawCells(Graphics2D canvas, Iterable<GridCell<Character>> cells,
        CellPositionToPixelConverter converter, ColorTheme getColors) {  
        ImageIcon appleSymbol = new ImageIcon(GraphicHelperMethods.loadImageFromResources("/apple.png"));
        ImageIcon snakeSymbol = new ImageIcon(GraphicHelperMethods.loadImageFromResources("/green-circle.png"));
        
        for (GridCell<Character> gridChar : cells) {
            Rectangle2D tile = converter.getBoundsForCell(gridChar.pos());
            Color theme = getColors.getCellColor(gridChar.value());
            canvas.setColor(theme);
            canvas.fill(tile);

            if (gridChar.value() == 'A') {
                canvas.drawImage(appleSymbol.getImage(), (int)tile.getX(), (int)tile.getY(), (int)tile.getWidth(), (int)tile.getHeight(), null);
            }
            if (gridChar.value() == 'S') {
                canvas.drawImage(snakeSymbol.getImage(), (int)tile.getX(), (int)tile.getY(), (int)tile.getWidth(), (int)tile.getHeight(), null);
            }

        }
    }

    /**
     * Method drawing the snake-game itself.
     * 
     * @param graphics
     */
    private void drawGame(Graphics2D graphics) {
        double width = this.getWidth() - 2 * padding;
        double height = this.getHeight() - 2 * bottomPadding;
        Rectangle2D frameRectangle = new Rectangle2D.Double(padding, padding, width, height);
        graphics.fill(frameRectangle);
        CellPositionToPixelConverter cellConverter = new CellPositionToPixelConverter(frameRectangle,
                model.getDimension(),
                0);
        drawCells(graphics, model.tileCells(), cellConverter, theme);
        drawCells(graphics, model.snakeCells(), cellConverter, theme);
        // draw score on bottom:
        graphics.setColor(theme.menuFont);
        Font str = new Font("Monospaced", Font.BOLD, 30);
        graphics.setFont(str);
        GraphicHelperMethods.drawCenteredString(
            graphics, "SCORE: " + model.getScore(),
            20, 0, this.getWidth() - 40, this.getHeight() + 680);
    }

    /**
     * Method for drawing the start screen,
     * which goes on top of the game.
     * 
     * This is the first screen you see when you start the game.
     * 
     * @param graphics
     */
    private void drawStartScreen(Graphics2D graphics) {
        double width = this.getWidth() - 2 * padding;
        double height = this.getHeight() - 2 * bottomPadding;
        Rectangle2D frameRectangle = new Rectangle2D.Double(padding, padding, width, height);

        graphics.setColor(theme.transparentgray);
        graphics.fill(frameRectangle);

        graphics.setColor(theme.screenFont);
        Font str = new Font("Monospaced", Font.BOLD, 40);
        graphics.setFont(str);
        GraphicHelperMethods.drawCenteredString(
            graphics, "Press ENTER",
            20, 75, this.getWidth() - 40, this.getHeight() - 540);
        GraphicHelperMethods.drawCenteredString(
            graphics, "to begin!",
            20, 125, this.getWidth() - 40, this.getHeight() - 540);
    }

    /**
     * Method for drawing the pause screen,
     * which goes on top of the game.
     * 
     * @param graphics
     */
    private void drawPauseScreen(Graphics2D graphics) {
        double width = this.getWidth() - 2 * padding;
        double height = this.getHeight() - 2 * bottomPadding;
        Rectangle2D frameRectangle = new Rectangle2D.Double(padding, padding, width, height);

        graphics.setColor(theme.transparentgray);
        graphics.fill(frameRectangle);

        graphics.setColor(theme.screenFont);
        Font str = new Font("Monospaced", Font.BOLD, 40);
        graphics.setFont(str);
        GraphicHelperMethods.drawCenteredString(
            graphics, "Press ENTER",
            20, 75, this.getWidth() - 40, this.getHeight() - 540);
        GraphicHelperMethods.drawCenteredString(
            graphics, "to continue!",
            20, 125, this.getWidth() - 40, this.getHeight() - 540);

        Font scoreFont = new Font("Monospaced", Font.PLAIN, 20);
        graphics.setFont(scoreFont);
        GraphicHelperMethods.drawCenteredString(
            graphics, "Your current score is " + model.getScore() + "",
            20, 175, this.getWidth() - 40, this.getHeight() - 540);
    }

    /**
     * Method for drawing the game over screen,
     * which goes on top of the game.
     * 
     * @param graphics
     */
    private void drawGameOverScreen(Graphics2D graphics) {
        double width = this.getWidth() - 2 * padding;
        double height = this.getHeight() - 2 * bottomPadding;
        Rectangle2D frameRectangle = new Rectangle2D.Double(padding, padding, width, height);

        graphics.setColor(theme.transparentgray);
        graphics.fill(frameRectangle);

        graphics.setColor(theme.screenFont);
        Font str = new Font("Monospaced", Font.BOLD, 40);
        graphics.setFont(str);
        GraphicHelperMethods.drawCenteredString(
            graphics, "GAME OVER!",
            20, 75, this.getWidth() - 40, this.getHeight() - 540);
        GraphicHelperMethods.drawCenteredString(
            graphics, "Your final score is " + model.getScore(),
            20, 125, this.getWidth() - 40, this.getHeight() - 540);
        
        Font optionFont = new Font("Monospaced", Font.PLAIN, 20);
        graphics.setFont(optionFont);
        GraphicHelperMethods.drawCenteredString(
            graphics, "Press [R] to restart,",
            20, 200, this.getWidth() - 40, this.getHeight() - 540);
        GraphicHelperMethods.drawCenteredString(
            graphics, "or [Q] to quit.",
            20, 225, this.getWidth() - 40, this.getHeight() - 540);
    }
    
    @Override
    public Dimension getPreferredSize() {
        int width = 700;
        int height = 730;
        return new Dimension(width, height);
    }

    /**
     * Create a new frame with the board and right-hand menu.
     * 
     * @return the frame
     */
    public JFrame getFrame() {
        JFrame frame = new JFrame("SNAKE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Create a new panel with BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        // Add the board to the center of the frame
        frame.add(this, BorderLayout.CENTER);
    
        // Add the menu to the right
        frame.add(panel, BorderLayout.EAST);
    
        JLabel keys = new JLabel("<html> Key menu:" + "<br>"
        + "----------------" + "<br>"
        + "move up: ⬆ " + "<br>"
        + "move down: ⬇ " + "<br>"
        + "move left: ⬅ " + "<br>"
        + "move right: ⮕" + "<br>"
        + "mute / unmute: [M]" + "<br>"
        + "pause: [P]" + "<br>"
        + "restart: [R]" + "<br>"
        + "quit: [Q] </html>");
        keys.setFont(new Font("Monospaced", Font.PLAIN, 18));
        keys.setForeground(theme.menuFont);
        keys.setBorder(BorderFactory.createEmptyBorder(150, 0, 40, 15));

        ImageIcon imageIcon = new ImageIcon(GraphicHelperMethods.loadImageFromResources("/snake.png"));
		JLabel imageLabel = new JLabel(imageIcon);

        panel.add(keys);
        panel.add(imageLabel);
    
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    
}
