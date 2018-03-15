package Creatures;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import Drops.CoinDrop;
import Effects.Effect;
import Files.AbstractTile;
import Files.GameMain.Direction;
import Files.HealthBar;
import Levels.Level;
import Weapons.Projectile;
import Weapons.Weapon;

//Creature Class
//Programmed by David Knolls
//Creates an abstract Creature Object

public abstract class AbstractCreature extends AbstractTile implements Creature
{	
	//Object Attributes
	protected boolean canMove = false;
	protected float MAX_HEALTH = 100;
	protected boolean hitsides = false;
	protected float health = 100;
    protected Direction direction = Direction.WEST;
	protected int Jumps = 0;
	protected boolean isjumping = false;
	protected float g = -1.5f;
	protected float gVelo = 0;
	protected boolean isAlive = true;
	protected Weapon currentWeapon;
	protected HealthBar bar;
	protected float walkingSpeed = 10;
    private float coins = 0;
    protected float score = 0;
	public ArrayList<Weapon> armory = new ArrayList<Weapon>();
    protected long lastFrame = 0;
    protected int fps = 60;
    protected long lastFPS = 60;
    protected boolean invincible = false;
    protected boolean attackable = true;
    
    protected Level level;
    protected ArrayList<Effect> effects = new ArrayList<Effect>();
	
