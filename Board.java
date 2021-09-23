import java.awt.*;

public class Board {
	// grid line width
	// makes the grid gridier
	public static final int GRID_WIDTH = 10;
	// grid line half width
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	
	//2D array of ROWS-by-COLS Cell instances
	public Cell [][] cells;
	
	/** Constructor to create the game board */
	public Board() {
		
	 // initialize the cells array using ROWS and COLS constants 		
		this.cells = new Cell[GameMain.ROWS][GameMain.COLS];
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				this.cells[row][col] = new Cell(row, col);
			}
		}
	}

	/** Return true if it is a draw (i.e., no more EMPTY cells) */ 
	public boolean isDraw() {
		// Check whether the game has ended in a draw. 
		// Note: Assume we have already checked for a win first
		// Iterate each cell in cells
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				// if this cell is blank then return false and exit
				if (this.cells[row][col].content == Player.Empty) {
					return false;
				}
			}
		}
		// If we have iterated each cell and it is filled in then the game is a draw
		return true;
	}
	
	/** Return true if the current player "thePlayer" has won after making their move  */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		
		// check if player has 3-in-that-row
		if(this.cells[playerRow][0].content == thePlayer && this.cells[playerRow][1].content == thePlayer && this.cells[playerRow][2].content == thePlayer ) {
			return true; 
		}

		// check if player has 3-in-that-col 
		if(this.cells[0][playerCol].content == thePlayer && this.cells[1][playerCol].content == thePlayer && this.cells[2][playerCol].content == thePlayer ) {
			return true; 
		}
		
		// 3-in-the-diagonal
		if( this.cells[0][0].content == thePlayer && this.cells[1][1].content == thePlayer && this.cells[2][2].content == thePlayer) {
			return true;
		}
		 
		// 3-in-the-other-diagonal
		if( this.cells[0][2].content == thePlayer && this.cells[1][1].content == thePlayer && this.cells[2][0].content == thePlayer) {
			return true;
		}
		
		//no winner, keep playing
		return false;
	}
	
	/**
	 * Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		//draw the grid
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		for (int col = 1; col < GameMain.COLS; ++col) {          
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 0,                
					GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
					GRID_WIDTH, GRID_WIDTH);
		}
		
		//Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			for (int col = 0; col < GameMain.COLS; ++col) {  
				cells[row][col].paint(g);
			}
		}
	}
	

}
