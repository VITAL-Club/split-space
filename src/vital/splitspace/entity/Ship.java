package vital.splitspace.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import vital.splitspace.datatypes.OrderedPair;
import vital.splitspace.main.GlobalConstants;

/**
 * The Ship is the main player object.
 */
public class Ship extends Controllable
{

	private int lives;
	
	public Ship()
	{
		// Loads the image for the Ship. This is passed back to
		// the DrawableImage parent class.
		super("resources/sprites/ship.png");
		
		this.speed = 4;
		this.scale = 3;
		
		this.lives = 3;
		
		setPosition(new OrderedPair(GlobalConstants.GAME_WIDTH / 2,
				   GlobalConstants.GAME_HEIGHT / 4*3));
		
		// We set the keys we'll use for movement, as dictated by
		// the Controllable parent class.
		setKeys(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);
		
		// Sets up the HitBox according to the ship's image's dimensions, and
		// links the HitBox's position to the ship's.
		this.hitbox = new HitBox((int) (img.getWidth() * this.scale),
								 (int) (img.getHeight() * this.scale),
								 this.position);
		
		return;
	}

	@Override
	public void update(Input input)
	{
		checkInputs(input);
		
		boundVelocity(img.getWidth(), img.getHeight());
		move();
		
		return;
	}
	
	@Override
	public void draw(GameContainer game, Graphics gfx)
	{
		drawScaled(game, gfx, scale);
		gfx.drawString("Lives: " + lives, 0, GlobalConstants.GAME_HEIGHT /10*9);
		return;
	}
	
	public boolean loseLife()
	{
		this.lives--;
		
		if (this.lives <= 0)
			return true;
		else
			return false;
	}
	
	public void addLife()
	{
		this.lives++;
		return;
	}
}
