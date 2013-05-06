package lasergame;
import lasergame.levels.Level1;

import org.lwjgl.opengl.GL11;
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
	  GL11.glMatrixMode(GL11.GL_PROJECTION);
	  GL11.glLoadIdentity();
	  GL11.glOrtho(0, 512, 0, 512, 1, -1);
	  GL11.glMatrixMode(GL11.GL_MODELVIEW);
	  
	  GL11.glEnable(GL11.GL_TEXTURE_2D);
  }
 
  @Override
  public void update(GameContainer gc, int delta) throws SlickException
  {
	  System.out.println(delta);
	  
	  double timeElapsed = delta / 1000.0;
	  
	  level1.update(gc, timeElapsed);
  }
 
  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException
  {
	  clearGL();
     level1.render(gc, g);
  }
  
  public void clearGL(){
	  GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	  GL11.glLoadIdentity();
  }

 
  public static void main(String[] args) throws SlickException
  {
     AppGameContainer app = new AppGameContainer(new Main());
     app.setTargetFrameRate(60);
     app.setDisplayMode(windowX, windowY, false);
     app.start();
  }
}