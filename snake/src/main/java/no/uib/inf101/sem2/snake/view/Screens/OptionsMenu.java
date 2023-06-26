package no.uib.inf101.sem2.snake.view.Screens;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.snake.controller.SnakeController;
import no.uib.inf101.sem2.snake.model.Board;
import no.uib.inf101.sem2.snake.model.SnakeModel;
import no.uib.inf101.sem2.snake.model.snake.Snake;
import no.uib.inf101.sem2.snake.view.GraphicHelperMethods;
import no.uib.inf101.sem2.snake.view.SnakeView;

/**
 * Options menu screen.
 * Includes dropdown menus for choosing color of snake and the food to eat.
 * 
 * Back button returns to main menu.
 * Play button starts the game.
 * 
 * @author Jasmine Næss
 */
public class OptionsMenu extends JFrame implements ActionListener {

    private JButton back;
    private JButton play;
    private final JFrame frame;
    private JComboBox<String> colorOpt;
    private JComboBox<String> foodOpt;


    public OptionsMenu() {
		frame = new JFrame();
		frame.setTitle("Options");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(10, 10, 30, 10)); 

        Color bcg = new Color(213, 189, 175);
        contentPane.setBackground(bcg);

        JLabel heading = new JLabel("Options");
        heading.setFont(new Font("Monospaced", Font.BOLD, 25));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(heading);

        // image
		ImageIcon imageIcon = new ImageIcon(GraphicHelperMethods.loadImageFromResources("/palette.png"));
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(imageLabel);

        // add color drop-down menu
        JPanel colorPanel = new JPanel();
        colorPanel.setOpaque(false);
        colorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));

        JLabel colorLabel = new JLabel("Choose snakes color:");
        colorLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // space between color and food-panel

        colorOpt = new JComboBox<>();
        colorOpt.addItem("Dark green (default)");
        colorOpt.addItem("Purple");
        colorOpt.addItem("Pink");
        colorOpt.addItem("Blue");

        JPanel colorSelectionPanel = new JPanel();
        colorSelectionPanel.setOpaque(false);
        colorSelectionPanel.setLayout(new BoxLayout(colorSelectionPanel, BoxLayout.Y_AXIS));
        colorSelectionPanel.add(colorLabel);
        colorSelectionPanel.add(colorOpt);
        colorPanel.add(colorSelectionPanel);

        // add food drop-down menu
        JPanel foodPanel = new JPanel();
        foodPanel.setOpaque(false);
        foodPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        foodPanel.setLayout(new BoxLayout(foodPanel, BoxLayout.Y_AXIS));

        JLabel foodLabel = new JLabel("Choose snakes food:");
        foodLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        foodLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0)); // space between color and food-panel

        foodOpt = new JComboBox<>();
        foodOpt.addItem("Apple (default)");
        foodOpt.addItem("Banana");
        foodOpt.addItem("Orange");
        foodOpt.addItem("Strawberry");

        JPanel foodSelectionPanel = new JPanel();
        foodSelectionPanel.setOpaque(false);
        foodSelectionPanel.setLayout(new BoxLayout(foodSelectionPanel, BoxLayout.Y_AXIS));
        foodSelectionPanel.add(foodLabel);
        foodSelectionPanel.add(foodOpt);
        foodPanel.add(foodSelectionPanel);

        // add the colorPanel and the foodPanel
        contentPane.add(colorPanel);
        contentPane.add(foodPanel);
        frame.add(contentPane);

        // spacing
		contentPane.add(Box.createRigidArea(new Dimension(0, 10)));

        // back button
        JPanel button = backButton();
        contentPane.add(button);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.add(contentPane);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

    /**
	 * Helper method that creates the back button.
	 */
	private JPanel backButton() {
		back = new JButton();
		back.addActionListener(this);
		back.setText("Back");

        play = new JButton();
		play.addActionListener(this);
		play.setText("PLAY");

		JPanel buttons = new JPanel();
        buttons.setOpaque(false); // set panel to transparent
		buttons.add(back);
        buttons.add(play);
		return buttons;
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose();
            MainMenu mainMenu = new MainMenu();
			mainMenu.show();
        }
        if (e.getSource() == play) {
            frame.dispose();
            Snake snake = new Snake(new CellPosition(10, 10));
			Board board = new Board(15, 15);
			SnakeModel model = new SnakeModel(board, snake);
            SnakeView view = new SnakeView(model);
            SnakeController controller = new SnakeController(model, view);
            view.getFrame();
        }
    }

    /**
     * Shows the options menu.
     * Is called from the main menu in order to switch between screens.
     */
    public void show() {
        frame.setVisible(true);
    }
    
}
