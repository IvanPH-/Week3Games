package blackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackDeal {
	protected static List<BlackJackCards> handConstructor(blackJack.Player player, blackJack.Player dealer, List<BlackJackCards> deck) {
		List<BlackJackCards> z = new ArrayList<>();
		BlackJackCards one = new BlackJackCards("2H", 2);
		BlackJackCards two = new BlackJackCards("2S", 2);
		BlackJackCards three = new BlackJackCards("2D", 2);
		BlackJackCards four = new BlackJackCards("2C", 2);
		BlackJackCards five = new BlackJackCards("3H", 3);
		BlackJackCards six = new BlackJackCards("3S", 3);
		BlackJackCards seven = new BlackJackCards("3D", 3);
		BlackJackCards eight = new BlackJackCards("3C", 3);
		BlackJackCards nine = new BlackJackCards("4H", 4);
		BlackJackCards ten = new BlackJackCards("4S", 4);
		BlackJackCards eleven = new BlackJackCards("4D", 4);
		BlackJackCards twelve = new BlackJackCards("4C", 4);
		BlackJackCards thirteen = new BlackJackCards("5H", 5);
		BlackJackCards fourteen = new BlackJackCards("5S", 5);
		BlackJackCards fifteen = new BlackJackCards("5D", 5);
		BlackJackCards sixteen = new BlackJackCards("5C", 5);
		BlackJackCards seventeen = new BlackJackCards("6H", 6);
		BlackJackCards eighteen = new BlackJackCards("6S", 6);
		BlackJackCards nineteen = new BlackJackCards("6D", 6);
		BlackJackCards twenty = new BlackJackCards("6C", 6);
		BlackJackCards twentyone = new BlackJackCards("7H", 7);
		BlackJackCards twentytwo = new BlackJackCards("7S", 7);
		BlackJackCards twentythree = new BlackJackCards("7D", 7);
		BlackJackCards twentyfour = new BlackJackCards("7C", 7);
		BlackJackCards twentyfive = new BlackJackCards("8H", 8);
		BlackJackCards twentysix = new BlackJackCards("8S", 8);
		BlackJackCards twentyseven = new BlackJackCards("8D", 8);
		BlackJackCards twentyeight = new BlackJackCards("8C", 8);
		BlackJackCards twentynine = new BlackJackCards("9H", 9);
		BlackJackCards thirty = new BlackJackCards("9S", 9);
		BlackJackCards thirtyone = new BlackJackCards("9D", 9);
		BlackJackCards thirtytwo = new BlackJackCards("9C", 9);
		BlackJackCards thirtythree = new BlackJackCards("TH", 10);
		BlackJackCards thirtyfour = new BlackJackCards("TS", 10);
		BlackJackCards thirtyfive = new BlackJackCards("TD", 10);
		BlackJackCards thirtysix = new BlackJackCards("TC", 10);
		BlackJackCards thirtyseven = new BlackJackCards("JH", 10);
		BlackJackCards thirtyeight = new BlackJackCards("JS", 10);
		BlackJackCards thirtynine = new BlackJackCards("JD", 10);
		BlackJackCards forty = new BlackJackCards("JC", 10);
		BlackJackCards fortyone = new BlackJackCards("QH", 10);
		BlackJackCards fortytwo = new BlackJackCards("QS", 10);
		BlackJackCards fortythree = new BlackJackCards("QD", 10);
		BlackJackCards fortyfour = new BlackJackCards("QC", 10);
		BlackJackCards fortyfive = new BlackJackCards("KH", 10);
		BlackJackCards fortysix = new BlackJackCards("KS", 10);
		BlackJackCards fortyseven = new BlackJackCards("KD", 10);
		BlackJackCards fortyeight = new BlackJackCards("KC", 10);
		BlackJackCards fortynine = new BlackJackCards("AH", 11);
		BlackJackCards fifty = new BlackJackCards("AS", 11);
		BlackJackCards fiftyone = new BlackJackCards("AD", 11);
		BlackJackCards fiftytwo = new BlackJackCards("AC", 11);
		z.add(one);
		z.add(two);
		z.add(three);
		z.add(four);
		z.add(five);
		z.add(six);
		z.add(seven);
		z.add(eight);
		z.add(nine);
		z.add(ten);
		z.add(eleven);
		z.add(twelve);
		z.add(thirteen);
		z.add(fourteen);
		z.add(fifteen);
		z.add(sixteen);
		z.add(seventeen);
		z.add(eighteen);
		z.add(nineteen);
		z.add(twenty);
		z.add(twentyone);
		z.add(twentytwo);
		z.add(twentythree);
		z.add(twentyfour);
		z.add(twentyfive);
		z.add(twentysix);
		z.add(twentyseven);
		z.add(twentysix);
		z.add(twentyseven);
		z.add(twentyeight);
		z.add(twentynine);
		z.add(thirty);
		z.add(thirtyone);
		z.add(thirtytwo);
		z.add(thirtythree);
		z.add(thirtyfour);
		z.add(thirtyfive);
		z.add(thirtysix);
		z.add(thirtyseven);
		z.add(thirtyeight);
		z.add(thirtynine);
		z.add(forty);
		z.add(fortyone);
		z.add(fortytwo);
		z.add(fortythree);
		z.add(fortyfour);
		z.add(fortyfive);
		z.add(fortysix);
		z.add(fortyseven);
		z.add(fortyeight);
		z.add(fortynine);
		z.add(fifty);
		z.add(fiftyone);
		z.add(fiftytwo);
		int k = 5;
		while (k > 0) {
			Collections.shuffle(z);
			k--;
		}
		for(int i = 0; i < 2; i++) {
			player.hand.add(z.get(0));
			z.remove(0);
			dealer.hand.add(z.get(0));
			z.remove(0);
		}
		return z;
	}
	
	protected static void addCard(Player player, List<BlackJackCards> deck) {
		player.hand.add(deck.get(0));
		deck.remove(0);
	}
}
