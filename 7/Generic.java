public class Generic {
    static class DataStore<T> {
        private T data;
        
        public void simpan(T data) {
            this.data = data;
        }

        public T ambil() {
            return data;
        }
    }

    public static void main(String[] args) {
        DataStore<String> kodePromo = new DataStore<>();
        kodePromo.simpan("Hemat10");

        DataStore<Integer> poin = new DataStore<>();
        poin.simpan(210);

        System.out.println("Kode Promo Aktif : " + kodePromo.ambil());
        System.out.println("Poin Loyalti : " + poin.ambil());
    }
}
