package Levels;

import java.util.ArrayList;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import Blocks.Block;
import Creatures.Creature;
import Creatures.Player;
import Drops.ItemDrop;
import Files.Camera;
import Files.GameMain;
import Files.HUD;
import Files.ScoreScreen;
import Files.Tile;
import Files.GameMain.Screen;

public class Level 
{
	//Creates each layer in which is drawn in a given level
	protected ArrayList<Tile> backgroundList = new ArrayList<Tile>();
	protected ArrayList<Tile> backsceneList = new ArrayList<Tile>();
	protected ArrayList<ItemDrop> itemList = new ArrayList<ItemDrop>();
	protected ArrayList<Creature> creatureList = new ArrayList<Creature>();
	protected ArrayList<Block> blockList = new ArrayList<Block>();
	protected ArrayList<Tile> frontsceneList = new ArrayList<Tile>();
	protected boolean isAdded = false;
	protected boolean winner = false;
	protected boolean finished = false;
	protected float finalscore = 0;
	protected final float SCREEN_WIDTH = 800;
	protected final float SCREEN_HEIGHT = 600;
	protected float xStart = 0;
	protected float xEnd = 4500;
	protected Player player = null;
	protected boolean needsReset = false;
	protected long timeStarted = 0;
	protected HUD hud;
	protected Camera camera;
	protected ScoreScreen score;
	protected long TimeFinished;
	protected boolean moveable = true;
	protected boolean initFinished = false;
	
	public void loadScreen()
	{
		if(isAdded == false)
		{
			addObjects();
			timeStarted = getTime();
			isAdded = true;
			hud = new HUD(this);
			camera = new Camera(this);
			score = new ScoreScreen(this);
		}
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		player.getInput();
		camera.update();
		renderBackground();
		renderBackscene();
		renderBlocks();
		renderItems();
		renderCreatures();
		renderFrontscene();
		hud.draw();
		if(finished)
		{
			if(!initFinished)
			{
				TimeFinished = getTime();
				initFinished = true;
			}
			moveable = false;
			score.loadScreen();
			if(getTime() - TimeFinished >= 10000)
			{
				gotoMain();
			}
			
		}
		
	}
	public Player getPlayer()
	{
		return player;
	}
	public boolean isMoveable()
	{
		return moveable;
	}
    public void setMoveable(boolean moveable)
    {
    	this.moveable = moveable;
    }
	public float getScreenWidth()
	{
		return SCREEN_WIDTH;
	}
	public void gotoMain()
	{
		needsReset = true;
		GameMain.screen = Screen.START;
	}
	public boolean isDone()
	{
		return needsReset;
	}
	public void setFinished(boolean flag)
	{
		this.finished = flag;
	}
	public long getTimeFinished()
	{
		return TimeFinished;
	}
	public boolean isFinished()
	{
		return finished;
	}
	public void needsReset()
	{
		needsReset = true;
	}
	public float getScreenHeight()
	{
		return SCREEN_HEIGHT;
	}
	public float getStartX()
	{
		return xStart;
	}
	public float getEndX()
	{
		return xEnd;
	}
	public void setStartX(float x)
	{
		this.xStart = x;
	}
	public void setEndX(float x)
	{
		this.xEnd = x;
	}
	public void setFinalScore(float score)
	{
		finalscore = score;
	}
	public float getFinalScore()
	{
		return finalscore;
	}
	public boolean isWinner()
	{
		return winner;
	}
	public void setWinner(boolean flag)
	{
		winner = flag;
	}
	public ArrayList<Tile> getBackground()
	{
		return backgroundList;
	}
	public ArrayList<Tile> getBackscene()
	{
		return backsceneList;
	}
	public ArrayList<ItemDrop> getItems()
	{
		return itemList;
	}
	public ArrayList<Creature> getCreatures()
	{
		return creatureList;
	}
	public ArrayList<Block> getBlocks()
	{
		return blockList;
	}
	public ArrayList<Tile> getFrontscene()
	{
		return frontsceneList;
	}
	public void addObjects()
	{
		//add objects to lists here
		timeStarted = getTime();
	}
	public void renderBackground()
	{
		int count = 0;
		while(count < backgroundList.size())
		{
			backgroundList.get(count).draw();
			count++;
		}
	}
	public void renderBackscene()
	{
		int count = 0;
		while(count < backsceneList.size())
		{
			backsceneList.get(count).draw();
			count++;
		}
	}
	public void renderItems()
	{
		int count = 0;
		while(count < itemList.size())
		{
			itemList.get(count).draw();
			count++;
		}
	}
	public void renderCreatures()
	{
		int count = 0;
		while(count < creatureList.size())
		{
			creatureList.get(count).draw();
			count++;
		}
	}
	public void renderBlocks()
	{
		int count = 0;
		while(count < blockList.size())
		{
			blockList.get(count).draw();
			count++;
		}
	}
	public void renderFrontscene()
	{
		int count = 0;
		while(count < frontsceneList.size())
		{
			frontsceneList.get(count).draw();
			count++;
		}
	}
	public void clear()
	{
		backgroundList.clear();
		backsceneList.clear();
		itemList.clear();
		creatureList.clear();
		blockList.clear();
		frontsceneList.clear();
	}
    public long getTime()
    {
    	//returns current time
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    public Camera getCamera()
    {
    	return this.camera;
    }
	
}
