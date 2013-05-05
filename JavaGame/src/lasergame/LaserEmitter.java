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
	public void update(GameContainer gc, double timeElapsed) {
		Input input = gc.getInput();
		
		Vector2 mouseLoc = new Vector2(input.getMouseX(), input.getMouseY());
		Vector2 currentLoc = new Vector2(mX, mY);
		
		if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
			mLevel.spawn(new LaserBeam(mLevel, currentLoc, mouseLoc.subtract(currentLoc).normalise().multiply(500)));
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		Input input = gc.getInput();
			
		LineSegment laser = LineSegment.fromPoints(new Vector2(mX,mY),  new Vector2(input.getMouseX(), input.getMouseY()));
		laser = laser.scale(1000);
		
		for (int j = 0; j < 100; j++) {
			List<Intersection> intersections = mLevel.getIntersectionsWith(laser);
			
			if(intersections.size() > 0) {
				Intersection.sortIntersections(intersections);
				Intersection intersection = intersections.get(0);
				Vector2 point = intersection.point;
				Vector2 normalDraw = intersection.point.add(intersection.normal.multiply(30));
				Vector2 tangent = intersection.normal.normalLeft();
				
				g.draw(new Circle((float)point.x,(float)point.y, 10));	
				g.drawString(((Long)Math.round(intersection.distance)).toString(), (float)point.x, (float)point.y);
	
				g.drawLine((float)intersection.point.x,(float)intersection.point.y,(float)normalDraw.x, (float)normalDraw.y);
				g.drawLine((float)laser.getStartPoint().x,(float)laser.getStartPoint().y,(float)point.x, (float)point.y);

				Vector2 newDirection = laser.getDisplacement().reflectOver(tangent);
				
				laser = new LineSegment(intersection.point, newDirection);
			}
			else{
				g.drawLine((float)laser.getStartPoint().x,(float)laser.getStartPoint().y,(float)laser.getEndPoint().x,(float)laser.getEndPoint().y);
				break;
			}
		}
		
		g.draw(new Circle(mX,mY, 10));
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		// TODO Auto-generated method stub
		return new ArrayList<Intersection>();
	}

}
