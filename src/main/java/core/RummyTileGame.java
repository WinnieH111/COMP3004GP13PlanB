package core;

import java.util.ArrayList;
import java.util.Scanner;

public class RummyTileGame {
	private Deck deck;
	private Table table;
	private ArrayList<Player> players; 
	private Player hPlayer = new HumanPlayer();
	private Player aiPlayer = new AIPlayer(table);
	
	public RummyTileGame() {
		deck = new Deck();
		table = new Table();
		players.add(hPlayer);
		players.add(aiPlayer);
	}
	
	public void run() {
		//Who start to play
		int random = (int)(Math.random() * (players.size()));
		Player startPlayer = players.get(random);
		startPlayer.setTurn();
		//Distribute initial hand cards
		for(Player player:players) {
			player.initialHand(deck.initialHandCards(14));
		}		
		//while(no one wins)
		while(true) {
			if(startPlayer.equals(hPlayer)){
				//Play
				if(!startPlayer.getIceBreakState()) {
					System.out.println("Please play melds to break the ice");
					int score = 0;
					while(score<30) {
						try {
							score += calScore(hPlayer.promptMelds());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					



				//if start player is not human 
				


			}

		}

	}
	}

	public int calScore(ArrayList<Card> cards) {
		int sum = 0;
		for(Card card:cards) {
			sum+=card.getRank();
		}
		return sum;

	}

	public static void main(String[] args) {
		new RummyTileGame().run();
	}
}
