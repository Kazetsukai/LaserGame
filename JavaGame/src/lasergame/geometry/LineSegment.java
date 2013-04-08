package lasergame.geometry;

import lasergame.vectormath.Vector2;

public class LineSegment {
	
	private final Vector2 mStartPoint;
	private final Vector2 mDisplacement;
	
	/***
	 * Creates a line segment (a finite line, bounded on both sides).
	 * @param startPoint the starting point of the line
	 * @param displacement the displacement from the starting point
	 */
	public LineSegment(Vector2 startPoint, Vector2 displacement) {
		mStartPoint = startPoint;
		mDisplacement = displacement;
	}
	
	/***
	 * Creates a line segment from a starting point to an end point.
	 * @param startPoint the starting point
	 * @param endPoint the end point
	 */
	public static LineSegment fromPoints(Vector2 startPoint, Vector2 endPoint) {
		return new LineSegment(startPoint, endPoint.subtract(startPoint));
	}

	/***
	 * @return the displacement between the starting point and the end point of the line segment
	 */
	public Vector2 getDisplacement() {
		return mDisplacement;
	}

	/***
	 * @return the starting point of the line segment
	 */
	public Vector2 getStartPoint() {
		return mStartPoint;
	}
	
	/***
	 * @return the end point of the line segment
	 */
	public Vector2 getEndPoint() {
		return mStartPoint.add(mDisplacement);
	}
}
