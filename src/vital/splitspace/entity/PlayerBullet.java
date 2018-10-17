package vital.splitspace.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * This represents the bullets that the player can shoot.
 *
 */
public class PlayerBullet extends Controllable
{

	public PlayerBullet()
	{
		super("resources/sprites/playerBullet.png");
		
		this.speed = 6;
		this.scale = 2;
		
		this.hitbox = new HitBox((int) (img.getWidth() * this.scale),
								 (int) (img.getHeight() * this.scale), 
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
		
		this.hitbox.setPosition(this.position);
		
		return;
	}
	
	@Override
	public void draw(GameContainer game, Graphics gfx)
	{
		drawScaled(game, gfx, scale);
		return;
	}

}
