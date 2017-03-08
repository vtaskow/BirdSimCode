package flybehaviors;

import java.awt.Color;

import gla.joose.birdsim.boards.Board;
import gla.joose.birdsim.pieces.Bird;

/**
 * This class implements a specific bird behavior.
 * It needs to be associated with a Board instance so as to interact with it.
 * Birds need to be places on new rectangles etc.
 * 
 * Irrespective of the board configuration(even if there are grains), the birds
 * do not fly towards and eat from the grains(if any). They just keep flying randomly.
 * Also, birds die off if they are left with no grain for too long, which is always.
 */
public class DyingNoForage implements FlyBehavior {
	/* association with a board configuration */
	private Board board;

	/* constructor */
	public DyingNoForage(Board board){
		this.board = board;
	}
	
	/**
	 * Generic bird behaviour for any concrete board. This class is overridden
	 * when a different board behaviour is preferred
	 * 
	 */
	@Override
	public void fly() {

		Bird bird = new Bird(Color.orange);

		int randRow = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
		int randCol = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;

		board.place(bird, randRow, randCol);
		bird.setDraggable(false);
		bird.setSpeed(20);
		board.updateStockDisplay();

		/* set up a timer of 10 iterations = 10 bird movements and if becomes zero
		 * that means the birds were left with no grain to feed themselves from, so 
		 * they start to die off */
		Integer timer = new Integer(10);
		
		while (!board.areScaredBirds() && timer >= 0) {
			randRow = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
			randCol = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;
			bird.moveTo(randRow, randCol);
			bird.setSpeed(20);
			/* if there are no grains, timer gets decremented */
			timer--;
		}
		bird.remove();
		board.updateStockDisplay();
	}

}
