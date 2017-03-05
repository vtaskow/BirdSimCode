package flybehaviors;

import java.awt.Color;

import gla.joose.birdsim.boards.Board;
import gla.joose.birdsim.pieces.*;
import gla.joose.birdsim.util.Distance;
import gla.joose.birdsim.util.DistanceMgr;

public class DyingMultiplyingMovingForage implements FlyBehavior {
	private Board board;
	
	public DyingMultiplyingMovingForage(Board board){
		this.board = board;
	}
	
	@Override
	public void fly() {

		Bird bird = new Bird(Color.BLACK);

		int randRow = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
		int randCol = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;
		board.place(bird, randRow, randCol);
		bird.setDraggable(false);
		bird.setSpeed(20);
		board.updateStockDisplay();

		Integer timer = new Integer(10);
		
		while (!board.areScaredBirds()) {

			DistanceMgr dmgr = new DistanceMgr();
			int current_row = bird.getRow();
			int current_col = bird.getColumn();

			synchronized (board.allPieces) {
				int grains = 0;
				for (int i = 0; i < board.getAllPieces().size(); i++) {
					Piece piece = board.getAllPieces().get(i);
					if (piece instanceof Grain) {
						grains++;
						int dist_from_food_row = current_row - piece.getRow();
						int dist_from_food_col = piece.getColumn() - current_col;
						Distance d = null;
						if (dist_from_food_row <= dist_from_food_col) {
							d = new Distance(bird, (Grain) piece, dist_from_food_row, dist_from_food_col);
						} else {
							d = new Distance(bird, (Grain) piece, dist_from_food_row, dist_from_food_col);
						}
						dmgr.addDistance(d);

					}
				}
				if (grains < 1) {
					timer--;
				}
			}
			if (timer < 0) {
				break;
			}
			////

			Distance distances[] = dmgr.getDistances();
			boolean movedone = false;

			for (int i = 0; i < distances.length; i++) {
				Distance d = distances[i];

				if (d.getRowDist() <= d.getColDist()) {

					if (d.getRowDist() > 0) {
						boolean can_move_down = bird.canMoveTo(current_row - 1, current_col);
						if (can_move_down) {
							bird.moveTo(current_row - 1, current_col);
							movedone = true;
							break;
						}
					} else if (d.getRowDist() < 0) {
						boolean can_move_down = bird.canMoveTo(current_row + 1, current_col);
						if (can_move_down) {
							bird.moveTo(current_row + 1, current_col);
							movedone = true;
							break;
						}
					} else if (d.getRowDist() == 0) {
						if (d.getColDist() > 0) {
							boolean can_move_right = bird.canMoveTo(current_row, current_col + 1);
							if (can_move_right) {
								bird.moveTo(current_row, current_col + 1);
								movedone = true;
								break;
							}
						} else if (d.getColDist() < 0) {
							boolean can_move_left = bird.canMoveTo(current_row, current_col - 1);
							if (can_move_left) {
								bird.moveTo(current_row, current_col - 1);
								movedone = true;
								break;
							}
						} else if (d.getColDist() == 0) {
							// bingo -food found (eat and move away)
							Grain grain = (Grain) d.getTargetpiece();
							grain.deplete();
							timer++;
							board.createThread();

							int randRowf = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
							int randColf = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;
							grain.moveTo(randRowf, randColf);
							grain.setSpeed(10);
							
							if (board.areStarvedBirds()) {
								grain.remove();
								board.updateStockDisplay();
							} else if (grain.getRemaining() <= 0) {
								grain.remove();
								board.updateStockDisplay();
							}
							int randRow1 = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
							int randCol2 = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;
							bird.moveTo(randRow1, randCol2);
							bird.setSpeed(20);
							movedone = true;
							break;

						}

					}
				}
				///////
				else if (d.getRowDist() > d.getColDist()) {

					if (d.getColDist() > 0) {
						boolean can_move_right = bird.canMoveTo(current_row, current_col + 1);
						if (can_move_right) {
							bird.moveTo(current_row, current_col + 1);
							movedone = true;
							break;
						}
					} else if (d.getColDist() < 0) {
						boolean can_move_left = bird.canMoveTo(current_row, current_col - 1);
						if (can_move_left) {
							bird.moveTo(current_row, current_col - 1);
							movedone = true;
							break;
						}
					} else if (d.getColDist() == 0) {
						if (d.getRowDist() > 0) {
							boolean can_move_up = bird.canMoveTo(current_row - 1, current_col);
							if (can_move_up) {
								bird.moveTo(current_row - 1, current_col);
								movedone = true;
								break;
							}

						} else if (d.getRowDist() < 0) {
							boolean can_move_down = bird.canMoveTo(current_row + 1, current_col);/// kkkk
							if (can_move_down) {
								bird.moveTo(current_row + 1, current_col);
								movedone = true;
								break;
							}
						} else if (d.getRowDist() == 0) {
							// bingo -food found (eat and move away)
							Grain grain = (Grain) d.getTargetpiece();
							grain.deplete();
							timer++;
							board.createThread();

							int randRowf = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
							int randColf = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;
							grain.moveTo(randRowf, randColf);
							grain.setSpeed(10);

							if (board.areStarvedBirds()) {
								grain.remove();
								board.updateStockDisplay();
							} else if (grain.getRemaining() <= 0) {
								grain.remove();
								board.updateStockDisplay();
							}
							
							int randRow1 = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
							int randCol2 = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;
							bird.moveTo(randRow1, randCol2);
							bird.setSpeed(20);
							movedone = true;
							break;

						}
					}
				}
			}
			if (!movedone) {
				int randRow1 = board.getRand().nextInt((board.getRows() - 3) + 1) + 0;
				int randCol2 = board.getRand().nextInt((board.getColumns() - 3) + 1) + 0;
				bird.moveTo(randRow1, randCol2);
			}

		}
		bird.remove();
		board.updateStockDisplay();

	}
}
