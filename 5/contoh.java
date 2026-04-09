class Tabungan {
    private double saldo; //tidak dienkapsulasi
    void tambah(double jumlah) {
        saldo += jumlah;
    }

    void ambil(double jumlah) {
        saldo -= jumlah;
    }

    void infosaldo() {
        System.out.println("saldo : " + saldo);
    }
}

public class contoh {
    public static void main(String[] args) {
        Tabungan maul = new Tabungan();
       // maul.saldo = -9000000; 
        maul.tambah(100000);
        maul.infosaldo();
        maul.ambil(5000);
        maul.infosaldo();
    }
}