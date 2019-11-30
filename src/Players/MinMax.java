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

			Engine engine = new Engine();
			Quixo game = (Quixo) b.clone();
			this.Play(game);
			if(engine.rule(this.getSigne(), xi, yi, xx, yy, game)) {
				game.ConcretePlay(xi, yi, xx, yy);
			}
	}
	
	public int minimax(Quixo game, int profondeur ,boolean isMax) { 
	    int score = game.eval(); 

	    if (score == 100) return score; 
	    if (score == -105)  return score; 
	    
	    if(isMax) return Max(game, profondeur, isMax);
	    else return Min(game, profondeur, isMax);
	}
	private int Max(Quixo game, int profondeur ,boolean isMax) {
		Engine engine = new Engine();
	        int best = -1000; 
	        if(profondeur == this.getProfondeur()) return 0;
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
				                    best = max( best, minimax(game, profondeur+1, !isMax) );
				                    game = null;
								}
							}
						}
	                } 
	            } 
	        } 
	        return best; 
	}
		
	private int Min(Quixo game, int profondeur ,boolean isMax) {
		if(profondeur == this.getProfondeur()) return 0;
		int best = 1000; 
        Engine engine = new Engine();
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
	            					
									best = min(best,  minimax(game, profondeur+1, isMax));
									game.undoMove();
								}
						}
	                } 
            	 }
            }
        }
        return best; 
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
	            					if (move > best)  { 
	        	                        this.xi = i; 
	        	                        this.yi = j; 
	        	                        this.xx = i2;
	        	                        this.yy = j2;
	        	                        best = move; 
	        	                    }
	            					game.undoMove();
	            				}
	            			}
	            		}
	                } 
	            } 
	        } 
	      
	        System.out.println("deplcement : "+this.xi+" "+this.yi); 

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
	public int getBest() {
		return best;
	}
	public void setBest(int best) {
		this.best = best;
	}
}
