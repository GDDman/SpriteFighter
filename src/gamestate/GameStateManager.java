package gamestate;

public class GameStateManager {
	
	private GameState gameState;
	
	public static final int MENUSTATE = 0;
    public static final int SELECTSTATE = 1;
    public static final int OPTIONSTATE = 2;
    public static final int CAMPAIGNSTATE = 3;
    public static final int STAGE1STATE = 4;
    
    public GameStateManager() {
    	
    	setState(MENUSTATE, null, null, 0);
    	
    }
	
    public void setState(int state, String player1, String player2, int choice) {
    	
    	if (gameState != null) gameState.dispose();
    	
    	if (state == MENUSTATE) {
    		gameState = new MenuState(this, choice);
    	}
    	if (state == SELECTSTATE) {
    		gameState = new SelectState(this);
    	}
    	if (state == OPTIONSTATE) {
    	//	gameState = new OptionState(this);
    	}
    	if (state == CAMPAIGNSTATE) {
    		gameState = new CampaignState(this);
    	}
    	
    	if (player1 != null || player2 != null) {
    	
    		if (state == STAGE1STATE) {
    			gameState = new Level1State(this, player1, player2, 4);
    		}
    	
    	}
    	
    }
    
    public void update(float dt) {
    	gameState.update(dt);	
    }
    
    public void draw() {
    	gameState.draw();
    }
    
}
