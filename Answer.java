package sudoku;
import java.util.*;

public class Answer {
	static int[][] matrix = new int[9][9];

	Answer() {
		while(true) {
			for(int i=0; i<9; i++)
				for(int j=0; j<9; j++)
					matrix[i][j] = 0;
			boolean hasErr = false;
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					int blk = i / 3 * 3 + j / 3;
					Set<Integer> optSet = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)),
					             rowSet = getRow(i),
					             colSet = getCol(j),
					             blkSet = getBlock(blk);
					optSet.removeAll(rowSet);
					optSet.removeAll(colSet);
					optSet.removeAll(blkSet);
					if(optSet.isEmpty()) {
						hasErr = true;
						break;
					}
					Integer[] optAry = optSet.toArray(new Integer[0]);
					Random randGen = new Random();
					matrix[i][j] = optAry[randGen.nextInt(optAry.length)];
				}
				if(hasErr)
					break;
			}
			if(!hasErr)
				break;
		}
	}

	Set<Integer> getRow(int row) {
		Set<Integer> rowSet = new HashSet<Integer>();
		for(int i=0; i<9; i++) 
			rowSet.add(matrix[row][i]);

		return rowSet;
	}
	
	Set<Integer> getCol(int col) {
		Set<Integer> colSet = new HashSet<Integer>();
		for(int i=0; i<9; i++)
			colSet.add(matrix[i][col]);
		
		return colSet;
	}
	
	Set<Integer> getBlock(int blk) {
		Set<Integer> blkSet = new HashSet<Integer>();
		int rh = blk / 3 * 3;
		int ch = blk % 3 * 3;
		for(int i=rh; i<rh+3; i++)
			for(int j=ch; j<ch+3; j++)
				blkSet.add(matrix[i][j]);
		
		return blkSet;
	}
	
	static void print(){
		print(matrix);
	}
	
	static void print(int[][] matrix) {
		for(int i=0; i<9; i++) {
			if(i%3 == 0)
				System.out.println();
			for(int j=0; j<9; j++){
				if(j%3 == 0)
					System.out.print(" ");
				if(matrix[i][j]== 0)
					System.out.print("* ");
				else
					System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
