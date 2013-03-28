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
	}

}
