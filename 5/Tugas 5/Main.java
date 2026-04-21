import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SIMULASI SISTEM AKADEMIK PBO ===");

        // --- Simulasi Input Dosen ---
        System.out.println("\n[1] Input Data Dosen");
        System.out.print("Masukkan Nama Dosen: ");
        String teacherName = scanner.nextLine();
        System.out.print("Masukkan Alamat Dosen: ");
        String teacherAddress = scanner.nextLine();
        
        Teacher teacher = new Teacher(teacherName, teacherAddress);
        System.out.println(teacher.toString()); // Memanggil toString()

        System.out.print("Masukkan mata kuliah yang diampu Dosen: ");
        String tCourse = scanner.nextLine();
        if(teacher.addCourse(tCourse)) {
            System.out.println("Mata kuliah " + tCourse + " berhasil ditambahkan.");
        } else {
            System.out.println("Gagal! Mata kuliah sudah ada.");
        }

        // --- Simulasi Input Mahasiswa ---
        System.out.println("\n[2] Input Data Mahasiswa");
        System.out.print("Masukkan Nama Mahasiswa: ");
        String studentName = scanner.nextLine();
        System.out.print("Masukkan Alamat Mahasiswa: ");
        String studentAddress = scanner.nextLine();

        Student student = new Student(studentName, studentAddress);
        System.out.println(student.toString()); // Memanggil toString()

        // Input Nilai Mahasiswa
        System.out.print("Berapa banyak mata kuliah yang ingin diinput untuk Mahasiswa ini? ");
        int courseCount = scanner.nextInt();
        scanner.nextLine(); // menghilangkan bug

        for (int i = 1; i <= courseCount; i++) {
            System.out.print("Nama Mata Kuliah ke-" + i + ": ");
            String sCourse = scanner.nextLine();
            System.out.print("Nilai " + sCourse + ": ");
            int sGrade = scanner.nextInt();
            scanner.nextLine(); // menghilangkan bug

            student.addCourseGrade(sCourse, sGrade);
        }

        // --- Output Hasil ---
        System.out.println("\n=== HASIL SIMULASI ===");
        student.printGrades();
        System.out.println("Rata-rata Nilai: " + student.getAverageGrade());

        scanner.close();
    }
}