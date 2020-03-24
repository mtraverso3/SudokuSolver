import javax.swing.*;

import java.awt.FlowLayout;

public class GridLayoutDemo
        extends JFrame
{
    private String a;
    JTextField a1 = new JTextField(100);
    JButton submit = new JButton("Submit");

    GridLayoutDemo()
    {
        super("Sudoku Solver");

        setLayout(new FlowLayout());
        submit.addActionListener(a -> submitAction());

        add(a1);
        add(submit);
        setSize(400, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void submitAction(){
        a = a1.getText();

    }
}