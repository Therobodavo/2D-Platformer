package Drops;

import Creatures.Creature;
import Effects.GunEffect;
import Effects.SpeedEffect;
import Effects.StrengthEffect;
import Levels.Level;

public class EffectDrop extends AbstractItemDrop
{
	
	protected String effect;
	public EffectDrop(Level level, String effect)
	{
		super(level);
		this.effect = effect.toLowerCase();
	}
	public EffectDrop(Level level, String effect, float x, float y)
	{
		super(level, x, y);
		this.effect = effect.toLowerCase();
	}
	public EffectDrop(Level level, String effect, float x, float y, float width, float height)
	{
		super(level, x, y, width, height);
		this.effect = effect.toLowerCase();
	}
	public void giveEffect(Creature collided)
	{
		if(effect.equalsIgnoreCase("Strength"))
		{
			collided.addEffect(new StrengthEffect(collided));
		}
		else if(effect.equalsIgnoreCase("Speed"))
		{
			collided.addEffect(new SpeedEffect(collided));
		}
		else if(effect.equalsIgnoreCase("Gun"))
		{
			collided.addEffect(new GunEffect(collided));
		}
	}
}
