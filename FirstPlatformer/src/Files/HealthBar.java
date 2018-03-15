package Files;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import Creatures.Creature;


public class HealthBar extends AbstractTile
{
	protected Creature mainHost;
	
	public HealthBar(Creature host)
	{
		this.mainHost = host;
		height = 20;
		width = getBarHealth();
		height = getBarHeight();
	}
	public void drawTile()
	{
		if (hasinit == false)
		{
			initVBO();
			hasinit = true;
		}
		updateVBO();
	}
	public float getBarHealth()
	{
		float BarHealth = (mainHost.getHealth() / mainHost.getMaxHealth()) * mainHost.getWidth();
		
		return BarHealth;
	}
	public float getBarHeight()
	{
		float BarHeight;
		BarHeight = (mainHost.getHeight() / 5);
		return BarHeight;
	}
	public void initVBO()
	{
		GL11.glColor4f(1.0f,0.0f,0.0f,1.0f);
		x = (mainHost.getX());
		y = (mainHost.getY() + mainHost.getHeight() + 10);
		width = getBarHealth();
		height = getBarHeight();
		vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
        vertexData.put(new float[]{x, y, x + width, y, x + width, y + height, x, y + height});
        vertexData.flip();

        vboVertexHandle = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

	}
	public void updateVBOCoords()
	{
		x = (mainHost.getX());
		y = (mainHost.getY() + mainHost.getHeight() + 15);
		width = getBarHealth();
		height = getBarHeight();
		
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
		GL11.glColor4f(1.0f,0.0f,0.0f,1.0f);
         glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
         glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);
         
         glEnableClientState(GL_VERTEX_ARRAY);
         
         glDrawArrays(GL_QUADS, 0, amountOfVertices);
         
         glDisableClientState(GL_VERTEX_ARRAY);
	}
}
