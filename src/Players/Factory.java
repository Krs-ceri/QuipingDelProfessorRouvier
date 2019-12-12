package Players;

import Model.Tictactoe;

public class Factory {

	private Player c ;
	
	public Player getPlayer(String name) {
		
		if(name.equals("Min-Max")) {

			this.c = new MinMaxOnefunction("Min-Max", 3, Tictactoe.CIRCLE);
			System.out.println("minimax");
		}
		else if(name.equals("Threaded")){
			this.c = new ThreadMM("Threaded", 3, Tictactoe.CIRCLE);
			System.out.println("threaded");
		}
		
		return this.c ;
	}

	public void setCommerce(Player c) {
		this.c = c ;
	}
	

	public Player getCommerceControlleur() {
		return this.c ;
	}
}
