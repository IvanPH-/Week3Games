package blackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import poker.Player;

public class BlackJackDeal {
	protected static List<String> handConstructor(blackJack.Player player, blackJack.Player dealer, List<String> y) {
		List<String> z = new ArrayList<>();
		z.add("2H");
		z.add("2S");
		z.add("2C");
		z.add("2D");
		z.add("3H");
		z.add("3S");
		z.add("3C");
		z.add("3D");
		z.add("4H");
		z.add("4S");
		z.add("4C");
		z.add("4D");
		z.add("5H");
		z.add("5S");
		z.add("5C");
		z.add("5D");
		z.add("6H");
		z.add("6S");
		z.add("6C");
		z.add("6D");
		z.add("7H");
		z.add("7S");
		z.add("7C");
		z.add("7D");
		z.add("8H");
		z.add("8S");
		z.add("8C");
		z.add("8D");
		z.add("9H");
		z.add("9S");
		z.add("9C");
		z.add("9D");
		z.add("TH");
		z.add("TS");
		z.add("TC");
		z.add("TD");
		z.add("JH");
		z.add("JS");
		z.add("JC");
		z.add("JD");
		z.add("QH");
		z.add("QS");
		z.add("QC");
		z.add("QD");
		z.add("KH");
		z.add("KS");
		z.add("KC");
		z.add("KD");
		z.add("AH");
		z.add("AS");
		z.add("AC");
		z.add("AD");
		Collections.shuffle(z);
	
		for(int i = 0; i < 2; i++) {
			player.hand.add(z.get(0));
			z.remove(0);
			dealer.hand.add(z.get(0));
			z.remove(0);
		}
		return z;
	}
	
	protected static void addCard(blackJack.Player player, List<String> deck) {
		player.hand.add(deck.get(0));
		deck.remove(0);
	}
}
