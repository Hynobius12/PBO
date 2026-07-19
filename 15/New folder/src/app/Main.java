package app;

import model.Pelanggan;
import model.Lapangan;
import service.PelangganService;
import service.LapanganService;
import service.ReservasiService;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PelangganService pelangganService = new PelangganService();
    private static final LapanganService lapanganService = new LapanganService();
    private static final ReservasiService reservasiService = new ReservasiService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n=========================================");
                System.out.println("    SISTEM RESERVASI LAPANGAN FUTSAL     ");
                System.out.println("=========================================");
                System.out.println("1. Kelola Pelanggan");
                System.out.println("2. Kelola Lapangan");
                System.out.println("3. Reservasi");
                System.out.println("4. Keluar");
                System.out.println("=========================================");
                System.out.print("Pilih menu [1-4]: ");
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (pilihan) {
                    case 1: menuPelanggan(); break;
                    case 2: menuLapangan(); break;
                    case 3: menuReservasi(); break;
                    case 4:
                        running = false;
                        System.out.println("Terima kasih telah menggunakan sistem reservasi.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid! Silakan masukkan angka 1-4.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan input data! Pastikan tipe inputan benar.");
                scanner.nextLine(); // Clear scanner junk buffer
            }
        }
    }

    private static void menuPelanggan() {
        boolean back = false;
        while (!back) {
            System.out.println("\n[ Kelola Pelanggan ]");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Lihat Data Pelanggan");
            System.out.println("3. Kembali");
            System.out.print("Pilih submenu [1-3]: ");
            int sub = scanner.nextInt();
            scanner.nextLine();

            if (sub == 1) {
                System.out.print("Nama Pelanggan: ");
                String nama = scanner.nextLine();
                System.out.print("Nomor Telepon: ");
                String telp = scanner.nextLine();
                pelangganService.tambahPelanggan(nama, telp);
            } else if (sub == 2) {
                List<Pelanggan> list = pelangganService.getAllPelanggan();
                System.out.println("\n=== DATA PELANGGAN (POLYMORPHISM OUTPUT) ===");
                for (Pelanggan p : list) {
                    p.tampilInfo(); // Menjalankan method overriding Polimorfisme
                }
            } else if (sub == 3) {
                back = true;
            } else {
                System.out.println("Menu salah!");
            }
        }
    }

    private static void menuLapangan() {
        boolean back = false;
        while (!back) {
            System.out.println("\n[ Kelola Lapangan ]");
            System.out.println("1. Tambah Lapangan (via Stored Procedure)");
            System.out.println("2. Lihat Data Lapangan");
            System.out.println("3. Kembali");
            System.out.print("Pilih submenu [1-3]: ");
            int sub = scanner.nextInt();
            scanner.nextLine();

            if (sub == 1) {
                System.out.print("Nama / No Lapangan: ");
                String nama = scanner.nextLine();
                System.out.print("Jenis Lapangan (Matras/Sintetis/Vinyl): ");
                String jenis = scanner.nextLine();
                lapanganService.tambahLapangan(nama, jenis);
            } else if (sub == 2) {
                List<Lapangan> list = lapanganService.getAllLapangan();
                System.out.println("\n=== DAFTAR LAPANGAN ===");
                System.out.printf("%-5s | %-15s | %-15s | %-10s\n", "ID", "Nama Lapangan", "Jenis", "Status");
                System.out.println("--------------------------------------------------------");
                for (Lapangan l : list) {
                    System.out.printf("%-5d | %-15s | %-15s | %-10s\n",
                            l.getIdLapangan(), l.getNamaLapangan(), l.getJenisLapangan(), l.getStatus());
                }
            } else if (sub == 3) {
                back = true;
            } else {
                System.out.println("Menu salah!");
            }
        }
    }

    private static void menuReservasi() {
        boolean back = false;
        while (!back) {
            System.out.println("\n[ Transaksi Reservasi ]");
            System.out.println("1. Buat Reservasi");
            System.out.println("2. Lihat Riwayat Reservasi");
            System.out.println("3. Kembali");
            System.out.print("Pilih submenu [1-3]: ");
            int sub = scanner.nextInt();
            scanner.nextLine();

            if (sub == 1) {
                System.out.print("Masukkan ID Pelanggan: ");
                int idP = scanner.nextInt();
                System.out.print("Masukkan ID Lapangan: ");
                int idL = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Tanggal Sewa (YYYY-MM-DD): ");
                String tgl = scanner.nextLine();
                System.out.print("Jam Sewa (HH:MM:SS): ");
                String jam = scanner.nextLine();
                
                reservasiService.buatReservasi(idP, idL, tgl, jam);
            } else if (sub == 2) {
                reservasiService.lihatRiwayatReservasi();
            } else if (sub == 3) {
                back = true;
            } else {
                System.out.println("Menu salah!");
            }
        }
    }
}