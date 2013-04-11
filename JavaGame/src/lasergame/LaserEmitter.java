package lasergame;

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
		
		for(Intersection i : mLevel.getIntersectionsWith(lazer)){
			g.draw(new Circle((float)i.point.x,(float)i.point.y, 10));	
			g.drawString(((Long)Math.round(i.distance)).toString(), (float)i.point.x, (float)i.point.y);
		}

		g.drawLine((float)lazer.getStartPoint().x,(float)lazer.getStartPoint().y,(float)lazer.getEndPoint().x,(float)lazer.getEndPoint().y);
		
		g.draw(new Circle(mX,mY, 10));
	}

	@Override
	public Intersection getIntersectionWith(LineSegment line) {
		// TODO Auto-generated method stub
		return null;
	}

}
