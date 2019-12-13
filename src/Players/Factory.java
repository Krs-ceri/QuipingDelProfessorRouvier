package Players;

import Model.Tictactoe;

public class Factory {

	private PlayerAi c ;
	
	public PlayerAi getPlayer(String name) {
			this.c = new MinMaxOnefunction("Min-Max", 3, Tictactoe.CIRCLE);
			System.out.print("default:->");

		if(name.equals("Min-Max")) {

			this.c = new MinMaxOnefunction("Min-Max", 3, Tictactoe.CIRCLE);
			System.out.println("minimax");
		}
		else if(name.equals("Threaded")){
			this.c = new ThreadMM("Threaded", 4, Tictactoe.CIRCLE);
			System.out.println("threaded");
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
