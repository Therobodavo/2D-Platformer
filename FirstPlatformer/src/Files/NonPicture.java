package Files;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureImpl;

public class NonPicture extends AbstractTile
{
	public NonPicture(float x, float y, float width, float height)
	{
		super(x,y,width,height);
	}
	public void drawTile()
	{
		if (hasinit == false)
		{
			//setTexture("PNG", "Enemy.png");
			initVBO();
			hasinit = true;
		}
		GL11.glColor3f(1.0f, 0f, 0f);
		TextureImpl.unbind();
		GL11.glPushMatrix();
        	GL11.glBegin(GL11.GL_QUADS);
            	GL11.glVertex2f(x, y);
            	GL11.glVertex2f(x + width, y);
            	GL11.glVertex2f(x + width, y + height);
            	GL11.glVertex2f(x, y + height);
            GL11.glEnd();
        GL11.glPopMatrix();
	}
	
}
