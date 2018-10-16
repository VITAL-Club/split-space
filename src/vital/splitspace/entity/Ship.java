package vital.splitspace.entity;

import org.newdawn.slick.Input;

/**
 * The Ship is the main player object.
 */
public class Ship extends Controllable
{
	private HitBox hitbox;
	
	public Ship()
	{
		// Loads the image for the Ship. This is passed back to
		// the DrawableImage parent class.
		super("resources/sprites/ship.png");
		
		this.speed = 4;
		
		// We set the keys we'll use for movement, as dictated by
		// the Controllable parent class.
		setKeys(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);
		
		// Sets up the HitBox according to the ship's image's dimensions, and
		// links the HitBox's position to the ship's.
		this.hitbox = new HitBox(img.getWidth(),
								 img.getHeight(),
								 this.position);
		
		return;
	}

	@Override
	public void update(Input input)
	{
		checkInputs(input);
		move();
		
		return;
	}
}
