package dev.codenmore.tile.states;

import java.awt.Graphics;

import dev.codenmore.tile.Handler;
import dev.codenmore.tile.entities.Enemy;
import dev.codenmore.tile.entities.Player;
import dev.codenmore.tile.world.World;

public class GameState extends State {

	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/starmap.txt");		
		handler.setWorld(world);
	}
	
	
	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {
		world.render(g);
	}

	
}
