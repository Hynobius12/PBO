public class NonGenericBanyakParameter {

    static class TrxData {
        private String kode;
        private Double total;

        public TrxData(String kode, Double total) {
            this.kode = kode;
            this.total = total;
        }

        public String getKode() {
            return kode;
        }

        public Double getTotal() {
            return total;
        }
    }

    static class CustData {
        private String id;
        private Integer poin;

        public CustData(String id, Integer poin) {
            this.id = id;
            this.poin = poin;
        }

        public String getId() {
            return id;
        }

        public Integer getPoin() {
            return poin;
        }
    }

    public static void main(String[] args) {
        TrxData transaksi = new TrxData("TRX-1001", 150000.0);
        CustData pelanggan = new CustData("CUST-01", 320);

        System.out.println("Transaksi : " + transaksi.getKode() + " | Rp" + transaksi.getTotal());
        System.out.println("Pelanggan : " + pelanggan.getId() + " | Poin " + pelanggan.getPoin());
        System.out.println();
    }
}
