package vital.splitspace.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import vital.splitspace.drawable.Drawable;
import vital.splitspace.main.GlobalConstants;

public class Score extends Entity implements Drawable
{
	private long score;
	
	public Score()
	{
		this.score = 0;
		this.position.x = 0;
		this.position.y = 20;
		
		return;
	}

	@Override
	public void draw(GameContainer game, Graphics gfx)
	{
		gfx.drawString(Long.toString(this.score),
					   this.position.x, 
					   this.position.y);
		
		return;
	}

	@Override
	public void update(Input input)
	{
		return;
	}

	public void addPoints(int points)
	{
		this.score += points;
		return;
	}
}
