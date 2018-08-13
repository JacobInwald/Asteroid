package dev.codenmore.tile.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.codenmore.tile.Handler;
import dev.codenmore.tile.gfx.Animation;
import dev.codenmore.tile.gfx.Assets;
import dev.codenmore.tile.states.GameOverState;
import dev.codenmore.tile.states.MenuState;
import dev.codenmore.tile.states.State;

public class Player extends Creature {
	
	private Animation animUp;
	private Animation animRight;
	private Animation animLeft;
	private Animation animDown;
	
	public int score = 0;
	

	public Bullet bullet1;
	
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bullet1 = new Bullet(handler, x, y);
		
		bounds.x = 3;
		bounds.y = 3;
		bounds.width = 25;
		bounds.height = 25;
		
		animUp = new Animation(250, Assets.player_up);
		animRight = new Animation(250, Assets.player_right);
		animLeft = new Animation(250, Assets.player_left);
		animDown = new Animation(250, Assets.player_down);
	}

	public void tick() {
	animUp.tick();
	animRight.tick();
	animLeft.tick();
	animDown.tick();
	handler.getGameCamera().centerOnEntity(this);
	getInput();
	move();
	//Attacks
	checkAttacks();
	bullet1.tick();
	}

	private void checkAttacks() {
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
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize / 2;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e == this)
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				System.out.print("Meelee Hit");
				e.hurt(10);
				return;}
		}
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;
	
		if(handler.getKeyManager().up) 
			yMove = -speed;
		else if(handler.getKeyManager().left) 
			xMove = -speed;
		else if(handler.getKeyManager().down) 
			yMove = speed;
		else if(handler.getKeyManager().right) 
			xMove = speed;
		else if(handler.getKeyManager().escKey)
			State.setCurrentState(new MenuState(handler));
	}
	
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		bullet1.render(g);
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
		}
		return Assets.player;
	}

	@Override
	public void die() {
		System.out.println("GAME OVER");
		State.setCurrentState(new GameOverState(handler));
	}

}
