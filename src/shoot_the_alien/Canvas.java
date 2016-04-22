package shoot_the_alien;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import shoot_the_alien.Stopwatch;



/**
 * Create a JPanel on which we draw the images and listen to the mouse events.
 * 
 * @author www.gametutorial.net
 * @author Luiz Eduardo da Costa
 * @category Game
 * @version 2.0
 */
public abstract class Canvas extends JPanel {
    
    
	
  	private static final long serialVersionUID = 1L;
    
  	/**
     * The stopwatch of the game.
     */
    private Stopwatch stopWatch = null;
    /**
     * An object Thread to start the chronometer of the game.
     */
    public static Thread th_stopwatch = null;
  	
  	
  	
    
    
    /**
     * Setup and create a new panel.
     */
    public Canvas()
    {
        // We use double buffer to draw on the screen.
        super.setDoubleBuffered(true);
        super.setFocusable(true);
        super.setBackground(Color.black);
        super.setLayout(null);
        
        
        BufferedImage blankCursorImg = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
        super.setCursor(blankCursor);

        this.stopWatch = new Stopwatch();
        this.stopWatch.setBounds((Window.frameWidth-220)/2, 10, 220, 50);
        super.add(stopWatch);
        th_stopwatch = new Thread(stopWatch);
        // The object th_stopwatch must to be started when the game state is GAME_CONTENT_LOADING.
        
        // Adds the keyboard listener to JPanel to receive key events from this component.
        this.addKeyListener(getKeyListener());

        
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(10, 10, 350, 40);
        progressBar.setString("UNIVÁS 90/100");
        progressBar.setStringPainted(true);
        progressBar.setMaximum(100);
        progressBar.setValue(90);
        progressBar.setBorderPainted(true);
        progressBar.setBackground(Color.white);
        progressBar.setForeground(Color.red);
        progressBar.setFont(new Font("Verdana", Font.BOLD, 20));
        
        JProgressBar progressBar2 = new JProgressBar();
        progressBar2.setBounds(Window.frameWidth-360, 10, 350, 40);
        progressBar2.setString("Tiros 70/100");
        progressBar2.setStringPainted(true);
        progressBar2.setMaximum(100);
        progressBar2.setValue(70);
        progressBar2.setBorderPainted(true);
        progressBar2.setBackground(Color.white);
        progressBar2.setForeground(Color.red);
        progressBar2.setFont(new Font("Verdana", Font.BOLD, 20));
        
        super.add(progressBar);
        super.add(progressBar2);
        
    }
    
    
    // This method is overridden in the class Framework.java and is used for drawing on the screen.
    public abstract void Draw(Graphics2D g2d);
    
    
    
    /**
     * The JPanel object is an invisible component until setup the method paintComponent(...). 
     * Override this method means that you want to use the graphic properties of the panel 
     * to draw something over it.
     * <p>
     * This is a native method of the JPanel class and it's useful to draw something over it.
     * 
     * How does paintComponent work? check it up:
     * http://stackoverflow.com/questions/5446396/concerns-about-the-function-of-jpanel-paintcomponent
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;        
        super.paintComponent(g2d); // Prepare the component to receive new paint.
        Draw(g2d);
    }
       
    
    
    
    /**
     * Sets up a listener of the keyboard.
     * 
     * @return A configured object KeyListener.
     */
    private KeyListener getKeyListener(){
    	return new KeyAdapter() {
    		
    		@Override
    	    public void keyPressed(KeyEvent e) 
    	    {
    	    	
    	        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
    	    		// ESC - Exit the game.
    	        	System.exit(0);
    	    		
    	    	}else if(e.getKeyCode() == KeyEvent.VK_SPACE ||
    	    			e.getKeyCode() == KeyEvent.VK_ENTER){
    	    		// SPACE or ENTER - Stop the game and return to main menu.
    	    		Framework.gameState = Framework.GameState.MAIN_MENU;
    	    	
    	    	}else if(e.getKeyCode() == KeyEvent.VK_R){
    	    		// R - Restart the game
    	    		Framework.gameState = Framework.GameState.RESTART;
    	    	}
    	    }
		};
    }
    
    
    
}
