package Files;

import org.newdawn.slick.opengl.Texture;

//Programmed by David Knolls
//Tile Interface
//Creates the layout for blocks/creatures

public interface Tile 
{
	public void setX(float x);
	public void setY(float y);
	public float getX();
	public float getY();
	public void setWidth(float width);
	public void setHeight(float height);
	public float getWidth();
	public float getHeight();
	public void draw();
	public void despawn();
	public void drawTile();
	public void setTexture(String type, String dir);
	public Texture getTexture();
	public void relocate(float x, float y);
	public void resize(float width, float height);
}
