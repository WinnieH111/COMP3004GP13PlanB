package core;

import java.util.ArrayList;

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
					Action action = hPlayer.promptAction();
					int score = 0;
					if(action.compareTo(Action.DRAW_CARD)==0) {
						hPlayer.drawCard(deck.drawCard());
						hPlayer.setEndTurn();
					}
					else if(action.compareTo(Action.ICE_BREAKING)==0) {
						while(action!=Action.END_TURN) {
							try {
								System.out.println("Please enter the melds you want to play: ");
									ArrayList<Card> tempMeld = hPlayer.promptMelds();
									if(table.isMeld(tempMeld))
										tempPlayedCard.add(tempMeld);
							} catch (Exception e) {
								e.printStackTrace();
							}
							action = hPlayer.promptAction();
						}
						hPlayer.setEndTurn();
						for(ArrayList<Card> cards:tempPlayedCard) {
							score+=calScore(cards);
						}
						if(score>30) {
							//remove the tempPlayedCard from Player's rack
							hPlayer.playedCard(tempPlayedCard);
							//Add the tempPlayedCard to tableCard
							table.addMelds(tempPlayedCard);
							hPlayer.setIceBrokeStatus();
						}
						//if ice breaking not success, draw card 
						else {
							hPlayer.drawCard(deck.drawCard());
						}
					}
				}
				//Player's ice is broken
				else {
					Action action = hPlayer.promptAction();
					if(action.equals(Action.ADD_RUN)|| action.equals(Action.ADD_SET)) {
						
					}
				}
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
