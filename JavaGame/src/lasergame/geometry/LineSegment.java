package lasergame.geometry;

import lasergame.Constants;
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

	public Vector2 getSegmentIntersection(LineSegment rhs) {
		return getIntersection(rhs, false);
	}
	
	public Vector2 getLineIntersection(LineSegment rhs) {
		return getIntersection(rhs, true);
	}
	
	private Vector2 getIntersection(LineSegment rhs, boolean infinite) {
		
		double cross = this.mDisplacement.crossMagnitude(rhs.mDisplacement);
		
		if (cross == 0) 
			return null; // The lines are parallel
		
		Vector2 startPointDiff = rhs.mStartPoint.subtract(this.mStartPoint);
		
		double t = startPointDiff.crossMagnitude(rhs.mDisplacement) / cross;
		double u = startPointDiff.crossMagnitude(this.mDisplacement) / cross;
		
		if (!infinite && (t <= Constants.EPSILON || t > 1 || u < 0 || u > 1)) 
			return null; // the infinite lines are colliding, but not within the line segments 
		
		return this.mStartPoint.add(this.mDisplacement.multiply(t));
	}
	
	private double leftnessOfPoint(Vector2 rhs) {
		return rhs.subtract(mStartPoint).crossMagnitude(mDisplacement);
	}
	
	public boolean isPointOnLeft(Vector2 rhs) {
		return leftnessOfPoint(rhs) > 0;
	}
	
	public boolean isPointOnRight(Vector2 rhs) {
		return leftnessOfPoint(rhs) < 0;
	}
	
	public Vector2 reflectPoint(Vector2 rhs) {
		return rhs.subtract(mStartPoint).reflectOver(mDisplacement).add(mStartPoint);
	}
	
	public Vector2 nearestPointTo(Vector2 rhs) {
		return rhs.subtract(mStartPoint).projectOnto(mDisplacement).add(mStartPoint);
	}
	
	public Vector2 center() {
		return this.scale(0.5).getEndPoint();
	}
}
