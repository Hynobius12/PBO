public class Main {
    public static void main(String[] args) {
        BankAccount maul = new BankAccount(1000000,"8080123","Maulana");
        maul.deposit(14000);
        maul.withdraw(5000);
        System.out.println(maul);
    }
}
