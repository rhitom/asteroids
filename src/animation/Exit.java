package animation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Exit {
	
	/**
     * Paints all components of the exit screen
     * 
     * @param g the graphic context to draw on
     */
	static void paint(Graphics g) {
		
		//Font color and size of the "Game over"
		Font gameOverFnt = new Font("arial", Font.BOLD, 20);
		//Font color and size of the play again instruction
		Font AgainFnt = new Font("arial", Font.BOLD, 10);
		// Setting the font for the game over
		g.setFont(gameOverFnt);
		// Setting the color
		g.setColor(Color.WHITE);
		// Drawing the game over
		g.drawString("GAME OVER", 250, 300);
		// Setting the font for the play again
		g.setFont(AgainFnt);
		// Drawing the play again
		g.drawString("PRESS ENTER TO GO BACK TO MAIN MENU", 205, 330);
		
	}
}