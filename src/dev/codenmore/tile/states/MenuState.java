package dev.codenmore.tile.states;

import java.awt.Graphics;

import dev.codenmore.tile.Handler;
import dev.codenmore.tile.gfx.Assets;
import dev.codenmore.tile.ui.ClickListener;
import dev.codenmore.tile.ui.UIImageButton;
import dev.codenmore.tile.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(600 - 63, 500 - 52, 64, 64, Assets.btnStart, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setCurrentState(new GameState(handler));
			}}));
	}
	
	public void tick() {
		uiManager.tick();
	}

	public void render(Graphics g) {
		uiManager.render(g);
		g.drawImage(Assets.Asteroid_Logo, (handler.getGame().getWidth() / 2) - 64,(handler.getGame().getHeight() / 2) - 64, 128, 128, null);
	}

	
}
