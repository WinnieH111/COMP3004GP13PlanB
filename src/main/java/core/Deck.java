package com.comp3004GP13.rummy;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    
    private ArrayList<Card> deck;
    
    Deck(){
        deck = new ArrayList<Card>();
        for (int i = 0; i<Card.colors.length; i++) {
            for(int j = 0; j<Card.ranks.length; j++) {
                deck.add(new Card(Card.colors[i], Card.ranks[j]));
                deck.add(new Card(Card.colors[i], Card.ranks[j]));
            }
        }
        shuffleCard();
    }

    private void shuffleCard() {
        Random rand = new Random();
        int deckSize = deck.size();
        int shuffleTime = 10000;
        for(int i=0; i < shuffleTime; i++) {
            int randomIndex = rand.nextInt(deckSize-1) + 0;
            deck.add(deck.remove(randomIndex));
        }
    }
    
    public Card drawCard(){
        Card card = null;
        if(deck.size()>0) {
            card = deck.remove(0);
        }
        
       return card;
    }
    
    public ArrayList<Card> initialHandCards(int numberOfInitialCards) {
    	ArrayList<Card> playerCards = new ArrayList<Card>();
    	for(int i=0; i<numberOfInitialCards; i++) {
    		try {
				playerCards.add(drawCard());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return playerCards;
    }

    
    //Function used in testing
    public int getNumberOfCardOnDeck() {
    	return deck.size();
    }
    
    public Card drawCard(int i) {
    	return deck.remove(i); 
    }
    
    
}