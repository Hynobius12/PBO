import java.util.InputMismatchException;
import java.util.Scanner;

public class MathException {
    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("isikan bilangan 1:");
            int num1 = sc.nextInt();
            System.out.print("isikan bilangan 2:");
            int num2 = sc.nextInt();
            int result = num1 / num2;
            System.out.println(result);
        } catch (ArithmeticException ae) {
            System.out.println("tidak boleh pembagian dengan nol");
        } catch (InputMismatchException ime) {
            System.out.println("isikan hanya angka");
        }

        finally {
            System.out.println("Finaly akan selalu dikerjakan");
        }

    }
}