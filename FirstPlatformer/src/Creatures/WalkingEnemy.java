package Creatures;

import Files.GameMain.Direction;
import Levels.Level;
import Weapons.Rifle;

//Programmed by David Knolls
//Walking Enemy
//creates and enemy that runs right and left

public class WalkingEnemy extends AbstractCreature
{
	Direction initdir = Direction.WEST;
	int walkingCounter = 0;
	
	public WalkingEnemy(Level level, float x, float y, float width, float height, Direction dir)
	{
		super(level, x, y, width, height);
		//Default constructor for Creature objects
		this.setHealth(100);
		this.setScore(75);
		currentWeapon = new Rifle(level, this, 750, 15);
		this.direction = dir;
	}
	public void drawTile()
	{
		//draws the enemy, bullets, and the running left and right is handeled here
		currentWeapon.attack();
		currentWeapon.draw();
		
		//running code
		if(initdir == Direction.WEST)
		{
			if(walkingCounter < 20)
			{
				moveLeft(10);
				walkingCounter++;
			}
			else if(walkingCounter >= 20)
			{
				direction = Direction.EAST;
				moveRight(10);
				walkingCounter++;
				if(walkingCounter == 40)
				{
					direction = Direction.WEST;
					walkingCounter = 0;
				}
			}
		}
		
		super.drawTile();
	}
}
