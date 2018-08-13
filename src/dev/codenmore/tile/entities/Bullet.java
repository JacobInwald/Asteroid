package dev.codenmore.tile.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import dev.codenmore.tile.Handler;
import dev.codenmore.tile.gfx.Assets;
import dev.codenmore.tile.tiles.Tile;

public class Bullet extends Creature{

	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
	
	public Bullet(Handler handler, float x, float y) {
		super(handler, x, y , 1, 2);
		
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 1;
		bounds.height = 2;
	}

	@Override
	public void tick() {
		fire();
		move();
		if(x >= 30 * Tile.TILEWIDTH)
			x = handler.getWorld().getEntityManager().getPlayer().getX();
		if(x >= 30 * Tile.TILEHEIGHT)
			y = handler.getWorld().getEntityManager().getPlayer().getY();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bullet,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), null);
	}

	@Override
	public void die() {
		
	}
	
	public void fire() {
			attackTimer += System.currentTimeMillis() - lastAttackTimer;
			lastAttackTimer = System.currentTimeMillis();
			if(attackTimer < attackCooldown)
				return;
			
			Rectangle cb = getCollisionBounds(0, 0);
			Rectangle ar = new Rectangle();
			int arSize = 32;
			ar.width  = arSize;
			ar.height = arSize;
			
			if(handler.getKeyManager().aUp) {
				x = handler.getWorld().getEntityManager().getPlayer().x + 16;
				y =  handler.getWorld().getEntityManager().getPlayer().y + 3;
				if(xMove != 0)
					xMove = 0;
				if(yMove != 0)
					yMove = 0;
				yMove -= 4;
				
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y - arSize;
			}else if(handler.getKeyManager().aDown) {
				x = handler.getWorld().getEntityManager().getPlayer().x + 16;
				y = handler.getWorld().getEntityManager().getPlayer().y + handler.getWorld().getEntityManager().getPlayer().getHeight() - 3;
				if(xMove != 0)
					xMove = 0;
				if(yMove != 0)
					yMove = 0;
				yMove += 4;
				
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y + cb.height;
			}else if(handler.getKeyManager().aLeft) {
				x =  handler.getWorld().getEntityManager().getPlayer().x - 3;
				y = handler.getWorld().getEntityManager().getPlayer().y + 16;
				if(xMove != 0)
					xMove = 0;
				if(yMove != 0)
					yMove = 0;
				xMove -= 4;
				
				ar.x = cb.x - arSize / 2;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			}else if(handler.getKeyManager().aRight) {
				x = handler.getWorld().getEntityManager().getPlayer().x + handler.getWorld().getEntityManager().getPlayer().getWidth() + 3;
				y = handler.getWorld().getEntityManager().getPlayer().y + 16;
				if(xMove != 0)
					xMove = 0;
				if(yMove != 0)
					yMove = 0;
				xMove += 4;
				
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			}else {
				return;
			}
			
			attackTimer = 0;
			
			for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
				if(e == this || e == handler.getWorld().getEntityManager().getPlayer())
					continue;
				if(e.getCollisionBounds(0, 0).intersects(ar)) {
					e.hurt(10);
					return;
					}
			}
		}
}

