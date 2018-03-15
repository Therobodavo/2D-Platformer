package Creatures;

import Weapons.Bullet;
import Levels.Level;

//Programmed by David Knolls
//Winner Enemy, ends game
//Creates an enemy that does nothing, and when killed ends the game (Trigger)

public class WinnerEnemy extends AbstractCreature
{	
	public WinnerEnemy(Level level, float x, float y, float width, float height)
	{
		super(level, x, y, width, height);
		this.setScore(500);
		this.health = 1;
		this.MAX_HEALTH = 1;
	}
	public void onDeath(Bullet proj)
	{
		isAlive = false;
	}
	public void despawn()
	{
		isAlive = false;
	}
}
