package net.rb.lei.entity;

import static net.rb.lei.graphics.Screen.*;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.rb.lei.entity.tower.TowerCannonBlue;
import net.rb.lei.entity.tower.TowerIce;
import net.rb.lei.entity.tower.TowerType;
import net.rb.lei.tile.TileGrid;
import net.rb.lei.tile.TileType;
import net.rb.lei.util.Clock;
import net.rb.lei.util.WaveManager;

public class Player {

	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtonDown, rightMouseButtonDown;
	public static int cash, lives;
	
	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.GRASS;
		this.types[1] = TileType.DIRT;
		this.types[2] = TileType.WATER;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		
		cash = 0;
		lives = 0;
	}
	
	public void setup() {
		cash = 450;
		lives = 100;
	}
	
	public static boolean modifyCash(int amount) {
		if (cash + amount >= 0) {
			cash += amount;
			return true;
		}
		return false;
	}
	
	public static void modifyLives(int amount) {
		lives += amount;
	}
	
	public void update() {
		for (Tower t : towerList) {			
			t.update();
			t.render();
			t.updateMobList(waveManager.getCurrentWave().getMobList());
		}
		
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			if (modifyCash(-70))
				towerList.add(new TowerCannonBlue(TowerType.CANNON_RED, grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE), waveManager.getCurrentWave().getMobList()));
		}
		
		if (Mouse.isButtonDown(1) && !rightMouseButtonDown) {
			if (modifyCash(-130))
				towerList.add(new TowerIce(TowerType.CANNON_ICE, grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE), waveManager.getCurrentWave().getMobList()));
		}
		
		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Clock.changeMultiplier(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Clock.changeMultiplier(-0.2f);
			}
		}
	}
}