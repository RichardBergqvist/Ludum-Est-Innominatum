package net.rb.lei.entity;

import static net.rb.lei.graphics.Screen.*;
import static net.rb.lei.util.Clock.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import net.rb.lei.ai.Checkpoint;
import net.rb.lei.tile.Tile;
import net.rb.lei.tile.TileGrid;

public class Mob implements Entity {

	private Texture texture, healthBackground, healthForeground, healthBorder;
	private Tile startTile;
	private TileGrid grid;
	private ArrayList<Checkpoint> checkpoints;
	private int width, height, currentCheckpoint;
	private float x, y, speed, health, startHealth;
	private int[] directions;
	private boolean first, alive;
	
	public Mob(Texture texture, Tile startTile, TileGrid grid, int width, int height, float health, float speed) {
		this.texture = texture;
		this.healthBackground = quickLoad("health_background");
		this.healthForeground = quickLoad("health_foreground");
		this.healthBorder = quickLoad("health_border");
		this.startTile = startTile;
		this.grid = grid;
		this.width = width;
		this.height = height;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.health = health;
		this.startHealth = health;
		this.speed = speed;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.currentCheckpoint = 0;
		this.directions = new int[2];
		this.directions[0] = 0;
		this.directions[1] = 0;
		this.directions = findNextD(startTile);
		this.first = true;
		this.alive = true;
		
		populateCheckpointList();
	}
	
	public void update() {
		if (first)
			first = false;
		else {
			if (checkpointReached()) {
				if (currentCheckpoint + 1 == checkpoints.size())
					endOfMazeReached();
				else
					currentCheckpoint++;
			} else {
				x += delta() * checkpoints.get(currentCheckpoint).getXDirection() * speed;
				y += delta() * checkpoints.get(currentCheckpoint).getYDirection() * speed;
			}
		}
	}
	
	private void endOfMazeReached() {
		Player.modifyLives(-1);
		die();
	}
	
	private boolean checkpointReached() {
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		
		if (x > t.getX() - 3 && x < t.getX() + 3 && y > t.getY() - 3 && y < t.getY() + 3) {
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		
		return reached;
	}
	
	private void populateCheckpointList() {
		checkpoints.add(findNextC(getStartTile(), directions = findNextD(getStartTile())));
		
		int counter = 0;
		boolean cont = true;
		
		while (cont) {
			int[] currentD = findNextD(checkpoints.get(counter).getTile());
			
			if (currentD[0] == 2 || counter == 20) {
				cont = false;
			} else {
				checkpoints.add(findNextC(checkpoints.get(counter).getTile(), directions = findNextD(checkpoints.get(counter).getTile())));
			}
			
			counter++;
		}
	}
	
	private Checkpoint findNextC(Tile s, int[] dir) {
		Tile next = null;
		Checkpoint c = null;
		
		boolean found = false;
		int counter = 1;
		
		while (!found) {
			if (s.getXPlace() + dir[0] * counter == getTileGrid().getTilesWide() || s.getYPlace() + dir[1] * counter == getTileGrid().getTilesHigh() || s.getType() != getTileGrid().getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter).getType()) {
				
				found = true;
				counter -= 1;
				next = getTileGrid().getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter);
			}
			
			counter++;
		}
		
		c = new Checkpoint(next, dir[0], dir[1]);
		return c;
	}
	
	private int[] findNextD(Tile s) {
		int[] dir = new int[2];
		
		Tile u = getTileGrid().getTile(s.getXPlace(), s.getYPlace() - 1);
		Tile r = getTileGrid().getTile(s.getXPlace() + 1, s.getYPlace());
		Tile d = getTileGrid().getTile(s.getXPlace(), s.getYPlace() + 1);
		Tile l = getTileGrid().getTile(s.getXPlace() - 1, s.getYPlace());
		
		if (s.getType() == u.getType() && directions[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
		} else if (s.getType() == r.getType() && directions[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
		} else if (s.getType() == d.getType() && directions[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
		} else if (s.getType() == l.getType() && directions[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2;
			dir[1] = 2;
		}
		
		return dir;
	}
	
	public void damage(int damage) {
		health -= damage;
		if (health <= 0) {		
			die();
			playerReward();
		}
	}
	
	public void playerReward() {
		Player.modifyCash(35);
	}
	
	protected void die() {
		this.alive = false;
	}
	
	public void render() {
		float healthPercentage = health / startHealth;
		
		renderQuadTex(texture, x, y, width, height);
		renderQuadTex(healthBackground, x, y - 16, width, 8);
		renderQuadTex(healthForeground, x, y - 16, TILE_SIZE * healthPercentage, 8);
		renderQuadTex(healthBorder, x, y - 16, width, 8);
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public TileGrid getTileGrid() {
		return grid;
	}

	public void setTileGrid(TileGrid grid) {
		this.grid = grid;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}