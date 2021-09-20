import java.awt.Color;

public class Paddle extends Sprite {
	private static final int PADDLE_WIDTH = 10;
	private static final int PADDLE_HEIGHT = 100;
	private static final Color PADDLE_COLOR = Color.WHITE;
	private static final int DISTANCE_FROM_EDGE = 40;
	
	Paddle (int panelWidth, int panelHeight, Player player) {
		
		// initiate variables
		this.setColour(PADDLE_COLOR);
		this.setWidth(PADDLE_WIDTH);
		this.setHeight(PADDLE_HEIGHT);
		
		// set initial position for the player
		int initialBallX = DISTANCE_FROM_EDGE;
		if (player == Player.Two) {
			// note object 0,0 is the top left corner 
			// so include paddle width to make it even distance from edge
			initialBallX = panelWidth - DISTANCE_FROM_EDGE - PADDLE_WIDTH;
		}
		int initialBallY = (panelHeight - this.getHeight()) / 2;
		this.setInitialPosition(initialBallX, initialBallY);
		
		// reset the paddle to its initial position
		this.resetToInitialPosition();
		
	}
}
