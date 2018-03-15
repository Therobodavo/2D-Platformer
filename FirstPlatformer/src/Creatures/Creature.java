package Creatures;

import java.util.ArrayList;

import Effects.Effect;
import Files.GameMain.Direction;
import Files.GravityI;
import Files.Tile;
import Levels.Level;
import Weapons.Projectile;
import Weapons.Weapon;

public interface Creature extends Tile, GravityI
{
	public float getScore();
	public float getCoins();
    public void setScore(float newScore);
    public void setCoins(float newCoins);
    public void addScore(float score);
    public void addCoins(float moreCoins);
	public float getHealth();
	public void addHealth(float health);
	public void setHealth(float health);
    public void takeDamage(float Damage);
    public void takeDamage(Projectile proj);
	public Direction getDirection();
	public void setDirection(Direction dir);
	public boolean isLiving();
	public void moveLeft(float length);
	public void moveRight(float length);
	public void giveWeapon(Weapon other);
    public void swapWeapon(Weapon other);
    public boolean doesWeaponExist(Weapon other);
    public ArrayList<Weapon> getArmory();
	public void draw();
	public Weapon getWeapon();
	public void onDeath(Projectile proj);
	public boolean canMove(Direction dir, float length);
    public long getTime();
    public float getMaxHealth();
    public void addMaxHealth(float health);
    public void setMaxHealth(float max);
    public ArrayList<Effect> getEffects();
    public void addEffect(Effect effect);
    public float getSpeed();
    public void setSpeed(float speed);
    public void addSpeed(float speed);
    public Level getLevel();
    public void swapWeapon(int index);
	public void setAttackable(boolean flag);
	public boolean isAttackable();
	public void setInvincible(boolean flag);
	public boolean isInvincible();
	public boolean isJumping();
}
