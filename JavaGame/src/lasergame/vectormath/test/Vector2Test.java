package lasergame.vectormath.test;

import static org.junit.Assert.*;
import lasergame.Constants;
import lasergame.vectormath.Vector2;

import org.junit.Test;

public class Vector2Test {

	@Test
	public void testAddVector() {
		Vector2 vecA = new Vector2(1.0, 2.5);
		Vector2 vecB = new Vector2(2.0, 5.1);
		
		vecA.add(vecB);

		assertEquals(3.0, vecA.x, Constants.EPSILON);
		assertEquals(7.6, vecA.y, Constants.EPSILON);
	}
	
	@Test
	public void testSubtractVector() {
		Vector2 vecA = new Vector2(1.0, 2.5);
		Vector2 vecB = new Vector2(2.0, 5.1);
		
		vecA.subtract(vecB);
		
		assertEquals(-1.0, vecA.x, Constants.EPSILON);
		assertEquals(-2.6, vecA.y, Constants.EPSILON);
	}
	
	@Test
	public void testMultiplyVector() {
		Vector2 vecA = new Vector2(1.0, 2.5);
		Vector2 vecB = new Vector2(-2.0, 5.1);
		
		vecA.multiply(2.5);
		vecB.multiply(-1.2);

		assertEquals(2.5, vecA.x, Constants.EPSILON);
		assertEquals(6.25, vecA.y, Constants.EPSILON);
		assertEquals(2.4, vecB.x, Constants.EPSILON);
		assertEquals(-6.12, vecB.y, Constants.EPSILON);
	}

	@Test
	public void testVectorNotEquals() {
		Vector2 vecA = new Vector2(1.0, 2.5);
		Vector2 vecB = new Vector2(-2.0, 5.1);
		
		assertFalse(vecA.equals(vecB));
	}
	
	@Test
	public void testVectorNotEqualsXSame() {
		Vector2 vecA = new Vector2(1.0, 2.5);
		Vector2 vecB = new Vector2(1.0, 5.1);
		
		assertFalse(vecA.equals(vecB));
	}

	@Test
	public void testVectorNotEqualsYSame() {
		Vector2 vecA = new Vector2(1.0, 2.5);
		Vector2 vecB = new Vector2(-2.0, 2.5);
		
		assertFalse(vecA.equals(vecB));
	}
	
	@Test
	public void testVectorEquals() {
		Vector2 vecA = new Vector2(1.0, 2.5);
		Vector2 vecB = new Vector2(1.0, 2.5);
		
		assertTrue(vecA.equals(vecB));
	}

