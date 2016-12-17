package net.rb.lei.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.rb.lei.tile.Tile;
import net.rb.lei.tile.TileGrid;
import net.rb.lei.tile.TileType;

public class MapManager {

	public static void saveMap(String mapName, TileGrid grid) {
		String mapData = "";
		
		for (int i = 0; i < grid.getTilesWide(); i++) {
			for (int j = 0; j < grid.getTilesHigh(); j++) {
				mapData += getTileID(grid.getTile(i, j));
			}
		}
		
		try {
			File file = new File(mapName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(mapData);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static TileGrid loadMap(String mapName) {
		TileGrid grid = new TileGrid();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapName));
			String data = br.readLine();
			
			for (int i = 0; i < grid.getTilesWide(); i++) {
				for (int j = 0; j < grid.getTilesHigh(); j++) {
					grid.setTile(i, j, getTileType(data.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return grid;
	}
	
	public static TileType getTileType(String ID) {
		TileType type = TileType.VOID;
		
		switch (ID) {
		case "0":
			type = TileType.VOID;
			break;
		case "1":
			type = TileType.GRASS;
			break;
		case "2":
			type = TileType.DIRT;
			break;
		case "3":
			type = TileType.WATER;
			break;
		}
		
		return type;
	}
	
	public static String getTileID(Tile t) {
		String ID = "E";
		
		switch (t.getType()) {
		case VOID:
			ID = "0";
			break;
		case GRASS:
			ID = "1";
			break;
		case DIRT:
			ID = "2";
			break;
		case WATER:
			ID = "3";
			break;
		}
		
		return ID;
	}
}