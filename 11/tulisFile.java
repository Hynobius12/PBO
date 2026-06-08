import java.io.FileWriter;
import java.io.IOException;

public class tulisFile {
    public static void main(String[] args) {
        try {
            FileWriter f = new FileWriter("file.txt");
            f.write("belajar pemrograman file handling");
            f.close();
            System.out.println("Proses berhasil !!");
        } catch (IOException e) {
            System.out.println("terjadi kesalahan dalam penulisan file");
            System.out.println(e.getMessage());
        }
    }
}
