package Drops;

import java.awt.geom.Rectangle2D;

import Creatures.Creature;
import Files.AbstractTile;
import Files.GameMain.Direction;
import Levels.Level;

public abstract class AbstractItemDrop extends AbstractTile implements ItemDrop
{
	protected long length = 10000; // 10 seconds
	protected float g = -1.5f;
	protected float gVelo = 0;
	protected Level level;
	
	public AbstractItemDrop(Level level)
	{
		this.level = level;
	}
	public AbstractItemDrop(Level level, float x, float y)
	{
		super(x,y);
		this.level = level;
	}
	public AbstractItemDrop(Level level, float x, float y, float width, float height)
	{
		super(x,y,width,height);
		this.level = level;
	}
	public void gravity()
	{
		moveUp(gVelo);
		if(!canMove(Direction.SOUTH,1))
		{
			gVelo = 0;
		}
		else
		{
			gVelo += g;
		}
	}
	public void moveUp(float length)
	{	
		boolean positive = false;
		if(length > 0)
			positive = true;
		else if(length < 0)
			positive = false;
		if(this.level.isMoveable())
		{
			if(canMove(Direction.NORTH,length) == true)
				this.y += length;
			else
			{
				if(positive == true)
					moveUp(length - 0.5f);
				else
					moveUp(length + 0.5f);
			}
		}
	}
	
	public float getG()
	{
		return g;
	}
	public float getGVelocity()
	{
		return gVelo;
	}
	public void setG(float g)
	{
		this.g = g;
	}
	public void setGVelocity(float g)
	{
		this.gVelo = g;
	}
	public boolean canMove(Direction dir, float length)
	{
		//See if objects Can move, collision check
		int count = 0;
		boolean moveable = true;
		if(dir == Direction.NORTH)
		{
			//goes through every block on the screen and checks if it can move to where it wants to move before moving
			count = 0;
			while (count < level.getBlocks().size())
			{
				Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX(),this.getY() + length, this.getWidth(), this.getHeight());
				Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getBlocks().get(count).getX(),level.getBlocks().get(count).getY(),level.getBlocks().get(count).getWidth(), level.getBlocks().get(count).getHeight());
			
				if(playerRect.intersects(otherrect))
				{
					moveable = false;
				}
				count++;
			}
		}
		else if(dir == Direction.SOUTH)
		{
			count = 0;
			while (count < level.getBlocks().size())
			{
				Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX(),this.getY() - length, this.getWidth(), this.getHeight());
				Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getBlocks().get(count).getX(),level.getBlocks().get(count).getY(), level.getBlocks().get(count).getWidth(), level.getBlocks().get(count).getHeight());
				
				if(playerRect.intersects(otherrect))
				{
					moveable = false;
				}
				count++;
			}
		}
		else if(dir == Direction.WEST)
		{
			count = 0;
			while (count < level.getBlocks().size())
			{
				Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX() - length,this.getY(), this.getWidth(), this.getHeight());
				Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getBlocks().get(count).getX(),level.getBlocks().get(count).getY(), level.getBlocks().get(count).getWidth(), level.getBlocks().get(count).getHeight());
			
				if(playerRect.intersects(otherrect))
				{
					moveable = false;	
				}
				count++;
			}
		}
		else if(dir == Direction.EAST)
		{
			count = 0;
			while (count < level.getBlocks().size())
			{
				Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX() + length,this.getY(), this.getWidth(), this.getHeight());
				Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getBlocks().get(count).getX(),level.getBlocks().get(count).getY(), level.getBlocks().get(count).getWidth(), level.getBlocks().get(count).getHeight());
			
				if(playerRect.intersects(otherrect))
				{
					moveable = false;
				}
				count++;
			}
		}
		
		//returns true if it can move to where it wants to, or else it returns false and doesn't move.
		return moveable;
	}
	public void collisionCheck()
	{
		int count = 0;
		while(count < level.getCreatures().size())
		{
			Rectangle2D.Double item = new Rectangle2D.Double(this.getX(),this.getY(), this.getWidth(), this.getHeight());
			Rectangle2D.Double other = new Rectangle2D.Double(level.getCreatures().get(count).getX(),level.getCreatures().get(count).getY(),level.getCreatures().get(count).getWidth(), level.getCreatures().get(count).getHeight());
			
			if(other.intersects(item))
			{
				giveEffect(level.getCreatures().get(count));
				despawn();
			}
			count++;
		}
	}
	public void giveEffect(Creature collided)
	{
		//do effect here
		
	}
	public void despawn()
	{
		level.getItems().remove(this);
	}
	public void draw()
	{
		this.gravity();
		this.collisionCheck();
		this.drawTile();
	}
	public void drawTile()
	{
		if (hasinit == false)
		{
			setTexture("PNG", "Enemy.png");
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
}
