package poker;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String name = null;
	protected PokerCards[] hand = new PokerCards[5];
	boolean[] value = new boolean[8];
	int handValue = 0;
	protected double wallet = 0;
	
	List<Integer> tieBreaker = new ArrayList<>();
	
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
}
