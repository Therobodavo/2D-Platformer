package Effects;

import org.lwjgl.Sys;

import Creatures.Creature;

public abstract class AbstractEffect implements Effect
{
	protected long STARTING_LENGTH = 10000; //10 seconds
	protected long STARTING_TIME = 0;
	protected Creature host;
	protected boolean isActive = true;
	protected boolean hasStarted = false;
	
	public AbstractEffect(Creature host)
	{
		STARTING_TIME = getTime();
		this.host = host;
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
	public long getTimeLeft()
	{	
		return getTime() - STARTING_TIME;
	}
	public long getLength()
	{
		return STARTING_LENGTH;
	}
	public void update()
	{
		if(hasStarted == false)
		{
			this.giveEffect();
			hasStarted = true;
		}
		if(getTimeLeft() >= getLength())
		{
			this.removeEffect();
		}
	}
    public long getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
