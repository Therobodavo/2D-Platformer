package Creatures;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import Files.GameMain.Direction;
import Files.HUD;
import Files.HealthBar;
import Levels.Level;
import Weapons.Projectile;
import Weapons.Rifle;

//Programmed by David Knolls and Rain Hawkins
//Creates an instance of a player
//Player can by default attack, jump, move left, and move right

public class Player extends AbstractCreature
{
	//Variables
	int texturecount = 0;
	HealthBar bar;
	protected HUD hud;
	
	public Player(Level level, float x, float y, float width, float height, Direction dir)
	{
		super(level, x, y, width, height);
		//Default constructor for Creature objects
		this.health = 350;
		MAX_HEALTH = health;
		bar = new HealthBar(this);
		hud = new HUD(level);
		currentWeapon = new Rifle(level, this);
		this.direction = dir;
	}
	public void onDeath(Projectile proj)
	{
		despawn();
		invincible = true;
		this.setAttackable(false);
		level.setMoveable(false);
		level.setFinalScore(this.getScore());
		level.setWinner(false);
		level.setFinished(true);
	}
	public void getInput()
	{
		if(this.level.isMoveable())
		{
			//checks the user input (keys pressed) and moves according
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			{
				moveLeft(walkingSpeed);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			{
				moveRight(walkingSpeed);
			}
		}
		
		//Checks if the camera has moved the player to get accurate mouse coordinates
		if(this.getX() > this.level.getCamera().getMouseXOffset())
		{
			if(Mouse.getX() + this.level.getCamera().getMouseXOffset() < this.getX() && Mouse.isButtonDown(0) && attackable)
			{
				this.direction = Direction.WEST;
				this.currentWeapon.attack();
			}
			else if(Mouse.getX() + this.level.getCamera().getMouseXOffset() > this.getX() && Mouse.isButtonDown(0) && attackable)
			{
				this.direction = Direction.EAST;
				this.currentWeapon.attack();
			}
		}
		else
		{
			if(Mouse.getX() < this.getX() && Mouse.isButtonDown(0) && attackable)
			{
				this.direction = Direction.WEST;
				this.currentWeapon.attack();
				System.out.println("My X: " + this.getX() + " Mouse X: " + Mouse.getX() + " Direction: " + direction);
			}
			else if(Mouse.getX() > this.getX() && Mouse.isButtonDown(0) && attackable)
			{
				this.direction = Direction.EAST;
				this.currentWeapon.attack();
				System.out.println("My X: " + this.getX() + " Mouse X: " + Mouse.getX() + " Direction: " + direction);
			}
		}
		if(attackable && Keyboard.isKeyDown(Keyboard.KEY_M))
		{
				this.currentWeapon.attack();
		}
		if(this.level.isMoveable() && Keyboard.isKeyDown(Keyboard.KEY_UP) && !canMove(Direction.SOUTH,1))
		{
			//user jumps, physics come into player and gravity.
			jump();
		}
		else if (!canMove(Direction.SOUTH,1))
		{
			isjumping = false;
		}
		//always checks if the player is jumping
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			level.gotoMain();
		}
	}
	public void jump()
	{
		isjumping = true;
		gVelo = 20;
	}
	public void moveLeft(float length)
	{	
		if(this.level.isMoveable())
		{
			direction = Direction.WEST;
		//Safely moves left by the length provided if possible
			if(canMove(direction,length) == true)
			{
				if(this.x - length >= level.getStartX())
				{
					this.x -= length;
				}
				else if((length - 1) > 0)
				{
					moveLeft(length - 1);
				}
			}
			else if((length - 1) > 0)
			{
				moveLeft(length - 1);
			}
		}
	}
	public void moveRight(float length)
	{	
		if(this.level.isMoveable())
		{
			direction = Direction.EAST;
			//Safely moves right by the length provided if possible
			if(canMove(direction,length) == true && this.level.isMoveable())
			{
				if(this.x + length <= (level.getEndX() - this.width))
				{
					this.x += length;
				}
				else if((length - 1) > 0)
				{
					moveRight(length - 1);
				}
			}
			else if((length - 1) > 0)
			{
				moveRight(length - 1);
			}
		}
	}
	public void despawn()
	{
		super.despawn();
	}
	public void drawTile()
	{
		
		if (hasinit == false)
		{
			setTexture("PNG", "Player3.png");
			initVBO();
			hasinit = true;
		}
		
		//Other updates/Rendering
		updateEffects();
		gravity();
		updateVBO();
		currentWeapon.draw();
		bar.draw();
	}
}
