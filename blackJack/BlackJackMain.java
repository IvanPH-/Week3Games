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
		List<BlackJackCards> deck = new ArrayList<>();
		deck = BlackJackDeal.handConstructor(player, dealer, deck);
		hitOrMiss(player, dealer, deck);
	}

	private static void hitOrMiss(Player player, Player dealer, List<BlackJackCards> deck) {
		System.out.println("Your hand is: " + printHand(player));
		player.handValue = findValues(player);
		dealer.handValue = findValues(dealer);
		highOrLowCheck(player);
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

	private static int findValues(Player player) {
		int toReturn = 0;
		for(int i = 0; i < player.hand.size(); i++) {
			toReturn += player.hand.get(i).cardValue;
		}
		return toReturn;
	}

	private static void checkToEnd(Player player, Player dealer, List<BlackJackCards> deck) {
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

	private static void compareHands(Player player, Player dealer, List<BlackJackCards> deck) {
		dealerDraw(dealer, deck);
		System.out.println("Your hand is: " + printHand(player));
		System.out.println(player.handValue);
		System.out.println("The dealer has: " + printHand(dealer));
		System.out.println(dealer.handValue);
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

	private static String printHand(Player player) {
		String toReturn = "";
		for(int i = 0; i < player.hand.size(); i++) {
			toReturn += player.hand.get(i).cardName;
			toReturn += " ";
		}
		return toReturn;
	}

	private static void dealerDraw(Player dealer, List<BlackJackCards> deck) {
		while (dealer.handValue < 17) {
			BlackJackDeal.addCard(dealer, deck);
			dealer.handValue = findValues(dealer);
			highOrLowCheck(dealer);
			if(dealer.handValue > 21) {
				System.out.println("The dealer busted out. You win!");
				startBlackJack();
				break;
			}
		}
		
	}
	
	private static void highOrLowCheck(Player player) {
		if (player.handValue > 21) {
			for(int i = 0; i < player.hand.size(); i++) {
				if(player.hand.get(i).cardValue == 11) {
					player.hand.get(i).cardValue = 1;
					player.handValue = findValues(player);
					break;
				}
			}
		}
	}
}