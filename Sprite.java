import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	private int initialXPosition;
	private int initialYPosition;
	private int xPosition;
	private int yPosition;
	private int xVelocity;
	private int yVelocity;
	private int width;
	private int height;
	private Color colour;
	
	// set and reset the initial X and Y position
	public void setInitialPosition(int initialXPosition, int initialYPosition) {
		this.initialXPosition = initialXPosition;
		this.initialYPosition = initialYPosition;
	}
	public void resetToInitialPosition() {
		this.xPosition = this.initialXPosition;
		this.yPosition = this.initialYPosition;
	}
	
	// get and set x, y -position
	public int getXPosition() {
		return xPosition;
	}
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}	 
	public void setXPosition(int newX, int panelWidth) {
		xPosition = newX;
		if (xPosition <= 0) {
	    	xPosition = 0;
		} else if(xPosition + width > panelWidth) {
			xPosition = panelWidth - width;
		}
	}
	public int getYPosition() {
		return yPosition;
	} 
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public void setYPosition(int newY, int panelWidth) {
	    this.yPosition = newY;
	    if (this.yPosition <= 0) {
	    	this.yPosition = 0;
		} else if(this.yPosition + height > panelWidth) {
			this.yPosition = panelWidth - height;
		}
	}
	
	// get and set x, y -velocity
	public int getXVelocity() {
		return this.xVelocity;
	}
	public void setXVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	public int getYVelocity() {
		return this.yVelocity;
	}
	public void setYVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	
	// get and set width / height
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	// Get set sprite color
	 public Color getColour() {
		 return this.colour;
     }
	 public void setColour(Color colour) {
		 this.colour = colour;
     }

	 // render the rectangle
	 public Rectangle getRectangle() {
         return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
     }
	 
	 
}
