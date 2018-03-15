package Weapons;

import org.newdawn.slick.opengl.Texture;
import Blocks.Block;
import Creatures.Creature;

public interface Entity 
{
	public boolean isBlockColliding();
	public boolean isCreatureColliding();
    public void onCreatureCollision(Creature other);
    public void onBlockCollision(Block other);
	public boolean isDespawnable();
    public void despawn();
	public void draw();
	public void setX(float x);
	public void setY(float y);
    public void setWidth(float width);
    public void setHeight(float height);
	public float getX();
	public float getY();
    public float getWidth();
    public float getHeight();
	public void update();
	public long getTime();
	public long getStartTime();
	public void setStartTime(long time);
    public float getDespawnTime();
    public void setDespawnTime(float time);
	public void setTexture(String type, String dir);
	public Texture getTexture();
}
