package Weapons;

import Creatures.Creature;
import Files.GameMain.Direction;
import Levels.Level;

//Programmed by David Knolls
//Bullet Class
//Creates a generic bullet based on the object shooting it

public class Bullet extends AbstractProjectile
{
	//Attributes
	
	public Bullet(Level level, Creature shooter)
	{
		super(level, shooter);
	}
	public Bullet(Level level, Creature shooter, float x, float y, float width, float height, float damage, Direction direction)
	{
		super(level, shooter,x,y,width,height,direction, damage);
	}
	
}
