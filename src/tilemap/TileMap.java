package tilemap;

import java.io.BufferedReader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import main.Game;

public class TileMap {

	private double x;
	private double y;
	
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double lag;
	
	private SpriteBatch batch;
	
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	private Texture tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize) {
		
		this.tileSize = tileSize;
		numRowsToDraw = Game.HEIGHT / tileSize + 2;
		numColsToDraw = Game.WIDTH / tileSize + 2;
		lag = 0.07;
		batch = new SpriteBatch();
		
	}
	
	public void loadTiles(String s) {
		
		try {
			
			tileset = new Texture(Gdx.files.internal(s));
			numTilesAcross = tileset.getWidth() / (tileSize / 2);
			tiles = new Tile[2][numTilesAcross];
			
			TextureRegion subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = new TextureRegion(tileset, col*tileSize/2, 0, tileSize/2, tileSize/2);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = new TextureRegion(tileset, col*tileSize/2, tileSize/2, tileSize/2, tileSize/2);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String s) {
		
		try {
		
			FileHandle handle = Gdx.files.internal(s);
			BufferedReader br = new BufferedReader(handle.reader());
		
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			xmin = Game.WIDTH - width;
			xmax = 0;
			ymin = Game.HEIGHT - height;
			ymax = 0;
			
			String delims = "\\s+";
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getTileSize() { return tileSize; }
	public double getx() { return x; }
	public double gety() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public int getType(int row, int col) {
		
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		
		return tiles[r][c].getType();
		
	}
	
	public void setPosition(double x, double y) {
		
		this.x += (x - this.x) * lag;
		this.y += (y - this.y) * lag;
		
		fixBounds();
		
		colOffset = (int) -this.x / tileSize;
		rowOffset = (int) -this.y / tileSize;
		
	}
	
	private void fixBounds() {
		
		if (x < xmin) x = xmin;
		if (y < ymin) y = ymin;
		if (x > xmax) x = xmax;
		if (y > ymax) y = ymax;
		
	}
	
	public void setLag(double i) {
		lag = i;
	}
	
	public void draw() {
		
		batch.begin();
		
		for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
			
			if (row >= numRows) break;
			
			for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
				
				if (col >= numCols) break;
				
				if (map[row][col] == 30) continue;
			
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				batch.draw(tiles[r][c].getImage(), (float) x + 1472 - (col * tileSize), (float) y + 832 - (row * tileSize), -tileSize, tileSize);
				
			}
		
		}
		
		batch.end();
		
	}
	
}
