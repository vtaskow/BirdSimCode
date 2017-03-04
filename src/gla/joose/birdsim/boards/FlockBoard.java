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

import flybehaviors.FlyBehavior;
import flybehaviors.NoForage;

/**
 * A BirdSim board with generic bird flying behavior.
 */
public class FlockBoard extends Board {
	/* container for buttons and labels */
	JPanel buttonPanel;

	/* buttons for hatching and scaring birds */
	JButton hatchEggButton;
	JButton scareBirdsButton;
	/* displays current number of birds */
	JLabel noOfBirdsLabel;

	/* not a fucking clue why this is here */
	Thread runningthread;

	/* set board's dimensions */
	public FlockBoard(int rows, int columns) {
		super(rows, columns);
		flyBehavior = new NoForage(this);
	}

	@Override
	public void initBoard(final JFrame frame) {
		/* get the main panel and put in in the window that contains the game */
		JPanel display = getJPanel();
		frame.getContentPane().add(display, BorderLayout.CENTER);

		/* add a panel for the buttons and the label */
		buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		hatchEggButton = new JButton("hatch egg");
		buttonPanel.add(hatchEggButton);
		hatchEggButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scareBirds = false;
				runningthread = new Thread(new Runnable() {
					public void run() {
						flyBehavior.fly();
						System.out.println("Created a new thread");
						
					}
				});
				runningthread.start();
			}
		});

		scareBirdsButton = new JButton("scare birds");
		buttonPanel.add(scareBirdsButton);
		scareBirdsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scareBirds = true;
			}
		});

		noOfBirdsLabel = new JLabel();
		noOfBirdsLabel.setText("#birds: " + 0);
		buttonPanel.add(noOfBirdsLabel);

		/* when the user clicks on the close window button, delete all pieces */
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// used to invoke birds removal from the board
				scareBirds = true;
				if (runningthread != null) {
					clear();
					try {
						runningthread.join();
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
