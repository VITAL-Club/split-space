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
	
	public Entity()
	{
		this.position = new OrderedPair();
		this.velocity = new OrderedPair();
		this.speed = 0;
		
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
				this.position.x + width < 0 ||
				this.position.y > GlobalConstants.GAME_WIDTH ||
				this.position.y + height < 0)
		{
			return true;
		}
		else
			return false;
	}
	
	public boolean needToDestroy()
	{
		return this.destroy;
	}
	
	public void setPosition(OrderedPair op)
	{
		this.position = op;
		return;
	}
	
	public OrderedPair getPosition()
	{
		return new OrderedPair(this.position.x, this.position.y);
	}
}
