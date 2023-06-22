package animation;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

/**
* Creates animated object Saucer, which spawns at a random 
* location on the borders of the window, moves in straight line, 
* destroys ship if hits it, shoots in the direction of the spaceship
* and is destroyed by asteroids.
* 
* @author Marwa Bouabid
*/
public class Saucer implements AnimatedObject {
    
	// The width of the saucer
    private static final int SAUCER_WIDTH = 50;
    
    // The height of the saucer
    private static final int SAUCER_HEIGHT = 28;
    
    //An array list that holds the coordinates of the asteroids
    private ArrayList<Integer> coordinates = this.randomCoordOnBorder();
    
    // x coordinate
    public double x = coordinates.get(0);
    
    // y coordinate
    public double y = coordinates.get(1);

    // The number of pixels to move on each frame of the animation.
    public static double moveAmount = 1.5;

    // The animation that this object is part of.
    private AbstractAnimation animation;
    
    // The shape of the asteroid
    private Ellipse2D saucer;
    
    // Did the saucer collide with another object
    public boolean collided = false; 
    
    // The angle for the movement
    private static double radians;

    /**
     * Creates the animated object
     * @param animation the animation this object is part of
     */
    public Saucer(AbstractAnimation animation) {
        this.animation = animation;
        saucer = new Ellipse2D.Double(x, y, SAUCER_WIDTH, SAUCER_HEIGHT);
        radians = randomMoveGenerator();
    }

    /**
     * Draws an asteroid at its current location.
     * @param g the graphics context to draw on.
     */
    public void paint(Graphics2D g) {
    	// Keeping space for the 'skin'
        String image_path = "saucer.PNG";
        BufferedImage bi = null;
        
        try {
        	// Importing the image for the saucer
			bi = ImageIO.read(new File(image_path));
		} catch (IOException e) {
			// Handling if the image is not found
			System.out.println("IMAGE NOT FOUND");
			e.printStackTrace();
		}
        
        // Drawing the skin on the object
        g.drawImage(bi, (int)x - 5, (int)y - 15, null);
        
    }
    
    /**
     * Moves already existing saucer bullets across the screen. 
     * Uses the saucers' bullet array.
     */
    public static void shoot() {
        for (int i = 0; i < Game.saucerBullets.size(); i++) {
            if (Game.saucerBullets.get(i) != null
                    && !Game.saucerBullets.get(i).collided) {
                Game.saucerBullets.get(i).setSpeed(Game.SAUCER_BULLET_SPEED);
                Game.saucerBullets.get(i).nextFrame();
            }
        }
    }
    
    /**
     * Check whether two objects collide. This tests whether their shapes
     * intersect.
     * 
     * @param asteroid: the first shape to test
     * @param saucer: 	the second shape to test
     * @return true, if the shapes intersect
     */
    public static boolean checkAsteroidSaucerCollision(Asteroid asteroid,
            Saucer saucer) {
        return !asteroid.collided && !saucer.collided && saucer.getShape()
                .intersects(asteroid.getShape().getBounds2D());
    }
    
    /**
     * Check whether two objects collide. This tests whether their shapes
     * intersect.
     * 
     * @param saucer:	the first shape to test
     * @param bullet:	the second shape to test
     * @return true, if the shapes intersect
     */
    public static boolean checkSaucersBulletCollision(Saucer saucer, Bullet bullet) {
        return !saucer.collided && !bullet.collided && (saucer.getShape().intersects(bullet.getShape().getBounds2D()));
    }
    
    /**
     * Randomly generates a number that will determinate
     * which direction the asteroid will move in
     * @return dir, a number that will determine the 
     * direction of the asteroid
     */
	public static double randomMoveGenerator() {
    	Random rand = new Random();
    	
    	while (true) {
    		double dir = rand.nextDouble()*(2 * Math.PI);
    		if (dir != 0.0 && dir != Math.PI/2 && dir != Math.PI 
    				&& dir != 3*Math.PI/2 && dir != 2*Math.PI) {
    			return dir;
    		}
    	}
    	
    }
    
    /**
     * Helper method that ensures that the asteroids spawn on random
     * sides of the screen
     * @return coord, a random coordinate on one of the borders
     */
    private ArrayList<Integer> randomCoordOnBorder() {
    	ArrayList<Integer> coord = new ArrayList<Integer>();
    	Random rand = new Random();
    	
    	//randomizes which border to start on (0 => Left, 1 => 
    	//Top, 2 => Right, 3 => Bottom)
    	int border = rand.nextInt(4);
    	switch (border) {
    	case 0: coord.add(0 - SAUCER_WIDTH/2);
    			coord.add(rand.nextInt(Game.WINDOW_HEIGHT) + SAUCER_HEIGHT/2);
    		break;
    	case 1: coord.add(rand.nextInt(Game.WINDOW_WIDTH) + SAUCER_WIDTH/2);
    			coord.add(0 - SAUCER_HEIGHT/2);
    		break;
    	case 2: coord.add(Game.WINDOW_WIDTH + SAUCER_WIDTH/2);
    			coord.add(rand.nextInt(Game.WINDOW_HEIGHT) + SAUCER_HEIGHT/2);
    		break;
    	case 3: coord.add(rand.nextInt(Game.WINDOW_WIDTH) + SAUCER_WIDTH/2);
    			coord.add(Game.WINDOW_HEIGHT + SAUCER_HEIGHT/2);
    		break;
    	}
    	
    	return coord;
    }
    
    
    /**
     * Moves the saucer a small amount. If it reaches the edges, it
     * wraps to the other side of the screen
     */
    public void nextFrame() {
        // Update the x value to move in the current direction
    	x += Math.sin(radians) * moveAmount;
        y += Math.cos(radians) * moveAmount;

        // Check if the right edge of the asteroid is beyond the right
        // edge of the window. If it is, wrap to other side
        if (x - SAUCER_WIDTH > animation.getWidth()) {
            x = 0 - SAUCER_WIDTH;
        }

        // Check if the left edge of the asteroid is beyond the left
        // edge of the window. If it is, wrap to other side
        else if (x + SAUCER_WIDTH < 0) {
            x = animation.getWidth() + SAUCER_WIDTH;
        }
        
        // check if bottom of asteroid goes beyond bottom
        //of window, if so, wrap to top
        if (y - SAUCER_HEIGHT > animation.getHeight()) {
        	y = 0 - SAUCER_HEIGHT;
        }
        
        // check if bottom of asteroid goes beyond top
        //of window, if so, wrap to bottom
        else if (y + SAUCER_HEIGHT < 0) {
        	y = animation.getHeight() + SAUCER_HEIGHT;
        }
        
        saucer.setFrame(x, y, SAUCER_WIDTH, SAUCER_HEIGHT);
    }
    
    /**
	 * Returns the saucer
	 * @return saucer, the saucer object
	 */
    public Shape getShape() {
        return saucer;
    }
    
    /**
   	 * FOR TESTING PURPOSES ONLY
   	 * Sets the direction (not random)
   	 */
       public static void setMoveAmount(double amount) {
       	moveAmount = amount;
       	
       }
    
	/**
	 * FOR TESTING PURPOSES ONLY
	 * Returns x
	 * @return double x coordinate of saucer
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY
	 * Changes x
	 */
	public void setX(int num) {
		 x = num;
	}

	/**
	 * FOR TESTING PURPOSES ONLY
	 * Returns y
	 * @return double y coordinate of saucer
	 */
	public double getY() {
		return y;
	}

	/**
	 * FOR TESTING PURPOSES ONLY
	 * Changes y
	 */
	public void setY(int num) {
		y = num;
	}
	
	

}