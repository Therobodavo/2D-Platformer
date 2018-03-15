package Files;

import Files.GameMain.Direction;

public interface GravityI 
{
	public void gravity();
	public float getG();
	public float getGVelocity();
	public void setG(float g);
	public void setGVelocity(float g);
	public void moveUp(float length);
	public boolean canMove(Direction dir, float length);
}
