package gla.joose.birdsim.pieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A grain piece.
 */
public class Grain extends Piece {
	/* the remaining quantity of this grain piece */
	private float remaining = 1.0f;

	/**
	 * Constructs a <code>RoundPiece</code>.
	 **/
	public Grain() {
	}

	public void deplete() {
		remaining -= 0.05f;
	}

	public float getRemaining() {
		return remaining;
	}

	/**
	 * Draws this <code>RoundPiece</code> on the given <code>Graphics</code>.
	 * Drawing should be limited to the provided
	 * <code>java.awt.Rectangle</code>.
	 * 
	 * @param g
	 *            The graphics on which to draw.
	 * @param r
	 *            The rectangle in which to draw.
	 */
	public void paint(Graphics g, Rectangle r) {
		int color = Color.HSBtoRGB(1, remaining, 1);
		g.setColor(new Color(color));
		g.fillOval(r.x, r.y, r.width, r.height);
	}
}