package Files;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import Files.GameMain.Screen;
import Levels.FirstLevel;

//Programmed by David Knolls and Rain Hawkins
//Starting Screen
//Creates a screen that displays 2 buttons, play and high scores

public class StoryLevelSelector
{
	//Creates buttons and instance of the game
	Button Level1 = new Button(300,375,200,75);
	Button Level2 = new Button(300,275,200,75);
	
	public void loadScreen()
	{
		//loads the screen and draws the button
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0f, 0.0f, 0.0f);
		
		//always checks if the player is jumping
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			GameMain.screen = Screen.START;
		}
        Level1.setTexture("Level1Button.png", "PNG");
        Level2.setTexture("Level2Button.png", "PNG");
        
        Level1.draw();
        Level2.draw();
        
        //if Play button is clicked
        if (Level1.isClicked())
        {
        	//set current screen to game
        	GameMain.screen = Screen.STORYLEVEL1;
        //if HighScores button is clicked
        }
        if(Level2.isClicked())
        {
        	GameMain.screen = Screen.STORYLEVEL2;
        }
	}
	
}
