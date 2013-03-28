package lasergame.vectormath;

public class Vector2 {
	
	public double x, y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void add(Vector2 a) {
		x += a.x;
		y += a.y;
	}
	
	public void subtract(Vector2 a) {
		x -= a.x;
		y -= a.y;
	}
	
	public void multiply(float a) {
		x *= a;
		y *= a;
	}
	
	public void normalise() {
		double length = length();
		
		if (length == 0.0f) 
			length = 1.0f;
		
		x /= length;
		y /= length;
	}
	
	public double length() {
		return Math.sqrt(x * x + y * y);
	}
}
