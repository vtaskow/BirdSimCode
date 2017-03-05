package gla.joose.birdsim;

import javax.swing.JFrame;

import flybehaviors.*;
import gla.joose.birdsim.boards.*;

/**
 * @author inah The main method for bootstrapping BirdSim.
 */
public class Play extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		/* create a window for the game */
		Play play = new Play();

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
		
		Board forageBoard = new StaticForageBoard(50, 50);
		forageBoard.setFlyBehavior(new DyingStaticForage(forageBoard));
		//forageBoard.setFlyBehavior(new DyingMultiplyingMovingForage(forageBoard));
		forageBoard.initBoard(play);
		
		//Board forageBoard = new MovingForageBoard(50, 50);
		//forageBoard.setFlyBehavior(new MultiplyingMovingForage(forageBoard));
		//forageBoard.initBoard(play);

	}

}
