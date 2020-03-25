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

public class SudokuFrame
        extends JFrame
{
    public static final int GRID_SIZE = 9;
    public static final int CELL_SIZE = 50;
    public static final int CANVAS_SIDE = CELL_SIZE * GRID_SIZE;
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, CELL_SIZE / 2);

    JTextField[][] textFields = new JTextField[GRID_SIZE][GRID_SIZE];
    public static final JButton submit = new JButton("Submit");

    public SudokuFrame()
    {

        JPanel northPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int row = 0; row < GRID_SIZE; ++row) {
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
        pane.add(northPanel);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        submit.addActionListener(a -> submitAction());
        southPanel.add(submit);
        pane.add(southPanel);

        getContentPane().add(pane);
        pane.setPreferredSize(new Dimension(CANVAS_SIDE, CANVAS_SIDE));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku Solver");
        setVisible(true);
    }

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
        RecursiveSolver solvedGrid = new RecursiveSolver(array);

        fillFields(solvedGrid.getSolved());
    }

    private void fillFields(int[][] finishedSudoku)
    {
        for (int row = 0; row < GRID_SIZE; ++row) {
            for (int col = 0; col < GRID_SIZE; ++col) {
                textFields[row][col].setText(Integer.toString(finishedSudoku[row][col]));
            }
        }
    }
}
