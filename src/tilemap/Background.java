package tilemap;

import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {

	private Texture image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	private boolean fit;
	private double moveScale;
	
	private SpriteBatch batch;
	
	public Background(String s, double ms, boolean f) {
		
		try{
			image = new Texture(Gdx.files.internal(s));
			moveScale = ms;
			fit = f;
			batch = new SpriteBatch();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % Game.WIDTH;
		this.y = (y * moveScale) % Game.HEIGHT;
	}
	
	public void setxy(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw() {
		
		batch.begin();
		
		batch.draw(image, (float) x, (float) y);
		
		if (fit) {
			
			if (x < 0) {
				batch.draw(image, (float) x + Game.WIDTH, (float) y);
			}
			
			if (x > 0) {
				batch.draw(image, (float) x - Game.WIDTH, (float) y);
			}
			
		}
		else {
			
			if (x <= -image.getWidth()) {
				
				x = Game.WIDTH;
			
			}
			
			if (x >=  image.getWidth()) {
			
				x = -image.getWidth(); 
				
			}
			
		}
		
		batch.end();
	
	}
	
}
