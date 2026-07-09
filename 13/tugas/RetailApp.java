import java.sql.*;
import java.util.Scanner;

public class RetailApp {
    // Konstanta koneksi
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/toko_ritel";
    static final String USER = "root";
    static final String PASS = "";

    private static Connection conn;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Load driver
            Class.forName(JDBC_DRIVER);

            // Koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            int pilihan;
            do {
                System.out.println("\nTOKO RETAIL MAUL");
                System.out.println("1. Tampil Semua Data");
                System.out.println("2. Tambah Data");
                System.out.println("3. Cari Data");
                System.out.println("4. Ubah Data");
                System.out.println("5. Hapus Data");
                System.out.println("0. Keluar");
                System.out.print("Pilihan : ");
                pilihan = sc.nextInt();
                sc.nextLine();

                switch (pilihan) {
                    case 1:
                        tampilData();
                        break;
                    case 2:
                        tambahData();
                        break;
                    case 3:
                        cariData();
                        break;
                    case 4:
                        ubahData();
                        break;
                    case 5:
                        hapusData();
                        break;
                }
            } while (pilihan != 0);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. Tampil semua data
    private static void tampilData() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM v_barang");
        System.out.println("\nDAFTAR BARANG TOKO RETAIL MAUL");
        System.out.println("#   Kode Barang   Nama Barang     Harga   Stok   Total Nilai");
        int i = 0;
        while (rs.next()) {
            i++;
            System.out.printf("%d   %s   %s   %d   %d   %d\n",
                    i,
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("harga_barang"),
                    rs.getInt("stok_barang"),
                    rs.getInt("total_nilai"));
        }
        System.out.println("Total: " + i + " barang");
    }

    // 2. Tambah data
    private static void tambahData() throws SQLException {
        System.out.print("Kode Barang: ");
        String kode = sc.nextLine();
        System.out.print("Nama Barang: ");
        String nama = sc.nextLine();
        System.out.print("Harga Barang: ");
        int harga = sc.nextInt();
        System.out.print("Stok Barang: ");
        int stok = sc.nextInt();
        sc.nextLine();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO tbl_barang (kode_barang, nama_barang, harga_barang, stok_barang) VALUES (?, ?, ?, ?)");
        ps.setString(1, kode);
        ps.setString(2, nama);
        ps.setInt(3, harga);
        ps.setInt(4, stok);
        ps.executeUpdate();
        System.out.println("Data berhasil ditambahkan.");
    }

    // 3. Cari data
    private static void cariData() throws SQLException {
        System.out.print("Masukkan kode barang: ");
        String kode = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM tbl_barang WHERE kode_barang=?");
        ps.setString(1, kode);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Kode Barang: " + rs.getString("kode_barang"));
            System.out.println("Nama Barang: " + rs.getString("nama_barang"));
            System.out.println("Harga Barang: " + rs.getInt("harga_barang"));
            System.out.println("Stok Barang: " + rs.getInt("stok_barang"));
        } else {
            System.out.println("Data tidak ditemukan.");
        }
    }

    // 4. Ubah data
    private static void ubahData() throws SQLException {
        System.out.print("Masukkan kode barang yang akan diubah: ");
        String kode = sc.nextLine();
        System.out.print("Nama baru: ");
        String nama = sc.nextLine();
        System.out.print("Harga baru: ");
        int harga = sc.nextInt();
        System.out.print("Stok baru: ");
        int stok = sc.nextInt();
        sc.nextLine();

        PreparedStatement ps = conn.prepareStatement(
                "UPDATE tbl_barang SET nama_barang=?, harga_barang=?, stok_barang=? WHERE kode_barang=?");
        ps.setString(1, nama);
        ps.setInt(2, harga);
        ps.setInt(3, stok);
        ps.setString(4, kode);
        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Data berhasil diubah.");
        else
            System.out.println("Data tidak ditemukan.");
    }

    // 5. Hapus data
    private static void hapusData() throws SQLException {
        System.out.print("Masukkan kode barang yang akan dihapus: ");
        String kode = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM tbl_barang WHERE kode_barang=?");
        ps.setString(1, kode);
        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Data berhasil dihapus.");
        else
            System.out.println("Data tidak ditemukan.");
    }
}