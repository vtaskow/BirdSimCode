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

		/*Board forageBoard = new MovingForageBoard(50, 50);
		forageBoard.setFlyBehavior(new NoForage(forageBoard));
		forageBoard.initBoard(play);*/
		
		Board forageBoard = new StaticForageBoard(50, 50);
		forageBoard.setFlyBehavior(new MovingForage(forageBoard));
		forageBoard.initBoard(play);
		
		/*Board simpleBoard = new FlockBoard(50, 50);
		simpleBoard.setFlyBehavior(new MovingForage(simpleBoard));
		simpleBoard.initBoard(play);*/

	}

}
