package Creatures;

import Weapons.Projectile;
import Levels.Level;

//Programmed by David Knolls
//Health Enemy
//Does nothing, and if killed gives the player health

public class HealthEnemy extends AbstractCreature
{
	protected float HEALTH_GAIN = 100;
	
	public HealthEnemy(Level level, float x, float y, float width, float height)
	{
		super(level, x, y, width, height);
		this.health = 1;
	}

	public void onDeath(Projectile proj)
	{
		proj.getShooter().addScore(this.getScore());
		proj.getShooter().addHealth(HEALTH_GAIN);
		despawn();
	}
	public void drawTile()
	{
		if (hasinit == false)
		{
			setTexture("PNG", "Health.png");
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
}
