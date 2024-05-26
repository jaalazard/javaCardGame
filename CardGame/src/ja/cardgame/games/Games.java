package ja.cardgame.games;

import ja.cardgame.controller.GameController;
import ja.cardgame.model.Deck;
import ja.cardgame.view.View;

public class Games {

	public static void main(String[] args) {
		GameController gc = new GameController(new Deck(), new View());
		gc.run();
	}

}
