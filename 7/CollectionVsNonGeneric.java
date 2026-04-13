import java.util.ArrayList;
import java.util.List;

public class CollectionVsNonGeneric {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        System.out.println("=== TANPA GENERIC (PAKAI CASTING) ===");
        List dataCampur = new ArrayList();
        dataCampur.add("Beras 5kg");
        dataCampur.add(100); // tipe campur: String + Integer

        System.out.println("Barang 1: " + (String) dataCampur.get(0));
        System.out.println("Barang 2: " + (int) dataCampur.get(1));
        System.out.println("Semua Barang: " + dataCampur);
        System.out.println();

        System.out.println("=== DENGAN GENERIC (TANPA CASTING) ===");
        List<String> dataBarang = new ArrayList<>();
        dataBarang.add("Beras 5kg");
        dataBarang.add("Minyak 2L");
        // dataBarang.add(100); // error compile-time: bukan String

        System.out.println("Barang 1: " + dataBarang.get(0));
        System.out.println("Barang 2: " + dataBarang.get(1));
        System.out.println("Semua: " + dataBarang);
        System.out.println();

    }
}
