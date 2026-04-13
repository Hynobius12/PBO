public class GenericBanyakParameter {
    static class  PairData<K,V> {
        private K key;
        private V val;

        public PairData(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getVal() {
            return val;
        }
        
    }

    public static void main(String[] args) {
        PairData<String, Double> transaksi = new PairData<>("TRX-1001", 150000.0);
        PairData<String, Integer> pelanggan = new PairData<>("CUST-01", 320);

        System.out.println("=== Generic Banyak Parameter ===");
        System.out.println("Transaksi :" + transaksi.getKey() + " | Rp " + transaksi.getVal());
        System.out.println("Pelanggan :" + pelanggan.getKey() + " | Poin " + pelanggan.getVal());
    }
}
