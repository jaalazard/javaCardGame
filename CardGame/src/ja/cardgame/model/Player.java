package ja.cardgame.model;

public class Player {
	public Player(String name) {
		super();
		this.name = name;
		hand = new Hand();
	}

	private String name;
	private Hand hand;
	
	public void addCardToHand(PlayingCard pc) {
		hand.addCard(pc);
	}
	
	public PlayingCard getCard(int index) {
		return hand.getCard(index);
	}
	
	public PlayingCard removeCard() {
		return hand.removeCard();
	}
}
