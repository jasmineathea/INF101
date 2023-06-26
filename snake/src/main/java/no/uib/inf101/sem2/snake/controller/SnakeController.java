package no.uib.inf101.sem2.snake.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import no.uib.inf101.sem2.snake.midi.Song;
import no.uib.inf101.sem2.snake.model.Direction;
import no.uib.inf101.sem2.snake.model.GameState;
import no.uib.inf101.sem2.snake.model.snake.Snake;
import no.uib.inf101.sem2.snake.view.SnakeView;

/**
 * Controller for Snake, tracking the keys pressed by the user. Implements KeyListener and ActionListener.
 * 
 * @author Jasmine Næss - jasmine.ness@student.uib.no
 */
public class SnakeController implements KeyListener, ActionListener {
    SnakeControllable controllable;
    SnakeView viewer;

    Snake snake;
    Timer timer;
    Song song;

    /**
     * Class constructor.
     * 
     * @param model
     * @param viewer
     */
    public SnakeController(SnakeControllable model, SnakeView viewer) {
        this.viewer = viewer;
        this.controllable = model;
        viewer.addKeyListener(this);
        this.song = new Song();
        this.timer = new Timer(model.getDelay(), this::clockTick);
        timer.start();
    }

    /**
     * 
     * @param action
     */
    private void clockTick(ActionEvent action) {
        this.controllable.clockTick();
        viewer.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (controllable.getGameScreen()) {
            case START_GAME:
                handleStart(e);
                break;

            case ACTIVE_GAME:
                handleActive(e);
                break;

            case PAUSE:
                handlePause(e);
                handleRestart(e);
                handleQuit(e);
                break;

            case GAME_OVER:
                handleGameOver(e);
                handleMute(e);
                handleQuit(e);
                break;
        }
    }
    
    /**
     * Method for handling the start game state.
     * 
     * @param e
     */
    public void handleStart(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            song.run();
            controllable.setGameScreen(GameState.ACTIVE_GAME);
            viewer.repaint();
        }
    }

    /**
     * Method for handling the active game state.
     * 
     * @param e
     */
    public void handleActive(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                controllable.setDirection(Direction.UP);
                break;

            case KeyEvent.VK_DOWN:
                controllable.setDirection(Direction.DOWN);
                break;

            case KeyEvent.VK_LEFT:
                controllable.setDirection(Direction.LEFT);
                break;

            case KeyEvent.VK_RIGHT:
                controllable.setDirection(Direction.RIGHT);
                break;

            case KeyEvent.VK_Q:
                handleQuit(e);
                break;

            case KeyEvent.VK_P:
                handlePause(e);
                break;

            case KeyEvent.VK_M:
                handleMute(e);
                break;
        }
        viewer.repaint();
    }

    /**
     * Method for handling the pause function.
     * 
     * @param e
     */
    public void handlePause(KeyEvent e) {
        controllable.setGameScreen(GameState.PAUSE);
        song.doPauseMidiSounds();

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            song.doUnpauseMidiSounds();
            controllable.setGameScreen(GameState.ACTIVE_GAME);
            viewer.repaint();
        }
    }

    public void handleGameOver(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Restart
            case KeyEvent.VK_R:
                controllable.restart();
                song.run();
                break;

            // Quit
            case KeyEvent.VK_Q:
                System.exit(0);
                break;
        }
        viewer.repaint();
    }

    /**
     * Method for handling the mute function.
     * 
     * @param e
     */
    public void handleMute(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_M) {
            if (song.songIsPlaying()) {
                song.doPauseMidiSounds();
            }
            else {
                song.doUnpauseMidiSounds();
            }
        }
        viewer.repaint();
    }

    /**
     * Method for handling the restart function.
     * 
     * @param e
     */
    public void handleRestart(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            controllable.restart();
        }
        viewer.repaint();
    }

    /**
     * Method for handling the quit function.
     * 
     * @param e
     */
    public void handleQuit(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
        viewer.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}
