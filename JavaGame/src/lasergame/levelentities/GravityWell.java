package lasergame.levelentities;

import java.util.ArrayList;
import java.util.List;

import lasergame.ILevel;
import lasergame.ILevelEntity;
import lasergame.IPhysicsEntity;
import lasergame.Intersection;
import lasergame.geometry.LineSegment;
import lasergame.levels.Levelx;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class GravityWell implements ILevelEntity 
{
	private int _x;
	private int _y;
	private double _force;
	private double _eventHorizon;
	private ILevel _level;
	
	
	public GravityWell(int x, int y, double force, double size, ILevel parent)
	{
		_x = x;
		_y = y;
		_force = force;
		_eventHorizon = size;
		_level = parent;
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) 
	{
		//some ugly casting
		ILevel level = _level;

		Vector2 wellCenter = new Vector2(_x, _y); 
		
		List<IPhysicsEntity> entities = level.getPhysicsEntities();

		
		for(IPhysicsEntity entity : entities) 
		{
			//need direction vector between two points: given two points what is the vector from the laser beam to the gravity well?
			//direction gravity is traveling in (toward well center)
			Vector2 direction = wellCenter.subtract(entity.getCentreOfMass());
			
			//get the length (strength)
			double length = direction.length();
								
			//check we are within the event horizon
			if (length < _eventHorizon)
			{
				direction = direction.normalise();
				
				//closer we are to the center of the well, the stronger the effect is
				//we want inverse power, i.e. the farther away we are from the center, the less gravity we feel
				Vector2 gravity = direction.multiply(_force * timeElapsed * (_eventHorizon - length));
				
				entity.impulse(gravity);
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) 
	{
		g.draw(new Circle(_x,_y, (int)_eventHorizon));
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) 
	{
		// TODO Auto-generated method stub
		return new ArrayList<Intersection>();
	}
}