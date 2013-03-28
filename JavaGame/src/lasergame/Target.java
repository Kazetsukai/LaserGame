package lasergame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Target implements IRenderable {
	private int _x;
	private int _y;
	
	public Target(int x, int y){
		_x = x;
		_y = y;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.draw(new Circle(_x,_y, 30));
	}
}
