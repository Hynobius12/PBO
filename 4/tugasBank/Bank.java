package tugasBank;

class Bank {
    void sukuBunga() {
        System.out.println("Suku Bunga standar Bank Umum adalah : 2%");
    }

    // Overloading 1
    void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer Umum: Rp" + jumlah + " ke " + rekeningTujuan);
    }

    // Overloading 2 (Ini yang sering di-override)
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biayaStandar = 6500;
        System.out.println("Transfer Antar Bank: Rp" + jumlah + " ke " + bankTujuan);
        System.out.println("Biaya Admin Standar: Rp" + biayaStandar);
    }
}