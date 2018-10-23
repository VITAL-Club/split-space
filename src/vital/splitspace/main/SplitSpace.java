package vital.splitspace.main;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import vital.splitspace.contentManagement.Overseer;
import vital.splitspace.entity.Ship;
import vital.splitspace.entity.Star;

/**
 * This class represents the main game and all of its
 * various loops. 
 *
 */
public class SplitSpace extends BasicGame
{
	private Overseer overseer;
	
	public SplitSpace(String title)
	{
		super(title);
		return;
	}

	@Override
	public void render(GameContainer game, Graphics gfx) throws SlickException
	{
		overseer.draw(game, gfx);
		
		return;
	}

	@Override
	public void init(GameContainer game) throws SlickException
	{
		game.setVSync(true);
		game.setTargetFrameRate(60);
		
		overseer = new Overseer();
		
		game.setShowFPS(false);
		
		overseer.addEntity(new Star());
		
		// Adds the player to the game loop
		Ship player = new Ship();
		// Puts player 3/4ths of the way down the screen
		overseer.addEntity(player);
		
		game.setMouseGrabbed(true);
		
		return;
	}

	@Override
	public void update(GameContainer game, int arg1) throws SlickException
	{
		overseer.update(game.getInput());
		
		if (game.getInput().isKeyPressed(Input.KEY_ESCAPE))
			game.exit();
		
		return;
	}
	
}
