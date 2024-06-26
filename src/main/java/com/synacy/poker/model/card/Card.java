package com.synacy.poker.model.card;

import java.util.Objects;

/**
 * The card in a deck. A combination of {@link CardRank} and {@link CardSuit}
 */
public class Card implements Comparable<Card> {

	private final CardRank rank;
	private final CardSuit suit;

	protected Card(Builder builder){
		this.rank = builder.rank;
		this.suit = builder.suit;
	}

	/**
	 * @return The {@link CardRank}
	 */
	public CardRank getRank() {
		return rank;
	}

	/**
	 * @return The {@link CardSuit}
	 */
	public CardSuit getSuit() {
		return suit;
	}

	/**
	 * @return The CSS class of the card, e.g. <code>card-red</code>
	 */
	public String styleClass() {
		return "card-" + suit.getColor();
	}

	/**
	 * @return The combination of the {@link CardRank} and {@link CardSuit}, e.g. Ace of Hearts is
	 * <code>A&hearts;</code>
	 */
	public String toString() {
		return getRank().toString() + getSuit().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return rank == card.rank &&
				suit == card.suit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}

	/**
	 * Sort in descending order according to rank
	 * @param otherCard the object to be compared.
	 * @return
	 */
	@Override
	public int compareTo(Card otherCard) {
		return otherCard.rank.compareTo(this.rank);
	}

	public static class Builder {
		private CardRank rank;
		private CardSuit suit;

		public Builder rank(CardRank rank) {
			this.rank = rank;
			return this;
		}

		public Builder suit(CardSuit suit) {
			this.suit = suit;
			return this;
		}

		public Card build() {
			return new Card(this);
		}
	}
}
