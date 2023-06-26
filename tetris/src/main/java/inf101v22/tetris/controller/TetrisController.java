package inf101v22.tetris.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import inf101v22.tetris.midi.TetrisSong;
import inf101v22.tetris.model.TetrisModel.GameScreen;
import inf101v22.tetris.view.TetrisView;

/**
 * Controller for tetris, tracking the keys pressed by the user. Implements KeyListener and ActionListener.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class TetrisController implements KeyListener, ActionListener {

    TetrisControllable controller;
    TetrisView viewer;
    Timer timer;
    TetrisSong song;
    
    /**
     * Class constructor.
     * 
     * @param controller
     * @param viewer
     */
    public TetrisController(TetrisControllable controller, TetrisView viewer) {
        this.controller = controller;
        this.viewer = viewer;
        viewer.addKeyListener(this);

        this.timer = new Timer(controller.getSeconds(), this);
        timer.start();

        this.song = new TetrisSong();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (controller.getGameScreen() == GameScreen.WELCOME_SCREEN) {
            // Enter key was pressed (to start game)
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                song.run();
                controller.setGameScreen(GameScreen.ACTIVE_GAME);
            }
        }
        
        if (controller.getGameScreen() == GameScreen.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                // Left arrow was pressed
                controller.moveFallingPiece(0, -1);
                viewer.repaint();
            }

            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // Right arrow was pressed
                controller.moveFallingPiece(0, 1);
                viewer.repaint();
            }

            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                // Down arrow was pressed
                controller.moveFallingPiece(1, 0);
                viewer.repaint();
            }

            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                // Up arrow was pressed
                controller.rotatePiece();
                viewer.repaint();
            }

            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // Spacebar was pressed
                controller.dropPiece();
                viewer.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (controller.getGameScreen() == GameScreen.ACTIVE_GAME) {
            controller.clockTick();
            viewer.repaint();
        }
        this.getDelay();
    }
    
    public void getDelay() {
        timer.setDelay(controller.getSeconds());
        timer.setInitialDelay(controller.getSeconds());
    }
}
