package Weapons;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import Blocks.Block;
import Creatures.Creature;
import Levels.Level;


public class AbstractEntity implements Entity
{
	protected float x = 0;
	protected float y = 0;
	protected float width = 0;
	protected float height = 0;
	protected Texture texture;
    protected boolean hasinit = false;
    int vboVertexHandle;
    int vboColorHandle;
    int vboTexCoordHandle;
    FloatBuffer vertexData;
    FloatBuffer colorData;
    FloatBuffer textureData;
	final int amountOfVertices = 4;
    final int vertexSize = 2;
	protected long timeCreated = 0;
	protected float despawnTime = 15000;
	protected boolean despawnable = false;
	
	protected Level level;
	
	public AbstractEntity(Level level)
	{
		this.level = level;
		timeCreated = getTime();
	}
	public AbstractEntity(Level level, float x, float y)
	{
		this.level = level;
		timeCreated = getTime();
		this.x = x;
		this.y = y;
	}
	public AbstractEntity(Level level, float x, float y, float width, float height)
	{
		this.level = level;
		timeCreated = getTime();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public boolean isCreatureColliding()
	{
		boolean colliding = false;
		int count = 0;
		
		//Goes through every object on the screen
		while(count < level.getCreatures().size())
		{
			Creature obj = level.getCreatures().get(count);
			Rectangle2D.Double you = new Rectangle2D.Double(x,y,width,height);
			Rectangle2D.Double other = new Rectangle2D.Double(obj.getX(),obj.getY(),obj.getWidth(),obj.getHeight());
			
			//if bullet hits an object...
			if(you.intersects(other))
			{
				//checks the type of the object, and if one listed below... take health away/give health.
				colliding = true;
				this.onCreatureCollision(obj);
				break;
			}
			count++;
		}
		return colliding;
	}
	public boolean isBlockColliding()
	{
		boolean colliding = false;
		int count = 0;
		
		while(count < level.getBlocks().size())
		{
			Block obj = level.getBlocks().get(count);
			Rectangle2D.Double you = new Rectangle2D.Double(x,y,width,height);
			Rectangle2D.Double other = new Rectangle2D.Double(obj.getX(),obj.getY(),obj.getWidth(),obj.getHeight());
			
			//if bullet hits an object...
			if(you.intersects(other))
			{
				//checks the type of the object, and if one listed below... take health away/give health.
				colliding = true;
				this.onBlockCollision(obj);
				break;
			}
			count++;
		}
		
		return colliding;
	}
	public boolean isDespawnable()
	{
		if(this.getTime() - this.getStartTime() >= this.despawnTime)
		{
			despawnable = true;
		}
		return despawnable;
	}

	@Override
	public void despawn()
	{
		//remove?
		glDeleteBuffers(vboVertexHandle);
        glDeleteBuffers(vboTexCoordHandle);
	}

	@Override
	public void draw()
	{
		drawEntity();
	}
	public void drawEntity()
	{
		if(this.isDespawnable() == true)
		{
			this.despawn();
		}
		if (hasinit == false)
		{
			setTexture("PNG", "Enemy.png");
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
	public void initVBO()
	{
		
		vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
        vertexData.put(new float[]{x, y, x + width, y, x + width, y + height, x, y + height});
        vertexData.flip();
        
        textureData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
        textureData.put(new float[]{0, 1, 1, 1, 1, 0, 0, 0});
        textureData.flip();

        vboVertexHandle = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        
        vboTexCoordHandle = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
        glBufferData(GL_ARRAY_BUFFER, textureData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	public void updateVBOCoords()
	{
		
		vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
        vertexData.put(new float[]{x, y, x + width, y, x + width, y + height, x, y + height});
        vertexData.flip();
        
        vboVertexHandle = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	public void updateVBO()
	{
		
		 updateVBOCoords();
		 
		 GL11.glColor4f(1.0f, 0f, 0f,1.0f);
		 glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		 
         glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
         glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);
         
         glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
         glTexCoordPointer(vertexSize, GL_FLOAT, 0, 0L);

         glEnableClientState(GL_VERTEX_ARRAY);
         glEnableClientState(GL_TEXTURE_COORD_ARRAY);
         
         glDrawArrays(GL_QUADS, 0, amountOfVertices);
         
         glDisableClientState(GL_VERTEX_ARRAY);
         glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	}
	@Override
	public void setX(float x)
	{
		this.x = x;
	}
	@Override
	public void setY(float y)
	{
		this.y = y;
	}
	@Override
	public void setWidth(float width)
	{
		this.width = width;
	}
	@Override
	public void setHeight(float height)
	{
		this.height = height;
	}
	@Override
	public float getX()
	{
		return x;
	}
	@Override
	public float getY()
	{
		return y;
	}
	@Override
	public float getWidth()
	{
		return width;
	}
	@Override
	public float getHeight()
	{
		return height;
	}
	@Override
	public void update()
	{
		//do stuff?
	}

	@Override
    public long getTime()
    {
    	//returns the time currently (used)
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
	@Override
	public long getStartTime()
	{
		return timeCreated;
	}
	@Override
	public void setStartTime(long time)
	{
		timeCreated = time;
	}
	@Override
	public void onCreatureCollision(Creature other) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBlockCollision(Block other)
	{
		//do stuff
		this.despawn();
	}
	@Override
	public float getDespawnTime()
	{
		return despawnTime;
	}
	@Override
	public void setDespawnTime(float time)
	{
		this.despawnTime = time;
	}
	@Override
	public void setTexture(String type, String dir)
	{
		try
		{
            // load texture from PNG file
            texture = TextureLoader.getTexture(type, ResourceLoader.getResourceAsStream(dir));
        }
		catch (IOException e)
		{
            e.printStackTrace();
        }
    }

	@Override
	public Texture getTexture()
	{
		return texture;
	}

}
