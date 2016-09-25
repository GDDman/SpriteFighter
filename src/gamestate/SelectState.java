package gamestate;

import main.Game;
import input.GameKeys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SelectState extends GameState {
	
	private long startTime;
	private long elapsed;
	private float fadeAmmount;
	private boolean fadeOut;
	
	private int currentChoice1 = 0;
	private int currentChoice2 = 0; 
	private String[] player1options = {"        Luffy", "    Elastigirl", "   Majin Buu", "Samurai Jack", "      Panda", "      Brago"};
	private String[] player2options = {"        Luffy", "    Elastigirl", "   Majin Buu", "Samurai Jack", "      Panda", "      Brago"};
	private int lives;
	
	private Texture currentChar1;
	private Texture currentChar2;
	private Texture luffy1;
	private Texture luffy2;
	private Texture egirl1;
	private Texture egirl2;
	private Texture buu1;
	private Texture buu2;
	private Texture brago;
	private Texture jack1;
	private Texture jack2;
	private Texture genma1;
	private Texture genma2;
	
	private Texture arrow;
	private Texture uparrow;
	private BitmapFont titleFont;
	private BitmapFont font;
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private TextureRegion arrowpart;
	private boolean p1arrowleft;
	private boolean p2arrowleft;
	private boolean p1arrowright;
	private boolean p2arrowright;
	private boolean arrowUp;
	private boolean arrowDown;
	
	public SelectState(GameStateManager gsm) {
		
		super(gsm);
		
	}
	
	public void init() {
		
		try {		
		
			luffy1 = new Texture(Gdx.files.internal("Images/Luffy1.png"));
			egirl1 = new Texture(Gdx.files.internal("Images/EGirl1.png"));
			buu1 = new Texture(Gdx.files.internal("Images/Buu1.png"));
			jack1 = new Texture(Gdx.files.internal("Images/Jack1.png"));
			genma1 = new Texture(Gdx.files.internal("Images/Genma1.png"));
			
			brago = new Texture(Gdx.files.internal("Images/Brago.png"));	
			
			luffy2 = new Texture(Gdx.files.internal("Images/Luffy2.png"));
			egirl2 = new Texture(Gdx.files.internal("Images/EGirl2.png"));
			buu2 = new Texture(Gdx.files.internal("Images/Buu2.png"));
			jack2 = new Texture(Gdx.files.internal("Images/Jack2.png"));
			genma2 = new Texture(Gdx.files.internal("Images/Genma2.png"));
			
			currentChar1 = luffy1;
			currentChar2 = luffy2;

			startTime = System.nanoTime();
			fadeOut = false;
			fadeAmmount = 1.1f;
			
			arrow = new Texture(Gdx.files.internal("Images/arrow.gif"));
			uparrow = new Texture(Gdx.files.internal("Images/Uparrow.gif"));
			batch = new SpriteBatch();
			sr = new ShapeRenderer();
			arrowpart =  new TextureRegion(arrow, 0, 150, 300, 150);
			
			FileHandle fontFile = Gdx.files.internal("Fonts/Gamera.ttf");
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
			titleFont = generator.generateFont(60);
			font = generator.generateFont(35);
			
			p1arrowleft = false;
			p1arrowright = false;
			p2arrowleft = false;
			p2arrowright = false;
			arrowUp = false;
			arrowDown = false;
			lives = 5;
			
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
		
		charUpdate(1, currentChoice1);
		charUpdate(2, currentChoice2);
		
		if (fadeOut) {
			fadeOut();
		}
		
	}
	
	public void draw() {
		
		sr.begin(ShapeType.Filled);
		sr.setColor(0f, 0f, 0f, 1f);
		sr.rect(0, 688, Game.WIDTH, 180);
		sr.end();
		
		batch.begin();
		titleFont.setColor(1f, 0f, 0f, 1f);
		titleFont.draw(batch, "Select Your Character", 350, 795);
		titleFont.draw(batch, "V.S.", 660, 300);
		batch.end();
		
		sr.begin(ShapeType.Filled);
		
		sr.setColor(0, 0, 0, 0);
		sr.rect(175, 75, 290, 75);
		sr.rect(1007, 75, 290, 75);
		
		sr.setColor(0, 0, 0, 0);
		sr.rect(170, 80, 300, 75);
		
		sr.setColor(0, 0, 0, 0);
		sr.rect(1002, 80, 300, 75);
		
		sr.setColor(0, 0, 0, 1);
		sr.rect(655, 495, 125, 100);
		
		sr.end();
		
		sr.begin(ShapeType.Filled);
		sr.setColor(1, 1, 1, 1);
		sr.triangle(640, 520, 640, 470, 690, 470);
		sr.triangle(795, 520, 795, 470, 745, 470);
		sr.triangle(640, 570, 640, 620, 690, 620);
		sr.triangle(795, 570, 795, 620, 745, 620);
		sr.triangle(155, 65, 155, 105, 195, 65);
		sr.triangle(155, 130, 155, 170, 195, 170);
		sr.triangle(445, 65, 485, 65, 485, 105);
		sr.triangle(445, 170, 485, 130, 485, 170);
		sr.triangle(990, 65, 990, 105, 1030, 65);
		sr.triangle(990, 130, 990, 170, 1030, 170);
		sr.triangle(1275, 65, 1315, 65, 1315, 105);
		sr.triangle(1275, 170, 1315, 130, 1315, 170);
		sr.end();
		
		batch.begin();
		
		if (p1arrowleft) {
			batch.draw(arrowpart, 20, 80, 160, 80);
		}
		else {
			batch.draw(arrowpart, 30, 85, 140, 70);
		}

		if (p1arrowright) {
			batch.draw(arrowpart, 620, 80, -160, 80);
		}
		else {
			batch.draw(arrowpart, 610, 85, -140, 70);
		}
		if (p2arrowleft) {
			batch.draw(arrowpart, 852, 80, 160, 80);
		}
		else {
			batch.draw(arrowpart, 862, 85, 140, 70);
		}
		if (p2arrowright) {
			batch.draw(arrowpart, 1452, 80, -160, 80);
		}
		else {
			batch.draw(arrowpart, 1442, 85, -140, 70);
		}
		if (arrowUp) {
			batch.draw(uparrow, 697, 590, 100, 100);
		} else {
			batch.draw(uparrow, 700, 595, 90, 90);
		}
		if (arrowDown) {
			batch.draw(uparrow, 697, 495, 100, -100);
		} else {
			batch.draw(uparrow, 700, 490, 90, -90);
		}
		
		
		batch.draw(currentChar1, 120, 180, 400, 400);
		batch.draw(currentChar2, 940, 180, 400, 400);
		
		
		font.setColor(0, 0, 1, 1);
		font.draw(batch, "Player 1", 240, 660);
		font.setColor(1, 0, 0, 1);
		font.draw(batch, "Player 2", 1062, 660);
		font.draw(batch, player1options[currentChoice1], 230 - (int)(player1options[currentChoice1].length() * 4), 138);
		font.draw(batch, player2options[currentChoice2], 1062 - (int)(player2options[currentChoice2].length() * 4), 138);
		font.setColor(1, 1, 1, 1);
		if (lives < 10) {
			font.draw(batch, " " + lives + " ", 696, 542);
		} else {
			font.draw(batch, " " + lives + " ", 686, 542);
		}
		font.setColor(1, 0, 0, 1);
		font.draw(batch, "Lives", 670, 582);
		
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
	
	private void select1() {
		
		if (currentChoice1 == 0) {
			
		}
		if (currentChoice1 == 1) {
			
		}
		if (currentChoice1 == 2) {
	
		}
		if (currentChoice1 == 3) {
			
		}
		if (currentChoice1 == 4) {
			
		}
		if (currentChoice1 == 5) {
			
		}
		
	}
	
	private void select2() {
		
		if (currentChoice2 == 0) {
			
		}
		if (currentChoice2 == 1) {
			
		}
		if (currentChoice2 == 2) {
	
		}
		if (currentChoice2 == 3) {
			
		}
		if (currentChoice2 == 4) {
			
		}
		if (currentChoice2 == 5) {
			
		}
		
	}
	
	private void back() {
		if (fadeOut == false) {
			fadeOut = true;
			startTime = System.nanoTime();
			return;
		}
		if (fadeAmmount >= 1.1) {
			gsm.setState(GameStateManager.MENUSTATE, null, null, 1);
		}
	}
	
	public void handleInput() {
		
		if (GameKeys.isPressed(GameKeys.C) || GameKeys.isPressed(GameKeys.COMMA)) {
			back();
		}
		
		if (GameKeys.isPressed(GameKeys.PER) || GameKeys.isPressed(GameKeys.ENTER)) {
			select2();
		}
		if (GameKeys.isPressed(GameKeys.X)) {
			select1();
		}
		
		if (GameKeys.isDown(GameKeys.LEFT)) {
			p2arrowleft = true;
		}
		else {
			p2arrowleft = false;
		}
		if (GameKeys.isDown(GameKeys.RIGHT)) {
			p2arrowright = true;
		}
		else {
			p2arrowright = false;
		}
		if (GameKeys.isDown(GameKeys.A)) {
			p1arrowleft = true;
		}
		else {
			p1arrowleft = false;
		}
		if (GameKeys.isDown(GameKeys.D)) {
			p1arrowright = true;
		}
		else {
			p1arrowright = false;
		}
		
		if (GameKeys.isPressed(GameKeys.LEFT)) {
			currentChoice2--;
			if (currentChoice2 == -1) {
				currentChoice2 = player2options.length - 1;
			}
		}
		if (GameKeys.isPressed(GameKeys.RIGHT)) {
			currentChoice2++;
			if (currentChoice2 == player2options.length) {
				currentChoice2 = 0;
			}
		}
		
		if (GameKeys.isPressed(GameKeys.D)) {
			currentChoice1++;
			if (currentChoice1 == player1options.length) {
				currentChoice1 = 0;
			}
		}
		if (GameKeys.isPressed(GameKeys.A)) {
			currentChoice1--;
			if (currentChoice1 == -1) {
				currentChoice1 = player1options.length - 1;
			}
		}
		
		if (GameKeys.isPressed(GameKeys.UP) || GameKeys.isPressed(GameKeys.W)) {
			lives++;
			if (lives > 99) {
				lives = 1;
			}
		}
		if (GameKeys.isPressed(GameKeys.DOWN) || GameKeys.isPressed(GameKeys.S)) {
			lives--;
			if (lives == 0) {
				lives = 99;
			}
		}
		if (GameKeys.isDown(GameKeys.DOWN) || GameKeys.isDown(GameKeys.S)) {
			arrowDown = true;
		}
		else {
			arrowDown = false;
		}
		if (GameKeys.isDown(GameKeys.UP) || GameKeys.isDown(GameKeys.W)) {
			arrowUp = true;
		}
		else {
			arrowUp = false;
		}
		
	}
	
	public void dispose() {
	
		font.dispose();
		arrow.dispose();
		
	}
	
	private void fadeIn() {
		elapsed = (System.nanoTime() - startTime) / 1000000 + 1;
		fadeAmmount = (float) (1.1 - (elapsed*0.004));		
	}
	
	private void fadeOut() {
		elapsed = (System.nanoTime() - startTime) / 1000000 + 1;
		fadeAmmount = (float) (elapsed*0.004);
		back();
	}
	
	private void charUpdate(int player, int choice) {
		
		if (player == 1) {	
			
			switch (choice) {
				case 0: currentChar1 = luffy1; break;
				case 1: currentChar1 = egirl1; break;
				case 2: currentChar1 = buu1; break;
				case 3: currentChar1 = jack1; break;
				case 4: currentChar1 = genma1; break;
				case 5: currentChar1 = brago; break;
				default: return;
			}
			
		}
		
		if (player == 2) {
			
			switch (choice) {
				case 0: currentChar2 = luffy2; break;
				case 1: currentChar2 = egirl2; break;
				case 2: currentChar2 = buu2; break;
				case 3: currentChar2 = jack2; break;
				case 4: currentChar2 = genma2; break;
				case 5: currentChar2 = brago; break;
				default: return;
			}
			
		}
		
	}
	
}
