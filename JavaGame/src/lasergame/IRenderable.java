package lasergame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IRenderable {
	void render(GameContainer gc, Graphics g);
	void update(GameContainer gc, double timeElapsed);
}
