package lasergame.vectormath.test;

import static org.junit.Assert.*;
import lasergame.vectormath.Vector2;

import org.junit.Test;

public class Vector2Test {

	@Test
	public void testAddVector() {
		Vector2 vecA = new Vector2(1.0f, 2.5f);
		Vector2 vecB = new Vector2(2.0f, 5.1f);
		
		vecA.add(vecB);

		assertEquals(vecA.x, 3.0f, 0.0001f);
		assertEquals(vecA.y, 7.6f, 0.0001f);
	}
	
	@Test
	public void testSubtractVector() {
		Vector2 vecA = new Vector2(1.0f, 2.5f);
		Vector2 vecB = new Vector2(2.0f, 5.1f);
		
		vecA.subtract(vecB);
		
		assertEquals(vecA.x, -1.0f, 0.0001f);
		assertEquals(vecA.y, -2.6f, 0.0001f);
	}
	
	@Test
	public void testMultiplyVector() {
		Vector2 vecA = new Vector2(1.0f, 2.5f);
		Vector2 vecB = new Vector2(-2.0f, 5.1f);
		
		vecA.multiply(2.5f);
		vecB.multiply(-1.2f);

		assertEquals(vecA.x, 2.5f, 0.0001f);
		assertEquals(vecA.y, 6.25f, 0.0001f);
		assertEquals(vecB.x, 2.4f, 0.0001f);
		assertEquals(vecB.y, -6.12f, 0.0001f);
	}

}
