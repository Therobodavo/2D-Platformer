package Effects;

import Weapons.Rifle;
import Creatures.Creature;

public class GunEffect extends AbstractEffect
{
	public GunEffect(Creature host)
	{
		super(host);
		STARTING_LENGTH = 3000;
	}
	public GunEffect(Creature host, long duration)
	{
		super(host);
		STARTING_LENGTH = duration;
	}
	public void giveEffect()
	{
		//do something
		Rifle wep = new Rifle(host.getLevel(),host, 200, 100);
		host.giveWeapon(wep);
		host.swapWeapon(wep);
	}
	public void removeEffect()
	{
		//undo it
		host.swapWeapon(0);
		host.getEffects().remove(this);
	}
}
