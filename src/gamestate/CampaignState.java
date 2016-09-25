package gamestate;

import main.Game;
import input.GameKeys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class CampaignState extends GameState {

	private int currentChoice = 0;
	private SpriteBatch batch;
	private BitmapFont font;
	private BitmapFont titleFont;
	private String[] options = {"           Play", "Return To Main Menu"};
	private ShapeRenderer sr;
	
	private float fadeAmmount;
	private boolean fadeOut;
	private long startTime;
	private long elapsed;
	
	public CampaignState(GameStateManager gsm) {
		
		super(gsm);
		
	}
	
	public void init() {
		
		try {
			
			batch = new SpriteBatch();
			
			FileHandle fontFile = Gdx.files.internal("Fonts/Gamera.ttf");
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
			font = generator.generateFont(28);
			titleFont = generator.generateFont(40);
			
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
			if (fadeAmmount < 0) {
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
		
		batch.begin();
		
		titleFont.setColor(0, 0, 0, 1);
		titleFont.draw(batch, "Campaign", 600, 600);
	
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				font.setColor(1, 0, 0, 1);
			}
			else {
				font.setColor(0, 0, 0, 1);
			}
			font.draw(batch, options[i], 620 - (int) (options[i].length() * 3.5), 550 - i * 35);
		}
		
		batch.end();
		
		GLCommon gl = Gdx.graphics.getGLCommon();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		sr.begin(ShapeType.Filled);
		sr.setColor(1, 1, 1, fadeAmmount);
		sr.rect(0, 0, Game.WIDTH, Game.HEIGHT);
		sr.end();
		
		gl.glDisable(GL10.GL_BLEND);
		
	}
		
	private void select() {
			
		if (currentChoice == 0) {
			// go to first stage
		}
		if (currentChoice == 1) {
			if (fadeOut == false) {
				fadeOut = true;
				startTime = System.nanoTime();
				return;
			}
			if (fadeAmmount >= 1.1) {
				gsm.setState(GameStateManager.MENUSTATE, null, null, 0);
			}
		}
			
	}
	
	public void handleInput() {
		
		if (GameKeys.isPressed(GameKeys.X) || GameKeys.isPressed(GameKeys.PER) || GameKeys.isPressed(GameKeys.ENTER)) {
			select();
		}
		if (GameKeys.isPressed(GameKeys.W) || GameKeys.isPressed(GameKeys.UP)) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (GameKeys.isPressed(GameKeys.S) || GameKeys.isPressed(GameKeys.DOWN)) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
			
		}
		
	}
	
	public void dispose() {
		
		batch.dispose();
		font.dispose();
		
	} 
	
	private void fadeIn() {	
		elapsed = (System.nanoTime() - startTime) / 1000000 + 1;
		fadeAmmount = (float) (1.1 - (elapsed * 0.004));
	}
	
	private void fadeOut() {
		elapsed = (System.nanoTime() - startTime) / 1000000 + 1;
		fadeAmmount = (float) (elapsed * 0.004);
		select();
	}
	
		
}
