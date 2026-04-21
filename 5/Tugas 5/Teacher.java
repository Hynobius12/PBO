public class Teacher extends Person {
    private int numCourses;
    private String[] courses;
    private static final int MAX_COURSES = 10; // Batas maksimal mata kuliah dosen

    // Constructor
    public Teacher(String name, String address) {
        super(name, address);
        numCourses = 0;
        courses = new String[MAX_COURSES];
    }

    // Menambahkan course (return false jika course sudah ada)
    public boolean addCourse(String course) {
        // Cek apakah course sudah ada
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                return false; 
            }
        }
        // Tambahkan jika belum ada dan kapasitas belum penuh
        if (numCourses < MAX_COURSES) {
            courses[numCourses] = course;
            numCourses++;
            return true;
        }
        return false;
    }

    // Menghapus course (return false jika course tidak ditemukan)
    public boolean removeCourse(String course) {
        int courseIndex = -1;
        
        // Cari index course yang akan dihapus
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                courseIndex = i;
                break;
            }
        }
        
        // Jika ditemukan, geser elemen array ke kiri untuk menutup celah
        if (courseIndex != -1) {
            for (int i = courseIndex; i < numCourses - 1; i++) {
                courses[i] = courses[i + 1];
            }
            courses[numCourses - 1] = null; // Bersihkan elemen terakhir
            numCourses--;
            return true;
        }
        return false;
    }

    // toString method
    @Override
    public String toString() {
        return "Teacher: " + super.toString();
    }
}