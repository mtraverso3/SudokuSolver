public class MainClass
{
    public static void main(String[] args)
    {
        SudokuFrame sudoku = new SudokuFrame();
        SudokuGrid startGrid = sudoku.getSudokuGrid();

        //option 1: have program wait until user submits to continue here.
        //option 2: automatically call solve the moment submit is pressed.
    }
}
