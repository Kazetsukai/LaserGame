package lasergame.levelentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lasergame.ILevelEntity;
import lasergame.Intersection;
import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;

public class Wall implements ILevelEntity {
	protected LineSegment mLine;
	protected double mWidth;
	

	protected Vector2 mTopLeft;
	protected Vector2 mBottomLeft;
	protected Vector2 mTopRight;
	protected Vector2 mBottomRight;
	protected Vector2 mNormal;
	
	protected LineSegment[] mWalls = new LineSegment[4];
	
	public Wall(LineSegment line, double width){
		mLine = line;
		mWidth = width;
		
		mNormal = mLine.getDisplacement().normalLeft().multiply(mWidth);
		Vector2 start = mLine.getStartPoint();
		Vector2 end = mLine.getEndPoint();
		
		mTopLeft = start.add(mNormal);
		mBottomLeft = start.subtract(mNormal);
		mTopRight = end.add(mNormal);
		mBottomRight = end.subtract(mNormal);

		mWalls[0] = LineSegment.fromPoints(mTopLeft, mTopRight);
		mWalls[1] = LineSegment.fromPoints(mTopRight, mBottomRight);
		mWalls[2] = LineSegment.fromPoints(mBottomRight, mBottomLeft);
		mWalls[3] = LineSegment.fromPoints(mBottomLeft, mTopLeft);
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) {
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.draw(getLine(mTopLeft, mTopRight));
		g.draw(getLine(mTopRight, mBottomRight));
		g.draw(getLine(mBottomRight, mBottomLeft));
		g.draw(getLine(mBottomLeft, mTopLeft));
	}

	protected Line getLine(Vector2 start, Vector2 end) {
		return new Line((float)start.x, (float)start.y, (float)end.x, (float)end.y);
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		
		LinkedList<Intersection> intersections = new LinkedList<Intersection>();

		double minDistance = Double.MAX_VALUE;
		LineSegment closestWall = null;
		for (LineSegment wall : mWalls) {
			Vector2 position = line.getStartPoint();
			
			// If we are to the left of any segment, then we are not inside
			if (wall.isPointOnLeft(position)) {
				closestWall = null;
				break;
			}
			
			double distanceToThisWall = wall.nearestPointTo(position).subtract(position).length();
			if (distanceToThisWall < minDistance) {
				minDistance = distanceToThisWall;
				closestWall = wall;
			}
		}
		
		if (closestWall != null) {
			Vector2 vec = line.getLineIntersection(closestWall);
			if (vec != null) {
				intersections.add(new Intersection(vec, closestWall.getDisplacement(), vec.subtract(line.getStartPoint()).length(), this));
			}
		}
		
		return intersections;
	}
}
