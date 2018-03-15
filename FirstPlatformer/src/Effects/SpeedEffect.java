package Effects;

import Creatures.Creature;

public class SpeedEffect extends AbstractEffect
{
	protected float SPEED_INCREASE = 10;
	
	public SpeedEffect(Creature host)
	{
		super(host);
		STARTING_LENGTH = 3000;
	}
	public SpeedEffect(Creature host, float damage, long duration)
	{
		super(host);
		SPEED_INCREASE = damage;
		STARTING_LENGTH = duration;
	}
	public void giveEffect()
	{
		//do something
		host.addSpeed(SPEED_INCREASE);
	}
	public void removeEffect()
	{
		//undo it
		host.addSpeed(-SPEED_INCREASE);
		host.getEffects().remove(this);
	}
}
