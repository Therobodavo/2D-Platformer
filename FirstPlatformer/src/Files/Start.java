package Files;

import org.lwjgl.opengl.GL11;

import Files.GameMain.Screen;
import Levels.FirstLevel;

//Programmed by David Knolls and Rain Hawkins
//Starting Screen
//Creates a screen that displays 2 buttons, play and high scores

public class Start 
{
	//Creates buttons and instance of the game
	Button StoryButton = new Button(450,400,200,75);
	Button SurvivalButton = new Button(450,300,200,75);
	Button ShopButton = new Button(450,200,200,75);
	Button OptionsButton = new Button(450,100,200,75);
	
	FirstLevel game = new FirstLevel();
	public void loadScreen()
	{
		//loads the screen and draws the button
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		GL11.glTranslatef(0.0f, 0.0f, 0.0f);
		
        StoryButton.setTexture("StoryModeButton.png", "PNG");
        SurvivalButton.setTexture("SurvivalButton.png", "PNG");
        ShopButton.setTexture("ShopButton.png", "PNG");
        OptionsButton.setTexture("OptionsButton.png", "PNG");
        
        StoryButton.draw();
        SurvivalButton.draw();
        ShopButton.draw();
        OptionsButton.draw();
        
        //if Play button is clicked
        if (StoryButton.isClicked())
        {
        	//set current screen to game
        	GameMain.screen = Screen.STORY;
        //if HighScores button is clicked
        }
        if(SurvivalButton.isClicked())
        {
        	//GameMain.screen = Screen.GAME2;
        }
        if(ShopButton.isClicked())
        {
        	//GameMain.screen = Screen.;
        }
        if(OptionsButton.isClicked())
        {
        	
        }
		
	}
}
