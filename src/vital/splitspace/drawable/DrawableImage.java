package vital.splitspace.drawable;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import vital.splitspace.entity.Entity;

/**
 * Represents an object which will be drawn to the screen using an image
 * instead of some more primitive drawing functions.
 */
public abstract class DrawableImage extends Entity implements Drawable
{
	protected Image img;
	
	public DrawableImage(String imagePath)
	{
		super();
		
		try{
			img = new Image(imagePath);
		} catch (SlickException e){
			e.printStackTrace();
		}
		
		// Turns off image blending.
		img.setFilter(Image.FILTER_NEAREST);
		
		return;
	}
	
	public DrawableImage(String[] images)
	{
		super();
		
		Random rand = new Random();
		int selection = rand.nextInt(images.length);
		
		try{
			img = new Image(images[selection]);
		} catch (SlickException e){
			e.printStackTrace();
		}
		
		// Turns off image blending.
		img.setFilter(Image.FILTER_NEAREST);
		
		return;
	}
	
	@Override
	public void draw(GameContainer game, Graphics gfx)
	{
		img.draw(position.x, position.y);
		
		return;
	}
}
