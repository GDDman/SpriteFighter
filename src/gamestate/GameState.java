package gamestate;

public abstract class GameState {

	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	protected abstract void init();
	protected abstract void update(float dt);
	protected abstract void draw();
	protected abstract void handleInput();
	protected abstract void dispose();
	
}
