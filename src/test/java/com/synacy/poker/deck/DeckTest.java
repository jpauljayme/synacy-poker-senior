package com.synacy.poker.deck;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.deck.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DeckTest {

	private Deck deck;

	@Before
	public void setUp() {
		deck = new Deck();
	}

	@Test
	public void constructor_initializesWithNoCards() {
		assertEquals(0, deck.size());
	}

	@Test
	public void addCard() {
		//new Card(CardRank.ACE, CardSuit.HEARTS);
		Card card = new Card.Builder().rank(CardRank.ACE)
						.suit(CardSuit.HEARTS).build();
		deck.addCard(card);

		assertEquals(1, deck.size());
	}

	@Test
	public void shuffle_returnsShuffledDeckOfEqualSize() {
//		Card aceHearts = new Card(CardRank.ACE, CardSuit.HEARTS);
//		Card aceDiamonds = new Card(CardRank.ACE, CardSuit.DIAMONDS);
//		Card aceClubs = new Card(CardRank.ACE, CardSuit.CLUBS);
//		Card aceSpades = new Card(CardRank.ACE, CardSuit.SPADES);
//		deck.addCards(Arrays.asList(aceHearts, aceDiamonds, aceClubs, aceSpades));
		Card aceHearts = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.HEARTS)
				.build();

		Card aceDiamonds = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.DIAMONDS)
				.build();

		Card aceClubs = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.CLUBS)
				.build();

		Card aceSpades = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.SPADES)
				.build();

		deck.addCards(Arrays.asList(aceHearts,
				aceDiamonds,
				aceClubs,
				aceSpades));

		deck.shuffle();

		assertEquals(4, deck.size());
	}

	@Test
	public void removeFromTop_removeCardFromTopOfDeck() {
		Card expectedTopCard = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.HEARTS)
				.build();

		Card bottomCard = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.DIAMONDS)
				.build();

		deck.addCards(Arrays.asList(expectedTopCard, bottomCard));

		Card actualRemovedTopCard = deck.removeFromTop();

		assertEquals(expectedTopCard, actualRemovedTopCard);
	}

}