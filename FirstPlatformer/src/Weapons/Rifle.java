package Weapons;

import Creatures.Creature;
import Levels.Level;

public class Rifle extends AbstractWeapon
{
	public Rifle(Level level, Creature shooter)
	{
		super(level, shooter);
	}
	public Rifle(Level level, Creature shooter, long delay, float damage)
	{
		super(level, shooter);
		this.fireRate = delay;
		this.damage = damage;
	}
}
