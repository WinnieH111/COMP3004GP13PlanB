package core;

import java.util.ArrayList;
import java.util.Observable;

public class Table extends Observable {
	ArrayList<ArrayList<Card>> tableCards = new ArrayList<ArrayList<Card>>();
	
	Table(){}
	
	public void playedCards(ArrayList<Card> cards) {
		tableCards.add(cards);
	}
	
}
