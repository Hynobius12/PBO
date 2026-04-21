package tugasBank;

class BankBNI extends Bank {
    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biayaTf;

        // Logika: Kalau ke sesama BNI gratis, kalau beda bank kena 6500
        if (bankTujuan.equalsIgnoreCase("BNI")) {
            biayaTf = 0;
        } else {
            biayaTf = 6500;
        }

        System.out.println("\n--- Resi Transaksi BNI ---");
        System.out.println("Tujuan     : " + bankTujuan + " (" + rekeningTujuan + ")");
        System.out.println("Nominal    : Rp" + jumlah);
        System.out.println("Biaya Admin: Rp" + biayaTf);
        System.out.println("Total      : Rp" + (jumlah + biayaTf));
        System.out.println("--------------------------");
    }
}