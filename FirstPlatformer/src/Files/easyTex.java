package Files;
	import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/*
 * Rian
 * easy Texture loader
 * purpose:Make loading images less redundant
 * 5/17/2015
 */

	public class easyTex {
		
		//the double just store the points location
		//we never used it but it would have made collision easier
		private double[] point = new double[1];
		 public Texture texture;
		 public int x,y;
		 
		 //Constructor for hard loading
		 easyTex(int x,int y){
			 this.x = x;
			 this.y = y*-1;
			 }
		 
		 /*
		  * Constructor to do nothing
		  * Reason to make a global easyTexture
		  */
		 easyTex(){
			 
		 }
		 
		 //can move image
		 //Rendering does not flick while in motion
		 public void setXY(int X,int Y){
		 x=X;
		 y=Y;
		 }
		 
		 /*
		  * all points for collision
		  * this is the array talked about in a earlier comment
		  * If done right this would make Collision easy, with loaded textures
		  */
		 
		 public double[] GetBl(){
			 point[0] = x;
			 point[1] = y;
			 return point;
		 }
		 public double[] GetTl(){
			 point[0] = x;
			 point[1] = y+texture.getTextureHeight();
			 return point;
		 }
		 public double[] GetBr(){
			 point[0] = x+texture.getTextureWidth();
			 point[1] = y;
			 return point;
		 }
		 public double[] GetTr(){
			 point[0] = x+texture.getTextureWidth();
			 point[1] = y+texture.getTextureHeight();
			 return point;
		 }
		 
		 /*
		  * init loades the location of image
		  * if image is in file(src) you dont have to hard code location
		  * you need file type aka "JPG","PNG","GIF", and Path
		  */
		 
		public void init(String type, String picLocation) {
	        
	        try {
	            // load texture from PNG file
	            texture = TextureLoader.getTexture(type, ResourceLoader.getResourceAsStream(picLocation));
	           } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		//Draws image on screen
		 public void render() {
		        Color.white.bind();
		        texture.bind(); // or GL11.glBind(texture.getTextureID());
		        GL11.glBegin(GL11.GL_QUADS);
		            GL11.glTexCoord2f(0,1);
		            GL11.glVertex2f(x,y);
		            GL11.glTexCoord2f(1,1);
		            GL11.glVertex2f(x+texture.getTextureWidth(),y);
		            GL11.glTexCoord2f(1,0);
		            GL11.glVertex2f(x+texture.getTextureWidth(),y+texture.getTextureHeight());
		            GL11.glTexCoord2f(0,0);
		            GL11.glVertex2f(x,y+texture.getTextureHeight());
		        GL11.glEnd();
		    }
		 
		 
}
