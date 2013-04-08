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
}
