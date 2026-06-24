import java.awt.*;
import javax.swing.*;

public class myForm extends JFrame {
    myForm() {
        super("belajar GUI");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        myForm form = new myForm();
    }
    
}