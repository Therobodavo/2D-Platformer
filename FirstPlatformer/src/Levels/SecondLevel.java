package Levels;

import Blocks.StructureBlock;
import Creatures.FirstBoss;
import Creatures.Player;
import Creatures.StationaryEnemy;
import Drops.CoinDrop;
import Drops.EffectDrop;
import Drops.HealthDrop;
import Files.GameMain.Direction;

public class SecondLevel extends Level
{
	StructureBlock floor = new StructureBlock(0,0,10000,1);
	StructureBlock block1 = new StructureBlock(0,1,275, 120);
	StructureBlock block2 = new StructureBlock(0,121,43,759);
	StructureBlock block3 = new StructureBlock(43,121,87,343);
	StructureBlock block4 = new StructureBlock(130,121,145,242);
	StationaryEnemy enemy1 = new StationaryEnemy(this,800,1,120,120,Direction.WEST);
	StructureBlock block5 = new StructureBlock(1150,1,200,45);
	StructureBlock block6 = new StructureBlock(1300,66,200,45);
	StructureBlock block7 = new StructureBlock(1450,126,200,45);
	StructureBlock block8 = new StructureBlock(1850,126,200,45);
	StructureBlock block9 = new StructureBlock(2200,200,200,45);
	StructureBlock block10 = new StructureBlock(2390,275,200,45);
	StructureBlock block11 = new StructureBlock(2580,350,200,45);
	StructureBlock block12 = new StructureBlock(2930,350,200,45);
	StructureBlock block13 = new StructureBlock(3280,350,200,45);
	StructureBlock block14 = new StructureBlock(3630,350,200,45);
	StructureBlock block15 = new StructureBlock(3980,350,200,45);
	StationaryEnemy enemy2 = new StationaryEnemy(this,4000,395,75,75,Direction.WEST);
	StructureBlock block16 = new StructureBlock(4330,350,200,45);
	
	StationaryEnemy enemy3 = new StationaryEnemy(this,3000,1,75,75,Direction.WEST);
	StationaryEnemy enemy4 = new StationaryEnemy(this,3500,1,150,150,Direction.WEST);
	
	StructureBlock block17 = new StructureBlock(4330,1,200,175);
	StructureBlock block18 = new StructureBlock(4200,1,75,50);
	
	StructureBlock block19 = new StructureBlock(4730,1,1100,50);
	StructureBlock block20 = new StructureBlock(5030,51,800,100);
	StructureBlock block21 = new StructureBlock(5400,402,430,10);
	StructureBlock block22 = new StructureBlock(5820,151,10,251);
	StructureBlock block23 = new StructureBlock(5400,380,10,22);
	
	FirstBoss enemy5 = new FirstBoss(this,5400,151,150,150,150, Direction.WEST);
	
	@Override
	public void addObjects()
	{
			xStart = 0;
			xEnd = 5830;
			player = new Player(this, 350,1, 100, 100, Direction.EAST);
			creatureList.add(player);
			creatureList.add(enemy1);
			creatureList.add(enemy2);
			creatureList.add(enemy3);
			creatureList.add(enemy4);
			creatureList.add(enemy5);
			blockList.add(floor);
			blockList.add(block1);
			blockList.add(block2);
			blockList.add(block3);
			blockList.add(block4);
			blockList.add(block5);
			blockList.add(block6);
			blockList.add(block7);
			blockList.add(block8);
			blockList.add(block9);
			blockList.add(block10);
			blockList.add(block11);
			blockList.add(block12);
			blockList.add(block13);
			blockList.add(block14);
			blockList.add(block15);
			blockList.add(block16);
			blockList.add(block17);
			blockList.add(block18);
			blockList.add(block19);
			blockList.add(block20);
			blockList.add(block21);
			blockList.add(block22);
			blockList.add(block23);
			itemList.add(new EffectDrop(this,"Speed",475,1,50,50));
			itemList.add(new EffectDrop(this,"Speed",2650,395,50,50));
			itemList.add(new CoinDrop(this,4340,395,50,50));
			itemList.add(new CoinDrop(this,4370,395,50,50));
			itemList.add(new CoinDrop(this,4400,395,50,50));
			itemList.add(new CoinDrop(this,4430,395,50,50));
			itemList.add(new CoinDrop(this,4460,395,50,50));
			itemList.add(new HealthDrop(this,4000,1,50,50));
			itemList.add(new HealthDrop(this,4100,395,50,50));
			super.addObjects();
	}
}
