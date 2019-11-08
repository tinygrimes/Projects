package com.chrishh.War;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Game {
 
	public List<Card> getCards() throws Exception {

		String url = "https://deckofcardsapi.com/api/deck/new/draw/?count=52";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject myResponse = new JSONObject(response.toString());
		JSONArray cards = myResponse.getJSONArray("cards");

		List<Card> cardList = new ArrayList<>();
		for (int i = 0; i < cards.length(); i++) {
			Card aCard = new Card();
			aCard.setSuit(cards.getJSONObject(i).getString("suit"));
			aCard.setValue(cards.getJSONObject(i).getString("value"));
			aCard.setRank(convertRank(aCard.getValue()));
			cardList.add(aCard);
		}
		return cardList;
	}

	public List<Card> dealCardsOne(List<Card> cards) throws JSONException {
		List<Card> cardsP1 = new ArrayList<Card>();

		int i = 0;
		for (int j = 0; j < 26; j++) {
			cardsP1.add(cards.get(i));
			i = i + 2;
		}
		return cardsP1;
	}

	public List<Card> dealCardsTwo(List<Card> cards) throws JSONException {
		List<Card> cardsP2 = new ArrayList<Card>();

		int i = 1;
		for (int j = 0; j < 26; j++) {
			cardsP2.add(cards.get(i));
			i = i + 2;
		}
		return cardsP2;
	}

	private int convertRank(String value) {
		int rank = 0;
		if (value.equals("ACE")) {
			rank = 14;
		} else if (value.equals("KING")) {
			rank = 13;
		} else if (value.equals("QUEEN")) {
			rank = 12;
		} else if (value.equals("JACK")) {
			rank = 11;
		} else {
			rank = Integer.parseInt(value);
		}
		return rank;
	}

	public void startGame(Player player1, Player player2, Queue<Card> p1Q, Queue<Card> p2Q, List<Card> warPile)
			throws InterruptedException {
		boolean inWar = false;
		int counter = 0;
		while (p1Q.peek() != null && p2Q.peek() != null) {
			System.out.println("Butch has " + p1Q.size());
			System.out.println("Sundance has " + p2Q.size());

			Card upCardPlayerOne = p1Q.poll();
			Card upCardPlayerTwo = p2Q.poll();
			counter++;

			System.out.println(
					player1.name + " plays the " + upCardPlayerOne.getValue() + " of " + upCardPlayerOne.getSuit());
			System.out.println(
					player2.name + " plays the " + upCardPlayerTwo.getValue() + " of " + upCardPlayerTwo.getSuit());

			if (upCardPlayerOne.getRank() > upCardPlayerTwo.getRank()) {

				if (!inWar) {
					p1Q.add(upCardPlayerOne);
					p1Q.add(upCardPlayerTwo);

				} else {
					p1Q.add(upCardPlayerOne);
					p1Q.add(upCardPlayerTwo);

					p1Q.addAll(warPile);
					warPile.clear();
					inWar = false;
				}
				System.out.println("counter: " + counter);
				System.out.println(" ---  " + player1.name + "  wins the Round ---");
				System.out.print("\n");
				System.out.print("\n");

			} else if (upCardPlayerOne.getRank() < upCardPlayerTwo.getRank()) {

				if (!inWar) {
					p2Q.add(upCardPlayerTwo);
					p2Q.add(upCardPlayerOne);

				} else {
					p2Q.add(upCardPlayerTwo);
					p2Q.add(upCardPlayerOne);
					p2Q.addAll(warPile);
					warPile.clear();
					inWar = false;
				}
				System.out.println("counter: " + counter);
				System.out.println(" ***  " + player2.name + " wins the Round  ***");
				System.out.print("\n");
				System.out.print("\n");

			} else {
				inWar = true;
				warPile.add(p1Q.poll());
				warPile.add(p2Q.poll());
				warPile.add(upCardPlayerOne);
				warPile.add(upCardPlayerTwo);
				System.out.println("counter: " + counter);
				System.out.print("\n");
				System.out.println(" @@@@@@@   WAR!   @@@@@@@");
				System.out.print("\n");

			}
		}

		if (p1Q.peek() == null) {
			System.out.println("The Winner is...." + player2.name + "!!!");
		} else if (p2Q.peek() == null) {
			System.out.println("The Winner is...." + player1.name + "!!!");
		}
	}

}
