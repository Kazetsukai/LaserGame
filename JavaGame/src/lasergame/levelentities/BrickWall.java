package lasergame.levelentities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import lasergame.Intersection;
import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

public class BrickWall extends Wall {

	Texture mWallTexture;
	
	public BrickWall(LineSegment line, double width) {
		super(line, width);
	}
	@Override
	public void render(GameContainer gc, Graphics g) {		
		if(mWallTexture == null)
		{
			try
			{
				mWallTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("JavaGame/res/BlueGreenBrick.png"));
			}
			catch(IOException ex)
			{
				System.out.println(ex);
			}
		}
		
		mWallTexture.bind();
		
		//GL11.glColor3f(1 ,1, 1);
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2d(mTopLeft.x, mTopLeft.y);
		
		GL11.glTexCoord2f(3, 0);
		GL11.glVertex2d(mTopRight.x, mTopRight.y);
		
		GL11.glTexCoord2f(3, 1);
		GL11.glVertex2d(mBottomRight.x, mBottomRight.y);
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2d(mBottomLeft.x, mBottomLeft.y);
		
		GL11.glEnd();
	}
}
