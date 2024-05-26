package ja.cardgame.model;

public class PlayingCard {
	private Rank rank;
	private Suit suit;
	private boolean faceUp;

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}
	
	public boolean isFaceUp() {
		return faceUp;
	}
	
	public boolean flip() {
		faceUp = !faceUp;
		return faceUp;
	}

	public PlayingCard(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
}
