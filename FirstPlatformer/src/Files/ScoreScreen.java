package Files;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.TextureImpl;
import Levels.Level;

public class ScoreScreen 
{
	protected AngelCodeFont font;
	protected boolean created = false;
	protected Level level;
	Picture rect;
	
	public ScoreScreen(Level level)
	{
		this.level = level;
	}
	public void init()
	{
		if(!created)
		{
			rect = new Picture(100,100,600,400,"JPEG", "BLUE.jpg");
			try
			{
				font = new AngelCodeFont("newfont.fnt", new Image("newfont_0.png"));
			}
			catch (SlickException e)
			{
				e.printStackTrace();
			}
			created = true;
		}
	}
	public void loadScreen()
	{	
		init();
		rect.draw();
		TextureImpl.unbind();
		font.drawString(100, 350, "score: " + level.getFinalScore(), Color.orange);
		if(level.isWinner())
			font.drawString(350, 450, "You Win!", Color.orange);
		else
			font.drawString(350, 450, "You Lose!", Color.orange);
	}
}
