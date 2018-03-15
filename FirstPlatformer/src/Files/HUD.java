package Files;

import org.lwjgl.opengl.GL11;

import Levels.Level;

public class HUD
{
	protected Level level;
	Picture pic = new Picture(0, 584, 16, 16, "PNG", "heart.png");
	
	public HUD(Level level)
	{
		this.level = level;
	}
	public void draw()
	{
		GL11.glLoadIdentity();
		pic.draw();
	}
	public void update()
	{
		//something
	}
}
