package gla.joose.birdsim.boards;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A BirdSim board with buttons for creating birds and scaring birds.
 * The flying behavior is determined by the user during runtime.
 */
public class FlockBoard extends Board {
	/* container for buttons and labels */
	JPanel buttonPanel;

	/* buttons for hatching and scaring birds */
	JButton hatchEggButton;
	JButton scareBirdsButton;
	
	/* displays current number of birds */
	JLabel noOfBirdsLabel;

	/* constructor - set board's dimensions */
	public FlockBoard(int rows, int columns) {
		super(rows, columns);
	}

	@Override
	public void initBoard(final JFrame frame) {
		/* get the main panel and put in in the window that contains the game */
		JPanel display = getJPanel();
		frame.getContentPane().add(display, BorderLayout.CENTER);

		/* add a panel for the buttons and the label */
		buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		/* add a button for hatching new birds and an event listener to it so it creates birds when clicked on */
		hatchEggButton = new JButton("hatch egg");
		buttonPanel.add(hatchEggButton);
		hatchEggButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createThread();
			}
		});

		/* add a button for scaring the birds and attach an event listener to it*/
		scareBirdsButton = new JButton("scare birds");
		buttonPanel.add(scareBirdsButton);
		scareBirdsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scareBirds = true;
			}
		});

		/* show how many birds are on the screen */
		noOfBirdsLabel = new JLabel();
		noOfBirdsLabel.setText("#birds: " + 0);
		buttonPanel.add(noOfBirdsLabel);

		/* when the user clicks on the close window button, delete all pieces */
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// used to invoke birds removal from the board
				scareBirds = true;
				if (runningThread != null) {
					clear();
					try {
						runningThread.join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				frame.dispose();
				System.exit(0);
			}
		});

		frame.pack();
		frame.setSize(650, 650);
		frame.setVisible(true);

	}
	
	@Override
	public void updateStockDisplay() {
		updateStock();
		noOfBirdsLabel.setText("#birds: " + noofbirds);
	}

}
