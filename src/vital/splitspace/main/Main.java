package vital.splitspace.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

/**
 * The program's entry point. Handles lower-level initialization of
 * the Slick2D game.
 */
public class Main
{
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app;
			app = new AppGameContainer(new SplitSpace("Split Space"));
			app.setDisplayMode(	GlobalConstants.GAME_WIDTH,
								GlobalConstants.GAME_HEIGHT,
								false);
			app.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
		return;
	}
}
