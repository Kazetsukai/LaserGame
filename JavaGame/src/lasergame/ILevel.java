package lasergame;

import java.util.List;

import lasergame.geometry.LineSegment;

public interface ILevel extends IRenderable{
	 List<Intersection> getIntersectionsWith(LineSegment ls);
	 void spawn(ILevelEntity entity);
	 void kill(ILevelEntity entity);
}
