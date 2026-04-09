// KODE YANG Benar
public class HandPhoneAfterFix {

    String jenis_hp;
    int tahun_pembuatan;

    public void setDataHP(String jenis_hp, int tahun_pembuatan) {
        this.jenis_hp = jenis_hp;
        this.tahun_pembuatan = tahun_pembuatan;
    }

    String getJenisHP() {
        return jenis_hp;

    }
 
    int getTahunPembuatan() {
        return tahun_pembuatan;
    }

    public static void main(String args[]){
        HandPhoneAfterFix hp = new HandPhoneAfterFix();
        hp.setDataHP("Samsung",2024);
        hp.getJenisHP();
        hp.getTahunPembuatan();
    }

}