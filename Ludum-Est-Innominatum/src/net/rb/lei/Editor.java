package net.rb.lei;

import static net.rb.lei.graphics.Screen.*;
import static net.rb.lei.util.MapManager.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.rb.lei.tile.TileGrid;
import net.rb.lei.tile.TileType;

public class Editor {

	private TileGrid grid;
	private TileType[] types;
	private int index;
	
	public Editor() {
		this.grid = loadMap("mapTest");
		this.types = new TileType[3];
		this.types[0] = TileType.GRASS;
		this.types[1] = TileType.DIRT;
		this.types[2] = TileType.WATER;
		this.index = 0;
	}
	
	public void update() {
		grid.render();
		
		if (Mouse.isButtonDown(0)) {
			setTile();
		}
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				moveIndex();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
				saveMap("mapTest", grid);
			}
		}
	}
	
	private void setTile() {
		grid.setTile((int) Math.floor(Mouse.getX() / 64), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64), types[index]);
	}
	
	private void moveIndex() {
		index++;
		
		if (index > types.length - 1) {
			index = 0;
		}
	}
}