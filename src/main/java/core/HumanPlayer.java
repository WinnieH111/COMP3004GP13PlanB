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
	
	public Action promptAction(Action[] actions) {
		// TODO Auto-generated method stub
		return null;
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
	
	public void initialHand(ArrayList<Card> cards) {
		rack.addAll(cards);
	}
}
