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
	
	public LineSegment scale(double scale){
		return new LineSegment(this.mStartPoint, this.mDisplacement.multiply(scale));
	}

	public Vector2 getIntersection(LineSegment rhs) {
		
		double cross = this.mDisplacement.crossMagnitude(rhs.mDisplacement);
		
		if (cross == 0) 
			return null; // The lines are parallel
		
		Vector2 startPointDiff = rhs.mStartPoint.subtract(this.mStartPoint);
		
		double t = startPointDiff.crossMagnitude(rhs.mDisplacement) / cross;
		double u = startPointDiff.crossMagnitude(this.mDisplacement) / cross;
		
		if (t < 0 || t > 1 || u < 0 || u > 1) 
			return null; // the infinite lines are colliding, but not within the line segments 
		
		return this.mStartPoint.add(this.mDisplacement.multiply(t));
	}
}
