import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {

    public static void main(String[] args) {

        String sumber = "students.csv";
        String tujuan = "students_copy.csv";

        try (
                BufferedReader br = new BufferedReader(new FileReader(sumber));
                BufferedWriter bw = new BufferedWriter(new FileWriter(tujuan))) {

            String line;

            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

            System.out.println("File berhasil disalin.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}