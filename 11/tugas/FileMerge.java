import java.io.*;

public class FileMerge {
    public static void main(String[] args) {
        // Disesuaikan dengan nama file di Drive D kamu (text1 dan text2)
        String[] files = {"text1.txt", "text2.txt"};
        String mergedFile = "merge.txt";
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(mergedFile))) {
            for (String file : files) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                        bw.newLine();
                    }
                    System.out.println("Berhasil membaca dan menggabungkan: " + file);
                } catch (IOException e) {
                    System.out.println("Gagal membaca file: " + file);
                    e.printStackTrace();
                }
            }
            System.out.println("Proses selesai! Silakan cek D:\\merge.txt");
        } catch (IOException e) {
            System.out.println("Gagal membuat atau menulis ke file merge.txt. Pastikan Drive D tidak diproteksi/di-lock.");
            e.printStackTrace();
        }
    }
}