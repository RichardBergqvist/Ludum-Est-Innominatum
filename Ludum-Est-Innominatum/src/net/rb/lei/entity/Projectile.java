package net.rb.lei.entity;

import static net.rb.lei.graphics.Screen.*;
import static net.rb.lei.util.Clock.*;

import org.newdawn.slick.opengl.Texture;

import net.rb.lei.entity.Mob;
import net.rb.lei.entity.projectile.ProjectileType;

public abstract class Projectile implements Entity {

	private Texture texture;
	private Mob target;
	private float x, y, speed, xVelocity, yVelocity;
	private int width, height, damage;
	private boolean alive;
	
	public Projectile(ProjectileType type, Mob target, float x, float y, int width, int height) {
		this.texture = type.texture;
		this.target = target;
		this.x = x;
		this.y = y;
		this.speed = type.speed;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.width = width;
		this.height = height;
		this.damage = type.damage;
		this.alive = true;
		
		calculateDirection();
	}
	
	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x - TILE_SIZE / 4 + TILE_SIZE / 2);
		float yDistanceFromTarget = Math.abs(target.getY() - y - TILE_SIZE / 4 + TILE_SIZE / 2);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;
		
		if (target.getX() < x)
			xVelocity *= -1;
		if (target.getY() < y)
			yVelocity *= -1;
	}
	
	public void damage() {
		target.damage(damage);
		alive = false;
	}
	
	public void update() {
		if (alive ) {
			x += xVelocity * delta() * speed;
			y += yVelocity * delta() * speed;
		
			if (checkCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(), target.getHealth()))			
				damage();
			render();
		}
	}
	
	public void render() {
		renderQuadTex(texture, x, y, 32, 32);
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
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
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public Texture getTexture() {
		return texture;
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
	
	public float getSpeed() {
		return speed;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public boolean isAlive() {
		return alive;
	}
}