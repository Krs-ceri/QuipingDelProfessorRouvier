package Players;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import Model.Engine;
import Model.Quixo;
import Model.Tictactoe;

public class ThreadMM extends Player{
	

	private AtomicInteger xi;
	private AtomicInteger yi ;
	private AtomicInteger xx;
	private AtomicInteger yy;

	public ThreadMM(String name, int p, Tictactoe i) {
		super(name, p, i);
		// TODO Auto-generated constructor stub
		this.xi.set(-1);
		this.yi.set(-1);
		this.xx.set(-1);
		this.yy.set(-1);
	}

	public void setVariable() {
		this.xi.set(-1);
		this.yi.set(-1);
		this.xx.set(-1);
		this.yy.set(-1);
	}
	@Override
	public void execute(Quixo game) {
		// TODO Auto-generated method stub
		this.setVariable();
		this.calcIA(game, 
				this.getProfondeur());
		game.ConcretePlay(this.xi.get(), 
							this.yi.get(),
							this.xx.get(), 
							this.yy.get());
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
	                 	  //||  game.getBoard()[i][j].equals(Tictactoe.CIRCLE)
	                		 ) 
	                 { 
	                 	for (int i2 = 0; i2 < 5; i2++) {
	 						for (int j2 = 0; j2 < 5; j2++) {
	 							if(engine.rule(getSigne(), i, j, i2, j2, game)) {	
	 								game.ConcretePlay(i, i, i2, j2);
	 								game.switchPlayer();
	 								Quixo b = game.clone();
	 								Thread th = new Thread(this.getName());
	 								tmp = calcMin(game, prof-1);
	 								if((tmp>max)||(tmp==max )) {
	 									max = tmp;
	 									this.xi.set(i); 
	 									this.yi.set(j); 
	 									this.xx.set(i2);
	 									this.yy.set(j2);
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
	    int tmp;
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
							if(engine.rule(getSigne(), i, j, i2, j2, game)) {
								game.ConcretePlay(i, i, i2, j2);
 								game.switchPlayer();
 								tmp = calcMax(game, prof-1);
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

}
