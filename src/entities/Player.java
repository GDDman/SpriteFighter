package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tilemap.TileMap;

public class Player extends Entity {
	
	private SpriteBatch batch;
	private long startTimer;
	private long elapsed;
	
	private int currentPlayer;
	private final int LUFFY = 1;
	private final int ELASTIGIRL = 2;
	private final int BUU = 3;
	private final int JACK = 4;
	private final int PANDA = 5;
	private final int BRAGO = 6;
	
	// Brago
	
	private boolean bPunching;
	private boolean bUppercut;
	private boolean bJumpKick;
	private boolean bAttractBall;
	private boolean bRepelBall;
	private boolean bDownBall;
	private boolean bMegaBalling;
	private boolean bBeaming; 
	
	// 
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int MELEE1 = 3;
	private static final int MELEE2 = 4;
	private static final int MELEE3 = 5;
	private static final int BLOCK = 6;
	private static final int DODGE = 7;
	private static final int RANGE1 = 8;
	private static final int RANGE2 = 9;
	private static final int RANGE3 = 10;
	
	public Player(TileMap tm, String type, int l) {
		
		super(tm);
		
		batch = new SpriteBatch();
		startTimer = System.nanoTime();
		
		width = 32;
		height = 32;
		cwidth = 20;
		cheight = 20;
		lives = l;
		
		fallSpeed = -0.15;
		maxFallSpeed = -4.0;
		jumpStart = 4.8;
		stopJumpSpeed = -0.3;
		
		regenerating = false;
		
		if (type.equals("Brago")) {
			
			currentPlayer = BRAGO;
			moveSpeed = 0.8;
			stopSpeed = 1.5;
			health = maxHealth = 300;
			healthRegen = (int) (maxHealth * 0.2);
			RegenTime = 45000;
			defense = 80;
			rngDef = 90;
			superJumpSpeed = 6.0;
			meleeDmg = 100;
			meleeRng = 30;
			rangeDmg = 200;
			
		}
		
		if (type.equals("Luffy")) {
			
			currentPlayer = LUFFY;
			moveSpeed = 1.3;
			stopSpeed = 1.6;
			health = maxHealth = 320;
			healthRegen = (int) (maxHealth * 0.2);
			RegenTime = 60000;
			defense = 100;
			rngDef = 80;
			superJumpSpeed = 8.0;
			meleeDmg = 210;
			rangeDmg = 120;
			
		}
		
		if (type.equals("Jack")) {
			
			
		}
		
		loadSprites(type);
		
	}
	
	public void update() {
		
		elapsed = (startTimer - System.nanoTime())/1000000;
		
		if (elapsed > RegenTime) {
			regen();
		}
		
	}
	
	public void setFacing(boolean b) {	
		facingRight = b;
	}
	
	public void loadSprites(String s) {
		
		if (s.equals("Brago")) {
			
			Texture spritesheet = new Texture(Gdx.files.internal("Sprites/BragoSprites.gif"));
			
			
		}
		
	}
	
	private void regen() {
		
		if (regenerating) {
			health += healthRegen;
			if (health > maxHealth) {
				health = maxHealth;
			}
			startTimer = System.nanoTime();
			regenerating = false;
		}
		
	}
	
	public void draw() {
		
		batch.begin();
	//	batch.draw();
		batch.end();
		
	}

	public void setRegen(boolean b) {
		regenerating = b;
	}
	
}
