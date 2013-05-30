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
	
	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		
		ArrayList<Intersection> intersections = new ArrayList<Intersection>();

		Vector2 normal = mLine.getDisplacement().normalLeft().multiply(mWidth);
		Vector2 start = mLine.getStartPoint();
		Vector2 end = mLine.getEndPoint();
		Vector2 length = mLine.getDisplacement();
		
		LineSegment top = new LineSegment(start.add(normal), length);
		LineSegment bottom = new LineSegment(start.subtract(normal), length);
		LineSegment left = new LineSegment(start.add(normal), normal.multiply(-2));
		LineSegment right = new LineSegment(end.add(normal), normal.multiply(-2));
		
		Vector2 vTop = line.getIntersection(top);
		Vector2 vBottom = line.getIntersection(bottom);
		Vector2 vLeft = line.getIntersection(left);
		Vector2 vRight = line.getIntersection(right);

		if(vTop != null) {
			intersections.add(new Intersection(vTop, top.getDisplacement().normalLeft(), vTop.subtract(line.getStartPoint()).length(), this));
		}
		if(vBottom != null) {
			intersections.add(new Intersection(vBottom, bottom.getDisplacement().normalRight(), vBottom.subtract(line.getStartPoint()).length(), this));
		}
		if(vLeft != null) {
			intersections.add(new Intersection(vLeft, left.getDisplacement().normalRight(), vLeft.subtract(line.getStartPoint()).length(), this));
		}
		if(vRight != null) {
			intersections.add(new Intersection(vRight, right.getDisplacement().normalLeft(), vRight.subtract(line.getStartPoint()).length(), this));
		}
		
		return intersections;
	}
}
