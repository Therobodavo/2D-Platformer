package Blocks;

import Files.AbstractTile;

//Programmed by David Knolls
//Creates a basic block (abstract)
//used for all other blocks such as the invisible blocks and main blocks used

public abstract class AbstractBlock extends AbstractTile implements Block
{
	//Object Attributes
	protected boolean canMove = false;
	
	public AbstractBlock (float x, float y, float width, float height)
	{
		super(x,y,width,height);
	}
	public void drawTile()
	{
		if (hasinit == false)
		{
			setTexture("PNG", "Block.png");
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
}