	public AbstractCreature(Level level, float x, float y, float width, float height)
	{
		super(x,y,width,height);
		this.level = level;
		bar = new HealthBar(this);
	}
	public float getScore()
	{
		return this.score;
	}
	public float getCoins()
	{
		return this.coins;
	}
    public void setScore(float newScore)
    {
    	this.score = newScore;
    }
    public void setCoins(float newCoins)
    {
    	this.coins = newCoins;
    }
    public float getSpeed()
    {
    	return walkingSpeed;
    }
    public void setSpeed(float speed)
    {
    	this.walkingSpeed = speed;
    }
    public void addSpeed(float speed)
    {
    	this.walkingSpeed += speed;
    }
    public void addScore(float score)
    {
    	score += score;
    }
    public void addCoins(float moreCoins)
    {
    	coins += moreCoins;
    }
	public float getHealth()
	{
		//returns the health of the creature
		return health;
	}
	public ArrayList<Effect> getEffects()
	{
		return effects;
	}
	public void updateEffects()
	{
		int count = 0;
		while(count < effects.size())
		{
			effects.get(count).update();
			count++;
		}
	}
	public float getMaxHealth()
	{
		return MAX_HEALTH;
	}
	public void setMaxHealth(float max)
	{
		this.MAX_HEALTH = max;
	}
	public void addMaxHealth(float health)
	{
		this.MAX_HEALTH += health;
	}
	public void addHealth(float health)
	{
		if (this.health + health > this.MAX_HEALTH)
		{
			this.health = this.MAX_HEALTH;
		}
		else
		{
			this.health += health;
		}
	}
	public void setHealth(float health)
	{
		//sets the health of the creature
		this.health = health;
	}
    public void takeDamage(float Damage)
    {
    	if(!invincible)
    	{
        	health -= Damage;
    	}
    }
    public void addEffect(Effect effect)
    {
    	effects.add(effect);
    }
    public void takeDamage(Projectile proj)
    {
    	if(!invincible)
    	{
    		takeDamage(proj.getDamage());
    		if(!(isLiving()))
    		{
    			onDeath(proj);
    		}
    	}
    }
	public void despawn()
	{
		level.getCreatures().remove(this);
	}
	public Direction getDirection()
	{
		return direction;
	}
	public void setDirection(Direction dir)
	{
		this.direction = dir;
	}
	public boolean isLiving()
	{
		if(this.getHealth() > 0)
		{
			isAlive = true;
		}
		else
		{
			isAlive = false;
		}
		return isAlive;
	}
	public void moveLeft(float length)
	{	
		if(this.level.isMoveable())
		{
			direction = Direction.WEST;
		//Safely moves left by the length provided if possible
			if(canMove(direction,length) == true)
			{
				this.x -= length;
			}
		}
	}
	public void moveRight(float length)
	{
		if(this.level.isMoveable())
		{
			direction = Direction.EAST;
			//Safely moves right by the length provided if possible
			if(canMove(direction,length) == true)
			{
				this.x += length;
			}
		}
	}
	//248f, 247f, 216f, 0.7f
	public void drawTile()
	{
		if (hasinit == false)
		{
			setTexture("PNG", "Enemy2.png");
			initVBO();
			hasinit = true;
		}
		updateEffects();
		updateVBO();
		bar.draw();
	}
	public void giveWeapon(Weapon other)
	{
		armory.add(other);
	}
	public Level getLevel()
	{
		return level;
	}
    public void swapWeapon(Weapon other)
    {
    	try
    	{
    		if(doesWeaponExist(other))
    		{
    			armory.add(currentWeapon);
    			currentWeapon = other;
    			armory.remove(other);
    		}
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    public void swapWeapon(int index)
    {
    	try
    	{
    		if(armory.size() >= index)
    		{
    			armory.add(currentWeapon);
    			currentWeapon = armory.get(index);
    			armory.remove(index);
    		}
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    public boolean doesWeaponExist(Weapon other)
    {
    	boolean isReal = false;
    	int count = 0;
    	try
    	{
    	while(count < armory.size())
    	{
    		if(other.equals(armory.get(count)))
    		{
    					isReal = true;
    		}
    		count++;
    		
    	}
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return isReal;
    }
	public Weapon getWeapon()
	{
		return this.currentWeapon;
	}
	public ArrayList<Weapon> getArmory()
	{
		return armory;
	}
	public void onDeath(Projectile proj)
	{
		dropCoins(5,50);
		despawn();
	}
	public void dropCoins(float amount, float chance)
	{
		int ran = (int)Math.random() * 100;
		if(ran < chance - 1)
		{
			level.getItems().add(new CoinDrop(level,(x + (width / 2)), y + height, 50, 50, amount)); //drops coin;
		}
	}
	public boolean canMove(Direction dir, float length)
	{
		//See if objects Can move, collision check
		int count = 0;
		boolean moveable = true;
		if(dir == Direction.NORTH)
		{
			//goes through every object on the screen and checks if it can move to where it wants to move before moving
			while (count < level.getCreatures().size())
			{
				if(level.getCreatures().get(count) != this)
				{
					Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX(),this.getY() + length, this.getWidth(), this.getHeight());
					Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getCreatures().get(count).getX(),level.getCreatures().get(count).getY(), level.getCreatures().get(count).getWidth(), level.getCreatures().get(count).getHeight());
			
					if(playerRect.intersects(otherrect))
					{
						moveable = false;
					}
				}
				count++;
			}
			count = 0;
			while (count < level.getBlocks().size())
			{
				Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX(),this.getY() + length, this.getWidth(), this.getHeight());
				Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getBlocks().get(count).getX(),level.getBlocks().get(count).getY(), level.getBlocks().get(count).getWidth(), level.getBlocks().get(count).getHeight());
			
				if(playerRect.intersects(otherrect))
				{
					moveable = false;
				}
				count++;
			}
		}
		else if(dir == Direction.SOUTH)
		{
			while (count < level.getCreatures().size())
			{
				if(level.getCreatures().get(count) != this)
				{
					Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX(),this.getY() - length, this.getWidth(), this.getHeight());
					Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getCreatures().get(count).getX(),level.getCreatures().get(count).getY(), level.getCreatures().get(count).getWidth(), level.getCreatures().get(count).getHeight());
					Jumps = 0;
					if(playerRect.intersects(otherrect))
					{
						moveable = false;
						Jumps = 0;
					}
				}
				count++;
			}
			count = 0;
			while (count < level.getBlocks().size())
			{
				Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX(),this.getY() - length, this.getWidth(), this.getHeight());
				Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getBlocks().get(count).getX(),level.getBlocks().get(count).getY(), level.getBlocks().get(count).getWidth(), level.getBlocks().get(count).getHeight());
				Jumps = 0;
				if(playerRect.intersects(otherrect))
				{
					moveable = false;
					Jumps = 0;
				}
				count++;
			}
		}
		else if(dir == Direction.WEST)
		{
			while (count < level.getCreatures().size())
			{
				if(level.getCreatures().get(count) != this)
				{
					Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX() - length,this.getY(), this.getWidth(), this.getHeight());
					Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getCreatures().get(count).getX(),level.getCreatures().get(count).getY(), level.getCreatures().get(count).getWidth(), level.getCreatures().get(count).getHeight());
			
					if(playerRect.intersects(otherrect))
					{
						moveable = false;
						hitsides = true;
						
					}
				}
				count++;
			}
			count = 0;
			while (count < level.getBlocks().size())
			{
				Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX() - length,this.getY(), this.getWidth(), this.getHeight());
				Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getBlocks().get(count).getX(),level.getBlocks().get(count).getY(), level.getBlocks().get(count).getWidth(), level.getBlocks().get(count).getHeight());
			
				if(playerRect.intersects(otherrect))
				{
					moveable = false;
					hitsides = true;	
				}
				count++;
			}
		}
		else if(dir == Direction.EAST)
		{
			while (count < level.getCreatures().size())
			{
				if(level.getCreatures().get(count) != this)
				{
					Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX() + length,this.getY(), this.getWidth(), this.getHeight());
					Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getCreatures().get(count).getX(),level.getCreatures().get(count).getY(), level.getCreatures().get(count).getWidth(), level.getCreatures().get(count).getHeight());
			
					if(playerRect.intersects(otherrect))
					{
						moveable = false;
						hitsides = true;
					}
				}
				count++;
			}
			count = 0;
			while (count < level.getBlocks().size())
			{
				Rectangle2D.Double playerRect = new Rectangle2D.Double(this.getX() + length,this.getY(), this.getWidth(), this.getHeight());
				Rectangle2D.Double otherrect = new Rectangle2D.Double(level.getBlocks().get(count).getX(),level.getBlocks().get(count).getY(), level.getBlocks().get(count).getWidth(), level.getBlocks().get(count).getHeight());
			
				if(playerRect.intersects(otherrect))
				{
					moveable = false;
					hitsides = true;
				}
				count++;
			}
		}
		
		//returns true if it can move to where it wants to, or else it returns false and doesn't move.
		return moveable;
	}
    public long getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
	public void setAttackable(boolean flag)
	{
		this.attackable = flag;
	}
	public boolean isAttackable()
	{
		return attackable;
	}
	public boolean isJumping()
	{
		return isjumping;
	}
	public void setInvincible(boolean flag)
	{
		this.invincible = flag;
	}
	public boolean isInvincible()
	{
		return invincible;
	}
    public void updateFPS()
    {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }
    public int getDelta()
    {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
  
        return delta;
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
}
