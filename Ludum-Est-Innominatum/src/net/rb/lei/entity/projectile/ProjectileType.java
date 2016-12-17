package net.rb.lei.entity.projectile;

import static net.rb.lei.graphics.Screen.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum ProjectileType {

	CANNON_BALL(quickLoad("bullet"), 10, 500),
	ICE_BALL(quickLoad("ice_ball"), 6, 450);
	
	public Texture texture;
	public int damage;
	public float speed;
	
	ProjectileType(Texture texture, int damage, float speed) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
}