package core;

public enum Action {
	DRAW_CARD("Draw a card from the Deck", "DC"),
	ICE_BREAKING("Break the ice", "IB"),
	PLAY_SET("Play a set of card", "PS"),
	PLAY_RUN("Play a run of card", "PR"),
	ADD_SET("Add a card to a set on the table", "AD"),
	ADD_RUN("Add a card to a run on the table", "AR"),
	END_TURN("END the turn", "ET");
	
	private final String name;
	private final String symbol;
	
	public String toString() {
		return name + " (" + symbol + ")";
	}
	
	Action(String name, String symbol){
		this.name = name;
		this.symbol = symbol;
	}
	
}
