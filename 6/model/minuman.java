package model;
import interfacekasir.bisaDiskon;
import interfacekasir.bisaStok;
public class minuman extends produk implements bisaStok, bisaDiskon {
	@Override
    public int hitungDiskon() {
        return (harga * qty) * PERSEN_DISKON / 100;
    }

	@Override
    public int hitungTotal() {
        return (harga * qty) - hitungDiskon();
    }

	@Override
    public boolean cekStok(){
        return stok >= qty;
    }

	@Override
    public void kurangiStok(){
        if (cekStok()) {
            stok -= qty;
        }
    }

	@Override
    public void tampil(){
        super.tampil();
        System.out.println("Stok  :" + stok);
        
    }


}
