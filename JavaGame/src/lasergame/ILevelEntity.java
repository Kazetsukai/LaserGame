package lasergame;

import java.util.List;

import lasergame.geometry.LineSegment;

public interface ILevelEntity extends IRenderable{
	List<Intersection> getIntersectionsWith(LineSegment line);
}
