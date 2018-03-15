package Effects;

import Creatures.Creature;

public class StrengthEffect extends AbstractEffect
{
	protected float DAMAGE_INCREASE = 50;
	
	public StrengthEffect(Creature host)
	{
		super(host);
	}
	public StrengthEffect(Creature host, float damage, long duration)
	{
		super(host);
		DAMAGE_INCREASE = damage;
		STARTING_LENGTH = duration;
	}
	public void giveEffect()
	{
		//do something
		host.getWeapon().addDamage(50);
	}
	public void removeEffect()
	{
		//undo it
		host.getWeapon().addDamage(-50);
		host.getEffects().remove(this);
	}

}
