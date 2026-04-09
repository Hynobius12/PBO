package model;

import interfacekasir.bisaDiskon;

public class makanan extends produk implements bisaDiskon {
    @Override
    public int hitungDiskon(){
        return (harga * qty) * PERSEN_DISKON / 100;
    }
    
    @Override
    public int hitungTotal(){
        return (harga * qty) - hitungDiskon();
    }

}