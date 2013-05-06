package lasergame.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lasergame.ILevel;
import lasergame.ILevelEntity;
import lasergame.Intersection;
import lasergame.geometry.LineSegment;
import lasergame.levelentities.BrickWall;
import lasergame.levelentities.LaserEmitter;
import lasergame.levelentities.Target;
import lasergame.levelentities.Wall;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.*;

public class Level1 implements ILevel {
	private List<ILevelEntity> mLevelEntities = new ArrayList<ILevelEntity>(Arrays.asList(
			(ILevelEntity)new Wall(new LineSegment(new Vector2(400, 300), new Vector2(0, 400)), 20),
			(ILevelEntity)new Wall(new LineSegment(new Vector2(200, 10), new Vector2(400, 0)), 20),
			(ILevelEntity)new Wall(new LineSegment(new Vector2(100, 10), new Vector2(-70, 40)), 10),
			(ILevelEntity)new Wall(new LineSegment(new Vector2(820, 300), new Vector2(-70, 300)), 30),
			(ILevelEntity)new Wall(new LineSegment(new Vector2(50, 430), new Vector2(-40, 0)), 10),
			(ILevelEntity)new Wall(new LineSegment(new Vector2(30, 450), new Vector2(0, -40)), 10),
			(ILevelEntity)new LaserEmitter(100,500, this),
			(ILevelEntity)new Target(600, 500)));
	
	private LaserEmitter _emitter = new LaserEmitter(100,500, this);
	private Target _target = new Target(600, 500);

	private ArrayList<ILevelEntity> mEntitiesToAdd = new ArrayList<ILevelEntity>();
	private ArrayList<ILevelEntity> mEntitiesToRemove = new ArrayList<ILevelEntity>();
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		
		for(ILevelEntity e : mLevelEntities){
			e.render(gc, g);
		}
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) {

		for(ILevelEntity e : mEntitiesToRemove) {
			mLevelEntities.remove(e);
		}
		for(ILevelEntity e : mEntitiesToAdd) {
			mLevelEntities.add(e);
		}
		mEntitiesToAdd.clear();
		System.out.println("Entities: " + mLevelEntities.size());
		
		for(ILevelEntity e : mLevelEntities) {
			e.update(gc, timeElapsed);
		}
		
	}
	
	public List<Intersection> getIntersectionsWith(LineSegment ls){
		List<Intersection> intersections = new ArrayList<Intersection>();
		for (ILevelEntity e : mLevelEntities){
			for (Intersection i : e.getIntersectionsWith(ls)) {
				if(i != null)
					intersections.add(i);
			}
		}
		return intersections;
	}
	
	@Override
	public void spawn(ILevelEntity entity) {
		mEntitiesToAdd.add(entity);
	}
	
	@Override
	public void kill(ILevelEntity entity) {
		mEntitiesToRemove.add(entity);
	}
}
