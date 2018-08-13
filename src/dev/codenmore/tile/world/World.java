package dev.codenmore.tile.world;

import java.awt.Graphics;

import dev.codenmore.tile.Handler;
import dev.codenmore.tile.entities.Enemy;
import dev.codenmore.tile.entities.EntityManager;
import dev.codenmore.tile.entities.Player;
import dev.codenmore.tile.tiles.Tile;
import dev.codenmore.tile.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		spawnEnemy(10);
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void spawnEnemy(int no) {
		for(int i = 0; i < no; i++)
			entityManager.addEntity(new Enemy(handler, 100));
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void tick(){
		entityManager.tick();
		}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g , (int)( x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		entityManager.render(g);
	}
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0|| x >= width || y >= height)
			return Tile.starTile_5;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			t = Tile.starTile_1;
		return t;
	}
	
	private void loadWorld(String path) {
	String file = Utils.loadFileAsString(path);
	String[] tokens = file.split("\\s+");
	width = Utils.parseInt(tokens[0]);
	height = Utils.parseInt(tokens[1]);
	spawnX = Utils.parseInt(tokens[2]);
	spawnY = Utils.parseInt(tokens[3]);
	
	tiles = new int[width][height];
	for(int y = 0; y < height; y++) {
		for(int x = 0; x < width; x++) {
			tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
		}
	}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
