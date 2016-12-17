package net.rb.lei.entity.tower;

import static net.rb.lei.graphics.Screen.*;

import org.newdawn.slick.opengl.Texture;

import net.rb.lei.entity.projectile.ProjectileType;

public enum TowerType {

	CANNON_RED(new Texture[] { quickLoad("cannon_base"), quickLoad("cannon_gun") }, ProjectileType.CANNON_BALL, 10, 1000, 3),
	CANNON_BLUE(new Texture[] { quickLoad("blue_cannon_base"), quickLoad("blue_cannon_gun") }, ProjectileType.CANNON_BALL, 30, 1000, 3),
	CANNON_ICE(new Texture[] { quickLoad("blue_cannon_base"), quickLoad("blue_cannon_gun") }, ProjectileType.ICE_BALL, 30, 1000, 3 );
	
	public Texture[] textures;
	public ProjectileType projectileType;
	public int damage, range;
	public float firingSpeed;
	
	TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingSpeed) {
		this.textures = textures;
		this.projectileType = projectileType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
	}
}