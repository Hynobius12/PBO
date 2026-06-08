import java.io.*;

public class BuatFile {

    public static void main(String[] args) {
        try {
            File f = new File("file.txt");
            if (f.createNewFile()) {
                System.out.println("file created :" + f.getName());
            } else{
                System.out.println("file already exist");
            }
        } catch (Exception e){
            System.out.println("an error securred.");
            e.printStackTrace();        
        }
    }
}