package lasergame.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lasergame.ILevel;
import lasergame.ILevelEntity;
import lasergame.IPhysicsEntity;
import lasergame.Intersection;
import lasergame.geometry.LineSegment;
import lasergame.levelentities.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Levelx implements ILevel 
{
	public List<ILevelEntity> mLevelEntities = new ArrayList<ILevelEntity>(Arrays.asList(
			(ILevelEntity)new Ball(400, 400, 30, this),
			(ILevelEntity)new Ball(300, 200, 30, this),
			(ILevelEntity)new Ball(100, 200, 30, this),
			(ILevelEntity)new Ball(200, 100, 30, this),
			
			(ILevelEntity)new LaserEmitter(100, 500, this)
			));


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
	public List<IPhysicsEntity> getPhysicsEntities() 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void win() 
	{
	}

	@Override
	public ILevel getNextLevel() 
	{
		return new Level1();
	}

	@Override
	public boolean hasWon() 
	{
		for(ILevelEntity e : mLevelEntities)
		{
			if (e instanceof Ball)
			{
				Ball ball = (Ball)e;
				
				if (ball.IsOnScreen())
				{
					System.out.println("ball still on screen, x: " + ball._location.x + " y: " + ball._location.y);
					return false;
				}
					
			}
		}
		
		return true;
	}
}
