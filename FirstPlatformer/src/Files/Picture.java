package Files;

public class Picture extends AbstractTile
{
	protected String type;
	protected String dir;
	public Picture(float x, float y, float width, float height,String type, String dir)
	{
		super(x,y,width,height);
		this.type = type;
		this.dir = dir;
	}
	public void drawTile()
	{
		if (hasinit == false)
		{
			setTexture(type, dir);
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
}
