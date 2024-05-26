package ja.cardgame.controller;

import java.util.ArrayList;
import java.util.List;

import ja.cardgame.model.Deck;
import ja.cardgame.model.Player;
import ja.cardgame.model.PlayingCard;
import ja.cardgame.view.View;

public class GameController {

	public GameController(Deck deck, View view) {
		super();
		this.deck = deck;
		this.view = view;
		this.players = new ArrayList<Player>();
		this.gameState = GameState.AddingPlayers;
		view.setController(this);
	}

	enum GameState {
		AddingPlayers, CardsDealt, WinnerRevealed;
	}

	Deck deck;
	List<Player> players;
	Player winner;
	View view;
	GameState gameState;

	public void run() {
		while (gameState == GameState.AddingPlayers) {
			view.promptForPlayerName();
		}
		switch (gameState) {
		case CardsDealt:
			view.promptForFlip();
			break;
		case WinnerRevealed:
			view.promptForNewGame();
			break;
		}
	}

	public void addPlayer(String playerName) {
		if (gameState == GameState.AddingPlayers) {
			players.add(new Player(playerName));
			view.showPlayerName(players.size(), playerName);
		}
	}

	public void startGame() {
		if (gameState != GameState.CardsDealt) {
			deck.shuffle();
			int playerIndex = 1;
			for (Player player : players) {
				player.addCardToHand(deck.removeTopcard());
				view.showFaceDownCardForPlayer(playerIndex++, player.getName());
			}
			gameState = GameState.CardsDealt;
		}
		this.run();
	}

	public void flipCards() {
		System.out.println("flipkards");

		int playerIndex = 1;
		for (Player player : players) {
			PlayingCard pc = player.getCard(0);
			pc.flip();
			view.showCardForPlayer(playerIndex++, player.getName(), pc.getRank().toString(), pc.getSuit().toString());
		}
		this.evaluateWinner();
		this.displayWinner();
		rebuildDeck();
		gameState = GameState.WinnerRevealed;
		this.run();
	}

	void displayWinner() {
		view.showWinner(winner.getName());
	}

	void rebuildDeck() {
		for (Player player : players) {
			deck.returnCardToDeck(player.removeCard());
		}
	}

	void evaluateWinner() {
		Player bestPlayer = null;
		int bestRank = -1;
		int bestSuit = -1;

		for (Player player : players) {
			boolean newBestPlayer = false;

			if (bestPlayer == null) {
				newBestPlayer = true;
			} else {
				PlayingCard pc = player.getCard(0);
				int thisRank = pc.getRank().value();
				if (thisRank >= bestRank) {
					if (thisRank > bestRank) {
						newBestPlayer = true;
					} else {
						if (pc.getSuit().value() > bestSuit) {
							newBestPlayer = true;
						}
					}
				}
			}
			if (newBestPlayer) {
				bestPlayer = player;
				winner = bestPlayer;
				PlayingCard pc = player.getCard(0);
				bestRank = pc.getRank().value();
				bestSuit = pc.getSuit().value();
			}
			gameState = GameState.WinnerRevealed;

		}
	}

}
