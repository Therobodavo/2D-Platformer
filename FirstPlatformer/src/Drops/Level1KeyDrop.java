package Drops;

import java.awt.geom.Rectangle2D;
import Creatures.Creature;
import Creatures.Player;
import Levels.Level;

public class Level1KeyDrop extends AbstractItemDrop
{

	public Level1KeyDrop(Level level)
	{
		super(level);
	}
	public Level1KeyDrop(Level level, float x, float y)
	{
		super(level,x,y);
	}
	public Level1KeyDrop(Level level, float x, float y, float width, float height)
	{
		super(level,x,y,width,height);
	}
	public void giveEffect(Creature collided)
	{
		//do effect here
		collided.setInvincible(true);
		this.level.setMoveable(false);
		collided.setAttackable(false);
		level.setFinalScore(collided.getCoins());
		level.setWinner(true);
		level.setFinished(true);
	}
	public void collisionCheck()
	{
		int count = 0;
		while(count < level.getCreatures().size())
		{
			Rectangle2D.Double item = new Rectangle2D.Double(this.getX(),this.getY(), this.getWidth(), this.getHeight());
			Rectangle2D.Double other = new Rectangle2D.Double(level.getCreatures().get(count).getX(),level.getCreatures().get(count).getY(),level.getCreatures().get(count).getWidth(), level.getCreatures().get(count).getHeight());
			
			if(other.intersects(item) && (level.getCreatures().get(count).getClass().equals(Player.class)))
			{
				if(level.getCreatures().get(count).getClass().equals(Player.class))
				{
					giveEffect(level.getCreatures().get(count));
					despawn();
				}
			}
			count++;
		}
	}

}
