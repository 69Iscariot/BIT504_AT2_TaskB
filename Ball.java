import java.awt.Color;

public class Ball extends Sprite {
	
	private static final Color BALL_COLOUR = Color.WHITE;
	private static final int BALL_WIDTH = 25;
	private static final int BALL_HEIGHT = 25;
	
	// constructor
	Ball (int panelWidth, int panelHeight) {
		
		// initiate variables
		this.setColour(BALL_COLOUR);
		this.setWidth(BALL_WIDTH);
		this.setHeight(BALL_HEIGHT);
		
		// calculate the balls starting location
		int initialBallX = (panelWidth  - this.getWidth() ) / 2;
		int initialBallY = (panelHeight - this.getHeight()) / 2;
		this.setInitialPosition(initialBallX, initialBallY);
		
		// reset the ball to its initial position
		this.resetToInitialPosition();
		
		
		
	}
	
	
}