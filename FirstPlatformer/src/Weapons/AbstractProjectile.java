package Weapons;

import Blocks.Block;
import Creatures.Creature;
import Files.GameMain.Direction;
import Levels.Level;

//Programmed by David Knolls
//Entity Object
//Creates a structure for an entity (bullet uses this)

public class AbstractProjectile extends AbstractEntity implements Projectile 
{
	//attributes
	protected float damage = 10;
	protected float speed = 5;
	protected Direction direction = Direction.WEST;
	protected Creature shooter;
	
	public AbstractProjectile(Level level, Creature sender)
	{
		super(level);
		this.shooter = sender;
	}
	public AbstractProjectile(Level level, Creature sender, float x, float y)
	{
		super(level, x, y);
		this.shooter = sender;
	}
	public AbstractProjectile(Level level, Creature sender, float x, float y, float width, float height)
	{
		super(level, x, y, width, height);
		this.shooter = sender;
	}
	public AbstractProjectile(Level level, Creature sender, float x, float y, float width, float height, Direction direction)
	{
		super(level, x, y, width, height);
		this.shooter = sender;
		this.direction = direction;
	}
	public AbstractProjectile(Level level, Creature sender, float x, float y, float width, float height, Direction direction, float damage)
	{
		super(level, x, y, width, height);
		this.shooter = sender;
		this.direction = direction;
		this.damage = damage;
	}
	public void despawn()
	{
		super.despawn();
		shooter.getWeapon().getProjectiles().remove(this);
	}
	public void update()
	{	
		//moves the bullets
		if(getDirection() == Direction.WEST)
		{
			x -= getSpeed();
		}
		else
		{
			x += getSpeed();
		}
	}
	public Direction getDirection()
	{
		return direction;
	}
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
	public float getSpeed()
	{
		return speed;
	}
	@Override
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
	@Override
	public void onCreatureCollision(Creature other)
	{
		if(!(other.equals(shooter)))
		{
			other.takeDamage(this);
			this.despawn();
		}
	}
	@Override
	public void onBlockCollision(Block other)
	{
		this.despawn();
	}
	@Override
	public Creature getShooter()
	{
		return shooter;
	}
	@Override
	public float getDamage()
	{
		return damage;
	}
	@Override
	public void setDamage(float newDamage)
	{
		this.damage = newDamage;
	}
	@Override
	public void addDamage(float damage)
	{
		this.damage += damage;
	}
	public void draw()
	{
		drawEntity();
	}
}
