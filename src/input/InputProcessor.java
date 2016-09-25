package input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

	public boolean keyDown(int k) {
		
		if (k == Keys.W) {
			GameKeys.setKey(GameKeys.W, true);
		}
		if (k == Keys.A) {
			GameKeys.setKey(GameKeys.A, true);
		}
		if (k == Keys.S) {
			GameKeys.setKey(GameKeys.S, true);
		}
		if (k == Keys.D) {
			GameKeys.setKey(GameKeys.D, true);
		}
		if (k == Keys.UP) {
			GameKeys.setKey(GameKeys.UP, true);
		}
		if (k == Keys.LEFT) {
			GameKeys.setKey(GameKeys.LEFT, true);
		}
		if (k == Keys.DOWN) {
			GameKeys.setKey(GameKeys.DOWN, true);
		}
		if (k == Keys.RIGHT) {
			GameKeys.setKey(GameKeys.RIGHT, true);
		}
		if (k == Keys.X) {
			GameKeys.setKey(GameKeys.X, true);
		}
		if (k == Keys.C) {
			GameKeys.setKey(GameKeys.C, true);
		}
		if (k == Keys.V) {
			GameKeys.setKey(GameKeys.V, true);
		}
		if (k == Keys.M) {
			GameKeys.setKey(GameKeys.M, true);
		}
		if (k == Keys.COMMA) {
			GameKeys.setKey(GameKeys.COMMA, true);
		}
		if (k == Keys.PERIOD) {
			GameKeys.setKey(GameKeys.PER, true);
		}
		if (k == Keys.ENTER) {
			GameKeys.setKey(GameKeys.ENTER, true);
		}
		if (k == Keys.R) {
			GameKeys.setKey(GameKeys.R, true);
		}
		if (k == Keys.SPACE) {
			GameKeys.setKey(GameKeys.SPACE, true);
		}
		
		return true;
		
	}
	
	public boolean keyUp(int k) {
		
		if (k == Keys.W) {
			GameKeys.setKey(GameKeys.W, false);
		}
		if (k == Keys.A) {
			GameKeys.setKey(GameKeys.A, false);
		}
		if (k == Keys.S) {
			GameKeys.setKey(GameKeys.S, false);
		}
		if (k == Keys.D) {
			GameKeys.setKey(GameKeys.D, false);
		}
		if (k == Keys.UP) {
			GameKeys.setKey(GameKeys.UP, false);
		}
		if (k == Keys.LEFT) {
			GameKeys.setKey(GameKeys.LEFT, false);
		}
		if (k == Keys.DOWN) {
			GameKeys.setKey(GameKeys.DOWN, false);
		}
		if (k == Keys.RIGHT) {
			GameKeys.setKey(GameKeys.RIGHT, false);
		}
		if (k == Keys.X) {
			GameKeys.setKey(GameKeys.X, false);
		}
		if (k == Keys.C) {
			GameKeys.setKey(GameKeys.C, false);
		}
		if (k == Keys.V) {
			GameKeys.setKey(GameKeys.V, false);
		}
		if (k == Keys.M) {
			GameKeys.setKey(GameKeys.M, false);
		}
		if (k == Keys.COMMA) {
			GameKeys.setKey(GameKeys.COMMA, false);
		}
		if (k == Keys.PERIOD) {
			GameKeys.setKey(GameKeys.PER, false);
		}
		if (k == Keys.ENTER) {
			GameKeys.setKey(GameKeys.ENTER, false);
		}
		if (k == Keys.R) {
			GameKeys.setKey(GameKeys.R, false);
		}
		if (k == Keys.SPACE) {
			GameKeys.setKey(GameKeys.SPACE, false);
		}
		
		return true;
		
	}
	
}
