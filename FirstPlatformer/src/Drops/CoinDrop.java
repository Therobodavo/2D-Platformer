package Drops;

import Creatures.Creature;
import Levels.Level;

public class CoinDrop extends AbstractItemDrop
{
	protected float COIN_AMOUNT = 50;

	public CoinDrop(Level level, float x, float y, float width, float height)
	{
		super(level, x, y, width, height);
	}
	public CoinDrop(Level level, float x, float y, float width, float height, float coins)
	{
		super(level, x, y, width, height);
		this.COIN_AMOUNT = coins;
	}
	public void giveEffect(Creature collided)
	{
		//do effect here
		collided.addCoins(COIN_AMOUNT);
	}
	public void drawTile()
	{
		if (hasinit == false)
		{
			//change this to  the correct texture
			setTexture("PNG", "Coin.png");
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
	
}
