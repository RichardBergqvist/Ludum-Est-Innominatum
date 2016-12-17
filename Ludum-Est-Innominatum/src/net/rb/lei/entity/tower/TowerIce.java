package net.rb.lei.entity.tower;

import java.util.concurrent.CopyOnWriteArrayList;

import net.rb.lei.entity.Mob;
import net.rb.lei.entity.Tower;
import net.rb.lei.entity.projectile.ProjectileIceball;
import net.rb.lei.tile.Tile;

public class TowerIce extends Tower {

	public TowerIce(TowerType type, Tile startTile, CopyOnWriteArrayList<Mob> mobs) {
		super(type, startTile, mobs);
	}
	
	@Override
	public void shoot(Mob target) {
		super.projectiles.add(new ProjectileIceball(type.projectileType, target, getX(), getY(), 32, 32));
	}
}	