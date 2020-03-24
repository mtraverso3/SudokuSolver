import java.util.Arrays;

public class SudokuGrid
{
    public Cell[][] grid = new Cell[9][9];

    SudokuGrid(int[][] vals){
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.grid[row][col] = new Cell(vals[row][col],false);
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder a = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                a.append(grid[row][col].getValue()).append(" ");
            }
            a.append("\n");
        }

        return a.toString();
    }
}
