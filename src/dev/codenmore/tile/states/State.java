package dev.codenmore.tile.states;

import java.awt.Graphics;

import dev.codenmore.tile.Game;
import dev.codenmore.tile.Handler;

public abstract class State {
	
	private static State currentState = null;
	protected Handler handler; 
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State state) {
		currentState = state;
	}
	
	
	//abstract methods
	public abstract void tick();
	
	public abstract void render(Graphics g);


}
