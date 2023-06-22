package animation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Paints the items on the pause screen
 * @param g the graphics context to draw on.
 */
public class Pause {
	static void paint(Graphics g) {
		
		//Font color and size of the "pause"
		Font gameOverFnt = new Font("arial", Font.BOLD, 20);
		g.setFont(gameOverFnt);
		g.setColor(Color.WHITE);
		g.drawString("PAUSE", 267, 290);
		
		//Font color and size of the instructions
		Font helpFnt = new Font("arial", Font.BOLD, 10);
		g.setFont(helpFnt);
		g.drawString("press  'P'  to resume", 248, 500);
		
		
	}
}