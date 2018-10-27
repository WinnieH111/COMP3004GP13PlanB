package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	
	private boolean addCard2Melds(ArrayList<Card> meld1, ArrayList<Card> meld2, Card card) {
		Card start1 = meld1.get(0);
		Card end1 = meld1.get(meld1.size()-1);
		Card start2 = meld2.get(0);
		Card end2 = meld2.get(meld1.size()-1);
		ArrayList<Card> newMeld = new ArrayList<Card>();
		if(card.getRank()==start1.getRank() && !card.getColor().equals(start1.getColor())
				&& card.getRank()==start2.getRank() && !card.getColor().equals(start2.getColor())) {
			newMeld.add(meld1.remove(0));
			newMeld.add(meld2.remove(0));
			newMeld.add(card);	
		}
		else if(card.getRank()==end1.getRank() && !card.getColor().equals(end1.getColor())
				&& card.getRank()==start2.getRank() && !card.getColor().equals(start2.getColor())) {
			newMeld.add(meld1.remove(meld1.size()-1));
			newMeld.add(meld2.remove(0));
			newMeld.add(card);
		}
		else if(card.getRank()==start1.getRank() && !card.getColor().equals(start1.getColor())
				&& card.getRank()==end2.getRank() && !card.getColor().equals(end2.getColor())) {
			newMeld.add(meld1.remove(0));
			newMeld.add(meld2.remove(meld2.size()-1));
			newMeld.add(card);
		}
		else if(card.getRank()==end1.getRank() && !card.getColor().equals(end1.getColor())
				&& card.getRank()==end2.getRank() && !card.getColor().equals(end2.getColor())) {
			newMeld.add(meld1.remove(meld1.size()-1));
			newMeld.add(meld2.remove(meld2.size()-1));
			newMeld.add(card);
		}
		else {
			return false; 
		}
		tableCards.add(meld1);
		tableCards.add(meld2);
		tableCards.add(newMeld);
		return true; 
		
	}
	
	
    private boolean splitMeldAddCard(Card card, List<Card> meld){
		ArrayList<ArrayList<Card>> result = new ArrayList<ArrayList<Card>>();
		ArrayList<Card> meld1 = new ArrayList<Card>();
		ArrayList<Card> meld2 = new ArrayList<Card>();
		int i=0; 
		while(i<meld.size()) {
			meld1.add(meld.get(i));
			if(card.compareTo(meld.get(i))==0)
				break;
			i++;
		}
		
		meld2.add(card);
		
		while(i<meld.size()-1) {
			i++;
			meld2.add(meld.get(i));
			
		}
		
		if(isRun(meld1) && isRun(meld2)) {
			tableCards.add(meld1);
			tableCards.add(meld2);
			return true;
		}
		return false;
	}
    
	
	
	//For test only
    public List<Card> fileInputPlay(String filePath) 
            throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuffer sbf = new StringBuffer("");
        String line = null;
        while ((line = br.readLine()) != null) {
            sbf.append(line);
        }
        String[] cardsAndInstruction = sbf.toString().split(" "); 
        br.close();

        List<Card> fileInputCards = new ArrayList<Card>();
        for(String cardString:cardsAndInstruction) {
            Card newCard = new Card(cardString.substring(0, 1), Integer.parseInt(cardString.substring(1)));
            fileInputCards.add(newCard);
        }
        return fileInputCards;
    }

/*    
    public boolean fileInputTestsIsMeld(String filePath) {
    	List<Card> cards = null;
		try {
			cards = fileInputPlay(filePath);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Card card = cards.remove(0);
    	return isMeld(card, cards);
    }
	*/
    
    public boolean fileInputTestsSplitMeldAddCard(String filePath) {
    	boolean result = false;
    	List<Card> cards = null;
    	ArrayList<ArrayList<Card>> resultMelds;
    	try {
    		cards = fileInputPlay(filePath);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	Card card = cards.remove(0);
    	return splitMeldAddCard(card, cards);
    	
    }
    
    public boolean fileInputTestsAddCard2Meld(String filePath) {
    	boolean result = false;
    	List<Card> cards = null;
    	ArrayList<Card> meld1 = new ArrayList<Card>();
    	ArrayList<Card> meld2 = new ArrayList<Card>();
    	ArrayList<ArrayList<Card>> resultMelds;
    	try {
    		cards = fileInputPlay(filePath);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	Card card = cards.remove(0);
    	while(!cards.get(0).getColor().equals("S") && (cards.get(0).getRank()!=0)){
    			 meld1.add(cards.remove(0));
    	}
    	
    	cards.remove(0);
    	while(cards.size()>0) {
    		meld2.add(cards.remove(0));
    	}
    	return addCard2Melds(meld1, meld2, card);	
    }
    

    
	
}
