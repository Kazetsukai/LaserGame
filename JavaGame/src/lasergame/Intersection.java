package lasergame;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	
	public static void sortIntersections(List<Intersection> list){
		Collections.sort(list, new Comparator<Intersection>(){
			  public int compare(Intersection i1, Intersection i2) {
				    return Double.compare(i1.distance,i2.distance);
				  }
		});
	}
}