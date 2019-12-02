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
	    int score = game.eval(); 
	    Engine engine = new Engine();
        
	    if (score == 100) return score; 
	    if (score == -10)  return score; 
	    if(ia ) {
	    	int best = -1000; 
	    	 if(p == this.getProfondeur()) return score;
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
	 								game.setCurrent(Tictactoe.CROSS);
	 			                    best = max( best, minimax(game, p+1, !ia) );
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
	        for (int i = 0; i<3; i++) 
	        { 
	            for (int j = 0; j<3; j++) 
	            { 
	            	 if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
	            		|| game.getBoard()[i][j].equals(Tictactoe.CROSS)) { 
		                	for (int i2 = 0; i2 < 5; i2++) {
								for (int j2 = 0; j2 < 5; j2++) {
									if(engine.rule(this.getSigne(), i, j, i2, j2, game)) {
										
		            					game.ConcretePlay(i, i, i2, j2);
		            					game.setCurrent(Tictactoe.CIRCLE);
		            					
										best = min(best,  minimax(game, p+1, ia));
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

	        for (int i = 0; i<3; i++) 
	        { 
	            for (int j = 0; j<3; j++) 
	            { 
	            	if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
	            		|| game.getBoard()[i][j].equals(Tictactoe.CIRCLE)) { 
	            		for (int i2 = 0; i2 < 5; i2++) {
	            			for (int j2 = 0; j2 < 5; j2++) {
	            				if(engine.rule(this.getSigne(), i, j, i2, j2, game)) {
	            					game.ConcretePlay(i, i, i2, j2);
	            					game.setCurrent(Tictactoe.CROSS);
	            					int move = minimax(game, 0, false); 
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
