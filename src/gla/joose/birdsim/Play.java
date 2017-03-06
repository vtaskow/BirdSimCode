package gla.joose.birdsim;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

import flybehaviors.DyingMultiplyingMovingForage;
import flybehaviors.DyingMultiplyingStaticForage;
import flybehaviors.DyingNoForage;
import flybehaviors.DyingStaticForage;
import flybehaviors.MovingForage;
import flybehaviors.MultiplyingMovingForage;
import flybehaviors.NoForage;
import flybehaviors.StaticForage;
import gla.joose.birdsim.boards.Board;
import gla.joose.birdsim.boards.FlockBoard;
import gla.joose.birdsim.boards.MovingForageBoard;
import gla.joose.birdsim.boards.StaticForageBoard;

/**
 * @author inah The main method for bootstrapping BirdSim.
 */
public class Play extends JFrame {
	public static void printBoardChoices(){
		System.out.println("Choose a board style:");
		System.out.println("\t 1) Flockboard");
		System.out.println("\t 2) StaticForageBoard");
		System.out.println("\t 3) MovingForageBoard");
	}
	
	public static void printBehaviourChoices(){
		System.out.println("Choose a bird behaviour:");
		System.out.println("\t 1) Moving Forage, Birds Die and Multiply");
		System.out.println("\t 2) Static Forage, Birds Die and Multiply");
		System.out.println("\t 3) No Forage, Birds Die");
		System.out.println("\t 4) Static Forage, Birds Die");
		System.out.println("\t 5) Moving Forage");
		System.out.println("\t 6) Moving Forage, Birds Multiply");
		System.out.println("\t 7) No Forage");
		System.out.println("\t 8) Static Forage");
	}
	
	public static void validateChoice(int limit, int choice){
		if (!(choice <= limit && choice > 0)) {
			throw new IllegalArgumentException("Invalid choice!");
		}
	} 
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		printBoardChoices();
		System.out.print("--->");
		int Choice = input.nextInt();
		validateChoice(3, Choice); // we have three kinds of boards that we choose from 
		
		
		/* create a window for the game */
		Play play = new Play();
		
		Board generalBoard = new FlockBoard(20, 20); // need to do this 'dummy board' as otherwise java cries about this not being initialised 
		// generalBoard may not have been initialized
		switch (Choice) {
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
		//generalBoard.initBoard(play);
		
		printBehaviourChoices();
		System.out.print("--->");
		Choice = input.nextInt();
		validateChoice(8, Choice); // we have eight kinds of behaviours that we choose from
		input.close();
		
		switch (Choice){
		case 1:
			generalBoard.setFlyBehavior(new DyingMultiplyingMovingForage(generalBoard));
			break;
		case 2:
			generalBoard.setFlyBehavior(new DyingMultiplyingStaticForage(generalBoard));
			break;
		case 3:
			generalBoard.setFlyBehavior(new DyingNoForage(generalBoard));
			break;
		case 4:
			generalBoard.setFlyBehavior(new DyingStaticForage(generalBoard));
			break;
		case 5:
			generalBoard.setFlyBehavior(new MovingForage(generalBoard));
			break;
		case 6:
			generalBoard.setFlyBehavior(new MultiplyingMovingForage(generalBoard));
			break;
		case 7:
			generalBoard.setFlyBehavior(new NoForage(generalBoard));
			break;
		case 8:
			generalBoard.setFlyBehavior(new StaticForage(generalBoard));
			break;
		}
		generalBoard.initBoard(play);
	}

}

