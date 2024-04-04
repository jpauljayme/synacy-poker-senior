package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.ThreeOfAKind;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ThreeOfAKindTest {

    @Test
    public void toString_withTripFoursAndKickers() {
        List<Card> trips = Arrays.asList(
//                new Card(CardRank.FOUR, CardSuit.CLUBS),
//                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
//                new Card(CardRank.FOUR, CardSuit.SPADES)
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.FOUR)
                        .suit(CardSuit.SPADES)
                        .build()
        );
        List<Card> kickers = Arrays.asList(
//                new Card(CardRank.ACE, CardSuit.CLUBS),
//                new Card(CardRank.TWO, CardSuit.CLUBS)
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build()
        );

        ThreeOfAKind threeOfAKind = new ThreeOfAKind(trips, kickers);

        assertEquals("Trips (4) - A,2 High", threeOfAKind.toString());
    }

}