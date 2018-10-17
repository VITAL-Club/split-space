package vital.splitspace.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
		
		this.scale = 3;
		
		this.hitbox = new HitBox((int) (this.img.getWidth() * this.scale),
								 (int) (this.img.getHeight() * this.scale), 
								 this.position);
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
		
		// Sync hitbox and position
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
