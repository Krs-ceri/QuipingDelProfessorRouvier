package Model;

public class Move {

	private Tictactoe signeInitial;
	private int xi;
	private int yi;
	private Tictactoe signeFinal;
	private int xf;
	private int yf;
	
	
	public Move(Tictactoe si, int xi, int yi, Tictactoe sf, int xf, int yf) {
		
		this.signeInitial = si;
		this.xi = xi;
		this.yi = yi;
		
		this.signeFinal = sf;
		this.xf = xf;
		this.yf = yf;
		// TODO Auto-generated constructor stub
	}
	

	public Tictactoe getSigneInitial() {
		return signeInitial;
	}
	public void setSigneInitial(Tictactoe signeInitial) {
		this.signeInitial = signeInitial;
	}
	public int getXi() {
		return xi;
	}
	public void setXi(int xi) {
		this.xi = xi;
	}
	public Tictactoe getSigneFinal() {
		return signeFinal;
	}
	public void setSigneFinal(Tictactoe signeFinal) {
		this.signeFinal = signeFinal;
	}
	public int getXf() {
		return xf;
	}
	public void setXf(int xf) {
		this.xf = xf;
	}
	public int getYf() {
		return yf;
	}
	public void setYf(int yf) {
		this.yf = yf;
	}
	public int getYi() {
		return yi;
	}
	public void setYi(int yi) {
		this.yi = yi;
	}
}
