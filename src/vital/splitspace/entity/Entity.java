package vital.splitspace.entity;

import org.newdawn.slick.Input;

import vital.splitspace.datatypes.OrderedPair;
import vital.splitspace.main.GlobalConstants;

/**
 * An Entity is something which has a position and can move according to
 * some velocity which is capped by a maximum speed.
 * 
 * Movement is handled according to an update as is necessary.
 * 
 */
public abstract class Entity
{
	protected OrderedPair position;
	protected OrderedPair velocity;
	
	protected int speed;
	
	protected boolean destroy;
	
	protected HitBox hitbox;
	protected float scale;
	
	public Entity()
	{
		this.position = new OrderedPair();
		this.velocity = new OrderedPair();
		this.speed = 0;
		this.scale = 1;
		
		this.destroy = false;
		
		return;
	}
	
	// Every child class will do something different
	// per update.
	public abstract void update(Input input);
	
	public void move()
	{
		this.position.x += this.velocity.x;
		this.position.y += this.velocity.y;
		
		return;
	}
	
	public boolean isOffScreen(int width, int height)
	{
		if (this.position.x > GlobalConstants.GAME_WIDTH ||
				this.position.x + (width * scale) < 0 ||
				this.position.y > GlobalConstants.GAME_WIDTH ||
				this.position.y + (height * scale) < 0)
		{
			return true;
		}
		else
			return false;
	}
	
	public void boundVelocity(int width, int height)
	{
		if (this.position.x + this.velocity.x + (width * scale) > 
					GlobalConstants.GAME_WIDTH ||
				this.position.x + this.velocity.x < 0)
		{
			this.velocity.x = 0;
		}
		
		if (this.position.y + this.velocity.y  + (height * scale) > 
				GlobalConstants.GAME_HEIGHT ||
			this.position.y + this.velocity.y < 0)
		{
			this.velocity.y = 0;
		}
	}
	
	public boolean needToDestroy()
	{
		return this.destroy;
	}
	
	public void destroy()
	{
		this.destroy = true;
		return;
	}
	
	public void setPosition(OrderedPair op)
	{
		this.position = op;
		if (this.hitbox != null)
			this.hitbox.setPosition(this.position);
		
		return;
	}
	
	public OrderedPair getPosition()
	{
		return new OrderedPair(this.position.x, this.position.y);
	}
	
	public HitBox getHitBox()
	{
		return this.hitbox;
	}
}
