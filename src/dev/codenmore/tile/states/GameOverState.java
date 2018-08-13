package dev.codenmore.tile.states;

import java.awt.Graphics;

import dev.codenmore.tile.Handler;
import dev.codenmore.tile.gfx.Assets;
import dev.codenmore.tile.ui.ClickListener;
import dev.codenmore.tile.ui.UIImageButton;
import dev.codenmore.tile.ui.UIManager;

public class GameOverState extends State{

	private UIManager uiManager2;
	
	public GameOverState(Handler handler) {
		super(handler);
		uiManager2 = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager2);
		uiManager2.addObject(new UIImageButton(600 - 64, 500 - 64, 64, 64, Assets.mainMenu, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setCurrentState(new MenuState(handler));
			}}));
	}
	
	public void tick() {
		uiManager2.tick();
	}

	public void render(Graphics g) {
		uiManager2.render(g);
		g.drawImage(Assets.gameOver, (handler.getGame().getWidth() / 2) - 64,(handler.getGame().getHeight() / 2) - 64, 128, 128, null);
	}

}
