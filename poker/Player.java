package poker;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String name = "Computer";
	public String[] hand = new String[5];
	boolean[] value = new boolean[8];
	int handValue = 0;
	private double wallet = 0;
	
	List<Integer> tieBreaker = new ArrayList<>();
	
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public String[] getHand() {
		return hand;
	}
	public void setHand(String[] hand) {
		this.hand = hand;
	}
}
