package core;

public enum Action {
	DRAW_CARD("Draw a card from the Deck", "S"),
	ICE_BREAKING("Break the ice", "I"),
	PLAY_SET("Play a set of card", "PS"),
	PLAY_RUN("Play a run of card", "PR"),
	ADD_SET("Add a card to a set on the table", "AD"),
	ADD_RUN("Add a card to a run on the table", "AR");

	
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
