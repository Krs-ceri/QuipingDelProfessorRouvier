package Players;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import Threaded.MonThread;


import Model.Engine;
import Model.Quixo;
import Model.Tictactoe;

public class ThreadMM extends PlayerAi{
	
	private AtomicInteger xi;
	private AtomicInteger yi ;
	private AtomicInteger xx;
	private AtomicInteger yy;
	private AtomicInteger value;
	
	public ThreadMM(String name, int p, Tictactoe i) {
		super(name, p, i);
		// TODO Auto-generated constructor stub
		this.xi = new AtomicInteger(-1);
		this.xx = new AtomicInteger(-1);
		this.yi = new AtomicInteger(-1);
		this.yy = new AtomicInteger(-1);
		this.value = new AtomicInteger(-1000);
	}
	public void setVar() {
		this.value.set(-1000);
		this.xi.set(-1);
		this.xx.set(-1);
		this.yi.set(-1);
		this.yy.set(-1);
		
	}

	@Override
	public void execute(Quixo game) {
		// TODO Auto-generated method stub
		Vector<Thread> th = new Vector<Thread>();
		Engine engine = new Engine();
    	for (int i = 0; i<5; i++) 
         { 
             for (int j = 0; j<5; j++) 
             { 
                 if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
                 	  ||  game.getBoard()[i][j].equals(Tictactoe.CIRCLE)
                		 ) { 
                 	for (int i2 = 0; i2 < 5; i2++) {
 						for (int j2 = 0; j2 < 5; j2++) {
 							if(engine.rule(getSigne(), i, j, i2, j2, game)) {
 								Quixo tmp = game.clone();
 								Runnable runnable = new MonThread(tmp, 
 																	this, i, j, i2, j2);
 								
 							    th.add(new Thread(runnable));
 							    
 							    
 							}
 						}
                 	}
                 }
             }
         }
    	for (Thread t : th) {
			t.start();
		}
    	for (Thread t : th) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	game.ConcretePlay(this.getXi().intValue()
    			, this.getYi().intValue()
    			, this.getXx().intValue()
    			, this.getYy().intValue());
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

	public AtomicInteger getXi() {
		return xi;
	}

	public void setXi(AtomicInteger xi) {
		this.xi = xi;
	}

	public AtomicInteger getYi() {
		return yi;
	}

	public void setYi(AtomicInteger yi) {
		this.yi = yi;
	}

	public AtomicInteger getXx() {
		return xx;
	}

	public void setXx(AtomicInteger xx) {
		this.xx = xx;
	}

	public AtomicInteger getYy() {
		return yy;
	}

	public void setYy(AtomicInteger yy) {
		this.yy = yy;
	}

	public AtomicInteger getValue() {
		return value;
	}

	public void setValue(AtomicInteger value) {
		this.value = value;
	}


}
