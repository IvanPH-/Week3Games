package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DealingLogic extends PokerGame {

	protected static void handConstructor(List<Player> x) {
		List<PokerCards> z = new ArrayList<>();
		PokerCards one = new PokerCards("2H", 2);
		PokerCards two = new PokerCards("2S", 2);
		PokerCards three = new PokerCards("2D", 2);
		PokerCards four = new PokerCards("2C", 2);
		PokerCards five = new PokerCards("3H", 3);
		PokerCards six = new PokerCards("3S", 3);
		PokerCards seven = new PokerCards("3D", 3);
		PokerCards eight = new PokerCards("3C", 3);
		PokerCards nine = new PokerCards("4H", 4);
		PokerCards ten = new PokerCards("4S", 4);
		PokerCards eleven = new PokerCards("4D", 4);
		PokerCards twelve = new PokerCards("4C", 4);
		PokerCards thirteen = new PokerCards("5H", 5);
		PokerCards fourteen = new PokerCards("5S", 5);
		PokerCards fifteen = new PokerCards("5D", 5);
		PokerCards sixteen = new PokerCards("5C", 5);
		PokerCards seventeen = new PokerCards("6H", 6);
		PokerCards eighteen = new PokerCards("6S", 6);
		PokerCards nineteen = new PokerCards("6D", 6);
		PokerCards twenty = new PokerCards("6C", 6);
		PokerCards twentyone = new PokerCards("7H", 7);
		PokerCards twentytwo = new PokerCards("7S", 7);
		PokerCards twentythree = new PokerCards("7D", 7);
		PokerCards twentyfour = new PokerCards("7C", 7);
		PokerCards twentyfive = new PokerCards("8H", 8);
		PokerCards twentysix = new PokerCards("8S", 8);
		PokerCards twentyseven = new PokerCards("8D", 8);
		PokerCards twentyeight = new PokerCards("8C", 8);
		PokerCards twentynine = new PokerCards("9H", 9);
		PokerCards thirty = new PokerCards("9S", 9);
		PokerCards thirtyone = new PokerCards("9D", 9);
		PokerCards thirtytwo = new PokerCards("9C", 9);
		PokerCards thirtythree = new PokerCards("TH", 10);
		PokerCards thirtyfour = new PokerCards("TS", 10);
		PokerCards thirtyfive = new PokerCards("TD", 10);
		PokerCards thirtysix = new PokerCards("TC", 10);
		PokerCards thirtyseven = new PokerCards("JH", 11);
		PokerCards thirtyeight = new PokerCards("JS", 11);
		PokerCards thirtynine = new PokerCards("JD", 11);
		PokerCards forty = new PokerCards("JC", 11);
		PokerCards fortyone = new PokerCards("QH", 12);
		PokerCards fortytwo = new PokerCards("QS", 12);
		PokerCards fortythree = new PokerCards("QD", 12);
		PokerCards fortyfour = new PokerCards("QC", 12);
		PokerCards fortyfive = new PokerCards("KH", 13);
		PokerCards fortysix = new PokerCards("KS", 13);
		PokerCards fortyseven = new PokerCards("KD", 13);
		PokerCards fortyeight = new PokerCards("KC", 13);
		PokerCards fortynine = new PokerCards("AH", 14);
		PokerCards fifty = new PokerCards("AS", 14);
		PokerCards fiftyone = new PokerCards("AD", 14);
		PokerCards fiftytwo = new PokerCards("AC", 14);
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
		while(k > 0) {
			Collections.shuffle(z);
			k--;
		}
		
		
		for(int i = 0; i < x.size(); i++) {
			for(int j = 0; j <= 4; j++) {
				x.get(i).hand[j] = z.get(0);
				z.remove(0);
			}
		}
	}
}