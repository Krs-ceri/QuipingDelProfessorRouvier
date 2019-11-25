package Model;

import java.util.InputMismatchException;
import java.util.Scanner;

import Players.MinMax;
import Players.Player;
import Players.PlayerAi;
import Players.PlayerHumain;

public class Quixo implements Cloneable{


	private Tictactoe current;
	protected Tictactoe plateau[][];
	private Tictactoe playerX = Tictactoe.CROSS;
	private Tictactoe playerO = Tictactoe.CIRCLE;
	private int clique = 0;

	private Player human;
	private Player ai;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	public Quixo() {
		this.setHuman(new PlayerHumain("Shox", 0, playerX));
		this.setAi(new MinMax("MinMax", 3, playerO));
		this.plateau = new Tictactoe[5][5];
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau.length; j++) {
				this.plateau[i][j]  = Tictactoe.EMPTY;
			}
		}
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
				{
					winner = this.plateau[0][i]; 
				}
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
	
	/**
	 * "O" est l'IA
	 * @return	valeur 
	 */
	public int eval() {
		Tictactoe winner = null;		//Nobody win
		for (int i = 0; i < plateau.length; i++) { // ligne verification
			if(this.plateau[i][0] == Tictactoe.CIRCLE || this.plateau[i][0] == Tictactoe.CROSS) {
				if((this.plateau[i][1] == this.plateau[i][0] 
						&& this.plateau[i][2] == this.plateau[i][0] 
						&& this.plateau[i][3] == this.plateau[i][0] 
						&& this.plateau[i][4] == this.plateau[i][0] )) 
				{ 
					winner = this.plateau[i][0]; 
					if(winner == Tictactoe.CIRCLE)	return +100;
					else return -105;
				}
			}
		}
		for (int i = 0; i < plateau.length; i++) { // colonne verification
			if(this.plateau[0][i] == Tictactoe.CIRCLE || this.plateau[0][i] == Tictactoe.CROSS) {
				if((this.plateau[1][i] == this.plateau[0][i] 
						&& this.plateau[2][i] == this.plateau[0][i] 
						&& this.plateau[3][i] == this.plateau[0][i]
						&& this.plateau[4][i] == this.plateau[0][i] )) 
				{ 
					winner = this.plateau[0][i]; 
					if(winner == Tictactoe.CIRCLE)	return +100;
					else return -105;
				}
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
			{ 
				winner = this.plateau[0][0]; 
				if(winner == Tictactoe.CIRCLE)	return +100;
				else return -105;
			}
		}
		return 0;
	}
	
	
	/**
	 * 	le joueur "X" est le joueur humain
	 * le joueur "O" est l'IA créer
	 */
	public void switchPlayer() {
		if(this.current == playerX) {
			this.current = playerO;
			//this.ai.execute(this);
			//this.switchPlayer();
		}
		else this.current = playerX;
	}
	
	public boolean addTic(int x_pos, int y_pos, int xx, int yy) {
		if(this.plateau[x_pos][y_pos] == null) {
			if(x_pos == xx && (yy == 0 || yy == 4))	return true;
			if(y_pos == yy && (xx == 0 || xx == 4))	return true;
		}
		return false;
	}
	
	
	public void ConcretePlay(int xi, int yi, int xx, int yy) {
    	this.pushColNegative(xi, yi , xx , yy );
    	this.pushColPositive(xi, yi , xx , yy );
    	this.pushRowNegative(xi, yi , xx , yy );
    	this.pushRowPositive(xi, yi , xx , yy );
	}
	
	public void pushColPositive(int xi, int yi, int xx, int yy) {	
		if(xi < xx && yi == yy) {
			System.out.println("col pos");
			for (int i = xi; i < xx; i++) {
				this.plateau[i][yi] = this.plateau[i+1][yi];			
			}
			this.plateau[xx][yy] = this.current;
		}
	}
	
	public void pushColNegative(int xi, int yi, int xx, int yy) {
		if(xi > xx && yi == yy) {
			System.out.println("col neg");
			for (int i = xi; i > xx; i--) {
				this.plateau[i][yi] = this.plateau[i-1][yi];
			}
			this.plateau[xx][yy] = this.current;
		}
	}
	
	public void pushRowNegative(int xi, int yi, int xx, int yy) {
		if(xi == xx && yi < yy) {
			System.out.println("row neg");
			for (int i = yi; i < yy; i++) {
				this.plateau[xx][i] = this.plateau[xx][i+1];
			}
			this.plateau[xx][yy] = this.current;
		}
	}
	
	public void pushRowPositive(int xi, int yi, int xx, int yy) {
		if(xi == xx && yi > yy) {
			System.out.println("row pos ");
			for (int i = yi; i > yy; i--) {
				this.plateau[xx][i] = this.plateau[xx][i-1];
			}
			this.plateau[xx][yy] = this.current;
		}
	}
	
	public void Print() {
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau.length; j++) {
				System.out.print(this.plateau[i][j]);
			}
			System.out.println();
		}
	}

	public Tictactoe getCase(int i, int j) {
		//System.out.println(i+ "  " + j);
		return this.plateau[i][j];

	}
	
	public Tictactoe[][] getBoard() { 
		return this.plateau; 
	}
	
	
	public Tictactoe Current() {
		return this.current;
	}
	
	public void Reset() {
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau.length; j++) {
				this.plateau[i][j] = Tictactoe.EMPTY;
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
	public boolean isEmpty(int x , int y) {
		if(this.plateau[x][y] == null)	return true;
		return false;
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

	public Player getHuman() {
		return human;
	}

	public void setHuman(Player human) {
		this.human = human;
	}

	public Player getAi() {
		return ai;
	}

	public void setAi(Player ai) {
		this.ai = ai;
	}

}
