package entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

	private TextureRegion[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	public Animation() {
		
		playedOnce = false;
		
	}
	
	public void setFrames(TextureRegion[] frames) {
		
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false; 
		
	}
	
	public void setDelay(long d) { delay = d; }
	public void setFrame(int i) { currentFrame = i; }
	
	public void update() {
		
		if (delay == -1) return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		
		if (elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if (currentFrame == frames.length) {
			currentFrame = 0;
			playedOnce = true;
		}
		
	}
	
	public int getFrame() { return currentFrame; }
	public TextureRegion getImage() { return frames[currentFrame]; }
	public boolean hasPlayedOnce() {return playedOnce; }
	
}
