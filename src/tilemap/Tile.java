package tilemap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {

	private TextureRegion image;
	private int type;
	
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	public Tile(TextureRegion image, int type) {
		
		this.image = image;
		this.type = type;
		
	}
	
	public TextureRegion getImage() {
		return image;
	}
	
	public int getType() {
		return type;
	}
	
}
