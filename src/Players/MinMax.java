package Players;

import Model.*;

public class MinMax extends Player{
	
	private int xi = -1;
	private int yi = -1;
	private int xx = -1;
	private int yy = -1;

	public MinMax(String name, int profondeur, Tictactoe i) {
	// TODO Auto-generated constructor stub
		super(name, profondeur, i);
	}
	@Override
	public void execute(Quixo b) {
		// TODO Auto-generated method stub
		this.Play(b);
		b.ConcretePlay(this.xi, this.yi, this.xx, this.yy);
	}
	
	public int minimax(Quixo game, int p ,boolean ia) { 
	    int score = this.eval(game); 
	    Engine engine = new Engine();
        
	    if (score == 100) return score; 
	    if (score == -100)  return score; 
	    if(p == this.getProfondeur()) return 0;
	    if(ia ) {
	    	int best = -1000; 
	    	
	         for (int i = 0; i<5; i++) 
	         { 
	             for (int j = 0; j<5; j++) 
	             { 
	                 if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
	                 	|| game.getBoard()[i][j].equals(Tictactoe.CIRCLE)) 
	                 { 
	                 	for (int i2 = 0; i2 < 5; i2++) {
	 						for (int j2 = 0; j2 < 5; j2++) {
	 							if(engine.rule(getSigne(), i, j, i2, j2, game)) {	
	 								game.ConcretePlay(i, i, i2, j2);
	 								game.switchPlayer();
	 			                    best = Math.max( best, minimax(game, p+1, !ia) );
	 			                   game.undoMove();
	 							}
	 						}
	 					}
	                 } 
	                 
	             } 
	         } 
	         return best; 
	    }
	    else {
	    	int best = 1000; 
	        for (int i = 0; i<5; i++) 
	        { 
	            for (int j = 0; j<5; j++) 
	            { 
	            	 if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
	            		|| game.getBoard()[i][j].equals(Tictactoe.CROSS)) { 
		                	for (int i2 = 0; i2 < 5; i2++) {
								for (int j2 = 0; j2 < 5; j2++) {
									if(engine.rule(this.getSigne(), i, j, i2, j2, game)) {
										
		            					game.ConcretePlay(i, i, i2, j2);
		            					game.switchPlayer();
		            					
										best = Math.min(best,  minimax(game, p+1, ia));
										game.undoMove();
									}
							}
		                }
	            	 }
	            }
	        }
	        return best; 
	    }
	}

	

	public void Play(Quixo game) { 
	        int best = -1000; 
	        Engine engine = new Engine();

	        for (int i = 0; i<5; i++) 
	        { 
	            for (int j = 0; j<5; j++) 
	            { 
	            	if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
	            		|| game.getBoard()[i][j].equals(Tictactoe.CIRCLE)) { 
	            		for (int i2 = 0; i2 < 5; i2++) {
	            			for (int j2 = 0; j2 < 5; j2++) {
	            				if(engine.rule(this.getSigne(), i, j, i2, j2, game)) {
	            					game.ConcretePlay(i, i, i2, j2);
	            					game.switchPlayer();
	            					int move = minimax(game, 0, false); 
	            					System.out.println("this is the value:"+move);
	            					game.undoMove();
	            					if (move > best)  { 
	        	                        this.xi = i; 
	        	                        this.yi = j; 
	        	                        this.xx = i2;
	        	                        this.yy = j2;
	        	                        best = move; 
	        	                    }
	            				}
	            			}
	            		}
	                } 
	            } 
	        } 
	      
	        System.out.println("deplacement : " +" best:  "+ best +"  "+this.xi+" "+this.yi+" : " +this.xx+" "+this.yy); 

	    } 
	/**
	 * "O" est l'IA
	 * @return	valeur 
	 */
	
	public int eval(Quixo plateau) {
		Tictactoe winner = null;		//Nobody win
		for (int i = 0; i < plateau.getBoard().length; i++) { // ligne verification
			if(plateau.getBoard()[i][0] == Tictactoe.CIRCLE || plateau.getBoard()[i][0] == Tictactoe.CROSS) {
				if((plateau.getBoard()[i][1] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][2] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][3] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][4] == plateau.getBoard()[i][0] )) 
				{ 
					winner = plateau.getBoard()[i][0]; 
					if(winner == Tictactoe.CIRCLE)	return +100;
					else return -100;
				}
				if((plateau.getBoard()[i][1] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][2] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][3] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][4] != plateau.getBoard()[i][0] )) 
				{ 
					winner = plateau.getBoard()[i][0]; 
					if(winner == Tictactoe.CIRCLE)	return +70;
					else return -70;
				}
				if((plateau.getBoard()[i][1] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][2] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][3] != plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][4] != plateau.getBoard()[i][0] )) 
				{ 
					winner = plateau.getBoard()[i][0]; 
					if(winner == Tictactoe.CIRCLE)	return +50;
					else return -50;
				}
				if((plateau.getBoard()[i][1] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][2] != plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][3] != plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][4] != plateau.getBoard()[i][0] )) 
				{ 
					winner = plateau.getBoard()[i][0]; 
					if(winner == Tictactoe.CIRCLE)	return +25;
					else return -25;
				}
			}
		}
		for (int i = 0; i < plateau.getBoard().length; i++) { // colonne verification
			if(plateau.getBoard()[0][i] == Tictactoe.CIRCLE || plateau.getBoard()[0][i] == Tictactoe.CROSS) {
				if((plateau.getBoard()[1][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[2][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[3][i] == plateau.getBoard()[0][i]
						&& plateau.getBoard()[4][i] == plateau.getBoard()[0][i] )) 
				{ 
					winner = plateau.getBoard()[0][i]; 
					if(winner == Tictactoe.CIRCLE)	return +100;
					else return -100;
				}
				if((plateau.getBoard()[1][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[2][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[3][i] == plateau.getBoard()[0][i]
						&& plateau.getBoard()[4][i] != plateau.getBoard()[0][i] )) 
				{ 
					winner = plateau.getBoard()[0][i];
					if(winner == Tictactoe.CIRCLE)	return +70;
					else return -70;
				}
				if((plateau.getBoard()[1][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[2][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[3][i] != plateau.getBoard()[0][i]
						&& plateau.getBoard()[4][i] != plateau.getBoard()[0][i] )) 
				{ 
					winner = plateau.getBoard()[0][i];
					if(winner == Tictactoe.CIRCLE)	return +50;
					else return -50;
				}
				if((plateau.getBoard()[1][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[2][i] != plateau.getBoard()[0][i] 
						&& plateau.getBoard()[3][i] != plateau.getBoard()[0][i]
						&& plateau.getBoard()[4][i] != plateau.getBoard()[0][i] )) 
				{ 
					winner = plateau.getBoard()[0][i];
					if(winner == Tictactoe.CIRCLE)	return +25;
					else return -25;
				}
			}
		}
		if(plateau.getBoard()[2][2] == Tictactoe.CIRCLE || plateau.getBoard()[1][1] == Tictactoe.CROSS) {
			if((plateau.getBoard()[0][0] == plateau.getBoard()[2][2] 
					&& plateau.getBoard()[1][1] == plateau.getBoard()[2][2] 
							&& plateau.getBoard()[3][3] == plateau.getBoard()[2][2] 
									&& plateau.getBoard()[4][4] == plateau.getBoard()[2][2])  //barre centrale verticale
					|| (plateau.getBoard()[0][4] == plateau.getBoard()[2][2] 
							&& plateau.getBoard()[1][3] == plateau.getBoard()[2][2] 
									&& plateau.getBoard()[3][1] == plateau.getBoard()[2][2] 
											&& plateau.getBoard()[4][0] == plateau.getBoard()[2][2])) 	//barre centrale horizontal
			{ 
				winner = plateau.getBoard()[0][0]; 
				if(winner == Tictactoe.CIRCLE)	return +100;
				else return -100;
			}
		}
		return 3;
	}
	private int max(int best, int minmax) {
		if(best > minmax)	return best;
		else return minmax;
		// TODO Auto-generated method stub
		
	}
	
	private int min(int best, int minmax) {
		// TODO Auto-generated method stub
		   if(best < minmax )	return best;
		   else return minmax;
	}

}
