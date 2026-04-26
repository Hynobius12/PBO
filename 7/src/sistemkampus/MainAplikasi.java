package sistemkampus;

import java.util.Scanner;

public class MainAplikasi {
    public static void main(String[] args) {
        System.out.println("--- SISTEM KAMPUS SEDERHANA ---");

        GudangData<MahasiswaReguler> dataMahasiswa = new GudangData<>();

        Scanner sc = new Scanner(System.in);

        MahasiswaReguler mhsDemo = null;
        System.out.println("berapa banyak mahasiswa yang mau ditambahkan?");
        int jumlah = sc.nextInt();
        sc.nextLine(); // menghilangkan buffer

        for (int i = 1; i <= jumlah; i++) {
            System.out.print("Nama : ");
            String nama = sc.nextLine();
            System.out.print("NIM : ");
            String NIM = sc.nextLine();
            System.out.print("IPK : ");
            double IPK = sc.nextDouble();
            sc.nextLine();

            MahasiswaReguler mhs = new MahasiswaReguler(nama, NIM, IPK);
            dataMahasiswa.tambahData(mhs);
        }


        System.out.println("\n=== DAFTAR STATUS SEMUA MAHASISWA ===");

        //  ambil list dari GudangData dan looping satu per satu
        for (MahasiswaReguler mhs : dataMahasiswa.getDaftarData()) {
            System.out.println("--------------------------------");
            // Polymorphism: Menggunakan tipe data Orang untuk memanggil method
            Orang person = mhs;
            person.tampilkanPeran(); // Akan muncul "X adalah mahasiswa reguler"

            // Memanggil method dari Interface
            mhs.cetakStatus(); // Akan muncul "Status: Memuaskan/Perlu Peningkatan"
        }
        System.out.println("\n[Daftar Mahasiswa]");
        dataMahasiswa.tampilkanSemua();


        
    }
}