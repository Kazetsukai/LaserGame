package lasergame;

import org.newdawn.slick.*;

public class Level1 implements ILevel {
	private Wall _bottomWall= new Wall(390, 200, 20, 400);
	private Wall _topWall = new Wall(200, 0, 400, 20);
	private LaserEmitter _emitter = new LaserEmitter(100,500);
	private Target _target = new Target(600, 500);
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		_bottomWall.render(gc,g);
		_topWall.render(gc,g);
		_emitter.render(gc,g);
		_target.render(gc,g);
	}
	
}
