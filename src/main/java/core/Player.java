package core;

import java.util.ArrayList;

public interface Player {
	public Action promptAction();
	
	public void drawCard(Card card);

	public ArrayList<Card> promptMelds() throws Exception;
	
	public boolean getIceBreakState();
	
	public boolean getTurnState();
	
	public void setTurn();
	
	public void setEndTurn();
	
	public void setIceBrokeStatus();
	
	public void initialHand(ArrayList<Card> cards);
	
	public void playedCard(ArrayList<ArrayList<Card>> cards);
	
	public void printRack();
}
