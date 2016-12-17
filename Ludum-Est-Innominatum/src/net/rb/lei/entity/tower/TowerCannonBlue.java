package net.rb.lei.entity.tower;

import java.util.concurrent.CopyOnWriteArrayList;

import net.rb.lei.entity.Mob;
import net.rb.lei.entity.Tower;
import net.rb.lei.entity.projectile.ProjectileCannonball;
import net.rb.lei.tile.Tile;

public class TowerCannonBlue extends Tower {

	public TowerCannonBlue(TowerType type, Tile startTile, CopyOnWriteArrayList<Mob> mobs) {
		super(type, startTile, mobs);
	}
	
	@Override
	public void shoot(Mob target) {
		super.projectiles.add(new ProjectileCannonball(type.projectileType, target, getX(), getY(), 32, 32));
	}
}