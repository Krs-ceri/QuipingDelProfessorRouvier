package Model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.Main;
import Players.*;


/**
 * @author Nizar
 *
 */
/**
 * @author uapv1502995
 *
 */
public class Quixo implements Cloneable{


	private Tictactoe current;
	protected Tictactoe plateau[][];
	private Tictactoe playerX = Tictactoe.CROSS;
	private Tictactoe playerO = Tictactoe.CIRCLE;
	private ArrayList<Move> move;
	private PlayerHumain human;
	private PlayerAi ai;
	
	
    /**
     * @param p
     * @return	factory method
     */
    public static Quixo makeCopy(Quixo p) {
    	Quixo copy = new Quixo();
    	copy.move = p.getMove();
    	copy.current = p.getCurrent();
        copy.setBoard(p.getBoard());
        
        return copy;
    }
	
	public Quixo() {
		Main main =  Main.getInstance();
		this.setHuman(main.getHuman());
		this.setAi(main.getAi());
		
		System.out.println(this.human.getName() + " VS " + this.ai.getName());
		this.move = new ArrayList<Move>();
		
		this.plateau = new Tictactoe[5][5];
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau.length; j++) {
				this.plateau[i][j]  = Tictactoe.EMPTY;
			}
		}
		boolean choice = Math.random() < 0.45;					// Ramdom
		if(choice == true)	this.current = playerO;
		else this.current = playerX;
			
		if(this.current.equals(human.getSigne())) System.out.println("The human begin, you are very lucky" );
		else System.out.println("The AI :" + this.ai.getName()+ "begin !");
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
		if(this.plateau[2][2] == Tictactoe.CIRCLE || this.plateau[2][2] == Tictactoe.CROSS) {
			if((this.plateau[0][0] == this.plateau[2][2] 
						&& this.plateau[1][1] == this.plateau[2][2] 
						&& this.plateau[3][3] == this.plateau[2][2] 
						&& this.plateau[4][4] == this.plateau[2][2])  //barre diag verticale
					|| (this.plateau[0][4] == this.plateau[2][2] 
							&& this.plateau[1][3] == this.plateau[2][2] 
							&& this.plateau[3][1] == this.plateau[2][2] 
							&& this.plateau[4][0] == this.plateau[2][2])) 	//barre diag horizontal
				 { winner = this.plateau[2][2]; }
		}
		return winner;
	}
	

	/**
	 * 	le joueur "X" est le joueur humain
	 * le joueur "O" est l'IA cr�er
	 */
	public void switchPlayer() {
		if(this.current == playerX) this.current = playerO;
		else this.current = playerX;
	}
	
	public Player getCurrentPlayer() {
		if(this.current == playerX) return human;
		else return ai;
	}

	
	public boolean ConcretePlay(int xi, int yi, int xx, int yy) {
		Tictactoe tmpi = this.getCase(xi, yi);
    	this.pushColNegative(xi, yi , xx , yy );
    	this.pushColPositive(xi, yi , xx , yy );
    	this.pushRowNegative(xi, yi , xx , yy );
    	this.pushRowPositive(xi, yi , xx , yy );
    	Tictactoe tmpf = this.getCase(xx, yy);
    	Move e = new Move(tmpi, xi, yi, tmpf, xx, yy);
    	System.out.println("["+e.getSigneFinal()+"] "+ e.getXf()+" "+ e.getYf()+" ["+ 
    						e.getSigneInitial()+"] "+ e.getXi()+" "+ e.getYi());
    	this.move.add(e);
    	return true;
	}
	
	public void pushColPositive(int xi, int yi, int xx, int yy) {	
		if(xi < xx && yi == yy) {
			System.out.print("col pos : ");
			for (int i = xi; i < xx; i++) {
				this.plateau[i][yi] = this.plateau[i+1][yi];			
			}
			this.plateau[xx][yy] = this.current;
		}
	}
	
	public void pushColNegative(int xi, int yi, int xx, int yy) {
		if(xi > xx && yi == yy) {
			System.out.print("col neg : ");
			for (int i = xi; i > xx; i--) {
				this.plateau[i][yi] = this.plateau[i-1][yi];
			}
			this.plateau[xx][yy] = this.current;
		}
	}
	
	public void pushRowNegative(int xi, int yi, int xx, int yy) {
		if(xi == xx && yi < yy) {
			System.out.print("row neg : ");
			for (int i = yi; i < yy; i++) {
				this.plateau[xx][i] = this.plateau[xx][i+1];
			}
			this.plateau[xx][yy] = this.current;
		}
	}
	
	public void pushRowPositive(int xi, int yi, int xx, int yy) {
		if(xi == xx && yi > yy) {
			System.out.print("row pos : ");
			for (int i = yi; i > yy; i--) {
				this.plateau[xx][i] = this.plateau[xx][i-1];
			}
			this.plateau[xx][yy] = this.current;
		}
	}
	public boolean moveEmpty() { 
		if(this.move.isEmpty())	return true ;
		return false;
	}
	
	public void undoMove() {
		if(!this.move.isEmpty()) {
			Move e = this.move.remove(move.size()-1);
			this.Undo(e);
			this.switchPlayer();
		}
	}

	public void Undo(Move e) {
    	this.pushColNegative( e.getSigneFinal(), e.getXf(), e.getYf(), e.getSigneInitial(), e.getXi(), e.getYi());
    	this.pushColPositive( e.getSigneFinal(), e.getXf(), e.getYf(), e.getSigneInitial(), e.getXi(), e.getYi());
    	this.pushRowNegative( e.getSigneFinal(), e.getXf(), e.getYf(), e.getSigneInitial(), e.getXi(), e.getYi());
    	this.pushRowPositive( e.getSigneFinal(), e.getXf(), e.getYf(), e.getSigneInitial(), e.getXi(), e.getYi());
	}
	
	public void pushColPositive(Tictactoe si, int xi, int yi, Tictactoe sf, int xx, int yy) {	
		if(xi < xx && yi == yy) {
			this.plateau[xi][yi] = si;
			for (int i = xi; i < xx; i++) {
				this.plateau[i][yi] = this.plateau[i+1][yi];			
			}
			this.plateau[xx][yy] = sf;
		}
	}
	
	public void pushColNegative(Tictactoe si, int xi, int yi, Tictactoe sf, int xx, int yy) {
		if(xi > xx && yi == yy) {
			this.plateau[xi][yi] = si;
			for (int i = xi; i > xx; i--) {
				this.plateau[i][yi] = this.plateau[i-1][yi];
			}
			this.plateau[xx][yy] = sf;
		}
	}
	
	public void pushRowNegative(Tictactoe si, int xi, int yi, Tictactoe sf, int xx, int yy) {
		if(xi == xx && yi < yy) {
			this.plateau[xi][yi] = si;
			for (int i = yi; i < yy; i++) {
				this.plateau[xx][i] = this.plateau[xx][i+1];
			}
			this.plateau[xx][yy] = sf;	
		}
	}
	
	public void pushRowPositive(Tictactoe si, int xi, int yi, Tictactoe sf, int xx, int yy) {
		if(xi == xx && yi > yy) {
			this.plateau[xi][yi] = si;
			for (int i = yi; i > yy; i--) {
				this.plateau[xx][i] = this.plateau[xx][i-1];
			} 
			this.plateau[xx][yy] = sf;
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

	public void setBoard(Tictactoe[][] b) { 
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau.length; j++) {
				this.plateau[i][j] = b[i][j];
			}
		}
	}
	
	public Tictactoe Current() {
		return this.current;
	}
	
	public void addMove(Move e) {
		this.move.add(e);
	}
	
	public void Reset() {
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau.length; j++) {
				this.plateau[i][j] = Tictactoe.EMPTY;
			}
		}
		this.move.clear();
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

	public Tictactoe getCurrent() {
		return current;
	}

	public void setCurrent(Tictactoe current) {
		this.current = current;
	}

	public Player getHuman() {
		return human;
	}

	public void setHuman(PlayerHumain human) {
		this.human = human;
	}

	public PlayerAi getAi() {
		return ai;
	}

	public void setAi(PlayerAi ai) {
		this.ai = ai;
	}

	public ArrayList<Move> getMove() {
		return move;
	}

	public void setMove(ArrayList<Move> move) {
		this.move = move;
	}

}
