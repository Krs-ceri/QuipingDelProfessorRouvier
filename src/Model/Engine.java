package Model;

import Players.*;

public final class Engine
{

	private Engine() {}

	static boolean verifyPosition(int x, int y)
	{
		Quixo b = Quixo.getInstance();
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
	static boolean verifyPlace(int x, int y)
	{
		Quixo b = Quixo.getInstance();

		if(!b.getBoard()[x][y].toString().equals("gr"))
			return false;

		return true;
	}

	/**
	 * This method verify if we can put a Tictactoe in a position
	 *
	 * @param c
	 * 			c represent a Tictactoe
	 * @param x
	 * 			x represent the column of the pyramid
	 * @param y
	 * 			y represent the line of the pyramid
	 *
	 * @return true if we can put the Tictactoe or return false
	 */
	static boolean verifyTictactoeSecond(int x_pos, int y_pos, int xx, int yy)
	{
		Quixo b = Quixo.getInstance();

		if(b.getBoard()[x_pos][y_pos].toString().equals("gr")) {
			if(x_pos == xx && (yy == 0 || yy == 4))	return true;
			if(y_pos == yy && (xx == 0 || xx == 4))	return true;
		}
		return false;
	}

	/**
	 * @param p
	 * @param c
	 * @param x
	 * @param y
	 * @return
	 */
	static boolean rule(Player p, Tictactoe c,int x,int y, int message)
	{

		Quixo b = Quixo.getInstance();
		int floor = b.getBoard().length - y;
		int column = x + 1;
		if(Engine.verifyPosition(x, y))
		{
			if(Engine.verifyPlace(x, y))
			{
				if(!Engine.verifyTictactoeSecond(c, x, y))
				{
					if(message == 1)
					{
						
							if(p == g.getLocalPlayer())
							{
								System.out.println("Impossible to put the Tictactoe in : Floor " + floor + ", Column " + column );
							}
							else
							{
								//server.sendMessageToClient(p.getSocket(), "Impossible to put the Tictactoe in : Floor " + floor + ", Column " + column);
						
					return false;
				}
			}
			else
			{
				if(message == 1)
				{
					
						if(p == g.getLocalPlayer())
						{
							System.out.println("There is all ready a Tictactoe in : Floor " + floor + ", Column " + column );
						}
						else
						{
							//server.sendMessageToClient(p.getSocket(), "There is all ready a Tictactoe in : Floor " + floor + ", Column " + column );
						}

				return false;
			}
		}
		else
		{
			if(message == 1)
			{
					if(p == g.getLocalPlayer())
					{
						System.out.println("Position out of bound : Floor " + floor + ", Column " + column );
					}
					else
					{
						//server.sendMessageToClient(p.getSocket(), "Position out of bound : Floor " + floor + ", Column " + column );
					}

			return false;
		}
		return true;

	}
}
