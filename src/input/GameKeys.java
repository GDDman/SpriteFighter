package input;

public class GameKeys {

	private static boolean[] keys;
	private static boolean[] pkeys;
	
	private static final int NUM_KEYS = 17;
	
	public static final int W = 0;
	public static final int A = 1;
	public static final int S = 2;
	public static final int D = 3;
	public static final int UP = 4;
	public static final int LEFT = 5;
	public static final int DOWN = 6;
	public static final int RIGHT = 7;
	public static final int X = 8;
	public static final int C = 9;
	public static final int V = 10;
	public static final int M = 11;
	public static final int COMMA = 12;
	public static final int PER = 13;
	public static final int ENTER = 14;
	public static final int R = 15;
	public static final int SPACE = 16;
	
	static { 
		keys = new boolean[NUM_KEYS]; 
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		
		for(int i = 0; i < NUM_KEYS; i ++) {
			pkeys[i] = keys[i];
		}
		
	}
	
	public static void setKey(int k, boolean b) {
		keys[k] = b;
	}
	
	public static boolean isDown(int k) {
		
		return keys[k];
		
	}
	
	public static boolean isPressed(int k) {
		
		return keys[k] && !pkeys[k];
		
	}
	
	public static boolean isReleased(int k) {
		
		if (!keys[k] && pkeys[k]) {
			return true;
		}
		else {
			return false;
		}

	}
	
}
