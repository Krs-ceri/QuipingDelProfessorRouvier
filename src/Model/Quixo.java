package Model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Quixo {

	private static volatile Quixo instance = null;
	private Tictactoe current;
	protected Tictactoe plateau[][];
	private Tictactoe playerX = Tictactoe.CROSS;
	private Tictactoe playerO = Tictactoe.CIRCLE;
	private int clique = 0;
	private boolean ai = false;
	
	public Quixo() {
		this.plateau = new Tictactoe[5][5];
		boolean choice = Math.random() < 0.5;					// Ramdom, could have be the last player
		if(choice == true)	this.current = playerO;
		else {
			this.current = playerX;
		}
		System.out.println("le joueur "+  this.current.toString() + " commence.");
	}
	
	public Tictactoe winCondition() {
		Tictactoe winner = null;		//Nobody win
		for (int i = 0; i < plateau.length; i++) { // ligne verification
			if(this.plateau[i][0] == Tictactoe.CIRCLE || this.plateau[i][0] == Tictactoe.CROSS) {
				if((this.plateau[i][1] == this.plateau[i][0] 
						&& this.plateau[i][2] == this.plateau[i][0] 
						&& this.plateau[i][3] == this.plateau[i][0] 
						&& this.plateau[i][4] == this.plateau[i][0] )) 
				{ winner = this.plateau[i][0]; }
			}
		}
		for (int i = 0; i < plateau.length; i++) { // colonne verification
			if(this.plateau[0][i] == Tictactoe.CIRCLE || this.plateau[0][i] == Tictactoe.CROSS) {
				if((this.plateau[1][i] == this.plateau[0][i] 
						&& this.plateau[2][i] == this.plateau[0][i] 
						&& this.plateau[3][i] == this.plateau[0][i]
						&& this.plateau[4][i] == this.plateau[0][i] )) 
				{ winner = this.plateau[0][i]; }
			}
		}
		if(this.plateau[2][2] == Tictactoe.CIRCLE || this.plateau[1][1] == Tictactoe.CROSS) {
			if((this.plateau[0][0] == this.plateau[2][2] 
					&& this.plateau[1][1] == this.plateau[2][2] 
							&& this.plateau[3][3] == this.plateau[2][2] 
									&& this.plateau[4][4] == this.plateau[2][2])  //barre centrale verticale
					|| (this.plateau[0][4] == this.plateau[2][2] 
							&& this.plateau[1][3] == this.plateau[2][2] 
									&& this.plateau[3][1] == this.plateau[2][2] 
											&& this.plateau[4][0] == this.plateau[2][2])) 	//barre centrale horizontal
				 { winner = this.plateau[1][1]; }
		}
		return winner;
	}
	
	public boolean addTic(int x_pos, int y_pos, int xx, int yy) {
		if(this.plateau[x_pos][y_pos] == null) {
			if(x_pos == xx && (yy == 0 || yy == 4))	return true;
			if(y_pos == yy && (xx == 0 || xx == 4))	return true;
		}
		return false;
	}
	
	public void addTac(int x_pos, int y_pos, int xx, int yy) {
		
	}
	
	public void pushRow(int x_pos, int y_pos, int xx, int yy) {
		if(xx == 4) {
			
			for (int i = 0; i < plateau.length; i++) {
				
			}
		}
		if(xx == 0) {
			
		}
	}
	
	public void pushCol(int x_pos, int y_pos, int xx, int yy) {
		if(yy == 4) {
			
		}
		if(yy == 0) {
			
		}
	}

	public final static Quixo getInstance()
	{
		if(Quixo.instance == null)
		{
			synchronized(Quixo.class)
			{
				if(Quixo.instance == null)
				{
					Quixo.instance = new Quixo();
				}
			}
		}
		return Quixo.instance;
	}
	
	public Tictactoe Current() {
		return this.current;
	}
	
	public void Reset() {
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau.length; j++) {
				this.plateau[i][j] = null;
			}
		}
		this.clique = 0;
	}
	@SuppressWarnings("resource")
	public int Catch(int max) {
    	Scanner sc = new Scanner(System.in);
    	boolean flag = false;
    	int tmp = -1;
    	do {
    		try {
    			tmp = sc.nextInt();
    			if(tmp < 0 || tmp > max) { System.out.println("Value out of bound"); }
    			else {
    				flag = true;
    			}
    		}
    		catch(InputMismatchException e){
    			System.out.println("Only integer value !");
    			sc.next();
    		}
    	}while(!flag);
    	
    	return tmp;
	}
	protected boolean isEmpty(int x , int y) {
		if(this.plateau[x][y] == null)	return true;
		return false;
	}
	public void switchPlayer() {
		if(this.current == playerX)	this.current = playerO;
		else this.current = playerX;
	}
	public boolean nulRound() {
		if( this.clique == 16)	return true;
		return false;
	}

	public Tictactoe getCurrent() {
		return current;
	}

	public void setCurrent(Tictactoe current) {
		this.current = current;
	}

	public int getClique() {
		return clique;
	}

	public void setClique(int clique) {
		this.clique = clique;
	}

	public boolean isAi() {
		return ai;
	}

	public void setAi(boolean ai) {
		this.ai = ai;
	}
}
