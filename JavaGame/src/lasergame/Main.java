package lasergame;
import org.newdawn.slick.*;
public class Main extends BasicGame
{
	static int windowX = 800;
	static int windowY = 600;
	static Level1 level1 = new Level1();
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
     level1.render(gc, g);
  }
 
  public static void main(String[] args) throws SlickException
  {
     AppGameContainer app = new AppGameContainer(new Main());
     app.setTargetFrameRate(60);
     app.setDisplayMode(windowX, windowY, false);
     app.start();
  }
}