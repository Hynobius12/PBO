import java.awt.*;
import javax.swing.*;

public class myForm3 extends JFrame {
    JButton b1 = new JButton("Tombol 1");
    JButton b2 = new JButton("Tombol 2");
    JButton b3 = new JButton("Tombol 3");
    JButton b4 = new JButton("Tombol 4");
    JButton b5 = new JButton("Tombol 5");

    myForm3() {
        super("belajar GUI");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400,400));
        getContentPane().setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        BorderLayout BL = new BorderLayout(5,5);
        setLayout(BL);
        add("north",b1);
        add("soul",b2);
        add("west",b3);
        add("east",b4);
        add("center",b5);
        setVisible(true);
    }

    public static void main(String[] args) {
        myForm3 form = new myForm3();
    }
}
