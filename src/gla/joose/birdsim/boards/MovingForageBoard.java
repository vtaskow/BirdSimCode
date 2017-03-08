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

import gla.joose.birdsim.pieces.Grain;

/**
 * A BirdSim board with where birds simultaneously fly and perch on moving
 * grains.
 */
public class MovingForageBoard extends Board {
	/* panel for buttons */
	JPanel buttonPanel;
	
	/* buttons for hatching and feeding birds */
	JButton hatchEggButton;
	JButton feedBirdButton;
	
	/* button for scaring and starving birds */
	JButton scareBirdsButton;
	JButton starveBirdsButton;

	/* number of grains and birds */
	JLabel noOfGrainsLabel;
	JLabel noOfBirdsLabel;

	/* constructor - set board's dimensions */
	public MovingForageBoard(int rows, int columns) {
		super(rows, columns);
	}

	@Override
	public void initBoard(final JFrame frame) {
		JPanel display = getJPanel();
		frame.getContentPane().add(display, BorderLayout.CENTER);

		// Install button panel
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

		/* add a button for feeding the birds and attach an event listener to it 
		and create a new grain each time it is pressed */
		feedBirdButton = new JButton("feed birds");
		buttonPanel.add(feedBirdButton);
		feedBirdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				starveBirds = false;

				Grain grain = new Grain();
				int randRow = rand.nextInt((getRows() - 3) + 1) + 0;
				int randCol = rand.nextInt((getColumns() - 3) + 1) + 0;
				place(grain, randRow, randCol);
				grain.setDraggable(false);

				updateStockDisplay();
			}
		});

		/* add a button for starving the birds and attach an event listener to it */ 
		starveBirdsButton = new JButton("starve birds");
		buttonPanel.add(starveBirdsButton);
		starveBirdsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				starveBirds = true;

			}
		});

		/* add a button for scaring the birds and attach an event listener to it */
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

		/* show how many grains are on the screen */
		noOfGrainsLabel = new JLabel();
		noOfGrainsLabel.setText("#grains: " + 0);
		buttonPanel.add(noOfGrainsLabel);

		// Implement window close box
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
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
		noOfGrainsLabel.setText("#grains: " + noofgrains);
	}

}
