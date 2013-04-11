package lasergame.geometry.test;

import lasergame.Constants;
import lasergame.geometry.LineSegment;
import lasergame.vectormath.Vector2;

import static org.junit.Assert.*;
import org.junit.Test;

public class LineSegmentTest {

	@Test
	public void testCreateLineSegment() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(2.0, 5.1);
		
		final LineSegment seg = new LineSegment(vecA, vecB);
		
		final Vector2 start = seg.getStartPoint();
		final Vector2 end = seg.getEndPoint();
		final Vector2 displace = seg.getDisplacement();
		
		assertEquals(1.0, start.x, Constants.EPSILON);
		assertEquals(2.5, start.y, Constants.EPSILON);

		assertEquals(2.0, displace.x, Constants.EPSILON);
		assertEquals(5.1, displace.y, Constants.EPSILON);

		assertEquals(3.0, end.x, Constants.EPSILON);
		assertEquals(7.6, end.y, Constants.EPSILON);
	}
	
	@Test
	public void testCreateLineSegmentFromPoints() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(3.0, 7.6);
		
		final LineSegment seg = LineSegment.fromPoints(vecA, vecB);
		
		final Vector2 start = seg.getStartPoint();
		final Vector2 end = seg.getEndPoint();
		final Vector2 displace = seg.getDisplacement();
		
		assertEquals(1.0, start.x, Constants.EPSILON);
		assertEquals(2.5, start.y, Constants.EPSILON);

		assertEquals(2.0, displace.x, Constants.EPSILON);
		assertEquals(5.1, displace.y, Constants.EPSILON);

		assertEquals(3.0, end.x, Constants.EPSILON);
		assertEquals(7.6, end.y, Constants.EPSILON);
	}

	@Test
	public void testIntersectLineSegments() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(2, 4));
		
		final Vector2 intersection = segA.getIntersection(segB);

		assertEquals(2.5, intersection.x, Constants.EPSILON);
		assertEquals(3.5, intersection.y, Constants.EPSILON);
	}

	@Test
	public void testIntersectLineSegmentsAtTheTip() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(0, 3), new Vector2(0, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(-3, 5));
		
		final Vector2 intersection = segA.getIntersection(segB);

		assertEquals(0.0, intersection.x, Constants.EPSILON);
		assertEquals(4.0, intersection.y, Constants.EPSILON);
	}
	
	@Test
	public void testNonIntersectingLineSegmentsJustPastTheTip() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(0, 3), new Vector2(0, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(-3, 5.0001));
		
		final Vector2 intersection = segA.getIntersection(segB);

		assertNull(intersection);
	}

	@Test
	public void testNonIntersectingLineSegments() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(3.5, 4));
		
		final Vector2 intersection = segA.getIntersection(segB);

		assertNull(intersection);
	}
	
	@Test
	public void testNonIntersectingLineSegmentsParallel() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(4, 4));
		
		final Vector2 intersection = segA.getIntersection(segB);

		assertNull(intersection);
	}
}
