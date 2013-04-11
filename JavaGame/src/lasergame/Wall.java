package lasergame;

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
	public Intersection getIntersectionWith(LineSegment line) {
		
		Vector2 v = line.getIntersection(LineSegment.fromPoints(new Vector2(mX,mY), new Vector2(mX + mWidth,mY)));
		
		
		if(v != null) {
			return new Intersection(v, v.subtract(line.getStartPoint()).length(), this);
		}
		
		return null;
	}
}
