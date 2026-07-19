package app.model;

/**
 * Kelas User merepresentasikan pengguna aplikasi (admin/kasir).
 * Inheritance: User extends Person.
 */
public class User extends Person {
    private int idUser;
    private String username;
    private String password;
    private String role;

    public User(String nama) {
        super(nama);
    }

    public User(int idUser, String username, String password, String nama, String role) {
        super(nama);
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Polimorfisme: override method abstrak dari Person
    @Override
    public String getDeskripsi() {
        return "User: " + getNama() + " (" + role + ")";
    }
}
