package net.rb.lei.tile;

import static net.rb.lei.graphics.Screen.*;

public class TileGrid {
	
	public Tile[][] map;
	private int tilesWide, tilesHigh;
	
	public TileGrid() {
		this.tilesWide = 20;
		this.tilesHigh = 11;
		map = new Tile[20][11];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.GRASS);
			}
		}
	}
	
	public TileGrid(int[][] newMap) {
		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap.length;
		map = new Tile[tilesWide][tilesHigh];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (newMap[j][i]) {
				case 0:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.GRASS);
					break;
				case 1:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.DIRT);
					break;
				case 2:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.WATER);
					break;
				default:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.GRASS);
				}
			}
		}
	}
	
	public void setTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE, TILE_SIZE, type);
	}
	
	public Tile getTile(int xPlace, int yPlace) {
		if (xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1)
			return map[xPlace][yPlace];
		else
			return new Tile(0, 0, 0, 0, TileType.VOID);
	}
	
	public void update() {
		render();
	}

	public void render() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].render();
				
			}
		}
	}
	
	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}
}