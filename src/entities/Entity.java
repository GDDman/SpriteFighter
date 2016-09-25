package entities;


import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tilemap.Tile;
import tilemap.TileMap;

public class Entity {
	
	protected SpriteBatch batch;

	protected boolean regenerating;
	protected boolean attacking;
	protected boolean flinching;
	protected long flinchtimer;
	protected boolean respawning;
	protected boolean invulnerable;
	protected boolean dead;
	protected boolean hit;
	protected boolean blocking;
	protected boolean dodging;
	protected boolean walking;
	
	protected double hitTime;
	protected double superJumpSpeed;
	
	protected int health;
	protected int maxHealth;
	protected int healthRegen; 
	protected int RegenTime;
	protected int lives; 
	
	protected int defense;
	protected int rngDef;
	protected int meleeDmg;
	protected int meleeRng;
	protected int rangeDmg;
	
	//common
	protected TileMap tileMap;
	protected int tileSize;
	protected float xmap;
	protected float ymap;
	protected float x;
	protected float y;
	
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	protected boolean facingRight;
	
	protected int currRow;
	protected int currCol;
	
	protected float dx;
	protected float dy;
	protected float xdest;
	protected float ydest;
	protected float xtemp;
	protected float ytemp;
	
	protected float radians; 
	protected float speed;
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	protected int width;
	protected int height;
	protected int cwidth;
	protected int cheight;
	
	public float getx() { return x; }
	public float gety() { return y; }
	
	public Entity(TileMap tm) {
		
		tileMap = tm;
		tileSize = tm.getTileSize();
		
	}
	
	public boolean intersects(Entity other) {
		
		Rectangle r1 = getRectangle();
		Rectangle r2 = other.getRectangle();
		return r1.intersects(r2);
		
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth, cheight);
	}
	
	public void calculateCorners(double x, double y) {
		
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y + cheight / 2 - 1) / tileSize;
		int bottomTile = (int)(y - cheight / 2) / tileSize;
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = tl ==Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
		
	}
	
	public void checkTileMapCollision() {
		
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);
		if (dy > 0) {
			if (topLeft || topRight) {
				dy = 0;
				ytemp = 480 - (currRow * tileSize) + cheight / 2;
			}
			else {
				ytemp += dy;
			}	
		}
		if (dy < 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				ytemp = 480 - (currRow + 1) * tileSize - cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, y);
		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
				xtemp = currCol*tileSize + cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
				xtemp = (currCol +1) * tileSize - cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		
		if (!falling) {
			calculateCorners(x, ydest + 1);
			if (!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
		
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() {
		xmap = (float) tileMap.getx();
		ymap = (float) tileMap.gety();
	}
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setJumping(boolean b) { jumping = b; }
	
	public boolean notOnScreen() {
		return true;
	}
	
	public void draw() {
		
	}
	
}
