import javax.swing.JOptionPane;


public class tugas1 {
    public static void main(String[] args) {
        // Munculkan kotak input
        String matkul = JOptionPane.showInputDialog("Anda sedang belajar apa?");
        
        // Munculkan kotak pesan berdasarkan input tadi
        JOptionPane.showMessageDialog(null, "Belajar " + matkul + " sangat mudah");
    }
}