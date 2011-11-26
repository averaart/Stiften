import java.util.Random;


public class Card {
	
	public enum Suit {
		Diamonds, Clubs, Hearts, Spades
	}
	
	public enum Value {
		Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
	}
	
	private Suit suit;
	private Value value;
	private static Random random = new Random();
	
	/**
	 * Simple constructor
	 * Returns a random Card
	 */
	public Card(){
		int pick = random.nextInt(Suit.values().length);
		this.suit = Suit.values()[pick];
		pick = random.nextInt(Value.values().length);
		this.value = Value.values()[pick]; 
	}

	/**
	 * Returns a Card of a particular Suit and Value
	 * @param suit The Suit of the new Card
	 * @param value The Value of the new Card
	 */
	public Card(Suit suit, Value value){
		this.suit = suit;
		this.value = value;
	}
	
	/**
	 * Returns a numeric value for this Card
	 * @param bySuit if TRUE gives precedence to Suit, else to Value
	 * @return the numeric value for this Card
	 */
	public int val(boolean bySuit){
		int result;
		if (bySuit){
			result = this.value.ordinal()+(this.suit.ordinal()*Card.Value.values().length);
		} else {
			result = (this.value.ordinal()*Card.Suit.values().length)+this.suit.ordinal();
		}
		return result;
	}
	
	/**
	 * Returns a description of this Card as a String
	 */
	public String toString(){
		return this.value +" of "+this.suit;
	}
	
}
