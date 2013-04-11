package lasergame;

import lasergame.vectormath.Vector2;

public class Intersection {
	public Intersection(Vector2 point,double distance, ILevelEntity entity){
		this.point = point;
		this.distance = distance;
		this.entity = entity;
	}
	
	public Vector2 point;
	public double distance;
	public ILevelEntity entity;
}
