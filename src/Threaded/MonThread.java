package Threaded;

import Model.Engine;
import Model.Quixo;
import Model.Tictactoe;
import Players.*;

public class MonThread implements Runnable{
	
	private int xi = -1;
	private int yi = -1;
	private int xx = -1;
	private int yy = -1;
	private Quixo game = null;
	private PlayerAi player = null;

	public MonThread(Quixo b, PlayerAi player, int xi, int yi, int xx, int yy) {
		// TODO Auto-generated constructor stub
		this.game = b;
		this.xi = xi;
		this.xx = xx;
		this.yi = yi;
		this.player = player;
		this.yy = yy;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.calcIA();
		
	}
	public void calcIA() {
	    int tmp;
	    int max = -1000;
	    Engine engine = new Engine();
	    game.ConcretePlay(this.xi,this.yi, this.xx, this.yy);
	 	game.switchPlayer();
	 	tmp = calcMin( this.player.getProfondeur()-1);
	 	int old = this.player.getValue().getAndSet(tmp);
	 	if((tmp <  old)) {
	 		this.player.getValue().set(old);
	 		return;
	 	}
	 	else {
	 		this.player.getValue().set(tmp);
	 		this.player.getXi().set(xi); 
	 		this.player.getYi().set(yi); 
	 		this.player.getXx().set(xx);
	 		this.player.getYy().set(yy);
	 	}
	 	game = null;
	    System.out.println("deplacement : " +" best:  "+ max +"  "+this.xi+" "+this.yi+" : " +this.xx+" "+this.yy);
	    
	}
	
	private int calcMin(int prof)
	{
		 Engine engine = new Engine();
	    int tmp;
	    int min = 1000;
	    if(prof==0 || game.winCondition() != null)
	        return this.player.eval(game);
	 
	    for (int i = 0; i<5; i++) 
        { 
            for (int j = 0; j<5; j++) 
            { 
                if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
                	|| game.getBoard()[i][j].equals(Tictactoe.CROSS)) 
                { 
                	for (int i2 = 0; i2 < 5; i2++) {
						for (int j2 = 0; j2 < 5; j2++) {
							if(engine.rule(game.getCurrent(), i, j, i2, j2, game)) {
								game.ConcretePlay(i, i, i2, j2);
								game.switchPlayer();
								tmp = calcMax( prof-1);
								if(tmp>min) {
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
	
	private int calcMax( int prof)
	{
		 Engine engine = new Engine();
	    int tmp;
	    int max = -1000;
	    if(prof==0 || game.winCondition() != null)
	        return this.player.eval(game);
	 
	    for (int i = 0; i<5; i++) 
        { 
            for (int j = 0; j<5; j++) 
            { 
                if (game.getBoard()[i][j].equals(Tictactoe.EMPTY) 
                	|| game.getBoard()[i][j].equals(Tictactoe.CIRCLE)) 
                { 
                	for (int i2 = 0; i2 < 5; i2++) {
						for (int j2 = 0; j2 < 5; j2++) {
							if(engine.rule(this.player.getSigne(), i, j, i2, j2, game)) {
								game.ConcretePlay(i, i, i2, j2);
								game.switchPlayer();
								tmp = calcMin( prof-1);
								if(tmp<max) {
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
	
	public int getXi() {
		return xi;
	}
	public void setXi(int xi) {
		this.xi = xi;
	}
	public int getYi() {
		return yi;
	}
	public void setYi(int yi) {
		this.yi = yi;
	}
	public int getXx() {
		return xx;
	}
	public void setXx(int xx) {
		this.xx = xx;
	}
	public int getYy() {
		return yy;
	}
	public void setYy(int yy) {
		this.yy = yy;
	}

}
