package app.model;

/**
 * Kelas abstrak Person sebagai superclass.
 * Konsep PBO: Class, Object, Abstraction, Encapsulation, Inheritance.
 * Kelas User dan Pelanggan akan mewarisi (extends) kelas ini.
 */
public abstract class Person {
    // Enkapsulasi: atribut private, diakses lewat getter/setter
    private String nama;

    public Person(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method abstrak -> akan di-override berbeda oleh subclass (Polimorfisme)
    public abstract String getDeskripsi();
}
