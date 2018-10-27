package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

public class AIPlayer implements Observer, Player{
	
	private static ArrayList<Card> rack  = new ArrayList<Card>();
	private Observable observable; 
	private ArrayList<ArrayList<Card>> tableCards = null;
	private ArrayList<Integer> playersCardNum = null;
	private boolean turn;
	private boolean iceBroke; 
//	private static ArrayList<Card> played = new ArrayList<Card>();

//	private static ArrayList<ArrayList<Card>> runs   = new ArrayList<Card>();
//	private static ArrayList<ArrayList<Card>> groups = new ArrayList<Card>();
    private Deck deck; 
	
	AIPlayer(Observable observable){
	    this.observable = observable;
        observable.addObserver(this);
		rack = deck.initialHandCards(14);
		boolean iceBroke = false; 
		turn = false; 
		
		sortCards();
	}
	
	public boolean getIceBreakState() {
		return iceBroke;
	}
	
	public boolean getTurnState() {
		return turn;
	}
	
	public void setTurn() {
		turn = true; 
	}
	
	public void setEndTurn() {
		turn = false; 
	}
	
	private int score(ArrayList<Card> cards) {
		int result = 0;
		for(Card card: cards) {
			result+=card.getRank();
		}
		return result;
	}
	
	public void sortCards() {
		Collections.sort(rack);
	}
	
	public void iceBreaking() {
		ArrayList<int[]> runs = detectRuns();
		ArrayList<ArrayList<Integer>> sets = detectSet();
		ArrayList<Card> playMelds = new ArrayList<Card>();
		//Cards[] cards = new Card[13];
		if(sets.size() == 0 && runs.size() == 0) {
			deck.drawCard();
		}
		else if(sets.size()!=0){
			for(int i=0; i<sets.size();i++) {
				ArrayList<Card> set = playSet(sets.get(i));
				for(int j=0; j<set.size(); j++) {
					playMelds.add(set.get(i));
				}
			}	
		}
		else if(runs.size()!=0) {
			for(int i=0; i<sets.size();i++) {
				ArrayList<Card> run = playRun(runs.get(i));
				for(int j=0; j<run.size(); j++) {
					playMelds.add(run.get(i));
				}
			}	
		}
		if(score(playMelds)<30) {
			deck.drawCard();
		}
		else {
			//play playMelds
			rack.removeAll(playMelds);
			printCards(playMelds);
		}
	}

	public void playMeld(ArrayList<Card> cards) {
		rack.removeAll(cards);
		printCards(cards);
	}
	
	
	public ArrayList<Card> playRun(int[] index){
		ArrayList<Card> result = new ArrayList<Card>();
		for(int i=index[0]; i<=index[1]; i++) {
			result.add(rack.get(i));
		}
		return result;
	}


	public ArrayList<Card> playSet(ArrayList<Integer> index) {
		ArrayList<Card> result = new ArrayList<Card>();
		for(int i=0; i<index.size(); i++) {
			result.add(rack.get(index.get(i)));
		}
		return result;
	} 
			
	//Detect the run for different colors, 
	//and return the start and end index of their position in Rack
	public ArrayList<int[]> detectRuns() {
		ArrayList<Card> blueCards = sameColorCards("B");
		ArrayList<Card> orangeCards = sameColorCards("O");
		ArrayList<Card> redCards = sameColorCards("R");
		ArrayList<Card> greenCards = sameColorCards("G");
	    ArrayList<int[]> result = new ArrayList<int[]>();
		result.add(checkSet(blueCards));
		result.add(checkSet(orangeCards));
		result.add(checkSet(redCards));
		result.add(checkSet(greenCards));
		return result;
	}
	
	//return the index of runs for each color 
	public int[] checkSet(ArrayList<Card> cards) {
		int[] result = new int[2];
		int start = 0;
		int end = -1;
		if(cards.size()<3) return null;
		for(int i=0; i<cards.size(); i++) {
			if((cards.get(i).getRank()+1)==cards.get(i+1).getRank()) {
				start = i;
				for(int j=i+1; j<cards.size()-1; j++) {
					if((cards.get(j).getRank()+1) == cards.get(j+1).getRank()) {
						end = j+1;
					}
				}
			}
		}
		if(end-start>=3) {
			result[0] = rack.indexOf(cards.get(start));
			result[1] = rack.indexOf(cards.get(end));
			return result;
		}
		return null;
	}
	
	//Help class for detectRuns. 
	public ArrayList<Card> sameColorCards(String color){
		ArrayList<Card> result = new ArrayList<Card>();
		for(int i=0; i<rack.size(); i++) {
			if(color.equals(rack.get(i).getColor())) {
				result.add(rack.get(i));
			}
		}
		return result;
	}
	
	public ArrayList<ArrayList<Integer>> detectSet() {
		ArrayList<Integer> ranksCompSets = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if(rack.size()<3) return null;
		HashMap<Integer, Integer> tilesRanks = new HashMap<Integer, Integer>();
		for(int i=0; i<rack.size(); i++) {
			Integer key = rack.get(i).getRank();
			if(tilesRanks.get(key)==null) {
				tilesRanks.put(key, new Integer(1));
			}
			else {
				tilesRanks.put(key, tilesRanks.get(key)+1);
			}
		}
		for(Entry<Integer, Integer> entry:tilesRanks.entrySet()) {
			if(entry.getValue()>=3) {
				ranksCompSets.add(entry.getKey());
			}
		}
		for(Integer rank:ranksCompSets) {
			result.add(setIndex(rank));
		}
		return result;
		
	}
	
	//return the index of sets for certain rank
	public ArrayList<Integer> setIndex(int value) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i=0; i<rack.size(); i++) {
			if(rack.get(i).getRank()==value) {
				index.add(i);
			}
		}
		return index;
	}
	
	public Card playCard() {
		Card card = deck.drawCard();
		return card;
	}
	
	public void printCardsOnHand() {
		System.out.print("Cards on hand: [");
		for(Card card: rack) {
			System.out.print("/'"+card.toString()+"/'");
		}
		System.out.print("]\n");
	}

	public void printCards(ArrayList<Card> cards) {
		System.out.print("Play Cards: [");
		for(Card card: cards) {
			System.out.print("/"+card.toString()+"/");
		}
		System.out.print("]\n");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public Action promptAction(Action[] actions) {
		// TODO Auto-generated method stub
		return null;
	}

	public Card promptCard() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Card> promptMelds() {
		// TODO Auto-generated method stub
		return null;
	}

	public void initialHand(ArrayList<Card> cards) {
		rack.addAll(cards);
	}
	
}