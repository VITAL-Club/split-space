package vital.splitspace.entity;

import vital.splitspace.datatypes.OrderedPair;

/**
 *	Represents a rectangle around an object, and handles logic for
 *	detecting collisions.
 */
public class HitBox
{
	private OrderedPair position;
	private int width,
				height;
	
	public HitBox(int width, int height, OrderedPair link)
	{
		this.width = width;
		this.height= height;
		
		// The position of this HitBox is set to the reference of the
		// position of the other object. This means all movement updates
		// to the linked object will automatically move this one.
		this.position = link;
		
		return;
	}
	
	/**
	 * Returns true if the HitBoxes are touching.
	 * 
	 * @param other	The HitBox to check against this one.
	 * @return		True if they're touching/overlapping, false otherwise.
	 */
	public boolean isColliding(HitBox other)
	{
		boolean otherInsideThisX = 
				other.getPosition().x >= this.position.x &&
				other.getPosition().x <= this.position.x + this.width;
		boolean thisInsideOtherX = 
				this.position.x >= other.getPosition().x &&
				this.position.x <= other.getPosition().x + other.getWidth();
		
		if (otherInsideThisX || thisInsideOtherX)
		{
			boolean otherInsideThisY = 
					other.getPosition().y >= this.position.y &&
	   				other.getPosition().y <= this.position.y + this.height;
			boolean thisInsideOtherY = 
					this.position.y >= other.getPosition().y &&
	   				this.position.y <= other.getPosition().y + other.getHeight();
			
			if (otherInsideThisY || thisInsideOtherY)
				return true;
		}
		
		return false;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public OrderedPair getPosition()
	{
		return this.position;
	}
	
	public void setPosition(OrderedPair op)
	{
		this.position = op;
	}
}
