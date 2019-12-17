package Players;

import Model.Tictactoe;

public class Factory {

	private PlayerAi c ;
	
	public PlayerAi getPlayer(String name) {
			this.c = new MinMax("Min-Max", 3, Tictactoe.CIRCLE);
			System.out.print("default:->");

		if(name.equals("Min-Max: medium")) {

			this.c = new MinMax("Min-Max", 3, Tictactoe.CIRCLE);
			System.out.println("minimax");
		}
		else if(name.equals("Threaded: hard")){
			this.c = new ThreadMM("Threaded", 3, Tictactoe.CIRCLE);
			System.out.println("threaded");
		}
		else if(name.equals("Min-max: easy")) {
			this.c = new MinMaxOnefunction("low minmax", 2, Tictactoe.CIRCLE);
			System.out.println("one function");
		}
		return this.c ;
	}

	public void setCommerce(PlayerAi c) {
		this.c = c ;
	}
	

	public Player getCommerceControlleur() {
		return this.c ;
	}
}
