package lasergame.levelentities;

import java.util.ArrayList;
import java.util.List;

import lasergame.ILevel;
import lasergame.ILevelEntity;
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
	private ILevel _level;
	
	static double GRAVITY_FORCE = 5;
	static double EVENT_HORIZON = 50;
	
	public GravityWell(int x, int y, ILevel parent)
	{
		_x = x;
		_y = y;
		_level = parent;
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) 
	{
		//some ugly casting
		Levelx level = (Levelx)_level;
		
		List<ILevelEntity> entities = level.mLevelEntities;
		
		for(ILevelEntity entity : entities) 
		{
			//only effect laser beams
			if (entity instanceof LaserBeam)
			{
				LaserBeam laserBeam = (LaserBeam)entity;
				
				//check we are within the event horizon
				if ((Math.pow((laserBeam._location.x - _x), 2) + Math.pow((laserBeam._location.y - _y), 2)) < Math.pow(EVENT_HORIZON, 2))
				{
					//closer we are to the center of the well, the stronger the effect is
					//need direction vector between two points: given two points what is the vector from the laser beam to the gravity well?
					
					Vector2 wellCenter = new Vector2(_x, _y);
					
					//direction gravity is traveling in (toward well center)
					Vector2 direction = wellCenter.subtract(laserBeam._location);
					
					//get the length (strength)
					double length = direction.length();
										
					direction = direction.normalise();
					
					//we want inverse power, i.e. the farther away we are from the center, the less gravity we feel
					Vector2 gravity = direction.multiply(GRAVITY_FORCE * (EVENT_HORIZON - length));
					
					laserBeam._velocity = laserBeam._velocity.add(gravity);
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) 
	{
		g.draw(new Circle(_x,_y, (int)EVENT_HORIZON));
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) 
	{
		// TODO Auto-generated method stub
		return new ArrayList<Intersection>();
	}
}