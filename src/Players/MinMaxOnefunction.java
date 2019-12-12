package Players;

import Model.Engine;
import Model.Quixo;
import Model.Tictactoe;

public class MinMaxOnefunction extends Player{
	private int xi = -1;
	private int yi = -1;
	private int xx = -1;
	private int yy = -1;

	public MinMaxOnefunction(String name, int p, Tictactoe i) {
		super(name, p, i);
		// TODO Auto-generated constructor stub
	}

	public void setVariable() {
			this.xi = -1; 
			this.yi = -1; 
			this.xx = -1;
			this.yy = -1;
	}
	@Override
	public void execute(Quixo game) {
		// TODO Auto-generated method stub
		this.setVariable();
		this.calcIA(game, this.getProfondeur());
		do {
			game.ConcretePlay(this.xi, this.yi, this.xx, this.yy);
		}while(xi == -1);
		
	}
	
	public void calcIA(Quixo game, int prof) {
	    int tmp;
	    int max = -10000;
	    Engine engine = new Engine();
	    	for (int i = 0; i<5; i++) 
	         { 
	             for (int j = 0; j<5; j++) 
	             { 
	                 if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
	                 	  ||  game.getBoard()[i][j].equals(Tictactoe.CIRCLE)
	                		 ) 
	                 { 
	                 	for (int i2 = 0; i2 < 5; i2++) {
	 						for (int j2 = 0; j2 < 5; j2++) {
	 							if(engine.rule(getSigne(), i, j, i2, j2, game)) {	
	 								game.ConcretePlay(i, i, i2, j2);
	 								game.switchPlayer();
	 								tmp = calcMin(game, prof-1);
	 								if(game.getBoard()[i][j].equals(Tictactoe.EMPTY ))	tmp+=5;
	 								if((tmp>max)||(tmp==max )) {
	 									max = tmp;
	 									this.xi = i; 
	 									this.yi = j; 
	 									this.xx = i2;
	 									this.yy = j2;
	 								}
	 								game.undoMove();
	 							}
	 						}
	                 	}
	                 }
	             }
	         }
	    System.out.println("deplacement : " +" best:  "+ max +"  "+this.xi+" "+this.yi+" : " +this.xx+" "+this.yy);
	    
	}
	
	private int calcMin(Quixo game, int prof)
	{
		 Engine engine = new Engine();
	    int min = 1000;
	    if(prof==0 || game.winCondition() != null)
	        return eval(game);
	 
	    for (int i = 0; i<5; i++) 
        { 
            for (int j = 0; j<5; j++) 
            { 
                if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
                	|| game.getBoard()[i][j].equals(Tictactoe.CROSS)) 
                { 
                	
                	for (int i2 = 0; i2 < 5; i2++) {
						for (int j2 = 0; j2 < 5; j2++) {
							if(engine.rule(game.Current(), i, j, i2, j2, game)) {
								game.ConcretePlay(i, i, i2, j2);
 								game.switchPlayer();
 								int tmp = calcMax(game, prof-1);
 								if(game.getBoard()[i][j].equals(Tictactoe.EMPTY ))	tmp+=5;
 								if(tmp<min) {
 									min = tmp;
 								}
 								game.undoMove();
							}
						}
                	}
                }
            }
        }
	    return min;
	}
	
	private int calcMax(Quixo game, int prof)
	{
		 Engine engine = new Engine();
	    int tmp;
	    int max = -1000;
	    if(prof==0 || game.winCondition() != null)
	        return eval(game);

	 
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
 								tmp = calcMin(game, prof-1);
 								if(game.getBoard()[i][j].equals(Tictactoe.EMPTY ))	tmp+=5;
 								if(tmp>max) {
 									max = tmp;
 								}
 								game.undoMove();
							}
						}
                	}
                }
            }
        }
	    return max;
	}
	
	public int eval(Quixo plateau) {
		Tictactoe winner = null;		//Nobody win
		int value = 0;
		for (int i = 0; i < plateau.getBoard().length; i++) { // ligne verification
			if(plateau.getBoard()[i][0] == Tictactoe.CIRCLE || plateau.getBoard()[i][0] == Tictactoe.CROSS) {
				if((plateau.getBoard()[i][1] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][2] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][3] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][4] == plateau.getBoard()[i][0] )) 
				{ 
					winner = plateau.getBoard()[i][0]; 
					if(winner == Tictactoe.CIRCLE)	value += +10000;
					else value -=10000;
				}
				if((plateau.getBoard()[i][1] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][2] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][3] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][4] != plateau.getBoard()[i][0] )) 
				{ 
					winner = plateau.getBoard()[i][0]; 
					if(winner == Tictactoe.CIRCLE)	value += 70;
					else value -= 70;
				}
				if((plateau.getBoard()[i][1] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][2] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][3] != plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][4] != plateau.getBoard()[i][0] )) 
				{ 
					winner = plateau.getBoard()[i][0]; 
					if(winner == Tictactoe.CIRCLE)	value += 50;
					else value -= 50;
				}
				if((plateau.getBoard()[i][1] == plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][2] != plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][3] != plateau.getBoard()[i][0] 
						&& plateau.getBoard()[i][4] != plateau.getBoard()[i][0] )) 
				{ 
					winner = plateau.getBoard()[i][0]; 
					if(winner == Tictactoe.CIRCLE)	value += 25;
					else value -= 25;
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
					if(winner == Tictactoe.CIRCLE)	value += 10000;
					else value -= 10000;
				}
				if((plateau.getBoard()[1][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[2][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[3][i] == plateau.getBoard()[0][i]
						&& plateau.getBoard()[4][i] != plateau.getBoard()[0][i] )) 
				{ 
					winner = plateau.getBoard()[0][i];
					if(winner == Tictactoe.CIRCLE)	value += 70;
					else value -= 70;
				}
				if((plateau.getBoard()[1][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[2][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[3][i] != plateau.getBoard()[0][i]
						&& plateau.getBoard()[4][i] != plateau.getBoard()[0][i] )) 
				{ 
					winner = plateau.getBoard()[0][i];
					if(winner == Tictactoe.CIRCLE)	value += 50;
					else value -= 50;
				}
				if((plateau.getBoard()[1][i] == plateau.getBoard()[0][i] 
						&& plateau.getBoard()[2][i] != plateau.getBoard()[0][i] 
						&& plateau.getBoard()[3][i] != plateau.getBoard()[0][i]
						&& plateau.getBoard()[4][i] != plateau.getBoard()[0][i] )) 
				{ 
					winner = plateau.getBoard()[0][i];
					if(winner == Tictactoe.CIRCLE)	value += 25;
					else value -= 25;
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
				if(winner == Tictactoe.CIRCLE)	value += 1000;
				else value -= 1000;
			}
		}
		return value;
	}

}