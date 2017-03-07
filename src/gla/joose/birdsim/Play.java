package gla.joose.birdsim;

import javax.swing.JFrame;
import flybehaviors.*;
import gla.joose.birdsim.boards.*;

/**
 * @author inah The main method for bootstrapping BirdSim.
 */
public class Play extends JFrame {

	private static final long serialVersionUID = 1L;

	private int selectedBoard;

	private int selectedBehavior;

	private boolean areSelected = false;

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

		StartupWindow sw = new StartupWindow(play);
		sw.setVisible(true);

		while (!play.isSelected()) {
		}

		/* choose the board type */
		Board generalBoard = chooseBoard(play.getSelectedBoard());

		/* choose the birds' behavior */
		chooseBehaviour(generalBoard, play.getSelectedBehavior());

		/* initialize the grid and start the simulation */
		generalBoard.initBoard(play);
	}

}
