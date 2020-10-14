package sudoku;

import java.util.*;


public class Solver 
{
	private Grid						problem;
	private ArrayList<Grid>				solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	
		
	// 
	// FINISH THIS.
	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
			return;
		}
		else if (eval == Evaluation.ACCEPT)
		{
			solutions.add(grid);// A complete and legal solution. Add it to solutions.
		}
		else
		{
		
		ArrayList<Grid> nextGrid = grid.next9Grids();
			
		for(Grid g : nextGrid)
			{
				solveRecurse(g);// Here if eval == Evaluation.CONTINUE. Generate all 9 possible next grids. Recursively 
			}
			// call solveRecurse() on those grids.
		}
	}
	
	//
	//
	// Returns Evaluation.ABANDON if the grid is illegal. 
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	//
	public Evaluation evaluate(Grid grid)
	{
		if(!grid.isLegal())
			return Evaluation.ABANDON;
		if(grid.isLegal() && grid.isFull())
			return Evaluation.ACCEPT;
		else
			return Evaluation.CONTINUE;

	}

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle1();		
		Solver solver = new Solver(g);
		solver.solve();
		System.out.println(solver.getSolutions());
		
		
		// Print out your solution, or test if it equals() the solution in TestGridSupplier.
	}
}
