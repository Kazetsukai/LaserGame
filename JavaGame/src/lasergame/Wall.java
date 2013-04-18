package lasergame;

import java.util.ArrayList;
import java.util.List;

import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;

public class Wall implements ILevelEntity {
	private int mX;
	private int mY;
	private int mHeight;
	private int mWidth;
	
	public Wall(int x, int y, int width, int height){
		mX = x;
		mY = y;
		mHeight = height;
		mWidth = width;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.draw(new Rectangle(mX,mY,mWidth,mHeight));
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		
		ArrayList<Intersection> intersections = new ArrayList<Intersection>();
		
		Vector2 vTop = line.getIntersection(LineSegment.fromPoints(new Vector2(mX,mY), new Vector2(mX + mWidth, mY)));
		Vector2 vBottom = line.getIntersection(LineSegment.fromPoints(new Vector2(mX,mY + mHeight), new Vector2(mX + mWidth, mY + mHeight)));
		Vector2 vLeft = line.getIntersection(LineSegment.fromPoints(new Vector2(mX,mY), new Vector2(mX, mY + mHeight)));
		Vector2 vRight = line.getIntersection(LineSegment.fromPoints(new Vector2(mX + mWidth,mY), new Vector2(mX + mWidth, mY + mHeight)));

		if(vTop != null) {
			intersections.add(new Intersection(vTop, vTop.subtract(line.getStartPoint()).length(), this));
		}
		if(vBottom != null) {
			intersections.add(new Intersection(vBottom, vBottom.subtract(line.getStartPoint()).length(), this));
		}
		if(vLeft != null) {
			intersections.add(new Intersection(vLeft, vLeft.subtract(line.getStartPoint()).length(), this));
		}
		if(vRight != null) {
			intersections.add(new Intersection(vRight, vRight.subtract(line.getStartPoint()).length(), this));
		}
		
		return intersections;
	}
}
