package dev.codenmore.tile;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.codenmore.tile.display.Display;
import dev.codenmore.tile.gfx.Assets;
import dev.codenmore.tile.gfx.GameCamera;
import dev.codenmore.tile.input.KeyManager;
import dev.codenmore.tile.input.MouseManager;
import dev.codenmore.tile.states.GameOverState;
import dev.codenmore.tile.states.GameState;
import dev.codenmore.tile.states.MenuState;
import dev.codenmore.tile.states.State;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public State gameState;
	public State menuState;
	public State gameOverState;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private GameCamera gameCamera;
	
	private Handler handler;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameOverState = new GameOverState(handler);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		
		State.setCurrentState(menuState);
	}
	
	
	private void tick(){
		keyManager.tick();
		
		if(State.getCurrentState() != null) {
			State.getCurrentState().tick();
		}
		
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//draw here
		if(State.getCurrentState() != null) {
			State.getCurrentState().render(g);
		}
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				ticks ++;
				delta--;
			}
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames :" + ticks);
				timer = 0L;
				ticks = 0;
			}
		}
		
		stop();
		
	}
	
	
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	

	public Graphics getG() {
		return g;
	}

	public int getHeight() {
		return height;
	}
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}




