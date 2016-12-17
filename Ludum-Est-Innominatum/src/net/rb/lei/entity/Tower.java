package net.rb.lei.entity;

import static net.rb.lei.graphics.Screen.*;
import static net.rb.lei.util.Clock.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

import net.rb.lei.entity.tower.TowerType;
import net.rb.lei.tile.Tile;

public abstract class Tower implements Entity {

	private Texture[] textures;
	public Mob target;
	private CopyOnWriteArrayList<Mob> mobs;
	public ArrayList<Projectile> projectiles;
	public TowerType type;
	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, range;
	private boolean targeted;
	
	public Tower(TowerType type, Tile startTile, CopyOnWriteArrayList<Mob> mobs) {
		this.textures = type.textures;
		this.mobs = mobs;
		this.projectiles = new ArrayList<Projectile>();
		this.type = type;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.timeSinceLastShot = 0;
		this.firingSpeed = type.firingSpeed;
		this.angle = 0;
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.range = type.range;
		this.targeted = false;
	}
	
	private Mob acquireTarget() {
		Mob closest = null;
		float closestDistance = 10000;
		for (Mob m : mobs) {
			if (isInRange(m) && findDistance(m) < closestDistance && m.isAlive()) {
				closestDistance = findDistance(m);
				closest = m;
			}
		}
		if (closest != null) 
			targeted = true;
		
		return closest;
	}
	
	private boolean isInRange(Mob m) {
		float xDistance = Math.abs(m.getX() - x);
		float yDistance = Math.abs(m.getY() - y);
		
		if (xDistance < range && yDistance < range)
			return true;
		return false;
	}
	
	private float findDistance(Mob m) {
		float xDistance = Math.abs(m.getX() - x);
		float yDistance = Math.abs(m.getY() - y);
		
		return xDistance + yDistance;
	}
	
	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}
	
	public abstract void shoot(Mob target);
	
	public void updateMobList(CopyOnWriteArrayList<Mob> newList) {
		mobs = newList;
	}
	
	public void update() {
		if (!targeted) {
			target = acquireTarget();
		} else {
			angle = calculateAngle();
			if (timeSinceLastShot > firingSpeed) {
				shoot(target);
				timeSinceLastShot = 0;
			}
		}
		
		if (target == null || target.isAlive() == false)
			targeted = false;
		
		timeSinceLastShot += delta(); 
		
		for (Projectile p : projectiles)
			p.update();
	
		render();
	}

	public void render() {
		renderQuadTex(textures[0], x, y, width, height);
		if (textures.length > 1)
			for (int i = 1; i < textures.length; i++)
				renderQuadTexRot(textures[i], x, y, width, height, angle);
	}
	
	public Texture[] getTextures() {
		return textures;
	}
	
	public Mob getTarget() {
		return target;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setTexture(Texture[] textures) {
		this.textures = textures;
	}
	
	public void setTarget(Mob target) {
		this.target = target;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}