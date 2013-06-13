package lasergame.levelentities;

import java.util.ArrayList;
import java.util.List;

import lasergame.ILevel;
import lasergame.ILevelEntity;
import lasergame.IStrikable;
import lasergame.Intersection;
import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Target implements ILevelEntity, IStrikable {
	private Vector2 _location;
	private double _totalTimeElapsed;

	private double _targetRadius;
	private double _pulseRadius;
	
	private ILevel _level;
	
	public Target(int x, int y, double targetRadius, double pulseRadius, ILevel level){
		_level = level;
		_location = new Vector2(x,y);
		_totalTimeElapsed = 0;
		_targetRadius=targetRadius;
		_pulseRadius = pulseRadius;
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) {
		_totalTimeElapsed += timeElapsed;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		int radius = (int)((((Math.sin(_totalTimeElapsed * 10) + 1)/2) * (_pulseRadius - _targetRadius)) + _targetRadius);
		g.draw(new Circle((int)_location.x,(int)_location.y, radius));
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		ArrayList<Intersection> intersections = new ArrayList<Intersection>();
		
		if (line.getStartPoint().distanceTo(_location) < _targetRadius)
		{
			intersections.add(new Intersection(Vector2.Zero, Vector2.Zero, 0, this));
		}
		
		return intersections;
	}

	@Override
	public void strike(ILevelEntity entity) {
		_level.win();
	}
}
