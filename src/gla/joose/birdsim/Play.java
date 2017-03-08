package gla.joose.birdsim;

import javax.swing.JFrame;
import flybehaviors.*;
import gla.joose.birdsim.boards.*;

/**
 * @author inah The main method for bootstrapping BirdSim.
 */
public class Play extends JFrame {
	
	private static final long serialVersionUID = 8257593412667660991L;

	/* contain currently chosen board and behavior */
	private int selectedBoard;
	private int selectedBehavior;

	/* for checking if menu window has been closed and choices have been updated */
	/* volatile because a thread may not see the value being changed for a long time
	 * because of caching, thus, ensure the value is always synchronized between threads
	 * and all reads and writes of it will go to main memory */
	private volatile boolean areSelected = false;

	// Getters and setters
	
	public int getSelectedBoard() {
		return selectedBoard;
	}

	public int getSelectedBehavior() {
		return selectedBehavior;
	}

	public void setSelectedBoard(int selectedBoard) {
		this.selectedBoard = selectedBoard;
	}

	public void setSelectedBehavior(int selectedBehavior) {
		this.selectedBehavior = selectedBehavior;
	}

	public void setSelected(boolean value) {
		areSelected = value;
	}

	public boolean isSelected() {
		return areSelected;
	}

	/**
	 * Choose and initialize the board type from several options.
	 * @param choice
	 * @return Board instances
	 */
	public static Board chooseBoard(int choice) {
		Board generalBoard = null;
		switch (choice) {
		case 1:
			generalBoard = new FlockBoard(50, 50);
			break;
		case 2:
			generalBoard = new StaticForageBoard(50, 50);
			break;
		case 3:
			generalBoard = new MovingForageBoard(50, 50);
			break;
		}
		return generalBoard;
	}

	/**
	 * Choose and set the flying behavior to the Board instance from several options. 
	 * @param generalBoard
	 * @param choice
	 */
	public static void chooseBehaviour(Board generalBoard, int choice) {
		switch (choice) {
		case 1:
			generalBoard.setFlyBehavior(new NoForage(generalBoard));
			break;
		case 2:
			generalBoard.setFlyBehavior(new DyingNoForage(generalBoard));
			break;
		case 3:
			generalBoard.setFlyBehavior(new StaticForage(generalBoard));
			break;
		case 4:
			generalBoard.setFlyBehavior(new DyingStaticForage(generalBoard));
			break;
		case 5:
			generalBoard.setFlyBehavior(new DyingMultiplyingStaticForage(generalBoard));
			break;
		case 6:
			generalBoard.setFlyBehavior(new MovingForage(generalBoard));
			break;
		case 7:
			generalBoard.setFlyBehavior(new MultiplyingMovingForage(generalBoard));
			break;
		case 8:
			generalBoard.setFlyBehavior(new DyingMultiplyingMovingForage(generalBoard));
			break;
		}
	}

	public static void main(String[] args) {
		/* create a window for the game */
		Play play = new Play();

		/* start the menu for choosing a board and a behavior */
		StartupWindow sw = new StartupWindow(play);
		sw.setVisible(true);

		/* check continuously if the user has chosen the board and the 
		behavior - if so, launch simulation */
		// a better way to do this would be with using concurrent techniques such as countdownlatches but this is too advanced
		while (!play.isSelected()) {
			/* sleep the thread/while loop, so it does not consume so much CPU resources */
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/* choose the board type */
		Board generalBoard = chooseBoard(play.getSelectedBoard());

		/* choose the birds' behavior */
		chooseBehaviour(generalBoard, play.getSelectedBehavior());

		/* initialize the grid and start the simulation */
		generalBoard.initBoard(play);
	}

}
