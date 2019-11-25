package Model;

import Players.*;

public final class Engine
{

	public Engine() {}

	static boolean verifyPosition(int x, int y)
	{
		if(y < 0 || y > 5 || x < 0 || x > 5)
		{
			return false;
		}
		return true;
	}

	static boolean verifyEmpty(int x, int y, Quixo b, Tictactoe c) {
		if(b.getBoard()[x][y].toString().equals("gr") || b.getBoard()[x][y].toString().equals(c.toString())) return true;
		return false;
	}

	static boolean verifyTictactoe(int x_pos, int y_pos, Quixo b) {
		
		if(x_pos == 0 || y_pos == 0 || y_pos == 4 || x_pos == 4)	return true;
		return false;
	}
	
	static boolean verifyTictactoeSecond(int x_pos, int y_pos, int xx, int yy, Quixo b) {
		if(x_pos == 0 || y_pos == 0 || y_pos == 4 || x_pos == 4) {
			if(x_pos == xx && y_pos == yy) return false;
			if(x_pos == xx && (yy == 0 || yy == 4))	return true;
			else if(y_pos == yy && (xx == 0 || xx == 4))	return true;
		}		
		return false;
	}

	

	public boolean rule( Tictactoe c,int xi,int yi , int x, int y, Quixo b)	{
		
		if(Engine.verifyPosition(xi, yi) && Engine.verifyPosition(x, y)){
			if(Engine.verifyEmpty(xi, yi, b, c) ){
				if(Engine.verifyTictactoe(x, y, b) ){
					if(Engine.verifyTictactoeSecond(xi, yi , x, y,b)){
						if(c != b.getCurrent()) {
							System.out.println("Not your Turn !" );
						}
						else{
							return true;
						}
					}
					else {
						System.out.println("Position out of bound : Floor " + x + ", Column " + y );
						return false;
					}
				}
				else{
					System.out.println("Position out of bound : Floor " + xi + ", Column " + yi );
					return false;
				}

			}
			else {
				System.out.println("Position not playable : Floor " + xi + ", Column " + yi );
				return false;
			}
		}
		System.out.println("Position not on the board !");
		return false;
	}
		
	
	
}
