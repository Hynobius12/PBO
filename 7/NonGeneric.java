public class NonGeneric {
    // hanya bisa untuk String.
    static class PromoStore {
        private String data;

        public void simpan(String data) {
            this.data = data;
        }

        public String ambil() {
            return data;
        }
    }

    //  hanya bisa untuk Integer.
    static class PoinStore {
        private Integer data;

        public void simpan(Integer data) {
            this.data = data;
        }

        public Integer ambil() {
            return data;
        }
    }

    public static void main(String[] args) {
        PromoStore kodePromo = new PromoStore();
        kodePromo.simpan("HEMAT10");

        PoinStore poin = new PoinStore();
        poin.simpan(250);

        System.out.println("Kode Promo Aktif : " + kodePromo.ambil());
        System.out.println("Poin Loyalti     : " + poin.ambil());
    }
}
