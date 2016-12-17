package net.rb.lei.entity.projectile;

import net.rb.lei.entity.Mob;
import net.rb.lei.entity.Projectile;

public class ProjectileIceball extends Projectile {

	public ProjectileIceball(ProjectileType type, Mob target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}
	
	@Override
	public void damage() {
		super.getTarget().setSpeed(4);
		super.damage();
	}
}