	@Test
	public void testLengthXOnly() {
		Vector2 vecA = new Vector2(1.0, 0.0);
		
		assertEquals(1.0, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthWithNegativeX() {
		Vector2 vecA = new Vector2(-2.0, 0.0);
		
		assertEquals(2.0, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthYOnly() {
		Vector2 vecA = new Vector2(0.0, 2.2);
		
		assertEquals(2.2, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthWithNegativeY() {
		Vector2 vecA = new Vector2(0.0, -3.0);
		
		assertEquals(3.0, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthXY() {
		Vector2 vecA = new Vector2(3.0, -4.0);
		
		assertEquals(5.0, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthZero() {
		Vector2 vecA = new Vector2(0.0, 0.0);
		
		assertEquals(0.0, vecA.length(), Constants.EPSILON);
	}

	@Test
	public void testNormaliseVectorXOnly() {
		Vector2 vecA = new Vector2(3.0, 0.0);
		
		vecA.normalise();

		assertEquals(1.0, vecA.x, Constants.EPSILON);
		assertEquals(0.0, vecA.y, Constants.EPSILON);
	}
	
	@Test
	public void testNormaliseVectorYOnly() {
		Vector2 vecA = new Vector2(0.0, 0.004);
		
		vecA.normalise();

		assertEquals(0.0, vecA.x, Constants.EPSILON);
		assertEquals(1.0, vecA.y, Constants.EPSILON);
	}

	@Test
	public void testNormaliseVectorWithNegativeX() {
		Vector2 vecA = new Vector2(-45.2, 0.0);
		
		vecA.normalise();

		assertEquals(-1.0, vecA.x, Constants.EPSILON);
		assertEquals(0.0, vecA.y, Constants.EPSILON);
	}
	
	@Test
	public void testNormaliseVectorWithNegativeY() {
		Vector2 vecA = new Vector2(0.0, -30.0);
		
		vecA.normalise();

		assertEquals(0.0, vecA.x, Constants.EPSILON);
		assertEquals(-1.0, vecA.y, Constants.EPSILON);
	}

	@Test
	public void testNormaliseVectorBig() {
		Vector2 vecA = new Vector2(100000000.0, 100000000.0);
		
		vecA.normalise();

		assertEquals(Constants.ONE_OVER_SQRT_TWO, vecA.x, Constants.EPSILON);
		assertEquals(Constants.ONE_OVER_SQRT_TWO, vecA.y, Constants.EPSILON);
	}
	
	@Test
	public void testNormaliseVectorSmall() {
		Vector2 vecA = new Vector2(0.000001, 0.000001);
		
		vecA.normalise();

		assertEquals(Constants.ONE_OVER_SQRT_TWO, vecA.x, Constants.EPSILON);
		assertEquals(Constants.ONE_OVER_SQRT_TWO, vecA.y, Constants.EPSILON);
	}

	@Test
	public void testNormaliseZeroLengthVectorReturnsZeroLengthVector() {
		Vector2 vecA = new Vector2(0.0, 0.0);
		
		vecA.normalise();
		
		assertEquals(0.0, vecA.x, Constants.EPSILON);
		assertEquals(0.0, vecA.y, Constants.EPSILON);
	}

	@Test
	public void testDotProductWithSelf() {
		Vector2 vecA = new Vector2(3.0, 7.5);
		Vector2 vecB = new Vector2(3.0, 7.5);
		
		double dot = vecA.dot(vecB);
		
		assertEquals(65.25, dot, Constants.EPSILON);
	}

	@Test
	public void testDotProductWithSelfSameObject() {
		Vector2 vecA = new Vector2(3.0, 7.5);
		
		double dot = vecA.dot(vecA);
		
		assertEquals(65.25, dot, Constants.EPSILON);
	}
	
	@Test
	public void testDotProductWithOrthogonal() {
		Vector2 vecA = new Vector2(3.0, 7.5);
		Vector2 vecB = new Vector2(7.5, -3.0);
		
		double dot = vecA.dot(vecB);
		
		assertEquals(0.0, dot, Constants.EPSILON);
	}
	
	@Test
	public void testDotProductWithReverse() {
		Vector2 vecA = new Vector2(3.0, 7.5);
		Vector2 vecB = new Vector2(-3.0, -7.5);
		
		double dot = vecA.dot(vecB);
		
		assertEquals(-65.25, dot, Constants.EPSILON);
	}
	
	@Test
	public void testAngleWithSelf() {
		Vector2 vecA = new Vector2(3.0, 7.5);
		Vector2 vecB = new Vector2(3.0, 7.5);
		
		double angle = vecA.angleWith(vecB);
		
		assertEquals(0.0, angle, Constants.EPSILON);
	}
	
	@Test
	public void testAngleWithOrthogonal() {
		Vector2 vecA = new Vector2(3.0, 7.5);
		Vector2 vecB = new Vector2(7.5, -3.0);
		
		double angle = vecA.angleWith(vecB);
		
		assertEquals(Math.PI / 2.0, angle, Constants.EPSILON);
	}
	
	@Test
	public void testAngleWithReverse() {
		Vector2 vecA = new Vector2(3.0, 7.5);
		Vector2 vecB = new Vector2(-3.0, -7.5);
		
		double angle = vecA.angleWith(vecB);
		
		assertEquals(Math.PI, angle, Constants.EPSILON);
	}
}
