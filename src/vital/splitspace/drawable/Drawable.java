package vital.splitspace.drawable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Represents something which can be drawn onto the screen, but doesn't
 * necessarily have an image.
 * 
 * To draw something which has an image, check {@link DrawableImage}
 */
public interface Drawable
{
	public void draw(GameContainer arg0, Graphics arg1);
}
