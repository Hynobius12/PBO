import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Class utama
public class biodata extends JFrame {
    private JTextField nimField, namaField, prodiField;
    private JTextArea outputArea;
    private JButton tampilButton, resetButton;

    public biodata() {
        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("NIM:"));
        nimField = new JTextField();
        inputPanel.add(nimField);

        inputPanel.add(new JLabel("Nama:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("Program Studi:"));
        prodiField = new JTextField();
        inputPanel.add(prodiField);

        // Tombol
        JPanel buttonPanel = new JPanel();
        tampilButton = new JButton("Tampilkan");
        resetButton = new JButton("Reset");
        buttonPanel.add(tampilButton);
        buttonPanel.add(resetButton);

        // Output
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Susun ke mainPanel
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);

        // Pasang listener (class terpisah)
        tampilButton.addActionListener(new TampilAction(this));
        resetButton.addActionListener(new ResetAction(this));
    }

    // Getter untuk field
    public String getNIM() { return nimField.getText(); }
    public String getNama() { return namaField.getText(); }
    public String getProdi() { return prodiField.getText(); }
    public void setOutput(String text) { outputArea.setText(text); }
    public void resetFields() {
        nimField.setText("");
        namaField.setText("");
        prodiField.setText("");
        outputArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new biodata().setVisible(true));
    }
}

// Listener tampil
class TampilAction implements ActionListener {
    private biodata form;
    public TampilAction(biodata form) {
        this.form = form;
    }
    public void actionPerformed(ActionEvent e) {
        String output = "=========== BIODATA MAHASISWA ===========\n"
                + "NIM : " + form.getNIM() + "\n"
                + "Nama : " + form.getNama() + "\n"
                + "Program Studi : " + form.getProdi();
        form.setOutput(output);
    }
}

// Listener reset
class ResetAction implements ActionListener {
    private biodata form;
    public ResetAction(biodata form) {
        this.form = form;
    }
    public void actionPerformed(ActionEvent e) {
        form.resetFields();
    }
}