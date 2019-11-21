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

	/**
	 * This method return true of false if in position x,y is empty or not
	 *
	 * @param x
	 * 			x represent the column of the pyramid
	 * @param y
	 * 			y represent the line of the pyramid
	 * @return Return true if position is empty or return false
	 */
	public boolean verifyEmpty(int x, int y, Quixo b) {


		if(!b.getBoard()[x][y].toString().equals("gr"))
			return false;

		return true;
	}

	public boolean verifyTictactoe(int x_pos, int y_pos, Quixo b) {


		if(b.getBoard()[x_pos][y_pos].toString().equals("gr")) {
			if(x_pos == 0 || y_pos == 0 || y_pos == 4 || x_pos == 4)	return true;
		}
		return false;
	}
	
	public boolean verifyTictactoeSecond(int x_pos, int y_pos, int xx, int yy, Quixo b) {

		if(x_pos == xx && y_pos == yy) return false;
		if(b.getBoard()[x_pos][y_pos].toString().equals("gr") || b.getBoard()[x_pos][y_pos].equals(b.Current())) {
			if(x_pos == xx && (yy == 0 || yy == 4))	return true;
			else if(y_pos == yy && (xx == 0 || xx == 4))	return true;
		}
		else {
			return false;
		}
		return false;
	}

	

	public boolean rule(Player p, Tictactoe c,int x,int y , int xi, int yi, Quixo b)	{


		if(Engine.verifyPosition(x, y) && Engine.verifyPosition(xi, yi))
		{
			if(this.verifyEmpty(x, y,b))
			{
				if(!this.verifyTictactoeSecond(xi, yi , x, y,b))
				{

							if(c != b.getCurrent())
							{
								System.out.println("Impossible to put the Tictactoe in : Floor " + xi + ", Column " + yi );
							}
							else
							{
								return true;
							}
				}
				else {

					System.out.println("Position out of bound : Floor " + x + ", Column " + y );
					return false;
				}
			}
			else{
			
				System.out.println("There is all ready a Tictactoe in : Floor " + xi + ", Column " + yi );
				
				return false;
		}


	}
	else {
			System.out.println("Position out of bound : Floor " + xi + ", Column " + yi );
			return false;
		}
		return false;
	}
	
	
}
