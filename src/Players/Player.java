package Players;

import Model.*;

public abstract class Player{
	
	private String name;
	private Tictactoe signe;

	
	public Player(String name, Tictactoe i) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.signe = i;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tictactoe getSigne() {
		return signe;
	}

	public void setSigne(Tictactoe signe) {
		this.signe = signe;
	}

}
