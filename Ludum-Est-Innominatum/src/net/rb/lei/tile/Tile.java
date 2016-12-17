package net.rb.lei.tile;

import static net.rb.lei.graphics.Screen.*;

import org.newdawn.slick.opengl.Texture;

public class Tile {
	
	private float x, y;
	private int width, height;
	private TileType type;
	private Texture texture;
	
	public Tile(float x, float y, int width, int height, TileType type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.texture = quickLoad(type.textureName);
	}
	
	public void render() {
		renderQuadTex(texture, x, y, width, height);
	}

	public float getX() {
		return x;
	}
	
	public int getXPlace() {
		return (int) x / TILE_SIZE;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	
	public int getYPlace() {
		return (int) y / TILE_SIZE;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}