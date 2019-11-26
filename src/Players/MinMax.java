package Players;

import Model.*;

public class MinMax extends Player{
	
	private int xi = -1;
	private int yi = -1;
	private int xx = -1;
	private int yy = -1;
	private int best = -100000;

	public MinMax(String name, int profondeur, Tictactoe i) {
	// TODO Auto-generated constructor stub
		super(name, profondeur, i);
	}
	@Override
	public void execute(Quixo b) {
		// TODO Auto-generated method stub
		try {
			Engine engine = new Engine();
			Quixo game = (Quixo) b.clone();
			findBestMove(game);
			if(engine.rule(this.getSigne(), xi, yi, xx, yy, game)) {
				game.ConcretePlay(xi, yi, xx, yy);
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int minimax(Quixo game, int profondeur ,boolean isMax) { 
	    int score = game.eval(); 
	  
	    Engine engine = new Engine();

	    if (score == 100) return score; 
	    if (score == -105)  return score; 
	    
	    if (isMax) 
	    { 
	        int best = -1000; 
	  
	        // Traverse all cells 
	        for (int i = 0; i<5; i++) 
	        { 
	            for (int j = 0; j<5; j++) 
	            { 
	                // Check if cell is empty 
	                if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) || game.getBoard()[i][j].equals(Tictactoe.CIRCLE)) 
	                { 
	                	for (int i2 = 0; i2 < 5; i2++) {
							for (int j2 = 0; j2 < 5; j2++) {
								game.ConcretePlay(i, i, i2, j2);
							}
						}
	                	game.setCurrent(Tictactoe.CROSS);
	                    best = max( best, minimax(game, profondeur+1, !isMax) );
	                } 
	            } 
	        } 
	        return best; 
	    } 
	  
	    // If this minimizer's move 
	    else
	    { 
	        int best = 1000; 
	  
	        // Traverse all cells 
	        for (int i = 0; i<3; i++) 
	        { 
	            for (int j = 0; j<3; j++) 
	            { 
	            	 if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) || game.getBoard()[i][j].equals(Tictactoe.CIRCLE)) { 
		                	for (int i2 = 0; i2 < 5; i2++) {
								for (int j2 = 0; j2 < 5; j2++) {
									if(engine.rule(this.getSigne(), i, j, i2, j2, game)) {
		            					game.ConcretePlay(i, i, i2, j2);
		            					game.setCurrent(Tictactoe.CIRCLE);
		            					best = min(best,  minimax(game, profondeur+1, !isMax)); 
								}
							}
		                } 
	            	 }
	            }
	        }
	        return best; 
	    }
	}
	private int min(int best, int minmax) {
		// TODO Auto-generated method stub
		   if(best < minmax )	return best;
		   else return minmax;
	}
	public void findBestMove(Quixo game) { 
	        int bestVal = -1000; 
	        Engine engine = new Engine();

	      
	        // Traverse all cells, evaluate minimax function for 
	        // all empty cells. And return the cell with optimal 
	        // value. 
	        for (int i = 0; i<3; i++) 
	        { 
	            for (int j = 0; j<3; j++) 
	            { 
	            	if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) || game.getBoard()[i][j].equals(Tictactoe.CIRCLE)) { 
	            		for (int i2 = 0; i2 < 5; i2++) {
	            			for (int j2 = 0; j2 < 5; j2++) {
	            				if(engine.rule(this.getSigne(), i, j, i2, j2, game)) {
	            					game.ConcretePlay(i, i, i2, j2);
	            					int moveVal = minimax(game, 0, false); 
	            					if (moveVal > bestVal)  { 
	        	                        this.xi = i; 
	        	                        this.yi = j; 
	        	                        this.xx = i2;
	        	                        this.yy = j2;
	        	                        bestVal = moveVal; 
	        	                    } 
	            				}
	            			}
	            		}
	                } 
	            } 
	        } 
	      
	        System.out.println("The value of the best Move is : "); 

	    } 
	private int max(int best, int minmax) {
		if(best > minmax)	return best;
		else return minmax;
		// TODO Auto-generated method stub
		
	}
}
