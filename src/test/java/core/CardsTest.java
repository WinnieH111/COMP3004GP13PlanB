package core;

import org.junit.Test;

import junit.framework.TestCase;

public class CardsTest extends TestCase {
	@Test
	public void testGenerateCard() {
		Deck generatedCard = new Deck();
		assertEquals(104, generatedCard.getNumberOfCardOnDeck());
	}
	
	@Test
	public void testShuffle() throws Exception {
		Deck deck = new Deck();
        assertEquals(false, deck.drawCard().getCardString().equals("O1"));
        assertEquals(false, deck.drawCard().getCardString().equals("O2"));
        assertEquals(false, deck.drawCard(deck.getNumberOfCardOnDeck()-1).getCardString().equals("G13"));
        assertEquals(101, deck.getNumberOfCardOnDeck());
	}
	
	@Test
	public void testDistributeInitial() throws Exception {
		Deck deck = new Deck();
		assertEquals(14, deck.initialHandCards(14).size());
	}
	
	@Test 
	public void testCardCompare() {
		Card card1 = new Card("B", 5);
		assertEquals(1, card1.compareTo(new Card("O", 5)));
		Card card2 = new Card("O", 10);
		assertEquals(-1, card2.compareTo(new Card("O", 13)));
		Card card3 = new Card ("G", 6);
		assertEquals(0, card3.compareTo(new Card("G", 6)));
		Card card4 = new Card("R", 9);
		assertEquals(-1, card4.compareTo(new Card("G", 9)));
		Card card5 = new Card("R", 6);
		assertEquals(1, card5.compareTo(new Card("R", 1)));
		
	}
	
	@Test public void testColorValue() {
		Card card1 = new Card("G", 5);
		assertEquals(3, card1.getColorValue(card1.getColor()));
		Card card2 = new Card("B", 10);
		assertEquals(2, card2.getColorValue(card2.getColor()));
		Card card3 = new Card("O", 13);
		assertEquals(0, card3.getColorValue(card3.getColor()));
		Card card4 = new Card("R", 1);
		assertEquals(1, card4.getColorValue(card4.getColor()));
	}
	
	/*@Test
	public void testDistributeEndOfTurn() {
		Deck deck = new Deck();
		assertEquals(1, deck.drawCard().);

	}*/
}
