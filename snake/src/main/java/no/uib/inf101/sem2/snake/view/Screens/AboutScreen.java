package no.uib.inf101.sem2.snake.view.Screens;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Screen showing information about the game and the developers :)
 * 
 * Only one button to go back to the main menu,
 * so a player cannot start the game from this screen.
 * 
 * @author Jasmine Næss
 */
public class AboutScreen extends JFrame implements ActionListener {

    private JButton back;
    private final JFrame frame;

    public AboutScreen() {
		frame = new JFrame();
		frame.setTitle("About");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(10, 10, 30, 10)); 

        Color bcg = new Color(233, 245, 219);
        contentPane.setBackground(bcg);

        JLabel heading = new JLabel("💚 About this app 💚");
        heading.setFont(new Font("Monospaced", Font.BOLD, 25));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(heading);

        JLabel description = new JLabel("<html>Snake is a classic video game that originated in the 1970s and gained popularity in the 1990s. In the game, the player controls a snake that moves around on a grid, collecting food pellets and growing longer with each one. The player must avoid running into walls or the snake's own body, which will cause the game to end. The game becomes progressively more challenging as the snake grows longer, and the player must navigate more carefully to avoid collisions. Snake has been ported to a variety of platforms and is still popular today as a simple and addictive game. -ChatGPT, March 2023<br>"
        + "<br>"
        + " This game was created by Emma Gustavsen Rolfsnes and Jasmine Næss for INF101.</html>");
        description.setFont(new Font("Monospaced", Font.PLAIN, 14));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(description);

        // back button
        JPanel button = backButton();
        contentPane.add(button);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(contentPane);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.pack();
        frame.setLocationRelativeTo(null);

    }

    /**
	 * Helper method that creates the back button.
	 */
	private JPanel backButton() {
		back = new JButton();
		back.addActionListener(this);
		back.setText("Back");

		JPanel buttons = new JPanel();
        buttons.setOpaque(false); // set panel to transparent
		buttons.add(back);
		return buttons;
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose(); 
            MainMenu mainMenu = new MainMenu();
			mainMenu.show(); 
        }
    }

    /**
     * Shows the frame.
     * Is called from the main menu.
     */
    public void show() {
        frame.setVisible(true);
    }
}
