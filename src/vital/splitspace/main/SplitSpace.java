package vital.splitspace.main;

import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import vital.splitspace.drawable.Drawable;
import vital.splitspace.entity.Entity;
import vital.splitspace.entity.Ship;

/**
 * This class represents the main game and all of its
 * various loops. 
 *
 */
public class SplitSpace extends BasicGame
{
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Drawable> drawables = new ArrayList<>();
	
	// User input is processed through this object.
	private Input input = new Input(GlobalConstants.GAME_HEIGHT);
	
	public SplitSpace(String title)
	{
		super(title);
		return;
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException
	{
		for (Drawable d : drawables)
			d.draw(arg0, arg1);
		
		return;
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		arg0.setShowFPS(true);
		
		// Adds the player to the game loop
		Ship player = new Ship();
		entities.add(player);
		drawables.add(player);
		
		return;
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException
	{
		// This checks the game window for any input between now and
		// the last frame.
		input.poll(GlobalConstants.GAME_WIDTH, GlobalConstants.GAME_HEIGHT);
		
		for (Entity e : entities)
			e.update(input);
		
		return;
	}
	
}
