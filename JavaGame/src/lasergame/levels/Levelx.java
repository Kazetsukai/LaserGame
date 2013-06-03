package lasergame.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lasergame.ILevel;
import lasergame.ILevelEntity;
import lasergame.IPhysicsEntity;
import lasergame.Intersection;
import lasergame.geometry.LineSegment;
import lasergame.levelentities.GravityWell;
import lasergame.levelentities.LaserEmitter;
import lasergame.levelentities.Target;
import lasergame.levelentities.Wall;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Levelx implements ILevel 
{
	public List<ILevelEntity> mLevelEntities = new ArrayList<ILevelEntity>(Arrays.asList(
			//(ILevelEntity)new GravityWell(200, 200, this),
			//(ILevelEntity)new GravityWell(300, 400, this),
			//(ILevelEntity)new GravityWell(600, 500, this),
			//(ILevelEntity)new GravityWell(400, 100, this),
			//(ILevelEntity)new GravityWell(500, 200, this),
			(ILevelEntity)new LaserEmitter(100, 500, this)));
	
	private LaserEmitter _emitter = new LaserEmitter(100, 500, this);
	private Target _target = new Target(600, 500);

	private ArrayList<ILevelEntity> mEntitiesToAdd = new ArrayList<ILevelEntity>();
	private ArrayList<ILevelEntity> mEntitiesToRemove = new ArrayList<ILevelEntity>();
	
	@Override
	public void render(GameContainer gc, Graphics g) 
	{
		
		for(ILevelEntity e : mLevelEntities)
		{
			e.render(gc, g);
		}
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) 
	{

		for(ILevelEntity e : mEntitiesToRemove) 
		{
			mLevelEntities.remove(e);
		}
		
		for(ILevelEntity e : mEntitiesToAdd) 
		{
			mLevelEntities.add(e);
		}
		
		mEntitiesToAdd.clear();
				
		for(ILevelEntity e : mLevelEntities) 
		{
			e.update(gc, timeElapsed);
		}
		
	}
	
	public List<Intersection> getIntersectionsWith(LineSegment ls)
	{
		List<Intersection> intersections = new ArrayList<Intersection>();
		
		for (ILevelEntity e : mLevelEntities)
		{
			for (Intersection i : e.getIntersectionsWith(ls)) 
			{
				if(i != null)
					intersections.add(i);
			}
		}
		return intersections;
	}
	
	@Override
	public void spawn(ILevelEntity entity) 
	{
		mEntitiesToAdd.add(entity);
	}
	
	@Override
	public void remove(ILevelEntity entity) 
	{
		mEntitiesToRemove.add(entity);
	}

	@Override
	public List<IPhysicsEntity> getPhysicsEntities() {
		// TODO Auto-generated method stub
		return null;
	}
}
