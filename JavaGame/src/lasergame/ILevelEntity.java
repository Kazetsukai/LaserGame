package lasergame;

import lasergame.geometry.LineSegment;

public interface ILevelEntity extends IRenderable{
	Intersection getIntersectionWith(LineSegment line);
}
