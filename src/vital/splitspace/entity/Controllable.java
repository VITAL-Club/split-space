package vital.splitspace.entity;

import org.newdawn.slick.Input;

import vital.splitspace.drawable.DrawableImage;

/**
 * A Controllable object can be directly controlled by the user
 * according to customizable keys. In practice this is done
 * to avoid rewriting a lot of logic for controlling both the
 * Ship and the Bullet.
 * 
 * Since this only needs to be used for the Player and the Bullet,
 * and both of those are DrawableImages, we can simplify the inheritance
 * chain by making a Controllable also a child of a DrawableImage.
 *
 */

public abstract class Controllable extends DrawableImage
{
	protected int 	keyUp,
					keyDown,
					keyLeft,
					keyRight;
	
	public Controllable(String imagePath)
	{
		// Passes the desired image location to the parent
		// DrawableImage
		super(imagePath);
		
		return;
	}
	
	/**
	 * Sets the keys which will be used to control this object.
	 */
	protected void setKeys(int up, int down, int left, int right)
	{
		this.keyUp = up;
		this.keyDown = down;
		this.keyLeft = left;
		this.keyRight = right;
		
		return;
	}
	
	/**
	 * Checks if any keys are pressed, and modifies the entity's
	 * velocity accordingly.
	 * 
	 * @param input The Input instance which was polled in the main
	 * 				SplitSpace game loop to capture user input.
	 */
	protected void checkInputs(Input input)
	{
		if (input.isKeyDown(this.keyUp))
			this.velocity.y = -this.speed;
		else if (input.isKeyDown(this.keyDown))
			this.velocity.y = this.speed;
		else
			this.velocity.y = 0;
		
		if (input.isKeyDown(this.keyLeft))
			this.velocity.x = -this.speed;
		else if (input.isKeyDown(this.keyRight))
			this.velocity.x = this.speed;
		else
			this.velocity.x = 0;
		
		return;
	}
	
}
