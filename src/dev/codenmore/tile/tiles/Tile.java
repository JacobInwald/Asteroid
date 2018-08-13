package dev.codenmore.tile.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.codenmore.tile.tiles.starmaptiles.StarTile_1;
import dev.codenmore.tile.tiles.starmaptiles.StarTile_2;
import dev.codenmore.tile.tiles.starmaptiles.StarTile_3;
import dev.codenmore.tile.tiles.starmaptiles.StarTile_4;
import dev.codenmore.tile.tiles.starmaptiles.StarTile_5;
import dev.codenmore.tile.tiles.starmaptiles.StarTile_6;
import dev.codenmore.tile.tiles.starmaptiles.StarTile_7;
import dev.codenmore.tile.tiles.starmaptiles.StarTile_8;

public class Tile {

	public static Tile[] tiles = new Tile[256];
	public static Tile starTile_1 = new StarTile_1(0);
	public static Tile starTile_2 = new StarTile_2(1);
	public static Tile starTile_3 = new StarTile_3(2);
	public static Tile starTile_4 = new StarTile_4(3);
	public static Tile starTile_5 = new StarTile_5(4);
	public static Tile starTile_6 = new StarTile_6(5);
	public static Tile starTile_7 = new StarTile_7(6);
	public static Tile starTile_8 = new StarTile_8(7);
	
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id; 

	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}	
	
}
