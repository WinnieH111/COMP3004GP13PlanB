package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

public class Table extends Observable {
	ArrayList<ArrayList<Card>> tableCards = new ArrayList<ArrayList<Card>>();
	
	Table(){}
	
	public void playedCards(ArrayList<Card> cards) {
		tableCards.add(cards);
	}
	
	public boolean isMeld(ArrayList<Card> meld) {
		if(isRun(meld) || isSet(meld)) {
			
			return true;
		}
		return false; 
	}
	
	private boolean isRun(ArrayList<Card> meld) {
		String colorBase = meld.get(0).getColor();
		int rankBase = meld.get(0).getRank();
		if (meld.size()<3) {
			return false;
		}
		for(int i=1; i<meld.size(); i++) {
			if(!(colorBase.equalsIgnoreCase(meld.get(i).getColor()))) {
				return false; 
			}
			if((rankBase+i)!= meld.get(i).getRank()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isSet(ArrayList<Card> meld) {
		HashSet<String> colorSet = new HashSet<String>();
		HashSet<Integer> rankSet = new HashSet<Integer>();
		for(Card card:meld) {
			colorSet.add(card.getColor());
			rankSet.add(card.getRank());
		}
		if(colorSet.size()==meld.size() && rankSet.size()==1) {
			return true;
		}
		return false;
	}
	
	public void addMelds(ArrayList<ArrayList<Card>> meld) {
		for(ArrayList<Card> cards:meld) {
			tableCards.add(cards);
		}
	}
}
