package flybehaviors;

import java.awt.Color;

import gla.joose.birdsim.boards.Board;
import gla.joose.birdsim.pieces.Bird;

public class DyingNoForage implements FlyBehavior {
	private Board board;

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

		Integer timer = new Integer(10);
		
		while (!board.areScaredBirds() && timer >= 0) {
			randRow = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
			randCol = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;
			bird.moveTo(randRow, randCol);
			bird.setSpeed(20);
			timer--;
		}
		bird.remove();
		board.updateStockDisplay();
	}

}
