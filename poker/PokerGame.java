package poker;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
//To Do: Add AI to the bots; Give each bot a different name
public class PokerGame {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args)  {
		poker();
	}
	
	protected static void poker()  {
		
		System.out.println("Type 'Start Poker' to start the game. Type 'Exit' to terminate");
		String x = input.nextLine();
		x = x.toLowerCase();
		switch(x) {
		case "start poker":
			execution();
			break;
		case "exit":
			System.exit(0);
			break;
		default:
			System.out.println("Please enter either 'Start Poker' or 'Exit'");
			poker();
		}
	}
		
	protected static void execution()  {
		String[] names = {null, "CPU1", "CPU2", "CPU3", "CPU4", "CPU5"};
		System.out.println("How many other players are Playing? (Minimum 1 || Maximum of 5)");
		int x = input.nextInt();
		//Method to construct x amount of players
		List<Player> players = playerConstructor(x);
		//Ask for player Name to set. Set it to the first object in the list using player.name = input.nextln();
		System.out.println("Please input your name to use");
		input.nextLine();
		names[0] = input.nextLine();
		setNames(names, players);;
		//Set the ante size that gets passed into the poker game and keeps it constant. Set int to FINAL so it stays. Deduct this at the start of each round from wallet
		System.out.println("Input the ante that each player pays before each round");
		double z = input.nextDouble();
		//Set wallet size. Default is 3* the amount of the ante, but otherwise they need to add more than that
		double z2 = z * 3;
		System.out.println("Input the starting amount each player will have. (Minimum allowed is " + z2 + ")");
		z2 = input.nextDouble();
		input.nextLine();
		playerSetter(z2, players);
		//Iterate through list size. Set each objects wallet to wallet = inputnextInt();
		//There is no betting. Just folding or playing.
		startGame(players, z, names);
		
	}
	
	private static void setNames(String[] names, List<Player> players) {
		for(int i = 0; i < players.size(); i++) {
			players.get(i).name = names[i];
		}
	}

	private static void startGame(List<Player> x, double z, String[] y) {
		while(x.size() > 1) {
			double pot = z * 4;
			anteDeductor(x, z);
			List<Player> placeHold = new ArrayList<>(x);
			deal(x);
			System.out.println("Player " + x.get(0).name + " hand is: " + printPlayerHand(x.get(0)) + " who has " + x.get(0).wallet + " left in their wallet.");
			findValues(x);
			System.out.println("Would you like to play or fold?");
			String answer = input.nextLine();
			answer = answer.toLowerCase();
			//If fold just continue to check the bots. Otherwise include the player in the checks
			if (answer.equals("fold")) {
				System.out.println("You folded");
				x.remove(0);
				compareHands(x);
			}
			else if (answer.equals("play")) {
				//run logic with player
				compareHands(x);
			}
			else {
				//errors
				//Run the if statement again. Make this a method
			}
			x.get(0).wallet += pot;
			//Reflect that change to the player list;
			x = resetList(placeHold, x, y);
			//Run a check for all players to eliminate those who don't have enough to pay the ante for next round
			x = eliminateCheck(x, z);
			//If player was eliminated exit the program and say a computer won. Add a method here.
		}
		//Print the the players name and that they won
		System.out.println("Player " + x.get(0).name + " has won the game");
	}

	private static List<Player> eliminateCheck(List<Player> x, double z) {
		for(int i = 0; i < x.size();) {
			if(z > x.get(i).wallet) {
				System.out.println(x.get(i).name + " was eliminated");
				x.remove(i);
				i = 0;
				continue;
			}
			i++;
		}
		return x;
	}

	private static List<Player> resetList(List<Player> placeHold, List<Player> x, String[] y) {
		for (int i = 0; i < placeHold.size(); i++) {
			if (x.get(0).name.equals(placeHold.get(i).name)) {
				placeHold.remove(i);
				placeHold.add(x.get(0));
				break;
			}
		}
		x = placeHold;
		for (int j = 0; j < x.size(); j++) {
			if (x.get(j).name.equals(y[0])) {
				x.set(0, x.get(j));
				break;
			}
		}
		
		for (int k = 0; k < x.size(); k++) {
			x.get(k).tieBreaker.clear();
		}
		return x;
		//Return the player to the beginning of the list by having a variable which stores their name check the names and then move matching name to the first index
	}

	private static void findValues(List<Player> x) {
		initTieBreakers(x);
		for(int i = 0; i < x.size(); i++) {
			findValue(x.get(i));
		}
	}
	
	private static void initTieBreakers(List<Player> x) {
		for(int i = 0; i < x.size(); i++) {
			for(int j = 0; j < x.get(i).hand.length; j++) {
				x.get(i).tieBreaker.add(x.get(i).hand[j].cardValue);
			}
		}
		
	}

	private static void findValue(Player x) {
		String charArrayPrep = "";
		for (int i = 0; i < x.hand.length; i++) {
			charArrayPrep += x.hand[i].cardName;
		}
		char[] handArray = charArrayPrep.toCharArray();
		List<Character> handFinder = new ArrayList<>();
		for (int j = 0; j < handArray.length; j++) {
			handFinder.add(handArray[j]);
		}
		Collections.sort(x.tieBreaker);
		cleanHands(handFinder, x);
		findComparisonValue(x);
		x.handValue = findHandValue(x.value);
	}

	private static String printPlayerHand(Player x) {
		String toReturn = "";
		for(int i = 0; i < x.hand.length; i++) {
			toReturn += x.hand[i].cardName;
			toReturn += " ";
		}
		return toReturn;
	}

	private static void deal(List<Player> x) {
		DealingLogic.handConstructor(x);
	}

	private static void anteDeductor(List<Player> x, double z) {
		for(int i = 0; i < x.size(); i++) {
			x.get(i).wallet -= z;
		}
		
	}

	private static void playerSetter(double z2, List<Player> x) {
		for(int i = 0; i < x.size(); i++) {
			x.get(i).setWallet(z2);
		}
	}

	private static List<Player> playerConstructor(int y) {
		List<Player> toReturn = new ArrayList<>();
        for(int i = 0; i <= y; i++) {
        	Player player = new Player();
            toReturn.add(player);
        }
        return toReturn;
    }

	protected static String charArrayPrep(List<String> x) {
		String toReturn = "";
		for(int i = 0; i <= x.size() - 1; i++) {
			 toReturn += x.get(i);
			}
		return toReturn;
	}
	
	protected static void cleanHands(List<Character> x, Player y) {
		List<Character> flushFinder = new ArrayList<>();
		for(int i = 0; i <= x.size() - 1; i++) {
			if(x.get(i) == 'C' || x.get(i) == 'H' || x.get(i) == 'D' || x.get(i) == 'S') {
				flushFinder.add(x.get(i));
				x.remove(i);
				i = 0;
			}
		}
		findFlush(flushFinder, y.value);
	}

	protected static void findFlush(List<Character> x, boolean[] y) {
		for(int i = 0; i <= x.size() - 2; i++) {
			if(x.get(i) == x.get(i + 1)) {
				y[4] = true;
			}
			else {
				y[4] = false;
				break;
			}
		}
		
	}

	public static void findComparisonValue(Player x) {
		checkOfAKindSeries(x.tieBreaker, x.value);
		//If statment in a method to pass both a, b to make sure fullhouse(which we checked for), and four of a kind aren't possibilities. If they aren't we run to check for pairs
		checkToCheckPair(x.tieBreaker, x.value);
		//If statement to check for straights if NONE of the values aside from flush which is an exception is false. If false checks for straight
		checkToCheckStraight(x.tieBreaker, x.value);
	}
	
	protected static void checkToCheckStraight(List<Integer> x, boolean[] y) {
		boolean flagToCheck = true;
		for(int i = 0; i <= y.length - 1; i++) {
			if(i == 4) {
				continue;
			}
			else if(y[i] == true) {
				flagToCheck = false;
				break;
			}
		}
		if (flagToCheck == true) {
			checkStraight(x, y);
		}
	}
	
	protected static void checkToCheckPair(List<Integer> x, boolean[] y) {
		if (y[2] != true && y[5] != true && y[6] != true) {
			checkPairSeries(x, y);
		}
	}
	
	protected static void checkStraightFlushSeries(boolean[] y) {
		if(y[3] == true && y[4] == true) {
			y[3] = false;
			y[4] = false;
			y[7] = true;
		}
	}
	
	protected static void checkFullHouse(boolean[] y) {
		if (y[0] == true && y[2] == true) {
			y[0] = false;
			y[2] = false;
			y[5] = true;
		}
	}
	
	protected static void checkStraight(List<Integer> x, boolean[] y) {
		List<Integer> placeHold = new ArrayList<>(x);
		Collections.sort(x); //Return it to how it was originally before being sorted - Done
		for(int i = 0; i <= x.size() - 2; i++) {
			if (x.get(i) + 1 != x.get(i + 1)) {
				y[3] = false;
				break;
			}
			else {
				y[3] = true;
				continue;
			}
		}
		if(y[3] == false) {
			x = placeHold;
		}
		else {
			checkStraightFlushSeries(y);
		}
	}
	
	protected static void checkOfAKindSeries(List<Integer> x, boolean[] y) {
		int startingPoint = 0;
		boolean startSet = false;
		List<Integer> placeHold = new ArrayList<>(x);
		List<Integer> holdRemove = new ArrayList<>();
		//Return it to how it was originally before being sorted if none are true. Otherwise maintain the three or four at the end if true - Done
		//Check for pairs in here if three of a kind passes
		boolean threeFlag = false;
		boolean fourFlag = false;
		for(int i = 0; i <= x.size() - 2; i++) {
			if(x.get(i) == x.get(i + 1)) {
				if(threeFlag == true) {
					threeFlag = false;
					fourFlag = true;
					holdRemove.add(x.get(i));
					holdRemove.add(x.get(i + 1));
					holdRemove.add(x.get(i - 1));
					y[2] = true;
					continue;
				}
				else if(fourFlag == true) {
					holdRemove.add(x.get(i + 1));
					y[2] = false;
					y[6] = true;
					break;
				}
				threeFlag = true;
				if(startSet == false) {
					startingPoint = i;
					startSet = true;
				}
				else if (startSet == true) {
					continue;
				}
			}
			else if(x.get(i) != x.get(i + 1)) {
				threeFlag = false;
				fourFlag = false;
			}
		}
		threeFlag = false;
		
		//Remove three pair if true in y. check two for pair by calling pair check.
		if(y[2] == true) {
			x.remove(startingPoint);
			x.remove(startingPoint);
			x.remove(startingPoint);
			checkPairSeries(x, y);
			x.addAll(holdRemove);
		}
		else if(y[6] == true) {
			y[0] = false;
			y[1] = false;
		}
		
		checkFullHouse(y);
		
		if(fourFlag != true) {
			x = placeHold;
		}
		else if(fourFlag == true) {
			//run code to remove the matches from x, and place them at the end
			x.remove(startingPoint);
			x.remove(startingPoint);
			x.remove(startingPoint);
			x.remove(startingPoint);
			x.addAll(holdRemove);
		}
		
	}
	
	protected static void checkPairSeries(List<Integer> x, boolean[] y) {
		List<Integer> hold = new ArrayList<>();
		for(int i = 0; i <= x.size() - 2;) {
			if(x.get(i) == x.get(i + 1)) {
				if(y[0] == true) {
					y[0] = false;
					y[1] = true;
					hold.add(x.get(i));
					hold.add(x.get(i + 1));
					x.remove(x.get(i));
					x.remove(x.get(i));
					i = 0;
					continue;
				}
				hold.add(x.get(i));
				hold.add(x.get(i + 1));
				x.remove(x.get(i));
				x.remove(x.get(i));
				y[0] = true;
				i = 0;
			}
			else {
				i++;
			}
		}
		x.addAll(hold);
		hold.clear();
	}
		
	protected static void compareHigh(List<Integer> x, List<Integer> y, List<Player> z) {
		int player1High = findHighCard(x);
		int player2High = findHighCard(y);
		if (player1High > player2High) {
			z.remove(1);
		}
		else if (player1High == player2High) {
			compareNextHigh(x, y, z);
		}
		else {
			z.remove(0);
		}
	}

	protected static void compareNextHigh(List<Integer> x, List<Integer> y, List<Player> z) {
		for(int i = x.size() - 2; i >= 0; i--) {
			if (x.get(i) > y.get(i)) {
				z.remove(1);
				break;
			}
			else if (x.get(i) == y.get(i)) {
				continue;
			}
			else {
				z.remove(0);
				break;
			}
		}
		
	}

	protected static int findHighCard(List<Integer> x) {
		int highNum = x.size() - 1;
		return x.get(highNum);
	}

	protected static void compareHands(List<Player> x) {
		for(int j = 0; j < x.size(); j++) {
			System.out.println("Player " + x.get(j).name + " hand is: " + printPlayerHand(x.get(j)));
		}
		List<Player> checker = x;
		for(int i = 0; i + 1 < checker.size();) {
			if(checker.get(i).handValue > checker.get(i + 1).handValue) {
				checker.remove(i + 1);
			}
			else if(checker.get(i).handValue == checker.get(i + 1).handValue) {
				compareHigh(checker.get(i).tieBreaker, checker.get(i + 1).tieBreaker, checker);
			}
			else {
				checker.remove(i);
			}
		}
		System.out.println(checker.get(0).name + " wins");
		x = checker;
	}
	
	protected static int findHandValue(boolean[] y) {
		int x = 0;
		for(int i = 0; i <= y.length - 1; i++) {
			if (y[i] == true) {
				x = i + 1;
				y[i] = false;
				break;
			}
		}
		return x;
	}
}