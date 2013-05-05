package lasergame;

import java.util.ArrayList;
import java.util.List;

import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class LaserBeam implements ILevelEntity {

	ILevel _level;
	Vector2 _location;
	Vector2 _velocity;
	
	public LaserBeam(ILevel level, Vector2 location, Vector2 velocity) {
		_level = level;
		_location = location;
		_velocity = velocity;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawOval((float)_location.x - 5, (float)_location.y - 5, 10, 10);
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) {
		
		Vector2 newLocation = _location.add(_velocity.multiply((timeElapsed)));
		
		LineSegment laser = LineSegment.fromPoints(_location, newLocation.add(_velocity.multiply(0.01)));
		List<Intersection> intersections = _level.getIntersectionsWith(laser);
		
		if(intersections.size() > 0) {
			Intersection.sortIntersections(intersections);
			Intersection intersection = intersections.get(0);
			Vector2 point = intersection.point;
			Vector2 tangent = intersection.normal.normalLeft();
			
			Vector2 newDirection = laser.getDisplacement().reflectOver(tangent);
			
			LineSegment reflectedBeam = new LineSegment(intersection.point, newDirection.normalise().multiply(500));
			_level.spawn(new LaserBeam(_level, reflectedBeam.getStartPoint(), reflectedBeam.getDisplacement()));
			_level.kill(this);
		}
		
		_location = newLocation;
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		// TODO Auto-generated method stub
		return new ArrayList<Intersection>();
	}

}
