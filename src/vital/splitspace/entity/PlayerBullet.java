package vital.splitspace.entity;

import org.newdawn.slick.Input;

/**
 * This represents the bullets that the player can shoot.
 *
 */
public class PlayerBullet extends Controllable
{
	private HitBox hitbox;

	public PlayerBullet()
	{
		super("resources/sprites/playerBullet.png");
		
		this.speed = 6;
		
		hitbox = new HitBox(img.getWidth(),
							img.getHeight(), 
							this.position);
		
		setKeys(Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT);
	}

	@Override
	public void update(Input input)
	{
		// We won't save bullets that have traveled offscreen.
		if (isOffScreen(img.getWidth(), img.getHeight()))
			this.destroy = true;
		
		checkInputs(input);
		move();
		
		return;
	}

}
