package core;

import java.util.ArrayList;
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

	public Action promptAction() {
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
			if(card.isValidCard() && rack.contains(card)) {
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
}
