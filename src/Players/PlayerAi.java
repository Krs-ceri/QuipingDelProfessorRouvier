package Players;


import Model.*;

public abstract class PlayerAi extends Player{
	
	private int profondeur;
	
	public PlayerAi(String name, int p, Tictactoe i) {
	// TODO Auto-generated constructor stub
		super(name,i);
		this.profondeur = p;
	}

	public abstract void execute(Quixo b);
	public abstract int eval(Quixo plateau);

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	

}
