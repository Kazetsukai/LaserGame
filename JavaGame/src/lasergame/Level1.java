package lasergame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lasergame.geometry.LineSegment;

import org.newdawn.slick.*;

public class Level1 implements ILevel {
	private List<ILevelEntity> mLevelEntities = Arrays.asList(
			(ILevelEntity)new Wall(390, 200, 20, 400),
			(ILevelEntity)new Wall(200, 0, 400, 20),
			(ILevelEntity)new LaserEmitter(100,500, this),
			(ILevelEntity)new Target(600, 500));
	
	private LaserEmitter _emitter = new LaserEmitter(100,500, this);
	private Target _target = new Target(600, 500);
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		
		for(ILevelEntity e : mLevelEntities){
			e.render(gc, g);
		}
	}
	
	public List<Intersection> getIntersectionsWith(LineSegment ls){
		List<Intersection> intersections = new ArrayList<Intersection>();
		for (ILevelEntity e : mLevelEntities){
			Intersection i = e.getIntersectionWith(ls);
			if(i != null)
				intersections.add(i);
		}
		return intersections;
	}
}
