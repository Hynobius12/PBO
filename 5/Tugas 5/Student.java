public class Student extends Person {
    private int numCourses;
    private String[] courses;
    private int[] grades;
    private static final int MAX_COURSES = 30; // Batas maksimal mata kuliah

    // Constructor
    public Student(String name, String address) {
        super(name, address);
        numCourses = 0;
        courses = new String[MAX_COURSES];
        grades = new int[MAX_COURSES];
    }

    // Menambahkan course dan nilai
    public void addCourseGrade(String course, int grade) {
        if (numCourses < MAX_COURSES) {
            courses[numCourses] = course;
            grades[numCourses] = grade;
            numCourses++;
        } else {
            System.out.println("Kapasitas mata kuliah sudah penuh.");
        }
    }

    // Menampilkan semua nilai
    public void printGrades() {
        System.out.print(this.toString());
        for (int i = 0; i < numCourses; i++) {
            System.out.print(" " + courses[i] + ":" + grades[i]);
        }
        System.out.println();
    }

    // Menghitung rata-rata nilai
    public double getAverageGrade() {
        if (numCourses == 0) return 0.0;
        int sum = 0;
        for (int i = 0; i < numCourses; i++) {
            sum += grades[i];
        }
        return (double) sum / numCourses;
    }

    // toString method
    @Override
    public String toString() {
        return "Student: " + super.toString();
    }
}