import java.text.NumberFormat;
import java.util.Locale;
public class BankAccount {
    private double saldo;
    private String accountNumber;
    private String accountName;

    public BankAccount(double firsSaldo,String accountNumber,String accountName) {
        saldo = firsSaldo;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }

    public void deposit(double amount) {
        this.saldo += amount;
    }

    public void withdraw(double amount) {
        this.saldo -= amount;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getAccountName() {
        return this.accountName;
    }

    @Override
    public String toString() {
        NumberFormat format = NumberFormat.getInstance(new Locale("id","10"));
        return "Account number: " + this.accountNumber + "\nAccount name: " + this.accountName + "\nSaldo: " + format.format(this.saldo);
    }
}
