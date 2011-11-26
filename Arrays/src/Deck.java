import java.util.Arrays;
import java.util.Random;

public class Deck {

	private Card[] deck = new Card[0];
	private static Random random = new Random();

	/**
	 * Simple constructor Returns an empty Deck
	 */
	public Deck() {
	}

	/**
	 * Returns a Deck filled with all types of cards
	 * 
	 * @param shuffle
	 *            if TRUE, shuffles the new deck
	 */
	public Deck(boolean shuffle) {
		for (int i = 0; i < Card.Suit.values().length; i++) {
			for (int j = 0; j < Card.Value.values().length; j++) {
				this.add(Card.Suit.values()[i], Card.Value.values()[j]);
			}
		}
		if (shuffle) {
			this.shuffle(Card.Suit.values().length * Card.Value.values().length
					* 10);
		}
	}

	/**
	 * Fills the deck with the supplied Cards
	 * 
	 * @param cards
	 *            Array of Cards to be added to the newly created Deck
	 */
	public Deck(Card[] cards) {
		for (int i = 0; i < cards.length; i++) {
			this.add(cards[i]);
		}
	}

	/**
	 * Adds a random new Card to the Deck
	 */
	public void add() {
		this.deck = Arrays.copyOf(this.deck, this.deck.length + 1);
		this.deck[this.deck.length - 1] = new Card();
	}

	/**
	 * Adds a Card of a particular Suit and Value to the Deck
	 * 
	 * @param suit
	 *            Suit of the new Card
	 * @param value
	 *            Value of the new Card
	 */
	public void add(Card.Suit suit, Card.Value value) {
		this.deck = Arrays.copyOf(this.deck, this.deck.length + 1);
		this.deck[this.deck.length - 1] = new Card(suit, value);
	}

	/**
	 * Adds a Card to this Deck
	 * 
	 * @param card
	 *            The Card to be added
	 */
	public void add(Card card) {
		this.deck = Arrays.copyOf(this.deck, this.deck.length + 1);
		this.deck[this.deck.length - 1] = card;
	}

	/**
	 * Adds a Card to this Deck at a particular index
	 * 
	 * @param card
	 *            The Card to be added
	 * @param index
	 *            The place in the deck the Card should be added
	 */
	public void add(Card card, int index) {
		this.deck = Arrays.copyOf(this.deck, this.deck.length + 1);
		for (int i = this.deck.length - 1; i > index; i--) {
			this.deck[i] = this.deck[i - 1];
		}
		this.deck[index] = card;
	}

	/**
	 * Picks one random Card from this Deck
	 * 
	 * @return The random Card
	 */
	public Card pick() {
		int pick = random.nextInt(this.deck.length);
		Card result = remove(pick);
		return result;
	}

	/**
	 * Picks several random Cards from this Deck
	 * 
	 * @param number
	 *            The number of Cards to be picked
	 * @return An Array of Cards that were picked from this Deck
	 */

	public Card[] pick(int number) {
		Card[] result = new Card[number];
		for (int i = 0; i < number; i++) {
			result[i] = pick();
		}
		return result;
	}

	/**
	 * Removes the first card from the top of this Deck
	 * 
	 * @return The Card that was at the top of the Deck
	 */
	public Card deal() {
		return remove(0);
	}

	/**
	 * Burns the first Card from the top of this Deck, then returns the next
	 * Card
	 * 
	 * @param burn
	 *            If TRUE, removes the top Card before returning the next Card
	 * @return Essentially the second Card, after "burning" the first Card
	 */
	public Card deal(boolean burn) {
		if (burn) {
			remove(0);
		}
		return remove(0);
	}

	/**
	 * Removes several Cards from the top of this Deck
	 * 
	 * @param number
	 *            The number of Cards to be dealt
	 * @return The Cards from the top of this Deck
	 */
	public Card[] deal(int number) {
		Card[] result = new Card[number];
		for (int i = 0; i < number; i++) {
			result[i] = deal();
		}
		return result;
	}

