package net.rb.lei.entity.projectile;

import net.rb.lei.entity.Mob;
import net.rb.lei.entity.Projectile;

public class ProjectileCannonball extends Projectile {
	
	public ProjectileCannonball(ProjectileType type, Mob target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}
	
	public void damage() {
		super.damage();
	}	
}