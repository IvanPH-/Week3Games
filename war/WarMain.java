package war;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarMain {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		startWar();
	}

	private static void startWar() {
		WarPlayer computer = new WarPlayer();
		WarPlayer player = new WarPlayer();
		System.out.println("What's your name?");
		String x = input.nextLine();
		player.name = x;
		WarDeal.handConstructor(player, computer);
		startGame(player, computer);
	}
	
	private static void startGame(WarPlayer player, WarPlayer computer) {
		winCheck(player, computer);
		System.out.println("You have: " +  (player.deck.size() - 1) + " cards left. What would you like to do? Type (draw) to play, and type (exit) to exit the game");
		String x = input.nextLine();
		x = x.toLowerCase();
		switch(x) {
		case "draw":
			playWar(player, computer);
			break;
		case "exit":
			System.exit(0);
			break;
		default:
			System.out.println("Please enter either (draw) or (exit)");
			startGame(player, computer);
			break;
		}
	}

	private static void playWar(WarPlayer player, WarPlayer computer) {
		System.out.println(player.name + " has " + player.deck.get(0).cardName);
		System.out.println(computer.name + " has " + computer.deck.get(0).cardName);
		compareHands(player, computer);
	}

	private static void compareHands(WarPlayer player, WarPlayer computer) {
		if(player.deck.get(0).cardValue > computer.deck.get(0).cardValue) {
			giveCards(player, computer);
			System.out.println("You won the round!");
			startGame(player, computer);
		}
		else if(player.deck.get(0).cardValue == computer.deck.get(0).cardValue) {
			System.out.println("You both tied. Preparing for war..");
			war(player, computer);
		}
		else {
			giveCards(computer, player);
			System.out.println("The computer won the round!");
			startGame(player, computer);
		}
	}
	
	private static void giveCards(WarPlayer x, WarPlayer y) {
		x.deck.add(y.deck.get(0));
		x.deck.add(x.deck.get(0));
		x.deck.remove(0);
		y.deck.remove(0);
	}
	
	private static void war(WarPlayer player, WarPlayer computer) {
		List<WarCards> placeHold = new ArrayList<>();
		int i = 0;
		while(i <= 3) {
			winCheck(player, computer);
			placeHold.add(player.deck.get(0));
			placeHold.add(computer.deck.get(0));
			player.deck.remove(0);
			computer.deck.remove(0);
			i++;
		}
		compareHands(player, computer, placeHold);
	}

	private static void compareHands(WarPlayer player, WarPlayer computer, List<WarCards> placeHold) {
		System.out.println(player.name + " has " + player.deck.get(0).cardName);
		System.out.println(computer.name + " has " + computer.deck.get(0).cardName);
		
		if(player.deck.get(0).cardValue > computer.deck.get(0).cardValue) {
			giveCards(player, computer, placeHold);
			System.out.println("You won the round!");
			startGame(player, computer);
		}
		else if(player.deck.get(0).cardValue == computer.deck.get(0).cardValue) {
			System.out.println("You both tied. Preparing for war..");
			war(player, computer, placeHold);
		}
		else {
			giveCards(computer, player, placeHold);
			System.out.println("The computer won the round!");
			startGame(player, computer);
		}
		
	}

	private static void war(WarPlayer player, WarPlayer computer, List<WarCards> placeHold) {
		int i = 0;
		while(i <= 3) {
			winCheck(player, computer);
			placeHold.add(player.deck.get(0));
			placeHold.add(computer.deck.get(0));
			player.deck.remove(0);
			computer.deck.remove(0);
			i++;
		}
		compareHands(player, computer, placeHold);
		
	}

	private static void giveCards(WarPlayer x, WarPlayer y, List<WarCards> placeHold) {
		giveCards(x, y);
		giveHolds(x, placeHold);
	}

	private static void giveHolds(WarPlayer x, List<WarCards> placeHold) {
		List<WarCards> newPlaceHold = new ArrayList<>(x.deck);
		while (placeHold.size() > 0) {
			newPlaceHold.add(placeHold.get(0));
			placeHold.remove(0);
		}
		x.deck = newPlaceHold;
	}

	private static void winCheck(WarPlayer player, WarPlayer computer) {
		if(player.deck.size() == 0) {
			System.out.println(player.name + " has run out of cards. The computer gloats over its victory.");
			System.out.println("Would you like to play again?" + "\n" + "(yes) (no)");
			playAgainCheck();
		}
		else if (computer.deck.size() == 0) {
			System.out.println(player.name + " has conquered the computer, who has no more cards." + "\n" + "Would you like to play again?");
			System.out.println("(yes) (no)");
			playAgainCheck();
		}
	}

	private static void playAgainCheck() {
		String x = input.nextLine();
		x = x.toLowerCase();
		switch(x) {
			case "yes":
				System.out.println("Booting the game..");
				startWar();
				break;
			case "no":
				System.exit(0);
				break;
			default:
				System.out.println("Please enter either (yes) or (no)");
				playAgainCheck();
				break;
		}
	}
}
