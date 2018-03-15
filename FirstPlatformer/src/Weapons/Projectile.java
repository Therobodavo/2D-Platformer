package Weapons;

import Creatures.Creature;
import Files.GameMain.Direction;

public interface Projectile extends Entity
{
	public Direction getDirection();
    public void setDirection(Direction direction);
    public float getSpeed();
    public void setSpeed(float speed);
    public Creature getShooter();
    public float getDamage();
    public void setDamage(float newDamage);
    public void addDamage(float damage);
    
}
