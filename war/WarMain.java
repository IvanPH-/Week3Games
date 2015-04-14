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
		findValues(player);
		findValues(computer);
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
		System.out.println(player.name + " has " + player.deck.get(0));
		System.out.println(computer.name + " has " + computer.deck.get(0));
		compareHands(player, computer);
	}

	private static void compareHands(WarPlayer player, WarPlayer computer) {
		if(player.deckValues.get(0) > computer.deckValues.get(0)) {
			giveCards(player, computer);
			System.out.println("You won the round!");
			startGame(player, computer);
		}
		else if(player.deckValues.get(0) == computer.deckValues.get(0)) {
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
		x.deckValues.add(y.deckValues.get(0));
		x.deck.add(y.deck.get(0));
		x.deckValues.add(x.deckValues.get(0));
		x.deck.add(x.deck.get(0));
		y.deckValues.remove(0);
		x.deckValues.remove(0);
		x.deck.remove(0);
		y.deck.remove(0);
	}
	
	private static void war(WarPlayer player, WarPlayer computer) {
		List<String> stringHold = new ArrayList<>();
		List<Integer> intHold = new ArrayList<>();
		int i = 0;
		while(i <= 3) {
			winCheck(player, computer);
			stringHold.add(player.deck.get(0));
			intHold.add(player.deckValues.remove(0));
			player.deck.remove(0);
			computer.deck.remove(0);
			i++;
		}
		compareHands(player, computer, stringHold, intHold);
	}

	private static void compareHands(WarPlayer player, WarPlayer computer, List<String> stringHold, List<Integer> intHold) {
		System.out.println(player.name + " has " + player.deck.get(0));
		System.out.println(computer.name + " has " + computer.deck.get(0));
		
		if(player.deckValues.get(0) > computer.deckValues.get(0)) {
			giveCards(player, computer, stringHold, intHold);
			System.out.println("You won the round!");
			startGame(player, computer);
		}
		else if(player.deckValues.get(0) == computer.deckValues.get(0)) {
			System.out.println("You both tied. Preparing for war..");
			war(player, computer, stringHold, intHold);
		}
		else {
			giveCards(computer, player, stringHold, intHold);
			System.out.println("The computer won the round!");
			startGame(player, computer);
		}
		
	}

	private static void war(WarPlayer player, WarPlayer computer, List<String> stringHold, List<Integer> intHold) {
		int i = 0;
		while(i <= 3) {
			winCheck(player, computer);
			stringHold.add(player.deck.get(0));
			intHold.add(player.deckValues.remove(0));
			stringHold.add(computer.deck.get(0));
			intHold.add(computer.deckValues.get(0));
			player.deck.remove(0);
			player.deckValues.remove(0);
			computer.deck.remove(0);
			computer.deckValues.remove(0);
			i++;
		}
		compareHands(player, computer, stringHold, intHold);
		
	}

	private static void giveCards(WarPlayer x, WarPlayer y, List<String> stringHold, List<Integer> intHold) {
		giveCards(x, y);
		giveHolds(x, stringHold, intHold);
	}

	private static void giveHolds(WarPlayer x, List<String> stringHold, List<Integer> intHold) {
		List<String> placeHold1 = new ArrayList<>(x.deck);
		List<Integer> placeHold2 = new ArrayList<>(x.deckValues);
		while (stringHold.size() > 0) {
			placeHold1.add(stringHold.get(0));
			placeHold2.add(intHold.get(0));
			stringHold.remove(0);
			intHold.remove(0);
		}
		x.deck = placeHold1;
		x.deckValues = placeHold2;
	}

	private static void findValues(WarPlayer x) {
		String charArrayPrep = "";
		for (int i = 0; i < x.deck.size(); i++) {
			charArrayPrep += x.deck.get(i);
		}
		char[] handArray = charArrayPrep.toCharArray();
		List<Character> handFinder = new ArrayList<>();
		for (int j = 0; j < handArray.length; j++) {
			handFinder.add(handArray[j]);
		}
		cleanHands(handFinder, x);
	}
	
	protected static void cleanHands(List<Character> x, WarPlayer y) {
		for(int i = 0; i <= x.size() - 1; i++) {
			if(x.get(i) == 'C' || x.get(i) == 'H' || x.get(i) == 'D' || x.get(i) == 'S') {
				x.remove(i);
				i = 0;
			}
		}
		y.deckValues = remainingCharsToInt(x, y);
	}
	
	protected static List<Integer> remainingCharsToInt(List<Character> x, WarPlayer y) {
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
						newList.add("11");
						break;
					case "Q":
						newList.remove(i);
						newList.add("12");
						break;
					case "K":
						newList.remove(i);
						newList.add("13");
						break;
					case "A":
						newList.remove(i);
						newList.add("14");
						break;
				}
			toReturn.add(Integer.parseInt(newList.get(i)));
		}
		return toReturn;
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
