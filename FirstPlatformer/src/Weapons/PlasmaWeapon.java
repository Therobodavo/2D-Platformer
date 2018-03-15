package Weapons;

import Creatures.Creature;
import Files.GameMain.Direction;
import Levels.Level;

public class PlasmaWeapon extends AbstractWeapon
{

	public PlasmaWeapon(Level level, Creature sender, long delay, float damage)
	{
		super(level, sender);
		this.fireRate = delay;
		this.damage = damage;
	}
	public void fireBullet(float x, float y, float width, float height, Direction direction)
	{
		float newx = x - (1+(width / 2));
		float newy = y + (height / 2);
		PlasmaProjectile bullet = null;
		if(direction == Direction.WEST) //player is facing left when firing bullet
		{
			bullet = new PlasmaProjectile(level, shooter, newx, newy, width / 2, width / 2, direction, damage);
		}
		else //player is facing right when firing bullet
		{
			newx = x + (width + 1);
			bullet = new PlasmaProjectile(level, shooter, newx, newy, width / 2, width / 2, direction, damage);
		}
		projectiles.add(bullet);
	}

}
