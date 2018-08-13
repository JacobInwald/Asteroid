package dev.codenmore.tile.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import dev.codenmore.tile.Handler;
import dev.codenmore.tile.gfx.Animation;
import dev.codenmore.tile.gfx.Assets;
import dev.codenmore.tile.tiles.Tile;

public class Enemy  extends Creature{

	private Animation animUp;
	private Animation animRight;
	private Animation animLeft;
	private Animation animDown;
	private Animation explosion;
	private Random rnd;
	private boolean explode = false;
	public int t = 0;
	
	public Enemy(Handler handler, float y) {
		super(handler, 0, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		animUp = new Animation(250, Assets.enemy_up);
		animRight = new Animation(250, Assets.enemy_right);
		animLeft = new Animation(250, Assets.enemy_left);
		animDown = new Animation(250, Assets.enemy_down);
		explosion = new Animation(200, Assets.explosion);
		rnd = new Random();
		
		x = generateXPos();
		this.yMove = 2;
	
	}

	@Override
	public void tick() {
		
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		animDown.tick();
		explosion.tick();
		if(!explode) {
		checkAttacks();
		move();
		}
		if(explode) {
			t++;
			if(t == 60) {
				t = 0;
				respawn();
				return;
				}
		}
		if(y >= 29 * Tile.TILEHEIGHT) {
			die();
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		if(explode) {
			for(int t = 0; t <= 60; t++) {
				g.drawImage(explosion.getCurrentImage(), (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			}
			return;
		}
		
		g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			return animLeft.getCurrentImage();
		}else if(xMove > 0) {
			return animRight.getCurrentImage();
		}else if(yMove > 0) {
			return animDown.getCurrentImage();
		}else if(yMove < 0) {
			return animUp.getCurrentImage();
		}else if(yMove < 0) {
			return animUp.getCurrentImage();
		}
		return Assets.enemyShip;
	}
	
	public int generateXPos() {
		int rndInt = rnd.nextInt(30 * Tile.TILEWIDTH);
		while(rndInt <= 32) {
			rndInt = rnd.nextInt(30 * Tile.TILEWIDTH);
		}
		return rndInt;
	}
	
	public void respawn() {
		x = generateXPos();
		y = 100;
		explode = false;
	}

	private void checkAttacks() {
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 32;
		ar.width  = arSize;
		ar.height = 10;

		ar.x = cb.x + cb.width / 2 - arSize / 2;
		ar.y = cb.y + cb.height;
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e == this)
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(10);
				return;
			}
			}
		}
	
	@Override
	public void die() {
		    setActive(true);
			explode = true;
		}

}
	
