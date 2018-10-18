package vital.splitspace.contentManagement;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import vital.splitspace.datatypes.OrderedPair;
import vital.splitspace.drawable.Drawable;
import vital.splitspace.entity.Enemy;
import vital.splitspace.entity.EnemyBullet;
import vital.splitspace.entity.Entity;
import vital.splitspace.entity.PlayerBullet;
import vital.splitspace.entity.Score;
import vital.splitspace.entity.Ship;
import vital.splitspace.main.GlobalConstants;

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
	private ArrayList<Enemy> enemies;
	private ArrayList<EnemyBullet> enemyBullets;
	private ArrayList<PlayerBullet> playerBullets;
	private Ship player;
	private Score score;
	
	Sound playerShoot;
	Sound enemyShoot;
	Sound playerDie;
	Sound enemyDie;
	
	Sound bgm;
	
	public Overseer()
	{
		this.entities = new ArrayList<>();
		this.drawables = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.enemyBullets = new ArrayList<>();
		this.playerBullets = new ArrayList<>();
		
		this.score = new Score();
		
		try
		{
			this.playerShoot = new Sound("resources/sounds/playerShoot.ogg");
			this.enemyShoot = new Sound("resources/sounds/enemyShoot.ogg");
			
			this.playerDie = new Sound("resources/sounds/playerExplosion.ogg");
			this.enemyDie = new Sound("resources/sounds/enemyExplosion.ogg");
			
			this.bgm = new Sound("resources/sounds/bgm.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	/**
	 * Removes an Entity from all lists it is contained in.
	 * 
	 * @param e	The Entity to remove.
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void removeEntity(Entity e)
	{
		this.entities.remove(e);
		
		if (e instanceof Drawable)
			this.drawables.remove(e);
		
		if (e instanceof Enemy)
			this.enemies.remove(e);
		
		if (e instanceof PlayerBullet)
			this.playerBullets.remove(e);
		
		if (e instanceof EnemyBullet)
			this.enemyBullets.remove(e);
		
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
		else if (e instanceof EnemyBullet)
			this.enemyBullets.add((EnemyBullet) e);
		else if (e instanceof Enemy)
			this.enemies.add((Enemy) e);
		else if (e instanceof Ship)
			this.player = (Ship) e;
		
		return;
	}
	
	public void draw(GameContainer game, Graphics gfx)
	{
		for (Drawable d : this.drawables)
			d.draw(game, gfx);
		
		score.draw(game, gfx);
		
		return;
	}
	
	private void spawnWave()
	{
		Random rand = new Random();
		
		int num = rand.nextInt(6);
		
		for (int i = 0; i < num; i++)
		{
			Enemy e = new Enemy();
			
			e.setPosition(new OrderedPair(rand.nextInt(GlobalConstants.GAME_WIDTH),-50));
			
			addEntity(e);
		}
		
		return;
	}
	
	private void checkForBullets(Input input)
	{
		// The player shot a bullet
		if (input.isKeyPressed(Input.KEY_SPACE))
		{
			PlayerBullet b = new PlayerBullet();
			OrderedPair op = player.getPosition();
			
			b.setPosition(new OrderedPair(op.x + 18, op.y));
			addEntity(b);
			
			playerShoot.play();
		}
	}
	
	private void checkForCollisions()
	{
		for (Enemy e : enemies)
		{
			if (player.getHitBox().isColliding(e.getHitBox()))
			{
				resetField();
			}
			
			for (PlayerBullet p : playerBullets)
			{
				if (p.getHitBox().isColliding(e.getHitBox()))
				{
					e.destroy();
					p.destroy();
					
					score.addPoints(100);
					
					enemyDie.play();
					
					// Break to avoid multi-hits
					break;
				}
			}
		}
		
		for (EnemyBullet eb : enemyBullets)
		{
			if (player.getHitBox().isColliding(eb.getHitBox()))
			{
				resetField();
			}
		}
	}
	
	private void enemyShoot()
	{
		for (Enemy e : enemies)
		{
			if (Math.random() < ((double) 1 / 45))
			{
				EnemyBullet eb = new EnemyBullet();
				eb.setPosition(new OrderedPair(e.getPosition().x + 15,
											   e.getPosition().y));
				
				addEntity(eb);
				
				enemyShoot.play();
			}
		}
	}
	
	private void resetGame()
	{
		removeEntity(player);
		addEntity(new Ship());
		
		this.score = new Score();
		
		return;
	}
	
	private void resetField()
	{
		drawables.removeAll(enemies);
		drawables.removeAll(enemyBullets);
		drawables.removeAll(playerBullets);
		
		enemies = new ArrayList<>();
		playerBullets = new ArrayList<>();
		enemyBullets = new ArrayList<>();
		
		player.setPosition(new OrderedPair(GlobalConstants.GAME_WIDTH / 2,
										   GlobalConstants.GAME_HEIGHT /4*3));
		
		playerDie.play();
		
		if (this.player.loseLife())
		{
			resetGame();
		}
	}
	
	public void update(Input input)
	{
		checkForBullets(input);
		checkForCollisions();
		
		if (enemies.size() == 0)
			spawnWave();
		
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
		
		enemyShoot();
		
		if(!bgm.playing())
			bgm.play();
		
		return;
	}
}
