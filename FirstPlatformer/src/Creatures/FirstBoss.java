package Creatures;

import Drops.Level1KeyDrop;
import Files.GameMain.Direction;
import Levels.Level;
import Weapons.PlasmaWeapon;
import Weapons.Projectile;

public class FirstBoss extends AbstractCreature
{
	//Special Weapon (Bigger Projectiles)

	public FirstBoss(Level level, float x, float y, float width, float height, float health, Direction dir)
	{
		super(level,x,y,width,height);
		this.health = health;
		this.direction = dir;
		currentWeapon = new PlasmaWeapon(level, this, 2000, 50);
	}
	public void onDeath(Projectile proj)
	{
		level.getItems().add(new Level1KeyDrop(level,(x + (width / 2)), y + height, 75, 75));
		despawn();
	}
	public void drawTile()
	{
		currentWeapon.attack();
		currentWeapon.draw();
		super.drawTile();
	}

}
