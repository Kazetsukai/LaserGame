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
		
		LineSegment top = LineSegment.fromPoints(new Vector2(mX,mY), new Vector2(mX + mWidth, mY));
		LineSegment bottom = LineSegment.fromPoints(new Vector2(mX,mY + mHeight), new Vector2(mX + mWidth, mY + mHeight));
		LineSegment left = LineSegment.fromPoints(new Vector2(mX,mY), new Vector2(mX, mY + mHeight));
		LineSegment right = LineSegment.fromPoints(new Vector2(mX + mWidth,mY), new Vector2(mX + mWidth, mY + mHeight));
		
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
