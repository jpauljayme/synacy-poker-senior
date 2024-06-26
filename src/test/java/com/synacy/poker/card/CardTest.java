package com.synacy.poker.card;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

	@Test
	public void testCardEquality_sameRankAndSuitAreConsideredEqual() {
//		Card card1 = new Card(CardRank.ACE, CardSuit.DIAMONDS);
//		Card card2 = new Card(CardRank.ACE, CardSuit.DIAMONDS);
		Card card1 = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.DIAMONDS)
				.build();
		Card card2 = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.DIAMONDS)
				.build();
		assertEquals(card1, card2);
	}

	@Test
	public void testCardEquality_differentRankAndSuitAreNotEqual() {
//		Card card1 = new Card(CardRank.ACE, CardSuit.DIAMONDS);
//		Card card2 = new Card(CardRank.KING, CardSuit.DIAMONDS);
		Card card1 = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.DIAMONDS)
				.build();
		Card card2 = new Card.Builder()
				.rank(CardRank.KING)
				.suit(CardSuit.DIAMONDS)
				.build();
		assertNotEquals(card1, card2);
	}

	@Test
	public void styleClass_whenDiamondsOrHearts_returnsRedClass() {
//		Card card1 = new Card(CardRank.ACE, CardSuit.DIAMONDS);
//		Card card2 = new Card(CardRank.ACE, CardSuit.HEARTS);

		Card card1 = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.DIAMONDS)
				.build();
		Card card2 = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.HEARTS)
				.build();
		assertEquals("card-red", card1.styleClass());
		assertEquals("card-red", card2.styleClass());
	}

	@Test
	public void styleClass_whenClubsOrSpades_returnsBlackClass() {
//		Card card1 = new Card(CardRank.ACE, CardSuit.CLUBS);
//		Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);

		Card card1 = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.CLUBS)
				.build();
		Card card2 = new Card.Builder()
				.rank(CardRank.ACE)
				.suit(CardSuit.SPADES)
				.build();

		assertEquals("card-black", card1.styleClass());
		assertEquals("card-black", card2.styleClass());
	}

}