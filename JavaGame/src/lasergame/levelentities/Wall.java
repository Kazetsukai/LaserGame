package lasergame.levelentities;

import java.util.ArrayList;
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
	
	public Wall(LineSegment line, double width){
		mLine = line;
		mWidth = width;
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) {
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		Vector2 normal = mLine.getDisplacement().normalLeft().multiply(mWidth);
		Vector2 start = mLine.getStartPoint();
		Vector2 end = mLine.getEndPoint();
		
		Vector2 topLeft = start.add(normal);
		Vector2 bottomLeft = start.subtract(normal);
		Vector2 topRight = end.add(normal);
		Vector2 bottomRight = end.subtract(normal);

		g.draw(getLine(topLeft, topRight));
		g.draw(getLine(topRight, bottomRight));
		g.draw(getLine(bottomRight, bottomLeft));
		g.draw(getLine(bottomLeft, topLeft));
	}

	protected Line getLine(Vector2 start, Vector2 end) {
		return new Line((float)start.x, (float)start.y, (float)end.x, (float)end.y);
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
