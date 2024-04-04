package com.synacy.poker.hand;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A service class used to calculate the winning hand.
 */
@Component
public class WinningHandCalculatorService {

	/**
	 * @param playerHands
	 * @return The winning {@link Hand} from a list of player hands.
	 */
	public Optional<Hand> calculateWinningHand(List<Hand> playerHands) {
		return  playerHands.stream()
				.max(Comparator.comparing(Hand::getHandType));
	}
}