package core;

import java.util.ArrayList;
import java.util.Scanner;

public class RummyTileGame {
	private Deck deck;
	private Table table;
	private ArrayList<Player> players = new ArrayList<Player>(); 
	private Player hPlayer = new HumanPlayer();
	private Player aiPlayer = new AIPlayer();
	
	public RummyTileGame() {
		deck = new Deck();
		table = new Table();
		players.add(hPlayer);
		players.add(aiPlayer);
	}
	
	public void run() {
		//Who start to play
		int random = (int)(Math.random() * (players.size()));
		//Player currPlayer = players.get(random);
		Player currPlayer = hPlayer;
		currPlayer.setTurn();
		//Distribute initial hand cards
		for(Player player:players) {
			player.initialHand(deck.initialHandCards(14));
		}		
		//while(no one wins)
		while(true) {
			if(currPlayer.equals(hPlayer)){
				hPlayer.printRack();
				ArrayList<ArrayList<Card>> tempPlayedCard = new ArrayList<ArrayList<Card>>();
				//Play
				if(!currPlayer.getIceBreakState()) {
					System.out.println("You are required to break the ice. (type in IB for ice breaking) ");
					System.out.println("Please indicate your action: ");
					Action action = hPlayer.promptAction();
					if(action.compareTo(Action.DRAW_CARD)==0) {
						hPlayer.drawCard(deck.drawCard());
						hPlayer.setEndTurn();
					}
					else if(action.compareTo(Action.ICE_BREAKING)==0) {
						int score = 0;
						while(action!=Action.END_TURN) {
							try {
								System.out.println("Please enter the melds you want to play: ");
								tempPlayedCard.add(hPlayer.promptMelds());
							} catch (Exception e) {
								e.printStackTrace();
							}
							for(ArrayList<Card> cards:tempPlayedCard) {
								score+=calScore(cards);
							}
							action = hPlayer.promptAction();
						}
						if(score>30) {
							//remove the tempPlayedCard from Player's rack
							hPlayer.playedCard(tempPlayedCard);
							//Add the tempPlayedCard to tableCard
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
