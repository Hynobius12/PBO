package app;

import model.makanan;
import model.minuman;

public class kasirApp {
    public static void main(String[] args) {
        System.out.println("===== SISTEM KASIR =====");

        makanan makannn = new makanan();
        makannn.nama = "Nasi Goreng";
        makannn.harga = 20000;
        makannn.qty = 2;

        minuman minummm = new minuman();
        minummm.nama = "cincau";
        minummm.harga = 5000;
        minummm.qty = 1;
        minummm.stok = 12;
        int totalMinuman = 0;

        System.out.println("\n--- ITEM 1 ---");
        makannn.tampil();
        System.out.println("Diskon :" + makannn.hitungDiskon());
        System.out.println("Total  :" + makannn.hitungTotal());
        int totalMakanan = makannn.hitungTotal();

        System.out.println("\n--- ITEM 2 ---");
        minummm.tampil();
        if(minummm.cekStok()) {
            System.out.println("Diskon : " + minummm.hitungDiskon());
            System.out.println("Total  : " + minummm.hitungTotal());
            totalMinuman = minummm.hitungTotal();
            minummm.kurangiStok();
            System.out.println("Sisa stok : " + minummm.stok);
        } else {
            System.out.println("Stok tidak cukup");
        }

        int grandTotal = totalMakanan + totalMinuman;
        System.out.println("\n==========================");
        System.out.println("Grand Total : " + grandTotal);
    }
}
