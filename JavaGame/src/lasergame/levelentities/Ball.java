package lasergame.levelentities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

import lasergame.ILevel;
import lasergame.ILevelEntity;
import lasergame.IStrikable;
import lasergame.Intersection;
import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

public class Ball implements ILevelEntity, IStrikable
{
	public Vector2 _location;
	private Vector2 _velocity;
	private ILevel _level;
	private float _radius;
	
	public Ball(int x, int y, float radius, ILevel level)
	{
		_level = level;
		_location = new Vector2(x, y);
		_velocity = Vector2.Zero;
		_radius = radius;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) 
	{
		g.draw(new Circle((int)_location.x,(int)_location.y, _radius));
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) 
	{
		_location = _location.add(_velocity.multiply((timeElapsed)));
	}

	@Override
	public void strike(ILevelEntity entity) 
	{
		if (entity instanceof LaserBeam)
		{	
			LaserBeam beam = (LaserBeam)entity;
			
			_velocity = _velocity.add(beam._velocity.multiply(0.1));
		}
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) 
	{
		ArrayList<Intersection> intersections = new ArrayList<Intersection>();
		
		if (line.getStartPoint().distanceTo(_location) < _radius)
		{
			intersections.add(new Intersection(Vector2.Zero, Vector2.Zero, 0, this));
		}
		
		return intersections;
	}
	
	public boolean IsOnScreen()
	{
		if (_location.x > 800 || _location.x < 0 || _location.y > 600 || _location.y < 0)
			return false;
		
		return true;
	}

}
