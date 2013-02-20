import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;
public class Main extends BasicGame
{
	static int windowX = 800;
	static int windowY = 600;
 
  public Main()
  {
     super("Hello World");
  }
 
  @Override
  public void init(GameContainer gc) throws SlickException
  {
 
  }
 
  @Override
  public void update(GameContainer gc, int delta) throws SlickException
  {
 
  }
 
  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException
  {
     g.drawString("Hello World", 100, 100);
     
     Image img = new Image("res/kitten.png");
     
     img.draw(Mouse.getX(), windowY - Mouse.getY());
  }
 
  public static void main(String[] args) throws SlickException
  {
     AppGameContainer app = new AppGameContainer(new Main());
 
     app.setDisplayMode(windowX, windowY, false);
     app.start();
  }
}