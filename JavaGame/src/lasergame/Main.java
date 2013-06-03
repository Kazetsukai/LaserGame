package lasergame;
import lasergame.levels.Level1;
import lasergame.levels.Levelx;
import lasergame.levels.LukesLevel;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
public class Main extends BasicGame
{
	static int windowX = 800;
	static int windowY = 600;
	static Level1 level1 = new Level1();
	static LukesLevel lukesLevel = new LukesLevel();
	static Levelx levelx = new Levelx();
	
	static final double TIMESTEP = 0.001;
	
	double mTimeLeftOver = 0;
	
	
  public Main()
  {
     super("Hello World");
  }
 
  @Override
  public void init(GameContainer gc) throws SlickException
  {
	  mTimeLeftOver = 0;
  }
 
  @Override
  public void update(GameContainer gc, int delta) throws SlickException
  {
	  double timeElapsed = delta / 1000.0;
	  
	  mTimeLeftOver += timeElapsed;
	  
	  int updatesThisFrame = 0;
	  
	  while (mTimeLeftOver > TIMESTEP) {
		  mTimeLeftOver -= TIMESTEP;
		  updatesThisFrame++;
		  lukesLevel.update(gc, TIMESTEP);
		  
		  if (updatesThisFrame > 1 / TIMESTEP) {
			  mTimeLeftOver = 0;
			  break;
		  }
	  }
	  
	  System.out.println("     Updates: " + updatesThisFrame);
  }
 
  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException
  {
     lukesLevel.render(gc, g);
  }

 
  public static void main(String[] args) throws SlickException
  {
     AppGameContainer app = new AppGameContainer(new Main());
     app.setTargetFrameRate(60);
     app.setDisplayMode(windowX, windowY, false);
     app.start();
  }
}