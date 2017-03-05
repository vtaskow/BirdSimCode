package gla.joose.birdsim;

import javax.swing.JFrame;

import flybehaviors.NoForage;
import flybehaviors.StaticForage;
import flybehaviors.MovingForage;
import gla.joose.birdsim.boards.Board;
import gla.joose.birdsim.boards.FlockBoard;
import gla.joose.birdsim.boards.MovingForageBoard;
import gla.joose.birdsim.boards.StaticForageBoard;

/**
 * @author inah The main method for bootstrapping BirdSim.
 */
public class Play extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		/* create a window for the game */
		Play play = new Play();

		//Board forageBoard = new StaticForageBoard(50,50);
		//forageBoard.initBoard(play);
//		forageBoard.setFlyBehavior(new NoForage(forageBoard));
//		forageBoard.initBoard(play);
		Board forageBoard = new MovingForageBoard(50, 50);
		//forageBoard.setFlyBehavior(new NoForage(forageBoard));
		forageBoard.initBoard(play);
//		forageBoard.setFlyBehavior(new StaticForage(forageBoard));
		//Board simpleBoard = new FlockBoard(10, 20);
		//simpleBoard.initBoard(play);

	}

}
