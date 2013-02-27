package lasergame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;

public class Wall implements IRenderable {
	private int _x;
	private int _y;
	private int _height;
	private int _width;
	
	public Wall(int x, int y, int width, int height){
		_x = x;
		_y = y;
		_height = height;
		_width = width;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.draw(new Rectangle(_x,_y,_width,_height));
	}
}
