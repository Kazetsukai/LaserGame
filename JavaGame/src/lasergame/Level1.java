package lasergame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

import org.newdawn.slick.*;

public class Level1 implements ILevel {
	private List<ILevelEntity> mLevelEntities = Arrays.asList(
			(ILevelEntity)new Wall(new LineSegment(new Vector2(400, 300), new Vector2(0, 400)), 20),
			(ILevelEntity)new Wall(new LineSegment(new Vector2(200, 10), new Vector2(400, 0)), 20),
			(ILevelEntity)new Wall(new LineSegment(new Vector2(100, 10), new Vector2(-70, 40)), 10),
			(ILevelEntity)new Wall(new LineSegment(new Vector2(820, 300), new Vector2(-70, 300)), 30),
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
			for (Intersection i : e.getIntersectionsWith(ls)) {
				if(i != null)
					intersections.add(i);
			}
		}
		return intersections;
	}
}
