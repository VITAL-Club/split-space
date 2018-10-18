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
								true);
			app.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
		return;
	}
}

// For any future visitors, this was duct taped together with way less time
// than it deserved. As such it probably isn't the best code to learn any
// good techniques from.

// As you go through the commit history, you'll slowly see good intentions
// die :D
