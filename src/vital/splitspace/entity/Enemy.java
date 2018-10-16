package vital.splitspace.entity;

import org.newdawn.slick.Input;

import vital.splitspace.drawable.DrawableImage;
import vital.splitspace.main.GlobalConstants;

public class Enemy extends DrawableImage
{
	
	private static String[] enemies = {
			"resources/sprites/chickenpoxenemy.png",
			"resources/sprites/blackenemy.png", 
			"resources/sprites/americaenemy.png"			
	};
	
	public Enemy() 
	{
		super(enemies);
		// TODO Auto-generated constructor stub
		this.speed = (int) (Math.random() * 5 + 3);
		this.velocity.x = this.speed;
	}

	@Override
	public void update(Input input) 
	{
		if (isOffScreen(img.getWidth(), img.getHeight()))
		{
			this.velocity.x = -this.velocity.x;
			this.position.y += Math.random() * 20 + 20;
			if (this.position.y > GlobalConstants.GAME_HEIGHT)
			{
				this.destroy = true;
			}
		} 
	
		move();

		return;
		
	}
}
