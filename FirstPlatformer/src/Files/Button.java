package Files;

import java.awt.geom.Rectangle2D;

//Programmed by David Knolls and Rain Hawkins (David took Rians code and made it actually work)
//Creates a generic Button
//A button is created, with a default rectangle color, and a texture can be set. Does nothing by itself.

import java.io.IOException;

import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Button 
{
	//Default Constructor
	protected Texture texture;
	float x = 0;
	float y = 0;
	float width = 0;
	float height = 0;
	int clicks = 0;
	long lastClicked = 0;
	float clickDelay = 1000;
	
	public Button(float x, float y, float width, float height)
	{
		//Overloaded Constructor
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void setTexture(String picLocation, String type)
	{
		//sets the texture for the button
        try {
            // load texture from PNG file
            texture = TextureLoader.getTexture(type, ResourceLoader.getResourceAsStream(picLocation));
           } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void draw()
	{
		//draws the button based on the texture given. YOU MUST SET A TEXTURE!!!!!
		texture.bind();
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		//Learn vbos later
		GL11.glPushMatrix();
        	GL11.glBegin(GL11.GL_QUADS);
    		GL11.glTexCoord2f(0,1);
        	GL11.glVertex2f(x, y);
        	GL11.glTexCoord2f(1,1);
        	GL11.glVertex2f(x + width, y);
        	GL11.glTexCoord2f(1,0);
        	GL11.glVertex2f(x + width, y + height);
        	GL11.glTexCoord2f(0,0);
        	GL11.glVertex2f(x, y + height);
            GL11.glEnd();
        GL11.glPopMatrix();
	}
	public boolean isClicked()
	{
		//Checks if the user clicks the button, and returns true if the user does. Returns false if the user doesn't.
		
		Rectangle2D.Double button = new Rectangle2D.Double(x,y,width,height); //represents button
		Rectangle2D.Double point = new Rectangle2D.Double(Mouse.getX(),Mouse.getY(),1,1); // represents mouse point
		boolean clicked = false;
		if(point.intersects(button) && Mouse.isButtonDown(0) && clicks == 0 && (getTime() - lastClicked) > clickDelay)
		{
			lastClicked = getTime();
			clicked = true;
			clicks = 5;
		}
		else
		{
			clicks = 0;
			clicked = false;
		}
		return clicked;
	}
	public int getClicks()
	{
		return clicks;
	}
	public long getLastTimeClicked()
	{
		return lastClicked;
	}
    public long getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
