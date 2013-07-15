package lasergame.levelentities;

import java.util.ArrayList;
import java.util.List;

import lasergame.*;
import lasergame.geometry.*;
import lasergame.vectormath.*;

import org.newdawn.slick.*;

public class LaserBeam implements IPhysicsEntity {

	private static final double CULL_DISTANCE = 5000;
	
	ILevel _level;
	Vector2 _location;
	Vector2[] _tail;
	Vector2 _velocity;
	Vector2 _accum;
	LineSegment _beam;
	
	boolean _dying = false;
	double _velocityAtDeath = 0;
	
	final static int num_tail = 1000;
	final static float beam_length = 0.05f;
	
	
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
		Vector2 timestepVelocity = _velocity.multiply((timeElapsed));
		_location = _location.add(timestepVelocity);
		if (!_dying) {
			boolean done = false;
			int bounces = 0;
			
			while (!done) {
				//LineSegment laser = LineSegment.fromPoints(_location, _location.add(_velocity));
				//List<Intersection> intersections = _level.getIntersectionsWith(laser);
				timestepVelocity = _velocity.multiply((timeElapsed));
				_beam = LineSegment.fromPoints(_location, _location.add(timestepVelocity));
				List<Intersection> intersections = _level.getIntersectionsWith(_beam);
		
				if(intersections.size() > 0) {
					Intersection.sortIntersections(intersections);
					Intersection intersection = intersections.get(0);
					
					if(intersection.entity instanceof IStrikable)
						((IStrikable)intersection.entity).strike(this);
					
					LineSegment line = new LineSegment(intersection.point, intersection.tangent);
					
					pushTailPoint(intersection.point);
					
					_location = line.reflectPoint(_location);
					_velocity = _velocity.reflectOver(intersection.tangent);
					
					bounces++;
				}
				else {
					done = true;
				}
				
				if (bounces > 3) {
					this.kill();
					done = true;
				}
			}
		} else {
			_velocity = _velocity.normalise().multiply(_velocity.length() - _velocityAtDeath * timeElapsed * 2);
			if (_velocity.length() <= 0.1) {
				_level.remove(this);
			}
		}
		
		pushTailPoint(_location);
		
		// Kill any beams that are far away from the origin
		if (_location.length() > CULL_DISTANCE) {
			_level.remove(this);
		}
	}

	private void pushTailPoint(Vector2 nextPoint) {
		for(int i = 0; i< num_tail; i++){
			Vector2 temp = _tail[i];
			_tail[i] = nextPoint;
			nextPoint = temp;
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
	
	public LineSegment getBeam(){
		return _beam;
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
