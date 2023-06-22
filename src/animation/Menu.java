package animation;

import java.awt.AlphaComposite;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	// The width of the window
	private static int WINDOW_WIDTH = 600;
	// The height of the window
	private static int WINDOW_HEIGHT = 600;
	// Padding around the rectangles
	private static int padding = 25;
	
	//Creating a rectangle for the instructions
	private static Rectangle instructions = new Rectangle (WINDOW_WIDTH/2 - 150, (WINDOW_HEIGHT/2 - 200), 300, 420);
	private static Rectangle rotateR = new Rectangle (WINDOW_WIDTH/2 - 25, (WINDOW_HEIGHT/2 - 25) - 160, 50, 40);
	private static Rectangle rotateL = new Rectangle (WINDOW_WIDTH/2 - 25, (WINDOW_HEIGHT/2 - 25) - 160 + 40 + padding, 50, 40);
	private static Rectangle up = new Rectangle (WINDOW_WIDTH/2 - 25, (WINDOW_HEIGHT/2 - 25) - 160 + 80 + padding*2, 50, 40);
	private static Rectangle hyperspace = new Rectangle (WINDOW_WIDTH/2 - 45, (WINDOW_HEIGHT/2 - 25) - 160 + 120 + padding*3, 90, 45);
	private static Rectangle space = new Rectangle (WINDOW_WIDTH/2 - 55, (WINDOW_HEIGHT/2 - 25) - 160 + 120 + 45 + padding*4, 110, 45);
	private static Rectangle esc = new Rectangle (WINDOW_WIDTH/2 - 45, (WINDOW_HEIGHT/2 - 25) - 160 + 120 + 90 + padding*5, 90, 45);
	
	
	/**
     * Paints the items on the menu
     * @param g the graphics context to draw on.
     */
	static void paint(Graphics g) {
		
		Graphics2D myGraphics = (Graphics2D) g;
		
		// Rectangles holding key instructions
		myGraphics.setColor(Color.WHITE);
		// Drawing the rectangles
		myGraphics.draw(instructions);
		// draw half transparent
		float alpha = 0.1f;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		myGraphics.setComposite(ac);
		myGraphics.fill(instructions);
		
		//draw half transparent
		alpha = 1f; 
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		myGraphics.setComposite(ac);
		
		// Drawing all the rectangles for the "icons" 
		myGraphics.draw(rotateR);
		myGraphics.draw(rotateL);
		myGraphics.draw(up);
		myGraphics.draw(hyperspace);
		myGraphics.draw(space);
		myGraphics.draw(esc);
		
		//Font color and size of the title
		Font titleFont = new Font("arial", Font.BOLD, 40);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		// Drawing the title
		g.drawString("ASTEROIDS GAME!", 110, 60);
		
		// Font color and size of the "play command"
		Font playFont = new Font("arial", Font.BOLD, 10);
		g.setFont(playFont);
		g.drawString("PRESS              TO PLAY", 240, 85);
		g.setColor(Color.GREEN);
		// Drawing the command
		g.drawString("        ENTER        ", 255, 85);
		
		g.setColor(Color.WHITE);
		Font instructionsFont = new Font("arial", Font.PLAIN, 30);
		Font instructionsFont2 = new Font("arial", Font.PLAIN, 18);
		g.setFont(instructionsFont);
		g.drawString(">", 292, 190-43);
		g.drawString("<", 290, 260-50);
		g.drawString("^", 293, 330-52);
		g.setFont(instructionsFont2);
		g.drawString("[ SHIFT ]", 265, 400-63);
		g.drawString("[ ____ ]", 270, 470-62);
		g.drawString("[ P ]", 285, 520-42);
		
		
		// Font color and size of the text
		Font textFont = new Font("arial", Font.PLAIN, 9);
		g.setFont(textFont);
		g.drawString("Right key to turn right", 254, (WINDOW_HEIGHT/2 - 25) - 160 + 45 + padding/2);
		g.drawString("Left key to turn left", 258, (WINDOW_HEIGHT/2 - 25) - 160 + 40 + 45 + padding + padding/2);
		g.drawString("Up key to go forward", 254, (WINDOW_HEIGHT/2 - 25) - 160 + 80 + 45 + padding*2 + padding/2);
		g.drawString("Shift key to hyperspace", 250, (WINDOW_HEIGHT/2 - 25) - 160 + 120 + 49 + padding*3 + padding/2);
		g.drawString("Spacebar to shoot ! ", 260, (WINDOW_HEIGHT/2 - 25) - 160 + 120 + 94 + padding*4 + padding/2);
		g.drawString("P to pause ", 280, (WINDOW_HEIGHT/2 - 25) - 160 + 120 + 94 + 45 + padding*5 + padding/2);
		
		// Font color and size of the "signature"
		Font creditsFont = new Font("arial", Font.PLAIN, 10);
		g.setFont(creditsFont);
		g.drawString("A game designed by : Rhitom Mishra, "
				+ "Marwa Bouabid and Amna Shahid Majeed", 105, 545);
		
	}
}