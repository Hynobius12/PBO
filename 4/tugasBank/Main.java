package tugasBank;

import java.util.Scanner;

import java.util.Scanner;

public class Main {
    // SEMUA KODE HARUS MASUK KE DALAM METHOD MAIN INI
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Pilih Bank Anda (1. BNI, 2. BCA): ");
        int pilihan = input.nextInt();
        input.nextLine(); // Hapus buffer

        if (pilihan == 1) {
            BankBNI bni = new BankBNI();
            System.out.print("Masukkan Jumlah: ");
            int jml = input.nextInt();
            input.nextLine();
            System.out.print("Masukkan Rekening: ");
            String rek = input.nextLine();
            System.out.print("Masukkan Bank Tujuan: ");
            String tujuan = input.nextLine();
            
            bni.transferUang(jml, rek, tujuan);
            
        } else if (pilihan == 2) {
            BankBCA bca = new BankBCA();
            System.out.print("Masukkan Jumlah: ");
            int jml = input.nextInt();
            input.nextLine();
            System.out.print("Masukkan Rekening: ");
            String rek = input.nextLine();
            System.out.print("Masukkan Bank Tujuan: ");
            String tujuan = input.nextLine();
            
            bca.transferUang(jml, rek, tujuan);
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    } // Penutup method main
} // Penutup class Main
    // public static void main(String[] args) {

    //     Scanner input = new Scanner(System.in);

    //     Bank bankUmum = new Bank();
    //     BankBNI bni = new BankBNI();
    //     BankBCA bca = new BankBCA();

    //     System.out.println("--- Uji Method Overloading ---");
    //     bankUmum.transferUang(500000, "12345678");
    //     bankUmum.transferUang(1000000, "87654321", "Mandiri");
    //     bankUmum.transferUang(200000, "11223344", "BRI", "Bayar Bakso");

    //     System.out.println("\n--- Uji Method Overriding ---");
    //     bni.sukuBunga();
    //     bni.transferUang(750000, "998877", "");

    //     bca.sukuBunga();
    //     bca.transferUang(300000, "556677", "");



    //     //cekcek
    //     System.out.println("\n--- ini transaksi asli ---");
    //     System.out.println("jumlah :");
    //     int jumlah = input.nextInt();
    //     input.nextLine();
    //     System.out.println("rekeningTujuan :");
    //     String rekeningTujuan = input.nextLine();
    //     System.out.println("Bank Tujuan (BNI/BCA/Umum):");
    //     String bankTujuan = input.nextLine();
    //     System.out.println("Bank Tujuan (BNI/BCA/Umum):");
    //     String bankTujuan = input.nextLine();
    //     if(bankTujuan.equals("BNI")) {
    //         bni.transferUang(jumlah, rekeningTujuan, bankTujuan);
    //         System.out.println("anda melakukan tf sebesar " + jumlah + " kepada rekening " + rekeningTujuan + " dan biaya tf sebesar "  );
    //     } else if(bankTujuan.equals("BCA")) {
    //         System.out.println();
    //     }
    //     // System.out.println("");
    //     // System.out.println("");

    // }
