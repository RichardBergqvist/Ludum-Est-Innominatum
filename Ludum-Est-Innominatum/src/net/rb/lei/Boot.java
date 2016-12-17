package net.rb.lei;

import static net.rb.lei.graphics.Screen.*;

import org.lwjgl.opengl.Display;

import net.rb.lei.util.Clock;
import net.rb.lei.util.StateManager;

public class Boot {
	
	public Boot() {
		
		/** Calls the static method from the Artist class to initialize OpenGL calls **/
		beginRenderingSession();

		/** Main game loop **/
		while (!Display.isCloseRequested()) {
			Clock.update();
			StateManager.update();
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Boot();
	}
}