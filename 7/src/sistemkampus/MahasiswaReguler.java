package sistemkampus;

public class MahasiswaReguler extends Orang implements Evaluasi {
    private String nim;
    private double ipk;

    public MahasiswaReguler(String nama, String nim, double ipk) {
        super(nama);
        this.nim = nim;
        this.ipk = ipk;
    }

    @Override
    public void tampilkanPeran() {
        System.out.println(getNama() + " adalah seorang Mahasiswa Reguler.");
    }

    @Override
    public void cetakStatus() {
        if (ipk >= 3.0) {
            System.out.println("Status: Memuaskan");
        } else {
            System.out.println("Status: Perlu Peningkatan");
        }
    }

    @Override
    public String toString() {
        return "NIM: " + nim + " | Nama: " + getNama() + " | IPK: " + ipk;
    }
}