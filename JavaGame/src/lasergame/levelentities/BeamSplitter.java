package lasergame.levelentities;

import java.util.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;

import lasergame.*;
import lasergame.geometry.*;
import lasergame.vectormath.*;

public class BeamSplitter implements ILevelEntity, IStrikable{
	protected LineSegment mLine;
	protected ILevel mLevel;
	
	protected Vector2 mCorner1;
	protected Vector2 mCorner2;
	protected Vector2 mCorner3;
	protected Vector2 mCorner4;

	/**
	 * @param The diagonal reflection line around which the splitter square is formed 
	 */
	public BeamSplitter(LineSegment line, ILevel level){
		mLine = line;
		mLevel = level;
		
		Vector2 normal = mLine.getDisplacement().normalLeft().multiply(mLine.getDisplacement().length()/2);
				
		mCorner1 = mLine.getStartPoint();
		mCorner3 = mLine.getEndPoint();

		mCorner2 = line.center().add(normal);
		mCorner4 = line.center().subtract(normal);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.draw(new Circle((float)mCorner1.x, (float)mCorner1.y, 3));
		g.draw(new Circle((float)mCorner3.x, (float)mCorner3.y, 3));
		
		g.draw(getLine(mCorner1, mCorner2));
		g.draw(getLine(mCorner2, mCorner3));
		g.draw(getLine(mCorner3, mCorner4));
		g.draw(getLine(mCorner4, mCorner1));
		g.draw(getLine(mCorner1, mCorner3));
	}
	
	protected Line getLine(Vector2 start, Vector2 end) {
		return new Line((float)start.x, (float)start.y, (float)end.x, (float)end.y);
	}

	@Override
	public void update(GameContainer gc, double timeElapsed) {
	}

	@Override
	public void strike(ILevelEntity entity) {
		LaserBeam beam = (LaserBeam)entity;
		Vector2 intersection = beam.getBeam().getSegmentIntersection(mLine);
		
		mLevel.spawn(new LaserBeam(mLevel, intersection.add(beam._velocity.normalise().multiply(10)), beam._velocity));
	}

	@Override
	public List<Intersection> getIntersectionsWith(LineSegment line) {

		LinkedList<Intersection> intersections = new LinkedList<Intersection>();

		Vector2 intersection = line.getSegmentIntersection(mLine);
		if (intersection != null) {
			intersections.add(new Intersection(intersection, mLine.getDisplacement(), intersection.subtract(line.getStartPoint()).length(), this));
		}
		
		return intersections;
	}

}
