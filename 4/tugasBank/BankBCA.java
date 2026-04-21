package tugasBank;

class BankBCA extends Bank {
    @Override
    void sukuBunga() {
        System.out.println("Suku Bunga dari BCA adalah : 4.5%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biayaTf;
        
        // Logika: BCA ke BCA gratis, ke bank lain mungkin lebih mahal atau murah
        if (bankTujuan.equalsIgnoreCase("BCA")) {
            biayaTf = 0;
        } else {
            biayaTf = 7500; // Contoh: biaya transfer BCA ke bank lain
        }

        System.out.println("\n--- Resi Transaksi BCA ---");
        System.out.println("Ke Rekening : " + rekeningTujuan);
        System.out.println("Bank Tujuan : " + bankTujuan);
        System.out.println("Nominal     : Rp" + jumlah);
        System.out.println("Biaya Admin : Rp" + biayaTf);
        System.out.println("Total Bayar : Rp" + (jumlah + biayaTf));
    }
}