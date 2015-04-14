package blackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJackMain {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		startBlackJack();
	}
	
	private static void startBlackJack() {
		System.out.println("The dealer deals out the hands...");
		Player player = new Player();
		Player dealer = new Player();
		List<String> deck = new ArrayList<>();
		deck = BlackJackDeal.handConstructor(player, dealer, deck);
		hitOrMiss(player, dealer, deck);
	}
	
	private static void hitOrMiss(Player player, Player dealer, List<String> deck) {
		System.out.println("Your hand is: " + hand(player));
		findValues(player);
		findValues(dealer);
		highOrLowCheck(player, player.handValues);
		checkToEnd(player, dealer, deck);
		System.out.println("Would you like to get another card (hit) or stay?");
		String x = input.nextLine();
		x.toLowerCase();
		switch(x) {
		case "hit":
			BlackJackDeal.addCard(player, deck);
			hitOrMiss(player, dealer, deck);
			break;
		case "stay":
			compareHands(player, dealer, deck);
			break;
		default:
			System.out.println("Please enter a valid command (hit) (stay)");
			hitOrMiss(player, dealer, deck);
		}
	}

	private static void checkToEnd(Player player, Player dealer, List<String> deck) {
		// TODO Auto-generated method stub
		if(player.handValue > 21) {
			System.out.println("You busted out. The dealer wins");
			startBlackJack();
		}
		else if (player.handValue == 21) {
			System.out.println("You've got blackJack!");
			compareHands(player, dealer, deck);
		}
	}

	private static void compareHands(Player player, Player dealer, List<String> deck) {
		dealerDraw(dealer, deck);
		System.out.println("Your hand is: " + hand(player));
		System.out.println("The dealer has: " + hand(dealer));
		if(player.handValue > dealer.handValue) {
			System.out.println("You've won");
			startBlackJack();
		}
		else if(player.handValue == dealer.handValue) {
			System.out.println("You've tied");
			startBlackJack();
		}
		else {
			System.out.println("The dealer won");
			startBlackJack();
		}
	}

	private static void dealerDraw(Player dealer, List<String> deck) {
		while (dealer.handValue < 17) {
			BlackJackDeal.addCard(dealer, deck);
			findValues(dealer);
			if(dealer.handValue > 21) {
				System.out.println("The dealer busted out. You win!");
				startBlackJack();
				break;
			}
		}
		
	}

	private static String hand(Player x) {
		String y = "";
		for(int i = 0; i < x.hand.size(); i++) {
			y += x.hand.get(i);
			y += " ";
		}
		return y;
	}
	
	private static void findValues(Player x) {
		String charArrayPrep = "";
		for (int i = 0; i < x.hand.size(); i++) {
			charArrayPrep += x.hand.get(i);
		}
		char[] handArray = charArrayPrep.toCharArray();
		List<Character> handFinder = new ArrayList<>();
		for (int j = 0; j < handArray.length; j++) {
			handFinder.add(handArray[j]);
		}
		cleanHands(handFinder, x);
	}
	
	protected static void cleanHands(List<Character> x, Player y) {
		for(int i = 0; i <= x.size() - 1; i++) {
			if(x.get(i) == 'C' || x.get(i) == 'H' || x.get(i) == 'D' || x.get(i) == 'S') {
				x.remove(i);
				i = 0;
			}
		}
		y.handValues = remainingCharsToInt(x, y);
		y.handValue = Player.findValue(y.handValues);
	}

	protected static List<Integer> remainingCharsToInt(List<Character> x, Player y) {
		List<Integer> toReturn = new ArrayList<>();
		List<String> newList = new ArrayList<>();
		
		for(int i = 0; i <= x.size() - 1; i++){
			newList.add(String.valueOf(x.get(i)));
			switch(newList.get(i)) {
				case "T":
						newList.remove(i);
						newList.add("10");
						break;
					case "J":
						newList.remove(i);
						newList.add("10");
						break;
					case "Q":
						newList.remove(i);
						newList.add("10");
						break;
					case "K":
						newList.remove(i);
						newList.add("10");
						break;
					case "A":
						newList.remove(i);
						newList.add(highOrLowCheck(newList, y));
						break;
				}
			toReturn.add(Integer.parseInt(newList.get(i)));
		}
		return toReturn;
	}

	private static String highOrLowCheck(List<String> newList, Player y) {
		if(y.handValue + 11 > 21) {
			return "1";
		}
		else if(y.handValue + 11 <= 21) {
			return "11";
		}
		return null;
	}
	
	private static void highOrLowCheck(Player player, List<Integer> handValues) {
		if (player.handValue > 21) {
			for(int i = 0; i < handValues.size(); i++) {
				if(handValues.get(i) == 11) {
					handValues.remove(i);
					handValues.add(1);
				}
			}
		}
	}
}