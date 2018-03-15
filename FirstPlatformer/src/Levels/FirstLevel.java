package Levels;

import Blocks.InvsBlock;
import Blocks.StructureBlock;
import Creatures.FirstBoss;
import Creatures.HealthEnemy;
import Creatures.Player;
import Creatures.StationaryEnemy;
import Creatures.WalkingEnemy;
import Files.GameMain.Direction;

//Programmed by David Knolls
//First Level of the game
//Uses objects to create a level in the game, which shows the mechanics of the game


public class FirstLevel extends Level
{
	//All objects to be used in the level
	StructureBlock block = new StructureBlock(600,2,100,100);
	StructureBlock block2 = new StructureBlock(700,2,100,225);
	StructureBlock floor = new StructureBlock(0,1,5000,1);
	StructureBlock block3 = new StructureBlock(1200,250, 110,25);
	StationaryEnemy enemy = new StationaryEnemy(this,1205,275,75,75, Direction.WEST);
	StructureBlock block4 = new StructureBlock(1600,300,75,400);
	StructureBlock block5 = new StructureBlock(1750,2,80,120);
	StructureBlock block6 = new StructureBlock(1885,2,95,210);
	StructureBlock block7 = new StructureBlock(2700,2,1000,80);
	StructureBlock block8 = new StructureBlock(3000,2,1000,160);
	StructureBlock block9 = new StructureBlock(4000,2,500,300);
	InvsBlock barrierend = new InvsBlock(4501,2,10,1000);
	WalkingEnemy enemy2 = new WalkingEnemy(this,2500,2,100,100, Direction.WEST);
	StationaryEnemy enemy3 = new StationaryEnemy(this,3600,162,120,120, Direction.WEST);
	HealthEnemy enemy4 = new HealthEnemy(this,2600, 350, 50,50);
	FirstBoss enemy5 = new FirstBoss(this,4100,302,75,100,200, Direction.WEST);
	
	@Override
	public void addObjects()
	{
			player = new Player(this, 350,2, 100, 100, Direction.EAST);
			enemy2.getWeapon().setFireRate(3000);
			creatureList.add(player);
			blockList.add(floor);
			blockList.add(block);
			blockList.add(block2);
			blockList.add(block3);
			blockList.add(block4);
			blockList.add(block5);
			blockList.add(block6);
			blockList.add(block7);
			blockList.add(block8);
			blockList.add(block9);
			creatureList.add(enemy4);
			creatureList.add(enemy);
			creatureList.add(enemy2);
			creatureList.add(enemy3);
			creatureList.add(enemy5);
			blockList.add(barrierend);
			super.addObjects();
	}
}
