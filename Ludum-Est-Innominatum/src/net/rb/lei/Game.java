package net.rb.lei;

import static net.rb.lei.graphics.Screen.*;

import net.rb.lei.entity.Player;
import net.rb.lei.entity.mob.MobUFO;
import net.rb.lei.tile.TileGrid;
import net.rb.lei.util.WaveManager;

public class Game {

	private TileGrid grid;
	private WaveManager waveManager;
	private Player player;
	
	public Game(int[][] map) {
		grid = new TileGrid(map);
		waveManager = new WaveManager(new MobUFO(quickLoad("ufo"), grid.getTile(1, 0), grid, TILE_SIZE, TILE_SIZE, 25, 70), 2, 2);
		player = new Player(grid, waveManager);
		player.setup();
	}
	
	public void update() {
		grid.update();
		//blue.render();
		waveManager.update();
		player.update();
	}
}