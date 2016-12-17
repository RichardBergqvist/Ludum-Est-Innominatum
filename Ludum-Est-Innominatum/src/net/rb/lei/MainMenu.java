package net.rb.lei;

import static net.rb.lei.graphics.Screen.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import net.rb.lei.ui.UI;
import net.rb.lei.util.StateManager;
import net.rb.lei.util.StateManager.GameState;

public class MainMenu {

	private Texture background;
	private UI menuUI;
	
	public MainMenu() {
		background = quickLoad("main_menu");
		menuUI = new UI();
		menuUI.addButton("Play", "play", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
		menuUI.addButton("Editor", "editor", WIDTH / 2 - 128, (int) (HEIGHT * 0.55f));
		menuUI.addButton("Quit", "quit", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
	}
	
	public void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (menuUI.isButtonClicked("Play"))
				StateManager.setState(GameState.GAME);
			if (menuUI.isButtonClicked("Editor"))
				StateManager.setState(GameState.EDITOR);
			if (menuUI.isButtonClicked("Quit"))
				System.exit(0);
		}
	}
	
	public void update() {
		render();
		updateButtons();
	}
	
	public void render() {
		renderQuadTex(background, 0, 0, 2048, 1024);
		menuUI.render();
	}
}