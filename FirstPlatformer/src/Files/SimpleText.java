package Files;
/*
 * Rian H.
 * Text
 * 5/26/2015
 */
import java.awt.Font;
  
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;
 
public class SimpleText {
 
	/** The fonts to draw to the screen */
	private TrueTypeFont font;
	private TrueTypeFont font2;
	
	private int x=0,y=0;
	private String Text = "";
 
	/** Boolean flag on whether AntiAliasing is enabled or not */
	private boolean antiAlias = true;
	
	public SimpleText(){
		
	}
	public SimpleText(int x, int y){
		
	}
	public SimpleText(int x,int y, String Text){
		this.x = x;
		this.y = y;
		this.Text = Text;
		
	}
 
	/**
	 * Initialise resources
	 */
	public void init() {
		// load a default java font
		Font awtFont = new Font("Times New Roman", Font.BOLD, 60);
		font = new TrueTypeFont(awtFont, antiAlias);
		
		// load font from file
				try {
					InputStream inputStream	= ResourceLoader.getResourceAsStream("UpsideDown.ttf");
		 
					Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
					awtFont2 = awtFont2.deriveFont(24f); // set font size
					font2 = new TrueTypeFont(awtFont2, antiAlias);
		 
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
 
	/**
	 * Game loop render
	 */
	public void render() {
		
		font2.drawString(x, y, Text,Color.red);
		}
}