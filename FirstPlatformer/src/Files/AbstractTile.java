package Files;

import java.io.IOException;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class AbstractTile implements Tile {

	protected float x = 0;
	protected float y = 0;
	protected float width = 100;
	protected float height = 100;
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
    
	public AbstractTile()
	{
		x = 0;
		y = 0;
	}
	public AbstractTile(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	public AbstractTile(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void despawn()
	{
		//Despawns the render, but the actual object needs to be deleted
		glDeleteBuffers(vboVertexHandle);
        glDeleteBuffers(vboTexCoordHandle);
        x = 0;
        y = 0;
        width = 0;
        height = 0;
	}
	public void setX(float x)
	{
		this.x = x;
	}

	public void setY(float y)
	{
		this.y = y;
	}

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
	public float getWidth()
	{
		return width;
	}

	@Override
	public float getHeight()
	{
		return height;
	}

	public void drawTile()
	{
		if (hasinit == false)
		{
			setTexture("PNG", "Enemy.png");
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
	public void draw()
	{	
		this.drawTile();
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
		
		 GL11.glColor4f(0.5f,0.5f,1.0f,1.0f);
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
	public Texture getTexture()
	{
		return texture;
	}

	@Override
	public void relocate(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public void resize(float width, float height)
	{
		this.width = width;
		this.height = height;
	}
}
