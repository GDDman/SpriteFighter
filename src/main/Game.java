package main;

import gamestate.GameStateManager;
import input.GameKeys;
import input.InputProcessor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Game implements ApplicationListener {

	public static int WIDTH;
	public static int HEIGHT;
	private double zoom;
	
	public static OrthographicCamera cam;
	
	private GameStateManager gsm;
	
	public void create() {
		
		zoom = 1;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera((int) (WIDTH * zoom), (int) (HEIGHT * zoom));
		cam.translate(WIDTH/2, HEIGHT/2);
		cam.update();
		
		Texture.setEnforcePotImages(false);
		
		Gdx.input.setInputProcessor(new InputProcessor());
		
		gsm = new GameStateManager();
		
	}
	
	public void render() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();

		GameKeys.update();
		
	}
	
	public void resize(int width, int height) {}
	
	public void pause() {}
	
	public void resume() {}
	
	public void dispose() {}
	
}
