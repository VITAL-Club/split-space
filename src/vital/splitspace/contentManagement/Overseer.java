package vital.splitspace.contentManagement;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import vital.splitspace.drawable.Drawable;
import vital.splitspace.entity.Entity;
import vital.splitspace.entity.PlayerBullet;
import vital.splitspace.entity.Ship;

/**
 *	The Overseer handles all additions and deletions of objects in
 *	one place. This avoids memory leaks and null references.
 */
public class Overseer
{
	// We'll need to keep certain things seperate to optimize
	// some checks.
	private ArrayList<Entity> entities;
	private ArrayList<Drawable> drawables;
	private ArrayList<Entity> enemies;
	private ArrayList<PlayerBullet> playerBullets;
	private Ship player;
	
	public Overseer()
	{
		this.entities = new ArrayList<>();
		this.drawables = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.playerBullets = new ArrayList<>();
		
		return;
	}
	
	/**
	 * Removes an Entity from all lists it is contained in.
	 * 
	 * @param e	The Entity to remove.
	 */
	public void removeEntity(Entity e)
	{
		this.entities.remove(e);
		
		if (e instanceof Drawable)
			this.drawables.remove(e);
		
		//this.enemies.remove(e);
		
		if (e instanceof PlayerBullet)
			this.playerBullets.remove(e);
		
		if (this.player == e)
			this.player = null;
		
		return;
	}
	
	/**
	 * Adds a new Entity to all categories it should belong to.
	 * 
	 * @param e The Entity to add.
	 */
	public void addEntity(Entity e)
	{
		this.entities.add(e);
		
		if (e instanceof Drawable)
			this.drawables.add((Drawable) e);
		
		if (e instanceof PlayerBullet)
			this.playerBullets.add((PlayerBullet) e);
		//else if (e instanceof Enemy)
		//	this.enemies.add(e)
		else if (e instanceof Ship)
			this.player = (Ship) e;
		
		return;
	}
	
	public void draw(GameContainer game, Graphics gfx)
	{
		for (Drawable d : this.drawables)
			d.draw(game, gfx);
		
		return;
	}
	
	public void update(Input input)
	{
		// The player shot a bullet
		if (input.isKeyPressed(Input.KEY_SPACE))
		{
			PlayerBullet b = new PlayerBullet();
			b.setPosition(player.getPosition());
			addEntity(b);
		}
		
		// Concurrent manipulation of a list isn't supported in Java,
		// so we make a new list to temporarily hold all items that
		// must be removed.
		ArrayList<Entity> toRemove = new ArrayList<>();
		
		for (Entity e : this.entities)
		{
			if (e.needToDestroy())
				toRemove.add(e);
			else
				e.update(input);
		}
		
		// Remove the items that requested to be destroyed.
		for (Entity e : toRemove)
			removeEntity(e);
		
		return;
	}
}
