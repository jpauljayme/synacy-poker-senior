package com.synacy.poker.hand;

import com.synacy.poker.model.card.Card;
import com.synacy.poker.model.hand.Hand;
import com.synacy.poker.model.hand.types.*;
import com.synacy.poker.service.WinningHandCalculatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static com.synacy.poker.model.card.CardRank.*;
import static com.synacy.poker.model.card.CardSuit.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WinningHandCalculatorTest {

    @Autowired
    WinningHandCalculatorService winningHandCalculatorService;

    @Test
    public void givenThreePlayers_thenIdentifyWinningHand_shouldReturnPlayerOneInAListWithStraightFlush() {
        List<Hand> playerHands = new ArrayList<>();
        Hand expectedWinningHand = new StraightFlush(
                Arrays.asList(new Card.Builder()
                                .rank(QUEEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(QUEEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(JACK)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build())
        );
        playerHands.add(expectedWinningHand);
        playerHands.add(new FourOfAKind(
                Arrays.asList(new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(SPADES)
                                .build()),
                Collections.singletonList(new Card.Builder().rank(FIVE)
                        .suit(DIAMONDS).build())
        ));
        playerHands.add(new Flush(
                Arrays.asList(new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(SEVEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(TWO)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(HEARTS)
                                .build())
        ));
        winningHandCalculatorService.calculateWinningHand(playerHands);
        Hand winningHand = winningHandCalculatorService.getWinningHand();

        assertThat(winningHand)
                .isEqualByComparingTo(expectedWinningHand);
    }

    @Test
    public void givenThreePlayers_thenIdentifyWinningHand_shouldReturnPlayerOneAndTwoInAListOfStraightFlushAsTie() {
        List<Hand> playerHands = new ArrayList<>();
        Hand expectedWinningHand = new StraightFlush(
                Arrays.asList(new Card.Builder()
                                .rank(QUEEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(JACK)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(EIGHT)
                                .suit(HEARTS)
                                .build())
        );
        playerHands.add(expectedWinningHand);

        Hand expectedWinningHandPLayerTwo = new StraightFlush(
                Arrays.asList(new Card.Builder()
                                .rank(QUEEN)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(JACK)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(EIGHT)
                                .suit(CLUBS)
                                .build())
        );
        playerHands.add(expectedWinningHandPLayerTwo);

        playerHands.add(new Flush(
                Arrays.asList(new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(SEVEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(TWO)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(HEARTS)
                                .build())
        ));

        winningHandCalculatorService.calculateWinningHand(playerHands);
        Hand winningHand = winningHandCalculatorService.getWinningHand();
        assertThat(winningHand)
                .isEqualByComparingTo(expectedWinningHand);
    }

    @Test
    public void givenTwoOnePairOneHigh_thenIdentifyWinningHand_shouldReturnPlayerOneWinner() {
        List<Hand> playerHands = new ArrayList<>();
        Hand playerOne = new OnePair(
                Arrays.asList(new Card.Builder()
                                .rank(NINE)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(DIAMONDS)
                                .build()),
                Arrays.asList(
                        new Card.Builder()
                                .rank(QUEEN)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(JACK)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(HEARTS)
                                .build())
        );

        Hand playerTwo = new OnePair(
                Arrays.asList(new Card.Builder()
                                .rank(SIX)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(SIX)
                                .suit(HEARTS)
                                .build()),
                Arrays.asList(new Card.Builder()
                                .rank(KING)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(SEVEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FOUR)
                                .suit(CLUBS)
                                .build()
                ));

        Hand playerThree = new HighCard(
                Arrays.asList(new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(KING)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(FOUR)
                                .suit(HEARTS)
                                .build())
        );
        playerHands.add(playerTwo);
        playerHands.add(playerOne);
        playerHands.add(playerThree);

        winningHandCalculatorService.calculateWinningHand(playerHands);
        Hand winningHand = winningHandCalculatorService.getWinningHand();
        assertThat(winningHand)
                .isEqualByComparingTo(playerOne);
    }

    @Test
    public void givenTwoOnePairOneHigh_thenIdentifyWinningHand_shouldReturnTie() {
        List<Hand> playerHands = new ArrayList<>();
        Hand playerOne = new OnePair(
                Arrays.asList(new Card.Builder()
                                .rank(EIGHT)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(EIGHT)
                                .suit(DIAMONDS)
                                .build()),
                Arrays.asList(
                        new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(SIX)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(SPADES)
                                .build())
        );

        Hand playerTwo = new OnePair(
                Arrays.asList(new Card.Builder()
                                .rank(EIGHT)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(EIGHT)
                                .suit(CLUBS)
                                .build()),
                Arrays.asList(new Card.Builder()
                                .rank(TEN)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(SIX)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(CLUBS)
                                .build()
                ));

        Hand playerThree = new HighCard(
                Arrays.asList(new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(KING)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(FOUR)
                                .suit(HEARTS)
                                .build())
        );

        playerHands.add(playerTwo);
        playerHands.add(playerOne);
        playerHands.add(playerThree);

        winningHandCalculatorService.calculateWinningHand(playerHands);
        Hand winningHand = winningHandCalculatorService.getWinningHand();

        assertThat(winningHand)
                .isEqualByComparingTo(playerTwo);

        assertThat(playerOne)
                .isEqualByComparingTo(playerTwo);
    }

    @Test
    public void givenTwoOnePairOneHigh_thenIdentifyWinningHand_shouldReturnPlayerTwo() {
       List<Hand> playerHands = new ArrayList<>();
        Hand playerTwo = new OnePair(
                Arrays.asList(new Card.Builder()
                                .rank(NINE)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build()),
                Arrays.asList(
                        new Card.Builder()
                                .rank(QUEEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(HEARTS)
                                .build())
        );

        Hand playerThree = new OnePair(
                Arrays.asList(new Card.Builder()
                                .rank(TEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(SPADES)
                                .build()),
                Arrays.asList(new Card.Builder()
                                .rank(QUEEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(HEARTS)
                                .build()
                ));

        Hand playerOne = new HighCard(
                Arrays.asList(new Card.Builder()
                                .rank(KING)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(QUEEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(TEN)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(EIGHT)
                                .suit(CLUBS)
                                .build())
        );

        playerHands.add(playerTwo);
        playerHands.add(playerOne);
        playerHands.add(playerThree);

        winningHandCalculatorService.calculateWinningHand(playerHands);
        Hand winningHand = winningHandCalculatorService.getWinningHand();

        assertThat(winningHand)
                .isEqualByComparingTo(playerThree);

        assertThat(playerTwo)
                .isLessThan(playerThree);

        assertThat(playerOne)
                .isLessThan(playerTwo);
    }


    @Test
    public void givenTwoStraightOneHighCard_shouldReturnTie(){
        List<Hand> playerHands = new ArrayList<>();
        Hand playerTwoHand = new Straight(
                Arrays.asList(new Card.Builder()
                                .rank(SEVEN)
                                .suit(SPADES)
                                .build(),
                        new Card.Builder()
                                .rank(SIX)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(FOUR)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(THREE)
                                .suit(DIAMONDS)
                                .build()
                ));

        Hand playerThreeHand = new HighCard(
                Arrays.asList(new Card.Builder()
                                .rank(KING)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(QUEEN)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(NINE)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(SEVEN)
                                .suit(HEARTS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(CLUBS)
                                .build()
                ));

        Hand playerOneHand = new Straight(
                Arrays.asList(new Card.Builder()
                                .rank(SEVEN)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(SIX)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(FIVE)
                                .suit(CLUBS)
                                .build(),
                        new Card.Builder()
                                .rank(FOUR)
                                .suit(DIAMONDS)
                                .build(),
                        new Card.Builder()
                                .rank(THREE)
                                .suit(DIAMONDS)
                                .build())
        );

        playerHands.add(playerOneHand);
        playerHands.add(playerTwoHand);
        playerHands.add(playerThreeHand);

        winningHandCalculatorService.calculateWinningHand(playerHands);

        boolean isPlayerOneWinner = winningHandCalculatorService.isPlayerWinner(playerOneHand);
        boolean isPlayerTwoWinner = winningHandCalculatorService.isPlayerWinner(playerTwoHand);
        boolean isPlayerThreeWinner = winningHandCalculatorService.isPlayerWinner(playerThreeHand);

        assertThat(isPlayerOneWinner)
                .isTrue();

        assertThat(isPlayerTwoWinner)
                .isTrue();

        assertThat(isPlayerThreeWinner)
                .isFalse();
    }
}