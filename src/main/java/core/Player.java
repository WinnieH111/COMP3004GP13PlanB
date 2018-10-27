package core;

import java.util.ArrayList;

public interface Player {
	public Action promptAction(Action[] actions);

	public ArrayList<Card> promptMelds() throws Exception;
	
	public boolean getIceBreakState();
	
	public boolean getTurnState();
	
	public void setTurn();
	
	public void setEndTurn();
	
	public void initialHand(ArrayList<Card> cards);
}
