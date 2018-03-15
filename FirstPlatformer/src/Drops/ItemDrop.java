package Drops;

import Creatures.Creature;
import Files.GravityI;
import Files.Tile;

public interface ItemDrop extends Tile, GravityI
{
	public void collisionCheck();
	public void giveEffect(Creature collided);
}
