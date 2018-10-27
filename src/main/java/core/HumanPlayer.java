package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HumanPlayer implements Player{
	private ArrayList<Card> rack;
	private boolean iceBroke;
	private boolean turn; 
	
	HumanPlayer(){
		iceBroke = false;
		turn = false; 
		rack = new ArrayList<Card>();
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
	
	public void setIceBrokeStatus() {
		iceBroke = true;
	}

	public Action promptAction() {
		System.out.println("Please indicate your action: IB, DC, PS, PR, AS, AR or ET");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		try {
			if(input.equalsIgnoreCase("IB")) {
				return Action.ICE_BREAKING;
			}
			else if(input.equalsIgnoreCase("DC")) {
				return Action.DRAW_CARD;
			}
			else if(input.equalsIgnoreCase("PS")) {
				return Action.PLAY_SET;
			}
			else if(input.equalsIgnoreCase("PR")) {
				return Action.PLAY_RUN;
			}
			else if(input.equalsIgnoreCase("AS")) {
				return Action.ADD_SET;
			}
			else if(input.equalsIgnoreCase("AR")) {
				return Action.ADD_RUN;
			}
			else if(input.equalsIgnoreCase("ET")) {
				return Action.END_TURN;
			}
		} catch (Exception e) {
			System.out.println("Your Action is invalid! Please treat your life seriously!");
		}
		return null;
	}

	public void drawCard(Card card) {
		rack.add(card);
	}
	
	public ArrayList<Card> promptMelds() throws Exception {
		ArrayList<Card> cards = new ArrayList<Card>();
		Scanner scanner = new Scanner(System. in); 
		String input = scanner. nextLine();
		String [] arr = input.split("\\s+");

		for(String ss : arr){
			Card card = new Card(ss.substring(0,1), Integer.parseInt(ss.substring(1)));
			if(card.isValidCard() && cardIsOnHand(card)) {
				cards.add(card);
			}
			else{
				//System.out.println("Please re-enter your cards, seperate the cards by space");
				//input = scanner.nextLine();
				throw new Exception("Please enter correct card!!! Please treat your life seriously!");
			}		
		}
		return cards;
	}
	
	public void playedCard(ArrayList<ArrayList<Card>> melds) {
		for(ArrayList<Card> cards: melds) {
			rack.removeAll(cards);
		}
	} 
	
	public void initialHand(ArrayList<Card> cards) {
		rack.addAll(cards);
	}
	
	private boolean cardIsOnHand(Card card) {
		for(Card c:rack) {
			if(c.getCardString().equalsIgnoreCase(card.getCardString())) {
				return true;
			}
		}
		return false;
	}
	
	public void printRack() {
		System.out.println("You have these cards on hand: ");
		Collections.sort(rack);
		for(Card card:rack) {
			System.out.print(card.getCardString()+" ");
		}
		System.out.println("\n=====END OF RACK=====");
		}

	}
