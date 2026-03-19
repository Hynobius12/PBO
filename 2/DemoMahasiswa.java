
    class Mahasiswa {
        // ====== atribut {proverty} ======
        // "private" artinya hanya bisa diakses dari dalam class ini saja
        private String nama;
        private int umur;
        
        //  ====== Constructor =====
        // Constructor akan otomatis dipanggil saat objek baru dibuat dengan 'new'
        // parameter di dalam constructor digunakan untuk mengisi nilai awal atribut
        Mahasiswa(String nama, int umur) {
            // Kata kunci "this" dipakai untuk membedakan
            // antara atribut class {this.nama} dengan parameter method {namacon}
            this.nama = nama; 
            this.umur = umur; 
        }

        // ====== Getter =====
        // Getter dipakai untuk "membaca"/mengambil nilai atribut 
        public String getNama() {
            return this.nama; //'this.nama' merujuk ke atribut di class
        }

        public int getUmur() {
            return this.umur;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public void setUmur(int umur) {
            if (umur > 0) {
                this.umur = umur;
            }
        }

        public void tampilanInfo() {
            System.out.println("Nama : " + this.nama);
            System.out.println("Umur : " + this.umur + " tahun");
        }

        public class DemoMahasiswa {
            public static void main(String[] args) {
                // === membuat objek ====
                // memanggil construktor mahasiswa(String nama, int umur)
                Mahasiswa mhs1 = new Mahasiswa("Budi",30);
                Mahasiswa mhs2 = new Mahasiswa("siti",20);

                System.out.println("Data Awal");
                System.out.print("Mahasiswa 1: " + mhs1.getNama() + mhs1.getUmur() + " tahun");
                System.out.print("Mahasiswa 2: " + mhs2.getNama() + mhs2.getUmur() + " tahun");

                //===== menggunakan getter untuk membaca nilai =====
                mhs1.setNama("Budi Santoso");
                mhs1.setUmur(32);

                mhs2.setNama( "Santi Rahmawati");
                mhs2.setUmur(28);

                System.out.println("\n setelah diubah");
                mhs1.tampilanInfo();
                mhs2.tampilanInfo();
            }
            
        }
    }
