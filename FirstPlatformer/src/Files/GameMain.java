package Files;

import java.io.InputStream;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import Levels.FirstLevel;
import Levels.SecondLevel;

//Programmed by David Knolls and Rain Hawkins
//Main Game Class
//Starts the game up, and lets the screens be changed
//Basic "Fullscreen" class used as a starting point for this class. - Credits to Ninja Cave
public class GameMain {
  
    long lastFrame;
    int fps;
    long lastFPS;
    Start main = new Start();
    StoryLevelSelector storylevels = new StoryLevelSelector();
    FirstLevel game = new FirstLevel();
    SecondLevel game2 = new SecondLevel();
    
    //Different screens avaliable
    public enum Screen
    {
    	START, STORY,STORYLEVEL1, STORYLEVEL2, HIGHSCORES
    }
	public enum Direction
	{
		NORTH,SOUTH,EAST,WEST
	}
    
    public static Screen screen = Screen.START;
    
    InputStream is;
    Texture texture;
  
    
    public void start() {
        
    	//Initial startup for the game
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
  
        initGL(); // init OpenGL
        

        lastFPS = getTime(); // call before loop to initialize fps timer
        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            update(delta);
            renderGL();
  
            Display.update();
            Display.sync(60); // cap fps to 60fps
            
        }
  
        Display.destroy();
    }
  
    public void update(int delta) {
    	//updates fps
        
        updateFPS(); // update FPS Counter
    }
  
     
    /** 
     * Calculate how many milliseconds have passed 
     * since last frame.
     * 
     * @return milliseconds passed since last frame 
     */
    public int getDelta() {
    	//Gets delta, but this isn't used for us
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
  
        return delta;
    }
  
    /**
     * Get the accurate system time
     * 
     * @return The system time in milliseconds
     */
    public long getTime() {
    	//returns current time
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
  
    /**
     * Calculate the FPS and set it in the title bar
     */
    public void updateFPS() {
    	//updates fps
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("Capstone Game");
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }
  
    public void initGL() {
    	//loads OpenGL settings
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glClearColor(0.4f, 0.4f, 0.0f, 0.0f);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    public void renderGL() {
    	//Allows screens to be switched
    	switch(screen)
    	{
    	case START:
    		main.loadScreen();
    		break;
    	case STORY:
    		storylevels.loadScreen();
    		break;
    	case STORYLEVEL1:
    		if(game.isDone())
    		{
    			game = new FirstLevel();
    		}
    		game.loadScreen();
    		break;
    	case STORYLEVEL2:
    		if(game2.isDone())
    		{
    			game2 = new SecondLevel();
    		}
    		game2.loadScreen();
    	}
    }
    public static void main(String[] argv) {
        GameMain fullscreenExample = new GameMain();
        fullscreenExample.start();
    }
}