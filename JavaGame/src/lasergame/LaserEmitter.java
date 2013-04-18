package lasergame;

import java.util.ArrayList;
import java.util.List;

import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;

public class LaserEmitter implements IEmitter {

	private int mX;
	private int mY;
	private ILevel mLevel;
	
	public LaserEmitter(int x, int y, ILevel parent){
		mX = x; mY = y; mLevel = parent;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		Input input = gc.getInput();
			
		LineSegment lazer = LineSegment.fromPoints(new Vector2(mX,mY),  new Vector2(input.getMouseX(), input.getMouseY()));
		lazer = lazer.scale(1000);
		
		List<Intersection> intersections = mLevel.getIntersectionsWith(lazer);
		
		if(intersections.size() > 0) {
			Intersection.sortIntersections(intersections);
			Intersection i = intersections.get(0);
			Vector2 point = i.point;
			
			g.draw(new Circle((float)point.x,(float)point.y, 10));	
			g.drawString(((Long)Math.round(i.distance)).toString(), (float)point.x, (float)point.y);
		
			g.drawLine((float)lazer.getStartPoint().x,(float)lazer.getStartPoint().y,(float)point.x, (float)point.y);
		}
		else{
			g.drawLine((float)lazer.getStartPoint().x,(float)lazer.getStartPoint().y,(float)lazer.getEndPoint().x,(float)lazer.getEndPoint().y);
		}
		g.draw(new Circle(mX,mY, 10));
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		// TODO Auto-generated method stub
		return new ArrayList<Intersection>();
	}

}
