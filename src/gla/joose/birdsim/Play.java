package gla.joose.birdsim;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

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
		System.out.println("\t 1) Birds die off when there is no grain, otherwise they multiply; Forage is moving");
		System.out.println("\t 1) Birds die off when there is no grain, otherwise they multiply; Forage is static");
		System.out.println("\t 1) Birds die off when there is no grain, otherwise they multiply; Forage is static");
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
		int boardChoice = input.nextInt();
		validateChoice(3, boardChoice); // we have three kinds of boards that we choose from 

		/* create a window for the game */
		Play play = new Play();
		
		Board generalBoard;
		// generalBoard may not have been initialized
		switch (boardChoice) {
		case 1:
			generalBoard = new FlockBoard(50, 50);
			generalBoard.initBoard(play);
			break;
		case 2:
			generalBoard = new StaticForageBoard(50, 50);
			generalBoard.initBoard(play);
			break;
		case 3:
			generalBoard = new MovingForageBoard(50, 50);
			generalBoard.initBoard(play);
			break;
		}
		
		/*Board forageBoard = new StaticForageBoard(50,50);
		forageBoard.initBoard(play);
		forageBoard.setFlyBehavior(new DyingNoForage(forageBoard));
		forageBoard.initBoard(play);*/
		
		/*Board forageBoard = new MovingForageBoard(50, 50);
		forageBoard.setFlyBehavior(new DyingNoForage(forageBoard));
		forageBoard.initBoard(play);
		forageBoard.setFlyBehavior(new StaticForage(forageBoard));*/
		
		/*Board simpleBoard = new FlockBoard(10, 20);
		simpleBoard.setFlyBehavior(new DyingMultiplyingMovingForage(simpleBoard));
		simpleBoard.initBoard(play);*/
		
		/*Board forageBoard = new StaticForageBoard(50, 50);
		forageBoard.setFlyBehavior(new DyingStaticForage(forageBoard));
		forageBoard.setFlyBehavior(new DyingMultiplyingMovingForage(forageBoard));
		forageBoard.initBoard(play);*/
		
		//Board forageBoard = new MovingForageBoard(50, 50);
		//forageBoard.setFlyBehavior(new MultiplyingMovingForage(forageBoard));
		//forageBoard.initBoard(play);

	}

}

