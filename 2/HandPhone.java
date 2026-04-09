// KODE YANG SALAH
public class HandPhone {

    String jenis_hp;
    int tahun_pembuatan;

    String public setDataHP(String jenis_hp, int tahun_pembuatan) {
        jenis_hp = jenis_hp; 
        tahun_pembuatan = tahun_pembuatan;
    }

    String getJenisHP() {

    }

 
    String getTahunPembuatan() {
    }

    public static main void(String args[]){
        HandPhone hp = new HandPhone();
        hp.setDataHP(jenis_hp,tahun_pembuatan);
        hp.getJenisHP()
        hp.getTahunPembuatan()
    }

}