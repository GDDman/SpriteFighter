package gamestate;

import input.GameKeys;
import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import entities.Player;
import tilemap.Background;
import tilemap.TileMap;

public class Level1State extends GameState {

	private SpriteBatch batch;
	private BitmapFont font;
	private TileMap tileMap;
	private Background bg;
	private ShapeRenderer sr;
	private Player player1;
	private Player player2;
	
	private float fadeAmmount;
	private boolean fadeOut;
	private long startTime;
	private long elapsed;
	
	public Level1State(GameStateManager gsm, String p1, String p2, int lives) {
		
		super(gsm);	
		
	//	player1 = new Player(tileMap, p1, lives);
	//	player2 = new Player(tileMap, p2, lives);
	//	player1.setFacing(true);
	//	player2.setFacing(false);
		
	}
	
	public void init() {
		
		try {
			
			tileMap = new TileMap(64);
			tileMap.loadTiles("TileSets/Level1tile.gif");
			tileMap.loadMap("TileMaps/level1map.txt");
			tileMap.setPosition(0, 0);
			tileMap.setLag(0.07);
			
			batch = new SpriteBatch();
			sr = new ShapeRenderer();
			
			fadeAmmount = 1.1f;
			fadeOut = false;
			startTime = System.nanoTime();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(float dt) {
		
		if (!fadeOut) {
			if (fadeAmmount > 0) {
				fadeIn();
			}
			else {
				fadeAmmount = 0;
			}
			handleInput();
		}
		
		if (fadeOut) {
			fadeOut();
		}
		
	}
	
	public void draw() {
		
		sr.begin(ShapeType.Filled);
		sr.setColor(0.5f, 0f, 0f, 1f);
		sr.rect(0, 0, Game.WIDTH, Game.HEIGHT);
		sr.end();
		
		tileMap.draw();
		
		GLCommon gl = Gdx.graphics.getGLCommon();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		sr.begin(ShapeType.Filled);
		sr.setColor(1, 1, 1, fadeAmmount);
		sr.rect(0, 0, Game.WIDTH, Game.HEIGHT);
		sr.end();
		
		gl.glDisable(GL10.GL_BLEND);
		
	}
	
	private void back() {
		if (fadeOut == false) {
			fadeOut = true;
			startTime = System.nanoTime();
			return;
		}
		if (fadeAmmount >= 1.1) {
			gsm.setState(GameStateManager.MENUSTATE, null, null, 2);
		}
	}
	
	public void handleInput() {
		
		if (GameKeys.isDown(GameKeys.SPACE)) {
			back();
		}
			
	}
	
	public void dispose() {
		
		/*
		batch.dispose();
		font.dispose();
		sr.dispose();
		*/
		
	}
	
	private void fadeIn() {	
		elapsed = (System.nanoTime() - startTime) / 1000000 + 1;
		fadeAmmount = (float) (1.1 - (elapsed * 0.004));
	}
	
	private void fadeOut() {
		elapsed = (System.nanoTime() - startTime) / 1000000 + 1;
		fadeAmmount = (float) (elapsed * 0.004);
		back();
	}
	
}
