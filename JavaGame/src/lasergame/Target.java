package lasergame;

import java.util.ArrayList;
import java.util.List;

import lasergame.geometry.LineSegment;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Target implements ILevelEntity {
	private int _x;
	private int _y;
	private double _totalTimeElapsed;
	
	public Target(int x, int y){
		_x = x;
		_y = y;
		_totalTimeElapsed = 0;
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) {
		_totalTimeElapsed += timeElapsed;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		int radius = (int)((Math.sin(_totalTimeElapsed * 10) + 5) * 6);
		g.draw(new Circle(_x,_y, radius));
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		// TODO Auto-generated method stub
		return new ArrayList<Intersection>();
	}
}
