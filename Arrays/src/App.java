
public class App {

	public static void main(String[] args){

		Deck deck = new Deck(false);

		prt("Een nieuw stok kaarten:");
		prt(deck.toString());

		prt("Het dek wordt op een willekeurige plek gecoupeerd:");		
		deck.cut();
		prt(deck.toString());
		
		prt("Deze kaart wordt van de top van het dek verwijderd:");
		prt(deck.deal().toString());
		prt("");
		
		prt("Deze kaart wordt willekeurig verwijderd:");
		prt(deck.pick().toString());
		prt("");

		prt("Deze kaarten resteren:");
		prt(deck.toString());
		prt("");

		prt("Deze kaarten worden willekeurig verwijderd:");
		Card[] cards = deck.pick(6);
		for (int i=0; i<cards.length; i++){
			prt(cards[i].toString());
		}
		prt("");
		prt("Deze kaarten resteren:");
		prt(deck.toString());

		prt("Het deck wordt geschud:");
		deck.shuffle(520);
		prt(deck.toString());
		
		prt("Er worden 3 handen van 5 kaarten uitgedeeld:");
		prt("");
		Card[][] hand = deck.deal(3, 5);
		for (int i=0; i<hand.length; i++){
			prt("Hand "+(i+1)+":");
			for (int j=0; j<hand[i].length; j++){
				prt(hand[i][j].toString());
			}
			prt("");
		}
		prt("");
		
		prt("Deze kaarten resteren:");
		prt(deck.toString());	

		prt("Sorteren op getal, dan op soort:");
		deck.sort(false);
		prt(deck.toString());
		
		prt("Sorteren op soort, dan op getal:");
		deck.sort(true);
		prt(deck.toString());	

		prt("Een willekeurige kaart");
		Card card = new Card();
		prt(card.toString());
		
		prt("Sequential search van de nieuwe kaart (-1 als ie er niet meer is):");
		prt(""+deck.sequentialSearch(card));
				
		prt("Binary search van de nieuwe kaart (-1 als ie er niet meer is):");
		prt(""+deck.binarySearch(card));
		

	}
	
	static void prt(String str){
		System.out.println(str);
	}
	
}
