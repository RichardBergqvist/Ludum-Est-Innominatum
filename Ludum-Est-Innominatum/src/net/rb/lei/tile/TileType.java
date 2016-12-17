package net.rb.lei.tile;

public enum TileType {
	
	VOID("water", false),
	DIRT("dirt", false),
	GRASS("grass", false),
	WATER("water", false);
	
	String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable) {
		this.textureName = textureName;
		this.buildable = buildable;
	}
}