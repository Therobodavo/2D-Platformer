package Drops;

import Creatures.Creature;
import Levels.Level;

public class HealthDrop extends AbstractItemDrop
{
	protected float HEAL_AMOUNT = 50;

	public HealthDrop(Level level, float x, float y, float width, float height)
	{
		super(level, x, y, width, height);
	}
	public HealthDrop(Level level, float x, float y, float width, float height, float coins)
	{
		super(level, x, y, width, height);
		this.HEAL_AMOUNT = coins;
	}
	public void giveEffect(Creature collided)
	{
		//do effect here
		collided.addHealth(HEAL_AMOUNT);
	}
	public void drawTile()
	{
		if (hasinit == false)
		{
			//change this to  the correct texture
			setTexture("PNG", "heart.png");
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
	
}
