package blackJack;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name = "Dealer";
	protected List<String> hand = new ArrayList<>();
	List<Integer> handValues = new ArrayList<>();
	int handValue = 0;
	
	protected static int findValue(List<Integer> x) {
		int y = 0;
		for(int i = 0; i < x.size(); i++) {
			y += x.get(i);
		}
		return y;
	}
}
