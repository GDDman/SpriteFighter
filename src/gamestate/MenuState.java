package gamestate;

import input.GameKeys;
import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import tilemap.Background;

public class MenuState extends GameState {

	private Background bg;
	private SpriteBatch batch;
	private ShapeRenderer sr;
	
	private int currentChoice;
	private String[] options = {"    Campaign", "2-Player VS", "        Options", "             Quit"};
	
	private BitmapFont font;
	private BitmapFont titleFont;
	
	private long startTime;
	private long elapsed;
	private float fadeAmmount;
	private boolean fadeOut;
	
	public MenuState(GameStateManager gsm, int cState) {
		
		super(gsm);
		currentChoice = cState;
		
	}
	
	public void init() { 
		
		try {
			
			bg = new Background("Backgrounds/Menu.jpg", 1, false);
			bg.setVector(-5, 0);
			bg.setxy(Game.WIDTH, 0);
			
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Gamera.ttf"));
			titleFont = generator.generateFont(40);
			font = generator.generateFont(28);
			batch = new SpriteBatch();
			sr = new ShapeRenderer();
			
			startTime = System.nanoTime();
			fadeOut = false;
			fadeAmmount = 1.1f;

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

		bg.update();
		
		if (fadeOut) {
			fadeOut();
		}
		
	}
	
	public void draw() {
		
		bg.draw();
		
		sr.begin(ShapeType.Filled);
		sr.setColor(0f, 0f, 0f, 1f);
		sr.rect(0, 638, Game.WIDTH, 180);
		sr.end();
		
		batch.begin();
		titleFont.setColor(1, 0, 0, 1);
		titleFont.draw(batch, "Sprite Fighter: ", 100, 785);
		titleFont.draw(batch, "Ultimate Tournament", 70, 735);
		batch.end();
		
		for (int i = 0; i < options.length; i++) {
			
			if (i == currentChoice) {			
				batch.begin();
				font.setColor(1, 0, 0, 1);
				font.draw(batch, options[i], 1210, 795 - i * 30);
				batch.end();
			}
			else {
				batch.begin();
				font.setColor(1, 1, 1, 1);
				font.draw(batch, options[i], 1210, 795 - i * 30);
				batch.end();
			}

		}
		
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
			if (fadeOut == false) {
				fadeOut = true;
				startTime = System.nanoTime();
				return;
			}
			if (fadeAmmount >= 1.1) {
				gsm.setState(GameStateManager.CAMPAIGNSTATE, null, null, 0);
			}
		}
		if (currentChoice == 1) {
			if (fadeOut == false) {
				fadeOut = true;
				startTime = System.nanoTime();
				return;
			}
			if (fadeAmmount >= 1.1) {
			gsm.setState(GameStateManager.SELECTSTATE, null, null, 0);
			}
		}
		if (currentChoice == 2) {
			if (fadeOut == false) {
				fadeOut = true;
				startTime = System.nanoTime();
				return;
			}
			if (fadeAmmount >= 1.1) {
				gsm.setState(GameStateManager.STAGE1STATE, "Brago", "Brago", 0);
			}
		}
		if (currentChoice == 3) {
			Gdx.app.exit();
		}
		
	}

	protected void handleInput() {
		
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
		if (GameKeys.isPressed(GameKeys.X) || GameKeys.isPressed(GameKeys.ENTER) || GameKeys.isPressed(GameKeys.PER)) {
			select();
		}
		
	}

	protected void dispose() {
		batch.dispose();
		sr.dispose();
		font.dispose();
		titleFont.dispose();
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
