package Players;

import java.util.concurrent.atomic.AtomicInteger;

import Model.*;

public abstract class PlayerAi extends Player{
	
	private AtomicInteger xi;
	private AtomicInteger yi ;
	private AtomicInteger xx;
	private AtomicInteger yy;
	private AtomicInteger value;
	
	public PlayerAi(String name, int p, Tictactoe i) {
	// TODO Auto-generated constructor stub
		super(name, p, i);
		this.xi = new AtomicInteger(-1);
		this.xx = new AtomicInteger(-1);
		this.yi = new AtomicInteger(-1);
		this.yy = new AtomicInteger(-1);
		this.value = new AtomicInteger(-1000);
	}

	@Override
	public void execute(Quixo b) {
		// TODO Auto-generated method stub
		
	}
	
	public void setVar() {
		this.value.set(-1000);
		this.xi.set(-1);
		this.xx.set(-1);
		this.yi.set(-1);
		this.yy.set(-1);
		
	}
	
	public abstract int eval(Quixo plateau);

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
