package Players;

import Model.*;

public abstract class Player{
	
	private String name;
	private int profondeur;
	private Tictactoe signe;
	
	public Player(String name, int p, Tictactoe i) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setProfondeur(p);
		this.setSigne(i);
	}

	public abstract void execute(Quixo b);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	public Tictactoe getSigne() {
		return signe;
	}

	public void setSigne(Tictactoe signe) {
		this.signe = signe;
	}
	
}
