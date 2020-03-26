/**
 * First attempt at Solving the Sudoku. There's still some bug somewhere.
 *
 * @author Marcos Traverso
 */
public class OriginalSolver
{
    public Cell[][] grid = new Cell[9][9];

    OriginalSolver(Integer[][] vals)
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

    public OriginalSolver solveGrid()
    {
        Coordinate currentCoord = new Coordinate(0, 0);
        Cell currentCell = grid[currentCoord.getX()][currentCoord.getY()];
        boolean didBacktrack = false;

        while (currentCoord.getY() != 9) {
            if (currentCell.isModifiable()) {
                //check values & advance/backtrack
                if (check(currentCoord)) {
                    currentCoord = currentCoord.getForward();
                    currentCell = grid[currentCoord.getX()][currentCoord.getY()];
//                    System.out.println(currentCoord + ", " + currentCell.value);
                    didBacktrack = false;
                }
                else {
                    while ((currentCell.getValue() != 10) && !check(currentCoord)) {
                        currentCell = currentCell.getNextValue();
                        grid[currentCoord.getX()][currentCoord.getY()] = currentCell;
                        if (currentCell.getValue() == 10) {
                            grid[currentCoord.getX()][currentCoord.getY()] = new Cell();
                            currentCoord = currentCoord.getBackward();
                            currentCell = grid[currentCoord.getX()][currentCoord.getY()];
                            currentCell = currentCell.getNextValue();
//                            System.out.println(currentCoord + ", " + currentCell.value);
                            didBacktrack = true;
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
        int startX = coord.getX() - coord.getX() % 3;
        int startY = coord.getY() - coord.getY() % 3;

        for (int x = startX; x < startX + 3; x++) {
            for (int y = startY; y < startY + 3; y++) {
                if (x != coord.getX() && y != coord.getY() && grid[x][y].getValue() == grid[coord.getX()][coord.getY()].getValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkVertical(Coordinate coord)
    {
        for (int y = 0; y < 9; y++) {
            if (y != coord.getY() && grid[coord.getX()][y].getValue() == grid[coord.getX()][coord.getY()].getValue()) {
                return false;
            }
        }

        return true;
    }

    private boolean checkHorizontal(Coordinate coord)
    {
        for (int x = 0; x < 9; x++) {
            if (x != coord.getX() && grid[x][coord.getY()].getValue() == grid[coord.getX()][coord.getY()].getValue()) {
                return false;
            }
        }

        return true;
    }
}