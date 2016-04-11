package shoot_the_alien;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Creates frame and set its properties.
 * 
 * @author www.gametutorial.net
 * @author Luiz Eduardo da Costa
 * @category Game
 * @version 2.0
 */
public class Window extends JFrame{
        
	
	
	private static final long serialVersionUID = 1L;

	/**
     * Width of the frame.
     */
    public static int frameWidth;
    /**
     * Height of the frame.
     */
    public static int frameHeight;
	/**
	 * Dimension of the user's screen.
	 */
    private Dimension screenDimension;
	
	
	

	/**
	 * Sets the properties of the frame.
	 */
	private Window()
    {
        // Sets the title for this frame. It will be visible when setUndecorated() is false.
        super.setTitle("UNIVADERS - Shoot The Alien");
        // Disables decorations for this frame.
        super.setUndecorated(true);
        // Get the dimension of the user's screen.
        this.screenDimension = Toolkit.getDefaultToolkit().getScreenSize();      
        // Puts the frame to full screen.
        super.setSize(screenDimension);
        // Set the Width and Height individual.
        Window.frameHeight = (int) screenDimension.getHeight();
        Window.frameWidth = (int) screenDimension.getWidth();
        // Exit the application when user close the frame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        // Add the game panel.
        this.setContentPane(new Framework());
        // Set visible of the frame.
        this.setVisible(true);
    }

    
    
	
	
    /**
     * Main method loads the start of the game.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // Use the event dispatch thread to build the UI for thread-safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
