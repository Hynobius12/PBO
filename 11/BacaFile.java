import java.io.*;
import java.util.Scanner;

public class BacaFile {
    public static void main(String[] args) {
        try {
            File f = new File("file.txt");
            Scanner r = new Scanner(f);
            while (r.hasNextLine()) {
                String data = r.nextLine();
                System.out.println(data);
            }
            r.close();
        } catch (FileNotFoundException e) {
            System.out.println("terjadi kesalahan: file tidak ditemukan");
            //e.printStackTrace();
        }
    }        
}
