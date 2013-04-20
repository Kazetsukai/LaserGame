package lasergame.vectormath.test;

import static org.junit.Assert.*;
import lasergame.Constants;
import lasergame.vectormath.Vector2;

import org.junit.Test;

public class Vector2Test {

	@Test
	public void testAddVector() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(2.0, 5.1);
		
		final Vector2 result = vecA.add(vecB);

		assertEquals(3.0, result.x, Constants.EPSILON);
		assertEquals(7.6, result.y, Constants.EPSILON);
	}
	
	@Test
	public void testSubtractVector() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(2.0, 5.1);
		
		final Vector2 result = vecA.subtract(vecB);
		
		assertEquals(-1.0, result.x, Constants.EPSILON);
		assertEquals(-2.6, result.y, Constants.EPSILON);
	}
	
	@Test
	public void testMultiplyVector() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(-2.0, 5.1);
		
		final Vector2 result = vecA.multiply(2.5);
		final Vector2 result2 = vecB.multiply(-1.2);

		assertEquals(2.5, result.x, Constants.EPSILON);
		assertEquals(6.25, result.y, Constants.EPSILON);
		assertEquals(2.4, result2.x, Constants.EPSILON);
		assertEquals(-6.12, result2.y, Constants.EPSILON);
	}

	@Test
	public void testVectorNotEquals() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(-2.0, 5.1);
		
		assertFalse(vecA.equals(vecB));
	}
	
	@Test
	public void testVectorNotEqualsXSame() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(1.0, 5.1);

		assertFalse(vecA.equals(vecB));
		assertFalse(vecB.equals(vecA));
	}

	@Test
	public void testVectorNotEqualsYSame() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(-2.0, 2.5);

		assertFalse(vecA.equals(vecB));
		assertFalse(vecB.equals(vecA));
	}
	
	@Test
	public void testVectorEquals() {
		final Vector2 vecA = new Vector2(1.0, 2.5);
		final Vector2 vecB = new Vector2(1.0, 2.5);

		assertTrue(vecA.equals(vecB));
		assertTrue(vecB.equals(vecA));
	}
	
	@Test
	public void testVectorNotEqualsNull() {
		final Vector2 vecA = new Vector2(1.21, 23.52);
		final Vector2 vecB = new Vector2(0, 0);

		assertFalse(vecA.equals(null));
		assertFalse(vecB.equals(null));
	}

	@Test
	public void testLengthXOnly() {
		final Vector2 vecA = new Vector2(1.0, 0.0);
		
		assertEquals(1.0, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthWithNegativeX() {
		final Vector2 vecA = new Vector2(-2.0, 0.0);
		
		assertEquals(2.0, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthYOnly() {
		final Vector2 vecA = new Vector2(0.0, 2.2);
		
		assertEquals(2.2, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthWithNegativeY() {
		final Vector2 vecA = new Vector2(0.0, -3.0);
		
		assertEquals(3.0, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthXY() {
		final Vector2 vecA = new Vector2(3.0, -4.0);
		
		assertEquals(5.0, vecA.length(), Constants.EPSILON);
	}
	
	@Test
	public void testLengthZero() {
		final Vector2 vecA = new Vector2(0.0, 0.0);
		
		assertEquals(0.0, vecA.length(), Constants.EPSILON);
	}

	@Test
	public void testNormaliseVectorXOnly() {
		final Vector2 vecA = new Vector2(3.0, 0.0);
		
		final Vector2 result = vecA.normalise();

		assertEquals(1.0, result.x, Constants.EPSILON);
		assertEquals(0.0, result.y, Constants.EPSILON);
	}
	
	@Test
	public void testNormaliseVectorYOnly() {
		final Vector2 vecA = new Vector2(0.0, 0.004);
		
		final Vector2 result = vecA.normalise();

		assertEquals(0.0, result.x, Constants.EPSILON);
		assertEquals(1.0, result.y, Constants.EPSILON);
	}

	@Test
	public void testNormaliseVectorWithNegativeX() {
		final Vector2 vecA = new Vector2(-45.2, 0.0);
		
		final Vector2 result = vecA.normalise();

		assertEquals(-1.0, result.x, Constants.EPSILON);
		assertEquals(0.0, result.y, Constants.EPSILON);
	}
	
	@Test
	public void testNormaliseVectorWithNegativeY() {
		final Vector2 vecA = new Vector2(0.0, -30.0);
		
		final Vector2 result = vecA.normalise();

		assertEquals(0.0, result.x, Constants.EPSILON);
		assertEquals(-1.0, result.y, Constants.EPSILON);
	}

	@Test
	public void testNormaliseVectorBig() {
		final Vector2 vecA = new Vector2(100000000.0, 100000000.0);
		
		final Vector2 result = vecA.normalise();

		assertEquals(Constants.ONE_OVER_SQRT_TWO, result.x, Constants.EPSILON);
		assertEquals(Constants.ONE_OVER_SQRT_TWO, result.y, Constants.EPSILON);
	}
	
	@Test
	public void testNormaliseVectorSmall() {
		final Vector2 vecA = new Vector2(0.000001, 0.000001);
		
		final Vector2 result = vecA.normalise();

		assertEquals(Constants.ONE_OVER_SQRT_TWO, result.x, Constants.EPSILON);
		assertEquals(Constants.ONE_OVER_SQRT_TWO, result.y, Constants.EPSILON);
	}

	@Test
	public void testNormaliseZeroLengthVectorReturnsZeroLengthVector() {
		final Vector2 vecA = new Vector2(0.0, 0.0);
		
		final Vector2 result = vecA.normalise();
		
		assertEquals(0.0, result.x, Constants.EPSILON);
		assertEquals(0.0, result.y, Constants.EPSILON);
	}

	@Test
	public void testDotProductWithSelf() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		final Vector2 vecB = new Vector2(3.0, 7.5);
		
		double dot = vecA.dot(vecB);
		
		assertEquals(65.25, dot, Constants.EPSILON);
	}

	@Test
	public void testDotProductWithSelfSameObject() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		
		double dot = vecA.dot(vecA);
		
		assertEquals(65.25, dot, Constants.EPSILON);
	}
	
	@Test
	public void testDotProductWithOrthogonal() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		final Vector2 vecB = new Vector2(7.5, -3.0);
		
		double dot = vecA.dot(vecB);
		
		assertEquals(0.0, dot, Constants.EPSILON);
	}
	
	@Test
	public void testDotProductWithReverse() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		final Vector2 vecB = new Vector2(-3.0, -7.5);
		
		double dot = vecA.dot(vecB);
		
		assertEquals(-65.25, dot, Constants.EPSILON);
	}

	@Test
	public void testAngleWithSelfIsZero() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		final Vector2 vecB = new Vector2(3.0, 7.5);
		
		double angle = vecA.angleWith(vecB);
		
		assertEquals(0.0, angle, Constants.EPSILON);
	}
	
	@Test
	public void testAngleWithZeroVectorIsZero() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		final Vector2 vecB = new Vector2(0.0, 0.0);
		
		double angle = vecA.angleWith(vecB);
		
		assertEquals(0.0, angle, Constants.EPSILON);
	}
	
	@Test
	public void testAngleWithOrthogonal() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		final Vector2 vecB = new Vector2(7.5, -3.0);
		
		double angle = vecA.angleWith(vecB);
		
		assertEquals(Math.PI / 2.0, angle, Constants.EPSILON);
	}
	
	@Test
	public void testAngleWithReverse() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		final Vector2 vecB = new Vector2(-3.0, -7.5);
		
		double angle = vecA.angleWith(vecB);
		
		assertEquals(Math.PI, angle, Constants.EPSILON);
	}
	
	@Test
	public void testOperationsDontMutate() {
		final Vector2 vecA = new Vector2(3.0, 7.5);
		final Vector2 vecB = new Vector2(-3.3, -5.5);

		vecA.add(vecB);
		_checkNotMutated(vecA, vecB);
		vecA.subtract(vecB);
		_checkNotMutated(vecA, vecB);
		vecA.multiply(4);
		_checkNotMutated(vecA, vecB);
		vecA.normalise();
		_checkNotMutated(vecA, vecB);
		vecA.dot(vecB);
		_checkNotMutated(vecA, vecB);
	}
	
	@Test
	public void testProjectionEqualsOriginalVectorWhenParallel() {
		final Vector2 vecA = new Vector2(1.0, 1.0);
		final Vector2 vecB = new Vector2(5.5, 5.5);
		
		Vector2 resultA = vecA.projectOnto(vecB);
		Vector2 resultB = vecB.projectOnto(vecA);
		
		assertEquals(resultA.x, vecA.x, Constants.EPSILON);
		assertEquals(resultA.y, vecA.y, Constants.EPSILON);
		assertEquals(resultB.x, vecB.x, Constants.EPSILON);
		assertEquals(resultB.y, vecB.y, Constants.EPSILON);
	}
	
	@Test
	public void testProjectionOntoAxisAlignedVectorEqualsComponentInThatAxis() {
		final Vector2 vecA = new Vector2(3.0, 4.0);
		final Vector2 vecB = new Vector2(0.0, 1.0);
		
		Vector2 resultA = vecA.projectOnto(vecB);
		
		assertEquals(0.0, resultA.x, Constants.EPSILON);
		assertEquals(4.0, resultA.y, Constants.EPSILON);
	}

	private void _checkNotMutated(final Vector2 vecA, final Vector2 vecB) {
		assertEquals(3.0, vecA.x, Constants.EPSILON);
		assertEquals(7.5, vecA.y, Constants.EPSILON);
		assertEquals(-3.3, vecB.x, Constants.EPSILON);
		assertEquals(-5.5, vecB.y, Constants.EPSILON);
	}

	@Test
	public void testCrossMagnitudeIsZeroForParallelVectors() {
		final Vector2 vecA = new Vector2(3.0, 4.0);
		final Vector2 vecB = new Vector2(6.0, 8.0);
		
		double result = vecA.crossMagnitude(vecB);
		
		assertEquals(0.0, result, Constants.EPSILON);
	}
	
	@Test
	public void testCrossMagnitudeIsLengthSquaredForPerpendicularVectors() {
		final Vector2 vecA = new Vector2(3.0, 4.0);
		final Vector2 vecB = new Vector2(6.0, 8.0);
		
		double result = vecA.crossMagnitude(vecB);
		
		assertEquals(0.0, result, Constants.EPSILON);
	}

	@Test
	public void testNormalLeftInYGoesDownWorld() {
		final Vector2 vecA = new Vector2(-6.0, 8.0);
		final Vector2 vecB = new Vector2(1.0, 0.0);
		
		Vector2 resultA = vecA.normalLeft();
		Vector2 resultB = vecB.normalLeft();

		assertEquals(0.8, resultA.x, Constants.EPSILON);
		assertEquals(0.6, resultA.y, Constants.EPSILON);
		assertEquals(0.0, resultB.x, Constants.EPSILON);
		assertEquals(-1.0, resultB.y, Constants.EPSILON);
	}
	
	@Test
	public void testNormalRightInYGoesDownWorld() {
		final Vector2 vecA = new Vector2(-6.0, 8.0);
		final Vector2 vecB = new Vector2(1.0, 0.0);
		
		Vector2 resultA = vecA.normalRight();
		Vector2 resultB = vecB.normalRight();

		assertEquals(-0.8, resultA.x, Constants.EPSILON);
		assertEquals(-0.6, resultA.y, Constants.EPSILON);
		assertEquals(0.0, resultB.x, Constants.EPSILON);
		assertEquals(1.0, resultB.y, Constants.EPSILON);
	}
}
