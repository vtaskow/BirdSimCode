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

	/**
	 * Presents all board types, prompting the user to choose one
	 */
	public static void printBoardChoices() {
		System.out.println("Choose a board style:");
		System.out.println("\t 1) Flockboard");
		System.out.println("\t 2) StaticForageBoard");
		System.out.println("\t 3) MovingForageBoard");
		System.out.print("---> ");
	}

	/**
	 * Presents all flying behaviors, prompting the user to choose one
	 */
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

	/**
	 * Validates the user entered value. Takes a limit and a number 
	 * the user entered. Throws an exception if the number is invalid.
	 * 
	 * @param limit
	 * @param choice
	 */
	public static void validateChoice(int limit, int choice) {
		if (choice < 1 || choice > limit) {
			throw new IllegalArgumentException("Invalid choice!");
		}
	}

	/**
	 * Chooses the board from several board types and initializes a Board
	 * instance with the associated settings
	 * 
	 * @param input
	 * @return an instance of Board
	 */
	public static Board chooseBoard(Scanner input) {
		// print the options for choosing the board
		printBoardChoices();
		
		// prompt the user for number between 1 and 3, including
		int choice = input.nextInt();
		
		// we have 3 kinds of boards that we choose from - validate the choice
		validateChoice(3, choice); 

		// initialize the board settings
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
	 * Chooses the flying behavior from several options and sets the
	 * associated field in the given Board instance
	 * @param generalBoard
	 * @param input
	 */
	public static void chooseBehaviour(Board generalBoard, Scanner input) {
		// print the options for choosing the flying behavior
		printBehaviourChoices();
		
		// prompt the user for number between 1 and 8, including
		int choice = input.nextInt();
		
		// we have 8 kinds of flying behaviors that we choose from - validate the choice
		validateChoice(8, choice);

		// set the flying behavior to the board configuration
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
		Scanner input = new Scanner(System.in);
		
		/* choose the board type and set up the configuration */
		Board generalBoard = chooseBoard(input);
		
		/* choose the birds' behavior and set the board to contain it */
		chooseBehaviour(generalBoard, input);
		
		/* close scanner, it is not needed anymore*/
		input.close();
		
		/* create a window for the game */
		Play play = new Play();
		
		/* initialize the grid and start the simulation*/
		generalBoard.initBoard(play);
	}

}
