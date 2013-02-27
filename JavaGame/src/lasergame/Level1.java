package lasergame;

import org.newdawn.slick.*;

public class Level1 implements ILevel {
	private Wall _bottomWall= new Wall(390, 200, 20, 400);
	private Wall _topWall = new Wall(200, 0, 400, 20);
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		_bottomWall.render(gc,g);
		_topWall.render(gc,g);
	}
	
}
