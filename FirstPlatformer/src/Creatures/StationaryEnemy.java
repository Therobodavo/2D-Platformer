package Creatures;

import Files.GameMain.Direction;
import Levels.Level;
import Weapons.Rifle;

//Programmed by David Knolls
//Stationary Enemy Class
//Creates an enemy that doesn't move, but constantly shoots a direction

public class StationaryEnemy extends AbstractCreature
{
	
	public StationaryEnemy(Level level, float x, float y, float width, float height, Direction dir)
	{
		super(level, x, y, width, height);
		this.health = 100;
		this.score = 100;
		currentWeapon = new Rifle(level, this, 1000, 20);
		this.direction = dir;
	}
	public void drawTile()
	{
		currentWeapon.attack();
		currentWeapon.draw();
		super.drawTile();
	}
}
