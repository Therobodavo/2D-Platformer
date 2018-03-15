package Weapons;

import java.util.ArrayList;

import org.lwjgl.Sys;

import Creatures.Creature;
import Files.GameMain.Direction;
import Levels.Level;

public class AbstractWeapon implements Weapon {

	protected float fireRate = 500;
	protected long lastTimeShot = 0;
	protected Creature shooter;
	protected float damage = 20;
	protected boolean timed;
	protected long timedLength = 10000;
	protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	protected Level level;
	
	public AbstractWeapon(Level level, Creature sender)
	{
		this.level = level;
		this.shooter = sender;
	}
	public void drawProjectiles()
	{
		//Draws every bullet that is existing and the player fired
		for(int count = 0;count < projectiles.size();count++)
		{
			projectiles.get(count).update();
			if(projectiles.get(count).isCreatureColliding() || projectiles.get(count).isBlockColliding())
			{
				//do nothing here
			}
			else
			{
				projectiles.get(count).draw();
			}
		}
	}
	public void fireBullet(float x, float y, float width, float height, Direction direction)
	{
		float newx = x - 6;
		float newy = y + (height / 2);
		Bullet bullet = null;
		if(direction == Direction.WEST) //player is facing left when firing bullet
		{
			bullet = new Bullet(level, shooter, newx, newy, 5f, 5f, damage, direction);
		}
		else //player is facing right when firing bullet
		{
			newx = x + (width + 1);
			bullet = new Bullet(level, shooter, newx, newy, 5f, 5f, damage, direction);
		}
		projectiles.add(bullet);
	}
	public void setFireRate(float delay)
	{
		this.fireRate = delay;
	}
    public void attack()
    {
    	try
    	{
		//fires a bullet depending on the direction the player is facing
		long time = getTime();
		if(time - lastTimeShot >= fireRate)
		{
			fireBullet(shooter.getX(), shooter.getY(), shooter.getWidth(), shooter.getHeight(), shooter.getDirection());
			lastTimeShot = time;
		}
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
	@Override
	public void draw() 
	{
		this.drawProjectiles();
	}
    public long getTime()
    {
    	//gets the current time of the game
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
	@Override
	public void specialAttack()
	{
		attack();
	}
	@Override
	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}
	@Override
	public float getFireRate()
	{
		return fireRate;
	}
	@Override
	public float getDamage()
	{
		return damage;
	}
	@Override
	public void setDamage(float damage)
	{
		this.damage = damage;
	}
	@Override
	public void addDamage(float damage)
	{
		this.damage += damage;
	}

}
