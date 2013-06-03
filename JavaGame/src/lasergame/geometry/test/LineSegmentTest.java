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
		
		LineSegment seg = new LineSegment(vecA, vecB);
		
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
		
		LineSegment seg = LineSegment.fromPoints(vecA, vecB);
		
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
		
		Vector2 intersection = segA.getSegmentIntersection(segB);

		assertEquals(2.5, intersection.x, Constants.EPSILON);
		assertEquals(3.5, intersection.y, Constants.EPSILON);
	}

	@Test
	public void testIntersectLineSegmentsAtTheTip() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(0, 3), new Vector2(0, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(-3, 5));
		
		Vector2 intersection = segA.getSegmentIntersection(segB);

		assertEquals(0.0, intersection.x, Constants.EPSILON);
		assertEquals(4.0, intersection.y, Constants.EPSILON);
	}
	
	@Test
	public void testNonIntersectingLineSegmentsJustPastTheTip() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(0, 3), new Vector2(0, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(-3, 5.0001));
		
		Vector2 intersection = segA.getSegmentIntersection(segB);

		assertNull(intersection);
	}

	@Test
	public void testNonIntersectingLineSegments() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(3.5, 4));
		
		Vector2 intersection = segA.getSegmentIntersection(segB);

		assertNull(intersection);
	}

	@Test
	public void testNonIntersectingLineSegmentsParallel() {
		final LineSegment segA = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		final LineSegment segB = LineSegment.fromPoints(new Vector2(3, 3), new Vector2(4, 4));
		
		Vector2 intersection = segA.getSegmentIntersection(segB);

		assertNull(intersection);
	}

	@Test
	public void testScale() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		
		LineSegment result = seg.scale(2);

		final Vector2 start = result.getStartPoint();
		final Vector2 displace = result.getDisplacement();
		
		assertEquals(2.0, start.x, Constants.EPSILON);
		assertEquals(3.0, start.y, Constants.EPSILON);

		assertEquals(2.0, displace.x, Constants.EPSILON);
		assertEquals(2.0, displace.y, Constants.EPSILON);
	}
	
	@Test
	public void testScaleNegative() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		
		LineSegment result = seg.scale(-1);

		final Vector2 start = result.getStartPoint();
		final Vector2 displace = result.getDisplacement();
		
		assertEquals(2.0, start.x, Constants.EPSILON);
		assertEquals(3.0, start.y, Constants.EPSILON);

		assertEquals(-1.0, displace.x, Constants.EPSILON);
		assertEquals(-1.0, displace.y, Constants.EPSILON);
	}
	
	@Test
	public void testIsPointOnLeftInYGoesDownWorld() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		final Vector2 pointLeft = new Vector2(3, 3);
		final Vector2 pointRight = new Vector2(2, 4);
		
		boolean leftOnLeft = seg.isPointOnLeft(pointLeft);
		boolean rightOnLeft = seg.isPointOnLeft(pointRight);
		
		assertTrue(leftOnLeft);
		assertFalse(rightOnLeft);
	}

	@Test
	public void testIsPointOnRightInYGoesDownWorld() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		final Vector2 pointLeft = new Vector2(3, 3);
		final Vector2 pointRight = new Vector2(2, 4);
		
		boolean leftOnRight = seg.isPointOnRight(pointLeft);
		boolean rightOnRight = seg.isPointOnRight(pointRight);
		
		assertTrue(rightOnRight);
		assertFalse(leftOnRight);
	}
	
	@Test
	public void testLeftNormalIsOnLeft() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		
		Vector2 pointLeft = seg.getStartPoint().add(seg.getDisplacement().normalLeft());
		boolean result = seg.isPointOnLeft(pointLeft);
		
		assertTrue(result);
	}
	
	@Test
	public void testPointOnLineIsNeitherLeftNorRight() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(3, 4));
		final Vector2 point = new Vector2(2.5, 3.5);
		
		boolean onLeft = seg.isPointOnLeft(point);
		boolean onRight = seg.isPointOnRight(point);
		
		assertFalse(onLeft);
		assertFalse(onRight);
	}
	
	@Test
	public void testReflectPointOverVertical() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(2, 40));
		final Vector2 point = new Vector2(-6, 25);
		
		Vector2 reflected = seg.reflectPoint(point);

		assertEquals(10.0, reflected.x, Constants.EPSILON);
		assertEquals(25.0, reflected.y, Constants.EPSILON);
	}
	
	@Test
	public void testReflectPointOverDiagonal() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 2), new Vector2(12, 22));
		final Vector2 point = new Vector2(5, 13);
		
		Vector2 reflected = seg.reflectPoint(point);

		assertEquals(9.0, reflected.x, Constants.EPSILON);
		assertEquals(11.0, reflected.y, Constants.EPSILON);
	}

	@Test
	public void testNearestPointToVertical() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 3), new Vector2(2, 40));
		final Vector2 point = new Vector2(-6, 25);
		
		Vector2 reflected = seg.nearestPointTo(point);

		assertEquals(2.0, reflected.x, Constants.EPSILON);
		assertEquals(25.0, reflected.y, Constants.EPSILON);
	}
	
	@Test
	public void testNearestPointToDiagonal() {
		final LineSegment seg = LineSegment.fromPoints(new Vector2(2, 2), new Vector2(12, 22));
		final Vector2 point = new Vector2(5, 13);
		
		Vector2 reflected = seg.nearestPointTo(point);

		assertEquals(7.0, reflected.x, Constants.EPSILON);
		assertEquals(12.0, reflected.y, Constants.EPSILON);
	}
	
}
