package lasergame.vectormath;

import lasergame.Constants;

/***
 * A vector in two dimensions.
 * 
 * This class is intended to be immutable.
 * @author Kazetsukai
 *
 */
public final class Vector2 {
	
	public final double x, y;
	
	public Vector2(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public final Vector2 add(final Vector2 rhs) {
		return new Vector2(x + rhs.x, y + rhs.y);
	}
	
	public final Vector2 subtract(final Vector2 rhs) {
		return new Vector2(x - rhs.x, y - rhs.y);
	}
	
	public final Vector2 multiply(final double rhs) {
		return new Vector2(x * rhs, y * rhs);
	}
	
	/***
	 * @return the unit vector (length == 1) representation of the vector 
	 */
	public final Vector2 normalise() {
		double length = length();
		
		if (length == 0.0) {
			// todo: might be worth warning or logging this case
			length = 1.0;
		}
		
		return new Vector2(x / length, y / length);
	}
	
	/***
	 * @return the length of the vector in the Cartesian plane
	 */
	public final double length() {
		return Math.sqrt(dot(this));
	}

	/***
	 * @return the dot product of this vector with another
	 */
	public final double dot(final Vector2 rhs) {
		return (this.x * rhs.x + this.y * rhs.y);
	}
	
	/***
	 * @return the magnitude of the 3d cross product of this vector with another
	 */
	public final double crossMagnitude(final Vector2 rhs) {
		return (this.x * rhs.y - this.y * rhs.x);
	}

	/***
	 * @return the cosine of the angle between this vector and another
	 */
	private final double cosAngleWith(final Vector2 rhs) {
		double lengthMult = (this.length() * rhs.length());
		
		if (lengthMult == 0.0) {
			return 1.0; // Call the acos (and thus angle) 0 when one vector is a zero vector.
		}
		final double cosAngle = dot(rhs) / lengthMult;
		
		// Deal with floating point errors
		return Math.min(Math.max(cosAngle, -1.0), 1.0);
	}
	
	/***
	 * @return the angle between this vector and another, in radians
	 */
	public final double angleWith(final Vector2 rhs) {
		final double cosAngle = cosAngleWith(rhs);
		return Math.acos(cosAngle);
	}
	
	public final boolean equals(final Vector2 rhs) {
		if (rhs == null) return false;
		
		return (this.x >= rhs.x - Constants.EPSILON && 
				this.y >= rhs.y - Constants.EPSILON &&
				this.x <= rhs.x + Constants.EPSILON &&
				this.y <= rhs.y + Constants.EPSILON);
	}
	
	public final Vector2 projectOnto(Vector2 rhs) {
		Vector2 unit = rhs.normalise();
		return unit.multiply(this.dot(unit));
	}
	
	public final Vector2 normalRight() {
		return new Vector2(-y, x).normalise();
	}
	
	public final Vector2 normalLeft() {
		return new Vector2(y, -x).normalise();
	}
	
	public final Vector2 reflectOver(Vector2 rhs) {
		return this.add(this.projectOnto(rhs).subtract(this).multiply(2));
	}
	
	public final double distanceTo(Vector2 rhs) {
		return this.subtract(rhs).length();
	}
	
	public static final Vector2 Zero = new Vector2(0,0);
}
