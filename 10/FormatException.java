import java.util.Scanner;

public class FormatException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hasil =0;
        try{
            System.out.println("isikan bilangan 1:");
            String bil = sc.next();
            System.out.println("isikan bilangan 2:");
            String bil2 = sc.next();

            hasil = Integer.parseInt(bil) + Integer.parseInt(bil2);
        }
        catch(NumberFormatException nf){
            System.out.println("proses format salah!");
        }
        finally{
            System.out.println(hasil);
        }
    }
}
