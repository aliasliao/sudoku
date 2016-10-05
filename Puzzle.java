package sudoku;
import java.util.*;

import javax.swing.colorchooser.ColorSelectionModel;

public class Puzzle extends Answer {
	static int[][] ansMatrix = new int[9][9];

	Puzzle(int hint) {
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				ansMatrix[i][j] = matrix[i][j];
		
		int dig = 81 - hint;
		Set<Integer> digSet  = new HashSet<Integer>();
		Random randGen = new Random();
		while(digSet.size() < dig)
			digSet.add(randGen.nextInt(80));
		for(int index: digSet) {
			int i = index / 9;
			int j = index % 9;
			matrix[i][j] = 0;
		}
		
	}
	
	void solve() {
		Set<Integer> hintSet = new HashSet<Integer>();
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				if(matrix[i][j] != 0)
					hintSet.add(i * 9 + j);
		for(int n=0; n<81; n++) {
			int i = n / 9;
			int j = n % 9;
			if(hintSet.contains(n))
				continue;
			Set<Integer> rowSet = getRow(i),
						 colSet = getCol(j),
						 blkSet = getBlock(i / 3 * 3 + j / 3),
						 optSet = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
			optSet.removeAll(rowSet);
			optSet.removeAll(colSet);
			optSet.removeAll(blkSet);
			for(int k=0; k<matrix[i][j]; k++)
				optSet.remove(k);
			if(optSet.isEmpty()) {
				matrix[i][j] = 0;
				n--;
				while(hintSet.contains(n))
					n--;
				n--;
				continue;
			}
			else {
			    List<Integer> optList = new ArrayList<Integer>(optSet);
			    Collections.sort(optList);
			    matrix[i][j] = optList.get(0);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of hints: ");
		int hint = scanner.nextInt();
		scanner.close();

		Puzzle puzzle = new Puzzle(hint);
		System.out.print("Puzzle with " + hint + " hints:");
		puzzle.print();
		System.out.println();
		
		puzzle.solve();
		System.out.print("Solved by the program:");
		puzzle.print();
		System.out.println();

		System.out.print("Default answer:");
		print(ansMatrix);
	}
}
