package vital.splitspace.datatypes;

/**
 * Used for representing data which is more naturally expressed
 * as a union of two integers in the form of (x, y).
 */
public class OrderedPair
{
	public int x, y;
	
	public OrderedPair()
	{
		this.x = 0;
		this.y = 0;
		
		return;
	}
	
	public OrderedPair(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		return;
	}
	
	@Override
	public String toString()
	{
		return ("(" + this.x + ", " + this.y + ")");
	}
}
