package Weapons;

import java.util.ArrayList;

public interface Weapon 
{
	public void attack();
	public void specialAttack();
	public void draw();
	public float getDamage();
	public void setDamage(float damage);
	public void addDamage(float damage);
	public ArrayList<Projectile> getProjectiles();
	public float getFireRate();
    public void setFireRate(float delay);
}