	/**
	 * Removes several Cards from the top of this Deck The Cards are
	 * sequentially distributed across several hands
	 * 
	 * @param hands
	 *            The number of hands that are dealt
	 * @param cards
	 *            The number of Cards per hand
	 * @return A two dimensional Array of Cards, the outer Arrays representing
	 *         the hands, the inner representing the Cards in each hand.
	 */
	public Card[][] deal(int hands, int cards) {
		Card[][] result = new Card[hands][cards];
		for (int i = 0; i < cards; i++) {
			for (int j = 0; j < hands; j++) {
				result[j][i] = deal();
			}
		}
		return result;
	}

	/**
	 * Shuffles the deck by switching two cards X amount of times
	 * 
	 * @param times
	 *            The amount of times two cards will be switched places
	 */
	public void shuffle(int times) {
		for (int i = 0; i < times; i++) {
			int a = random.nextInt(this.deck.length);
			int b = random.nextInt(this.deck.length);
			Card tempCard = this.deck[a];
			this.deck[a] = this.deck[b];
			this.deck[b] = tempCard;
		}
	}

	/**
	 * Cuts this Deck at a random position
	 */
	public void cut() {
		int position = random.nextInt(this.deck.length);
		cut(position);
	}

	/**
	 * Cuts this Deck at a specified position
	 * 
	 * @param position
	 *            Number of Cards after which this Deck is to be cut
	 */
	public void cut(int position) {
		int len = this.deck.length;
		position %= len;
		Card[] temp = new Card[len];
		for (int i = 0; i < len - position; i++) {
			temp[i] = this.deck[position + i];
		}
		for (int i = len - position; i < len; i++) {
			temp[i] = this.deck[i - (len - position)];
		}
		this.deck = temp;
	}

	/**
	 * Sorts this Deck
	 * 
	 * @param bySuit
	 *            If true, sorts this Deck first by suit, then by value. The
	 *            other way around if false.
	 */
	public void sort(boolean bySuit) {
		int n = this.deck.length;
		Card temp;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (this.deck[j - 1].val(bySuit) > this.deck[j].val(bySuit)) {
					temp = this.deck[j - 1];
					this.deck[j - 1] = this.deck[j];
					this.deck[j] = temp;
				}
			}
		}
	}

	/**
	 * Returns the index of the Card in this Deck as an integer Returns -1 if
	 * the Card does not appear in this Deck
	 * 
	 * @param card
	 *            Card to be matched
	 * @return the index of the Card
	 */
	public int sequentialSearch(Card card) {
		int result = -1;
		for (int i = 0; i < this.deck.length; i++) {
			if (this.deck[i].val(true) == card.val(true)) {
				result = i;
			}
		}
		return result;
	}

	public int binarySearch(Card card){
		int result = -1;
		int target = card.val(true);
		int eval, test;
		int top = this.deck.length;
		int bottom = 0;
		while (top-bottom > 1){
			test = bottom + ((top - bottom)/2);
			eval = this.deck[test].val(true);
			if (eval < target){
				bottom = test;
			} else if (eval > target){
				top = test;
			} else {
				result = test;
				break;
			}			
		}
		return result;
	}
	
	/**
	 * Removes a Card from the Deck at position
	 * 
	 * @param position
	 *            Index of the Card
	 * @return The removed Card
	 */
	private Card remove(int position) {
		Card result = this.deck[position];
		Card[] temp = Arrays.copyOf(this.deck, this.deck.length - 1);
		for (int i = position; i < temp.length; i++) {
			temp[i] = this.deck[i + 1];
		}
		this.deck = temp;
		return result;
	}

	/**
	 * Builds a String describing all Cards in this Deck
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < this.deck.length; i++) {
			result.append(this.deck[i].toString());
			result.append("\n");
		}
		return result.toString();
	}

}
