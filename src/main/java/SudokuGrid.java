import java.util.Arrays;

public class SudokuGrid
{
    public Cell[][] grid = new Cell[9][9];

    SudokuGrid(Integer[][] vals)
    {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (vals[row][col] == null) {
                    this.grid[row][col] = new Cell();   //if it has no value uses this
                }
                else {
                    this.grid[row][col] = new Cell(vals[row][col], false);  //if it has a value sets that cell as non-modifiable
                }
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder a = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col].isModifiable()) {
                    a.append(1).append(" ");
                }
                else {
                    a.append(0).append(" ");
                }
            }
            a.append("\n");
        }

        return a.toString();
    }

    public SudokuGrid solveGrid()
    {

        Coordinate currentCoord = new Coordinate(0, 0);
        Cell currentCell = grid[currentCoord.getX()][currentCoord.getY()];
        boolean didBacktrack = false;

        while (currentCoord.getY() != 9) {
            System.out.println("a");
            if (currentCell.isModifiable()) {
                //check values & advance/backtrack
                if (check(currentCoord)) {
                    currentCoord = currentCoord.getForward();
                    currentCell = grid[currentCoord.getX()][currentCoord.getY()];
                }
                else {
                    while((currentCell.value != 10) && !check(currentCoord)) {
                            currentCell = currentCell.getNextValue();
                        if (currentCell.value == 10) {
                            currentCell = new Cell();
                            currentCoord = currentCoord.getBackward();
                            currentCell = grid[currentCoord.getX()][currentCoord.getY()];
                        }
                    }
                }
            }
            else {
                //moves forward if last movement was forward, otherwise moves backward
                currentCoord = didBacktrack ? currentCoord.getBackward() : currentCoord.getForward();
                currentCell = grid[currentCoord.getX()][currentCoord.getY()];
            }
        }
        return this;
    }

    private boolean check(Coordinate c)
    {
        return (grid[c.getX()][c.getY()].getValue() != 0) && checkBox(c) && checkVertical(c) && checkHorizontal(c);
    }

    private boolean checkBox(Coordinate coord)
    {
        boolean repeated = false;

        for (int x = coord.getX() / 3; x < coord.getX() / 3 + 3 && !repeated; x++) {
            if (x == coord.getX()) {x++;}

            for (int y = coord.getY() / 3; y < coord.getY() / 3 + 3 && !repeated; y++) {
                if (y == coord.getY()) {y++;}
                repeated = grid[x][y].getValue() == grid[coord.getX()][coord.getY()].value;
            }
        }
        return repeated;
    }

    private boolean checkVertical(Coordinate coord)
    {
        int x = coord.getX();
        boolean repeated = false;
        for (int y = 0; y < 9 && !repeated; y++) {
            if (y == coord.getY()) {y++;}
            repeated = grid[x][y].value == grid[x][coord.getY()].getValue();
        }
        return !repeated;
    }

    private boolean checkHorizontal(Coordinate coord)
    {
        int y = coord.getY();
        boolean repeated = false;
        for (int x = 0; x < 9 && !repeated; x++) {
            if (x == coord.getX()) {x++;}
            repeated = grid[x][y].value == grid[coord.getX()][y].getValue();
        }
        return !repeated;
    }
}
