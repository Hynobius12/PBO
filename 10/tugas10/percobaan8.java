import java.io.*;

public class percobaan8 {
    public void methodA() {
        System.out.println("Method A");
    }

    public static void main(String[] args) {
        System.out.println(20 / 0);
        System.out.println("Method B");
    }
}

class Utama {
    public static void main(String[] args) {
        percobaan8 o = new percobaan8();
        o.methodA();

        try {
            o.methodB();
        } catch (Exception e) {
            // Menangkap error yang terjadi di methodB
            System.out.println("Error di Method B");
        } finally {
            System.out.println("Ini selalu dicetak");
        }
    }
}
