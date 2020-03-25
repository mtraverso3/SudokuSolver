/**
 * Modified version of the other 'Solver' that uses recursion instead.
 *
 * @author Marcos Traverso
 */
public class RecursiveSolver
{
    public int[][] gridInts = new int[9][9];

    RecursiveSolver(Integer[][] vals)
    {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (vals[row][col] == null) {
                    //if it has no value uses this
                    this.gridInts[row][col] = 0;
                }
                else {
                    //if it has a value sets that cell as non-modifiable
                    this.gridInts[row][col] = vals[row][col];
                }
            }
        }
    }

    public int[][] getSolved()
    {
        recursiveSolve();
        return gridInts;
    }

    public boolean recursiveSolve()
    {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {

                if (gridInts[row][column] == 0) {
                    for (int number = 1; number <= 9; number++) {

                        if (check(number, row, column)) {
                            gridInts[row][column] = number;

                            if (recursiveSolve()) {
                                return true;
                            }
                            else {
                                gridInts[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private boolean check(int val, int x, int y)
    {
        return checkBox(val, x, y) && checkVertical(val, x, y) && checkHorizontal(val, x, y);
    }

    private boolean checkBox(int val, int xIn, int yIn)
    {
        int startX = xIn - xIn % 3;
        int startY = yIn - yIn % 3;

        for (int x = startX; x < startX + 3; x++) {
            for (int y = startY; y < startY + 3; y++) {
                if (x != xIn && y != yIn && gridInts[x][y] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkVertical(int val, int xIn, int yIn)
    {
        for (int y = 0; y < 9; y++) {
            if (y != yIn && gridInts[xIn][y] == val) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(int val, int xIn, int yIn)
    {
        for (int x = 0; x < 9; x++) {
            if (x != xIn && gridInts[x][yIn] == val) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder a = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                a.append(gridInts[row][col]).append(" ");
                if ((col + 1) % 3 == 0) {
                    a.append(" ");
                }
            }
            a.append("\n");
            if ((row + 1) % 3 == 0) {
                a.append("\n");
            }
        }

        return a.toString();
    }
}
