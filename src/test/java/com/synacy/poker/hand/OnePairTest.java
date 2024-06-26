package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.card.CardRank;
import com.synacy.poker.model.card.CardSuit;
import com.synacy.poker.model.hand.types.OnePair;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class OnePairTest {

    @Test
    public void toString_withOnePairAndKickers() {
        List<Card> pair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.HEARTS)
                        .build()
        );

        List<Card> kickers = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.SPADES)
                        .build()
        );

        OnePair onePair = new OnePair(pair, kickers);

        assertEquals("One Pair (2) - A,K,Q High", onePair.toString());
    }

    @Test
    public void toString_withOnePairNoKickers() {
        List<Card> pair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.HEARTS)
                        .build()
        );

        OnePair onePair = new OnePair(pair, Collections.emptyList());

        assertEquals("One Pair (2)", onePair.toString());
    }

    @Test
    public void givenTwoOnePair_thenCompareTo_shouldReturnEqual() {
        List<Card> pair = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.TWO)
                        .suit(CardSuit.HEARTS)
                        .build()
        );

        List<Card> kickers = Arrays.asList(
                new Card.Builder()
                        .rank(CardRank.ACE)
                        .suit(CardSuit.CLUBS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.KING)
                        .suit(CardSuit.DIAMONDS)
                        .build(),
                new Card.Builder()
                        .rank(CardRank.QUEEN)
                        .suit(CardSuit.SPADES)
                        .build()
        );

        OnePair onePairA = new OnePair(pair, kickers);
        OnePair onePairB = new OnePair(pair, kickers);
        assertThat(onePairA).isEqualByComparingTo(onePairB);
    }
}