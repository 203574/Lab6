package it.polito.tdp.sudoku.model;

public class SudokuSolver 
{
	public static final int BOARD_WIDTH = 9;
	public static final int BOARD_HEIGHT = 9;
	private int[][] matrice;
	
	public SudokuSolver() 
	{
		matrice = new int[BOARD_WIDTH][BOARD_WIDTH];
	}

	public int[][] getMatrice() 
	{
		return matrice;
	}

	public void setMatrice(int[][] matrice)
	{
		this.matrice = matrice;
	}

	public int[][] solve(int passo) 
	{
		if(passo==80)
		{
			return matrice;
		}
		else
		{
			for (int i = 1; i <= 9; i++)
			{
				if(matrice[passo/9][passo%9] != 0)
				{
					int temp[][] = solve(passo+1);
					if(temp != null)
					{
						return temp;
					}
					
				}
				else
				{
					matrice[passo/9][passo%9]=i;
					if(isValid(passo, i))
					{
						int temp[][] = solve(passo+1);
						if(temp != null)
						{
							return temp;
						}
					}
					matrice[passo/9][passo%9]=0;

				}
			}
		}
		return null;
		
	}

	private boolean isValid(int passo, int valore) 
	{
		//controllo riga
		for (int i = 0; i < 9; i++) 
		{
			if(i != passo%9)
			{
				if(matrice[passo/9][i]==valore)
					return false;
			}
		}
		
		//controllo colonna
		for (int i = 0; i < 9; i++)
		{
			if(i != passo/9)
			{
				if(matrice[i][passo%9] == valore)
					return false;
			}
		}
		
		//controllo quadrato
		int riga = passo/9;
		int colonna = passo%9;
		int rq = (riga/3)*3;
		int cq = (colonna/3)*3;
		
		for (int i = rq; i < rq+3; i++) 
		{
			for (int j = cq; j < cq+3; j++)
			{
				if(i!=riga || j!=colonna) // ||
				{
					if(matrice[i][j] == valore) //i e j per ciclare
						return false;
				}
			}
		}
		return true;
	}
	
	
	
}
