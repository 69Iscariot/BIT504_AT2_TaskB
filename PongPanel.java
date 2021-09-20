import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
 
public class PongPanel extends JPanel implements ActionListener, KeyListener{

	// Constants
	private final static Color BG_COLOR = Color.getHSBColor(100, 30, 80).darker().darker().darker().darker();
	private final static int TIMER_DELAY = 5;
	private final static int BALL_MOVEMENT_SPEED = 2;
	private final static int PADDLE_MOVEMENT_SPEED = 4;
	private final static int POINTS_TO_WIN = 11;
	
	// Sprite class objects
	private Ball ball;
	private Paddle paddle1;
	private Paddle paddle2;
	private int player1Score = 0;
	private int player2Score = 0;
	private Player gameWinner; 
	
	// game variables
	private GameState gameState = GameState.Initialising;
	private int fontSize = 50;
	private Font gameFont = new Font("Serif", Font.BOLD, fontSize);
	
	
	// Constructor
	public PongPanel() {
		
		// background color
		setBackground(BG_COLOR);
		
		// implement timer
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		
		// key listener
		addKeyListener(this);
		setFocusable(true);
		
	}
	
	// Event Listeners
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_W) {
            paddle1.setYVelocity(-PADDLE_MOVEMENT_SPEED);
        } else if(event.getKeyCode() == KeyEvent.VK_S) {
            paddle1.setYVelocity(PADDLE_MOVEMENT_SPEED);
        }
		if(event.getKeyCode() == KeyEvent.VK_UP) {
            paddle2.setYVelocity(-PADDLE_MOVEMENT_SPEED);
        } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
            paddle2.setYVelocity(PADDLE_MOVEMENT_SPEED);
        }
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// I split these up
		// if you are swapping between keys quickly e.g. up and down
		// you can lock the ball up = bad user experience
		// so only stop velocity if the ball is still moving in that direction
		
		// Player one
		if(event.getKeyCode() == KeyEvent.VK_W && paddle1.getYVelocity() < 0) {
            paddle1.setYVelocity(0);
        }
		if(event.getKeyCode() == KeyEvent.VK_S && paddle1.getYVelocity() > 0) {
            paddle1.setYVelocity(0);
        }
		
		// player two
		if(event.getKeyCode() == KeyEvent.VK_UP && paddle2.getYVelocity() < 0) {
            paddle2.setYVelocity(0);
        }
		if(event.getKeyCode() == KeyEvent.VK_DOWN && paddle2.getYVelocity() > 0) {
            paddle2.setYVelocity(0);
        }
		}
	 
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
	}

	 @Override
	 public void paintComponent(Graphics g) {
	     super.paintComponent(g);
	     paintDottedLine(g);
	     if(gameState != GameState.Initialising) {
	    	 this.paintSprite(g, ball);
	    	 this.paintSprite(g, paddle1);
	    	 this.paintSprite(g, paddle2);	
	    	 this.paintScores(g);
	    	 if (this.gameState == GameState.GameOver) {
	    		 this.paintWinner(g);
	    	 }
	     }
	 }

	 private void paintDottedLine(Graphics g) {
	      Graphics2D g2d = (Graphics2D) g.create();
	      Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	      g2d.setStroke(dashed);
	      g2d.setPaint(Color.WHITE);
	      g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	      g2d.dispose();
	 }

	 // create the sprite objects and update
	public void createObjects() {
		ball = new Ball(this.getWidth(), this.getHeight());
		this.ball.setXVelocity(BALL_MOVEMENT_SPEED);
   	 	this.ball.setYVelocity(BALL_MOVEMENT_SPEED);
		paddle1 = new Paddle(this.getWidth(), this.getHeight(), Player.One);
		paddle2 = new Paddle(this.getWidth(), this.getHeight(), Player.Two);
	}
	
	private void update() {
        switch(this.gameState) {
        	case Initialising: {
	            createObjects();
	            this.gameState = GameState.Playing;
	            break;
	        }
	        case Playing: {
	        	this.moveObject(paddle1);
	        	this.moveObject(paddle2);
	        	this.moveObject(ball);
	        	this.checkWallBounce();
	        	this.checkPaddleBounce();
	        	this.checkPaddleBoundary(paddle1);
	        	this.checkPaddleBoundary(paddle2);
	        	break;
	       }
	       case GameOver: {
	    	   break;
	       }
	   }
	}
	
	// paints the sprite to the screen
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
	}  
	
	// paint the scores to the surface
	private void paintScores(Graphics g) {
		int xPadding = 100;
		int yPadding = 100;
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(gameFont);
		g.drawString(leftScore, xPadding, yPadding);
		g.drawString(rightScore, getWidth()-xPadding, yPadding);
	}
	
	// paint the scores to the surface
	private void paintWinner(Graphics g) {
		int xPadding = 100;
		int yPadding = 250;
		// this lets us calculate width of winning message
		FontMetrics fm = g.getFontMetrics(gameFont);
		String leftScore = "You Win";
		String rightScore = "Win You"; // this is intentionally this way - its an in joke in allot of games
		g.setFont(gameFont);
		if (this.gameWinner == Player.One) {
			g.drawString(leftScore, xPadding, yPadding);
		}
		if (this.gameWinner == Player.Two) {
			g.drawString(rightScore, this.getWidth()-xPadding-fm.stringWidth(rightScore), yPadding);
	
		}
	}		
		
	// moves a sprite across the screen
	private void moveObject(Sprite sprite) {
		sprite.setXPosition(sprite.getXPosition()+sprite.getXVelocity());
		sprite.setYPosition(sprite.getYPosition()+sprite.getYVelocity());
	}
	
	// collision detection - ball hits boundary
	private void checkWallBounce() {
		// calculate each boundary
		// used half sprite to adjust the printing 
		int northBound = 0 - this.ball.getHeight() / 2;
		int eastBound  = this.getWidth() - this.ball.getWidth() / 2;
		int southBound = this.getHeight() - this.ball.getHeight() / 2;
		int westBound  = 0 - this.ball.getWidth() / 2;
		
		// check north boundary
		if (this.ball.getYPosition() <= northBound) {
			this.ball.setYPosition(northBound);
			this.ball.setYVelocity(BALL_MOVEMENT_SPEED);
		}
		
		// check east boundary
		if (this.ball.getXPosition() >= eastBound) {
			this.ball.setXVelocity(-BALL_MOVEMENT_SPEED);
			this.addScore(Player.One);
			this.resetBall();
		}
		
		// check south boundary
		if (this.ball.getYPosition() >= southBound) {
			this.ball.setYPosition(southBound);
			this.ball.setYVelocity(-BALL_MOVEMENT_SPEED);
		}
		
		// check west boundary
		if (this.ball.getXPosition() <= westBound) {
			this.ball.setXVelocity(BALL_MOVEMENT_SPEED);
			this.addScore(Player.Two);
			this.resetBall();
		}
				
	}
	
	// collision detection - Ball hits paddle
	private void checkPaddleBounce() {
		if(ball.getXVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setXVelocity(BALL_MOVEMENT_SPEED);
	    }
	    if(ball.getXVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
	        ball.setXVelocity(-BALL_MOVEMENT_SPEED);
	    }
	}
	
	// collision detection - Paddle hits boundary
	private void checkPaddleBoundary(Paddle paddle) {
		int northBound = 0 - paddle.getHeight() / 2;
		int southBound = this.getHeight() - paddle.getHeight() / 2;
		
		// what to do if paddle collides with North wall
		if (paddle.getYPosition() <= northBound) {
			paddle.setYPosition(northBound);
			paddle.setYVelocity(0);
		}
		
		// what to do if paddle collides with South wall
		if (paddle.getYPosition() >= southBound) {
			paddle.setYPosition(southBound);
			paddle.setYVelocity(0);
		}
	}
	
	// used for when a player increases the score
	private void addScore(Player player) {
		
		// Scorer is next in line to win
		this.gameWinner = player;
	 
		// increase score
		switch(player) {
		case One: {
			this.player1Score += 1; 
			break;
		}
		case Two: {
			this.player2Score += 1;
			break;
		}
		}
	 
		// Check if they won
		this.checkWin();
	}
	 
	// calculates if the current scoring player is the winner
	private void checkWin() {
		if (this.player1Score >= POINTS_TO_WIN || this.player2Score >= POINTS_TO_WIN) {
			this.gameState = GameState.GameOver;
		}
	}
	 
	 
	// reset the ball
	private void resetBall() {
		this.ball.resetToInitialPosition();
	}
	
}
