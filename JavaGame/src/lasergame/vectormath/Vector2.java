package lasergame.vectormath;

import lasergame.Constants;

public class Vector2 {
	
	public double x, y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void add(Vector2 rhs) {
		x += rhs.x;
		y += rhs.y;
	}
	
	public void subtract(Vector2 rhs) {
		x -= rhs.x;
		y -= rhs.y;
	}
	
	public void multiply(double rhs) {
		x *= rhs;
		y *= rhs;
	}
	
	public void normalise() {
		double length = length();
		
		if (length == 0.0) {
			// todo: might be worth warning or logging this case
			length = 1.0;
		}
		
		x /= length;
		y /= length;
	}
	
	public double length() {
		return Math.sqrt(dot(this));
	}
	
	public double dot(Vector2 rhs) {
		return (this.x * rhs.x + this.y * rhs.y);
	}

	private double cosAngleWith(Vector2 rhs) {
		double lengthMult = (this.length() * rhs.length());
		
		if (lengthMult == 0.0) {
			lengthMult = 1.0;
		}
		double cosAngle = dot(rhs) / lengthMult;
		
		// Deal with floating point errors
		cosAngle = Math.min(Math.max(cosAngle, -1.0), 1.0);
		
		return cosAngle;
	}
	
	public double angleWith(Vector2 rhs) {
		double cosAngle = cosAngleWith(rhs);
		return Math.acos(cosAngle);
	}
	
	public boolean equals(Vector2 rhs) {
		if (rhs == null) return false;
		
		return (this.x >= rhs.x - Constants.EPSILON && 
				this.y >= rhs.y - Constants.EPSILON &&
				this.x <= rhs.x + Constants.EPSILON &&
				this.y <= rhs.y + Constants.EPSILON);
	}
}
