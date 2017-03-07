package gla.joose.birdsim;

import java.util.Scanner;
import javax.swing.JFrame;
import flybehaviors.*;
import gla.joose.birdsim.boards.*;

/**
 * @author inah The main method for bootstrapping BirdSim.
 */
public class Play extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void printBoardChoices() {
		System.out.println("Choose a board style:");
		System.out.println("\t 1) Flockboard");
		System.out.println("\t 2) StaticForageBoard");
		System.out.println("\t 3) MovingForageBoard");
		System.out.print("---> ");
	}

	public static void printBehaviourChoices() {
		System.out.println("Choose a bird behaviour:");

		System.out.println("\t 1) No Forage");
		System.out.println("\t 2) No Forage, Birds Die");

		System.out.println("\t 3) Static Forage");
		System.out.println("\t 4) Static Forage, Birds Die");
		System.out.println("\t 5) Static Forage, Birds Die and Multiply");

		System.out.println("\t 6) Moving Forage");
		System.out.println("\t 7) Moving Forage, Birds Multiply");
		System.out.println("\t 8) Moving Forage, Birds Die and Multiply");
		System.out.print("---> ");
	}

	public static void validateChoice(int limit, int choice) {
		if (!(choice <= limit && choice > 0)) {
			throw new IllegalArgumentException("Invalid choice!");
		}
	}

	public static Board chooseBoard(int choice) {
		// provide options for choosing the board
		//printBoardChoices();
		//int choice = input.nextInt();
		// we have three kinds of boards that we choose from
		//validateChoice(3, choice);

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
		//printBehaviourChoices();
		//int choice = input.nextInt();
		// we have eight kinds of behaviors that we choose from
		//validateChoice(8, choice);

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

	private int selectedBoard;
	
	public void getSelectedBoardMenu(StartupWindow sw) {
		selectedBoard = sw.getSelectedBoard();
	}
	private int selectedBehavior;
	
	public void getSelectedBehaviorMenu(StartupWindow sw){
		selectedBehavior = sw.getSelectedBehavior();
	}

	public static void main(String[] args) {
		/* create a window for the game */
		Play play = new Play();
		
		StartupWindow sw = new StartupWindow(play);
		sw.setVisible(true);

		//Scanner input = new Scanner(System.in);
		/* choose the board type */
		Board generalBoard = chooseBoard(play.selectedBoard);
		/* choose the birds' behavior */
		chooseBehaviour(generalBoard, play.selectedBehavior);
		/* close scanner, it is not needed anymore */
		//input.close();

		/* initialize the grid and start the simulation */
		generalBoard.initBoard(play);
	}

}
