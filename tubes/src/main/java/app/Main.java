package app;

import app.view.LoginFrame;

import javax.swing.*;

/**
 * Entry point aplikasi.
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // biarkan default look and feel jika gagal
        }
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
