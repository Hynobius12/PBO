import java.io.File;

public class infoFile {
    public static void main(String[] args) {
        File f = new File("File.txt");

        if (f.exists()) {
            System.out.println("File name :" + f.getName());
            System.out.println("Absolute Path :" + f.getAbsolutePath());
            System.out.println("Writable :" + f.canWrite());
            System.out.println("Readable :" + f.canRead());
            System.out.println("File size :" + f.length());
        } else {
            System.out.println("file tidak ada");
        }
    }
}
