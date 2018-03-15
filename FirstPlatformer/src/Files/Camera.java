package Files;

import org.lwjgl.opengl.GL11;

import Creatures.Player;
import Levels.Level;

public class Camera
{
	protected float startOffset = 0;
	protected float endOffset = 0;
	protected Level level;
	protected Player player;
	protected float mouseXOffset = 0;
	
	public Camera(Level level)
	{
		this.level = level;
		this.player = level.getPlayer();
	}
	public void update()
	{
		//Camera Stuff
		startOffset = ((level.getScreenWidth() / 2) - (player.getWidth() / 2));
		endOffset = (level.getEndX() - ((level.getScreenWidth() / 2) + (player.getWidth() / 2)));
		if(player.getX() <= startOffset)
		{
			GL11.glLoadIdentity();
			GL11.glTranslatef(-level.getStartX(), 0.0f, 0.0f);
			mouseXOffset = 0;
		}
		else if(player.getX() >= endOffset && player.getX() <= (level.getEndX() - player.getWidth()))
		{
			GL11.glLoadIdentity();
			GL11.glTranslatef(-endOffset + startOffset, 0.0f, 0.0f);
			mouseXOffset = level.getEndX() - level.getScreenWidth();
		}
		else
		{
			GL11.glLoadIdentity();
			GL11.glTranslatef(-player.getX() + startOffset, 0.0f, 0.0f);
			mouseXOffset = player.getX() - startOffset;
		}
	}
	public float getMouseXOffset()
	{
		return this.mouseXOffset;
	}
	
}
