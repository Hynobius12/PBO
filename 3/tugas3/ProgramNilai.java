package tugas3;

import java.util.ArrayList;
import java.util.Scanner;

public class ProgramNilai {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<mahasiswa> listMahasiswa = new ArrayList<>();
        String lanjut;

        do {
            System.out.println("NIM : ");
            String nim = input.nextLine();
            System.out.println("Nama : ");
            String nama = input.nextLine();
            System.out.println("Nilai : ");
            int nilai = input.nextInt();
            input.nextLine(); // untuk membersihkan buffer

            if (nilai < 0 || nilai > 100) {
                System.out.println("Input nilai anda salah");
            } else {
                listMahasiswa.add(new mahasiswa(nim, nama, nilai));
            }

            System.out.println("Tambah data lagi? (y/n) ");
            lanjut = input.nextLine();
            System.out.println("=============================================");
        } while (lanjut.equalsIgnoreCase("y"));

        // Menampilkan Data Per Mahasiswa
        for (mahasiswa m : listMahasiswa) {
            System.out.println("NIM   : " + m.nim);
            System.out.println("Nama  : " + m.nama);
            System.out.println("Nilai : " + m.nilai);
            System.out.println("Grade : " + m.grade);
            System.out.println("=========================================");
        }

        int totalNilai = 0;
        int lulus = 0, tdkLulus = 0;
        int gradeA = 0, gradeB = 0, gradeD = 0;
        StringBuilder namaLulus = new StringBuilder();
        StringBuilder namaTdkLulus = new StringBuilder();
        StringBuilder namaA = new StringBuilder();
        StringBuilder namaB = new StringBuilder();
        StringBuilder namaD = new StringBuilder();

        for (mahasiswa m : listMahasiswa) {
            totalNilai += m.nilai;
            if (m.status.equals("Lulus")) {
                lulus++;
                namaLulus.append(m.nama).append(", ");
            } else {
                tdkLulus++;
                namaTdkLulus.append(m.nama).append(", ");
            }

            if (m.grade.equals("A")) {
                gradeA++;
                namaA.append(m.nama).append(", ");
            } else if (m.grade.equals("B")) {
                gradeB++;
                namaB.append(m.nama).append(", ");
            } else if (m.grade.equals("D")) {
                gradeD++;
                namaD.append(m.nama).append(", ");
            }
        }

        System.out.println("Jumlah Mahasiswa : " + listMahasiswa.size());
        System.out.println("Jumlah Mahasiswa yg Lulus : " + lulus + " yaitu " + rapihkan(namaLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tdkLulus + " yaitu " + rapihkan(namaTdkLulus));
        System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeA + " yaitu " + rapihkan(namaA));
        System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeB + " yaitu " + rapihkan(namaB));
        System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeD + " yaitu " + rapihkan(namaD));
    }
    private static String rapihkan(StringBuilder sb) {
        String s = sb.toString();
        return s.isEmpty() ? "-" : s.substring(0, s.length() - 2);
    }
}
