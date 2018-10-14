package vital.splitspace.entity;

import org.newdawn.slick.Input;

import vital.splitspace.datatypes.OrderedPair;

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
	
	public Entity()
	{
		this.position = new OrderedPair();
		this.velocity = new OrderedPair();
		this.speed = 0;
		
		return;
	}
	
	public void move()
	{
		this.position.x += this.velocity.x;
		this.position.y += this.velocity.y;
		
		return;
	}
	
	// Every child class will do something different
	// per update.
	public abstract void update(Input input);
}
