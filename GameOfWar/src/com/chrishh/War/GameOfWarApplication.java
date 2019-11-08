package com.chrishh.War;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameOfWarApplication {

	public static void main(String[] args) throws Exception {

		Player player1 = new Player("Butch");
		Player player2 = new Player("Sundance");
		List<Card> warPile = new ArrayList<Card>();

		Game theGame = new Game();

		List<Card> shuffledDeck = theGame.getCards();
		List<Card> playerOneList = theGame.dealCardsOne(shuffledDeck);
		List<Card> playerTwoList = theGame.dealCardsTwo(shuffledDeck);
		
		Queue<Card> p1Q = new LinkedList<Card>();
		for (int i = 0; i < 26; i++) {
			p1Q.offer(playerOneList.get(i));
		}

		Queue<Card> p2Q = new LinkedList<Card>();
		for (int i = 0; i < 26; i++) {
			p2Q.offer(playerTwoList.get(i));
		}
		
		theGame.startGame(player1, player2, p1Q, p2Q, warPile);

	}
}
