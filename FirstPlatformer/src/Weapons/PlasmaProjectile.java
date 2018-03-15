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
import static org.lwjgl.opengl.GL15.glBindBuffer;

import org.lwjgl.opengl.GL11;

import Creatures.Creature;
import Files.GameMain.Direction;
import Levels.Level;

public class PlasmaProjectile extends AbstractProjectile
{

	public PlasmaProjectile(Level level, Creature sender, float x, float y, float width, float height, Direction direction, float damage)
	{
		super(level, sender, x, y, width, height, direction, damage);
	}
	public void updateVBO()
	{
		
		 updateVBOCoords();
		
		 GL11.glColor4f(0.0f, 1.0f, 0.0f,1.0f);
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
}
