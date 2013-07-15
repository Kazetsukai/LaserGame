package lasergame.levelentities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import lasergame.ILevel;
import lasergame.ILevelEntity;
import lasergame.geometry.LineSegment;

public class BreakableWall extends Wall {

	private int _hitsToBreak;
	private int _hitsRemaining;
	private ILevel _level;
	
	public BreakableWall(ILevel level, LineSegment line, double width, int hitsToBreak) {
		super(line, width);
		 
		_hitsToBreak = hitsToBreak;
		_hitsRemaining = _hitsToBreak;
		_level = level;
	}
	
	@Override
	public void strike(ILevelEntity entity) {
		_hitsRemaining--;

		if (_hitsRemaining <= 0) 
			_level.remove(this);
		
		super.strike(entity);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		if (_hitsRemaining < 2) g.draw(getLine(mTopLeft, mBottomRight));
		if (_hitsRemaining < 3) g.draw(getLine(mTopRight, mBottomLeft));
		if (_hitsRemaining < 4) g.draw(getLine(mLine.getStartPoint(), mLine.getEndPoint()));
		
		super.render(gc, g);
	}
	
}
