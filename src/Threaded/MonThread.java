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
	private Quixo b = null;
	private ThreadMM player = null;

	public MonThread(Quixo b, ThreadMM player, int xi, int yi, int xx, int yy) {
		// TODO Auto-generated constructor stub
		this.b = b.clone();
		this.xi = xi;
		this.xx = xx;
		this.yi = yi;
		this.player = player;
		this.yy = yy;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.IA();
		
	}
	public void IA() {
	    int tmp;
	    int max = -1000;
	    this.b.ConcretePlay(this.xi,this.yi, this.xx, this.yy);
	 	this.b.switchPlayer();
	 	tmp = Min( this.player.getProfondeur()-1);
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
	 	b = null;
	    System.out.println("deplacement : " +" best:  "+ max +"  "+this.xi+" "+this.yi+" : " +this.xx+" "+this.yy);
	    
	}
	
	private int Min(int prof)
	{
		 Engine engine = new Engine();
	    int tmp;
	    int min = 1000;
	    if(prof==0 || b.winCondition() != null)
	        return this.player.eval(b);
	 
	    for (int i = 0; i<5; i++) 
        { 
            for (int j = 0; j<5; j++) 
            { 
                if (b.getBoard()[i][j].equals(Tictactoe.EMPTY) 
                	|| b.getBoard()[i][j].equals(Tictactoe.CROSS)) 
                { 
                	for (int i2 = 0; i2 < 5; i2++) {
						for (int j2 = 0; j2 < 5; j2++) {
							if(engine.rule(b.getCurrent(), i, j, i2, j2, b)) {
								b.ConcretePlay(i, i, i2, j2);
								b.switchPlayer();
								tmp = Max( prof-1);
								if(tmp>min) {
									min = tmp;
								}
								b.undoMove();
							}
						}
                	}
                }
            }
        }
	    return min;
	}
	
	private int Max( int prof)
	{
		 Engine engine = new Engine();
	    int tmp;
	    int max = -1000;
	    if(prof==0 || b.winCondition() != null)
	        return this.player.eval(b);
	 
	    for (int i = 0; i<5; i++) 
        { 
            for (int j = 0; j<5; j++) 
            { 
                if (b.getBoard()[i][j].equals(Tictactoe.EMPTY) 
                	|| b.getBoard()[i][j].equals(Tictactoe.CIRCLE)) 
                { 
                	for (int i2 = 0; i2 < 5; i2++) {
						for (int j2 = 0; j2 < 5; j2++) {
							if(engine.rule(b.getCurrent(), i, j, i2, j2, b)) {
								b.ConcretePlay(i, i, i2, j2);
								b.switchPlayer();
								tmp = Min( prof-1);
								if(tmp<max) {
									max = tmp;
								}
								b.undoMove();
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
