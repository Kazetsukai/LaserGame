package lasergame.levelentities;

import java.util.ArrayList;
import java.util.List;

import lasergame.*;
import lasergame.geometry.*;
import lasergame.vectormath.*;

import org.newdawn.slick.*;

public class LaserBeam implements IPhysicsEntity {

	ILevel _level;
	Vector2 _location;
	Vector2[] _tail;
	Vector2 _velocity;
	Vector2 _accum;
	
	boolean _dying = false;
	double _velocityAtDeath = 0;
	
	final static int num_tail = 1000;
	final static float beam_length = 0.25f;
	
	public LaserBeam(ILevel level, Vector2 location, Vector2 velocity) {
		_level = level;
		_location = location;
		_tail = new Vector2[num_tail];
		for(int i = 0; i< num_tail; i++){
			_tail[i] = new Vector2(_location.x, _location.y);
		}
		_velocity = velocity;
		_accum = new Vector2(0,0);
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		//g.drawOval((float)_location.x - 5, (float)_location.y - 5, 10, 10);
		
		Vector2 nextPoint = _location;
		float accumLength = 0;
		for(int i = 0; i< num_tail; i++){
			accumLength += nextPoint.subtract(_tail[i]).length();
			g.drawLine((float)_tail[i].x, (float)_tail[i].y, (float)nextPoint.x, (float)nextPoint.y);
			nextPoint = _tail[i];
			if(accumLength >= beam_length * _velocity.length()){
				break;
			}
		}
		
		//Vector2 acc = _location.add(_accum.multiply(5));
		//g.drawLine((float)_location.x, (float)_location.y, (float)acc.x, (float)acc.y);
		_accum = new Vector2(0,0);
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) {
		
		Vector2 newLocation = _location.add(_velocity.multiply((timeElapsed)));
		
		if (!_dying) {
		LineSegment laser = LineSegment.fromPoints(_location, newLocation.add(_velocity.multiply(0.01)));
		List<Intersection> intersections = _level.getIntersectionsWith(laser);

		Vector2 nextPoint = _location;
		for(int i = 0; i< num_tail; i++){
			Vector2 temp = _tail[i];
			_tail[i] = nextPoint;
			nextPoint = temp;
		}
		
		if(intersections.size() > 0) {
			Intersection.sortIntersections(intersections);
			Intersection intersection = intersections.get(0);
			Vector2 tangent = intersection.normal.normalLeft();
			
			Vector2 newDirection = laser.getDisplacement().reflectOver(tangent);
			
			LineSegment reflectedBeam = new LineSegment(intersection.point, newDirection.normalise().multiply(_velocity.length()));
			_level.spawn(new LaserBeam(_level, reflectedBeam.getStartPoint(), reflectedBeam.getDisplacement()));
			this.kill();
		}
		
		_location = newLocation;
		} else {
			_velocity = _velocity.normalise().multiply(_velocity.length() - _velocityAtDeath * timeElapsed * 2);
			if (_velocity.length() < 0) {
				_level.kill(this);
			}
		}
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {
		// TODO Auto-generated method stub
		return new ArrayList<Intersection>();
	}

	@Override
	public void impulse(Vector2 impulse) {
		if (!_dying) {
			_accum = _accum.add(impulse);
			_velocity = _velocity.add(impulse);
		}
	}

	@Override
	public Vector2 getCentreOfMass() {
		return _location;
	}
	
	public void kill() {
		_velocityAtDeath = _velocity.length();
		_dying = true;
	}

}
