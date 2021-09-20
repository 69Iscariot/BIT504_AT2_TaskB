import javax.swing.JFrame;

public class Pong extends JFrame{
	
	// constants
	static final String WINDOW_TITLE = "BIT504 Assignment Two: Pong";
	static final int WINDOW_WIDTH = 800;
	static final int WINDOW_HEIGHT = 600;
	static final int POINTS_TO_WIN = 11;
	static final int PADDLE_LENGTH = 11;
	
	// constructor
	public Pong() {
        	
		setTitle(WINDOW_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);	
        setResizable(false);
        	
        add(new PongPanel());
        setVisible(true);
        	
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
 
	// entry point
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Pong();	
			}
		});
	}
 
}