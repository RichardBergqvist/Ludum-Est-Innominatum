package net.rb.lei.entity.mob;

import org.newdawn.slick.opengl.Texture;

import net.rb.lei.entity.Mob;
import net.rb.lei.tile.Tile;
import net.rb.lei.tile.TileGrid;

public class MobUFO extends Mob {

	public MobUFO(Texture texture, Tile startTile, TileGrid grid, int width, int height, float health, float speed) {
		super(texture, startTile, grid, width, height, health, speed);
	}
}