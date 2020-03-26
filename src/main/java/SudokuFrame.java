import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * This class generates the JFrame window. It creates a grid of TextFields and two buttons.
 * The maze is solved once the submit button is pressed, triggering submitAction()
 *
 * @author Marcos Traverso
 */
public class SudokuFrame
        extends JFrame
{
    public static final int GRID_SIZE = 9;
    private static final int CELL_SIZE = 50;
    private static final int CANVAS_SIDE = CELL_SIZE * GRID_SIZE;
    private static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, CELL_SIZE / 2);

    private JTextField[][] textFields = new JTextField[GRID_SIZE][GRID_SIZE];
    private static final JButton submit = new JButton("Submit");
    private static final JButton reset = new JButton("Reset");

    public SudokuFrame()
    {

        JPanel northPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int row = 0; row < GRID_SIZE; ++row) {     //generates a 3x3 array of TextFields
            for (int col = 0; col < GRID_SIZE; ++col) {
                textFields[row][col] = new JTextField();
                textFields[row][col].setHorizontalAlignment(JTextField.CENTER);
                textFields[row][col].setFont(FONT_NUMBERS);
                textFields[row][col].setBackground(col / 3 % 2 != row / 3 % 2 ? Color.white : Color.LIGHT_GRAY); //generates a 3x3 checkered pattern

                northPanel.add(textFields[row][col]);
            }
        }
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(northPanel);   //northPanel for TextFields grid

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));  //southPanel for buttons
        submit.addActionListener(a -> submitAction());
        reset.addActionListener(a -> reset());
        southPanel.add(submit);
        southPanel.add(reset);
        pane.add(southPanel);

        getContentPane().add(pane);
        pane.setPreferredSize(new Dimension(CANVAS_SIDE, CANVAS_SIDE));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku Solver");
        setVisible(true);
    }

    /**
     * Reads the TextFields and calls the solver with them.
     */
    private void submitAction()
    {
        Integer[][] array = new Integer[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (!textFields[row][col].getText().equals("")) {
                    array[row][col] = Integer.parseInt(textFields[row][col].getText());
                }
            }
        }
        RecursiveSolver solvedGrid = new RecursiveSolver(array);    //uses RecursiveSolver, not OriginalSolver

        fillFields(solvedGrid.getSolved());
    }

    /**
     * Sets the text within the TextFields as empty and sets them as modifiable
     */
    private void reset()
    {
        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                textFields[row][col].setText("");
                textFields[row][col].setEditable(true);
            }
        }
    }

    /**
     * Fills the text within the TextFields with the solution and sets them as not modifiable.
     *
     * @param finishedSudoku Solved sudoku puzzle as an array.
     */
    private void fillFields(int[][] finishedSudoku)
    {
        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                textFields[row][col].setText(Integer.toString(finishedSudoku[row][col]));
                textFields[row][col].setEditable(false);
            }
        }
    }
}
