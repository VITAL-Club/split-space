package vital.splitspace.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import vital.splitspace.drawable.DrawableImage;

public class EnemyBullet extends DrawableImage
{

	public EnemyBullet()
	{
		super("resources/sprites/enemyBullet.png");
		
		this.speed = 5;
		this.scale = 3;
		
		this.velocity.y = speed;
		
		this.hitbox = new HitBox((int) (img.getWidth() * this.scale),
				 				 (int) (img.getHeight() * this.scale), 
				 				 this.position);
	}

	@Override
	public void update(Input input)
	{
		if (isOffScreen((int) (img.getWidth() * this.scale),
						(int) (img.getHeight() * this.scale)))
			this.destroy = true;
		
		move();
		
		return;
	}
	
	@Override
	public void draw(GameContainer game, Graphics gfx)
	{
		drawScaled(game, gfx, scale);
		return;
	}

}
