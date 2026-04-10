package tugas3;

public class mahasiswa {
    String nim;
    String nama;
    int nilai;
    String grade;
    String status;

    public mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        setGradeAndStatus(nilai);
    }

    private void setGradeAndStatus(int nilai) {
        if (nilai >= 80 && nilai <= 100) {
            grade = "A";
            status = "Lulus";
        } else if (nilai >= 70) {
            grade = "B";
            status = "Lulus";
        } else if (nilai >= 60) {
            grade = "C";
            status = "Tidak Lulus";
        } else if (nilai >= 50) {
            grade = "D";
            status = "Tidak Lulus";
        } else  {
            grade = "E";
            status = "Tidak Lulus";
        } 
    }
}
