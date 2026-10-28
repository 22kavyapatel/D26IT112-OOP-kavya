import java.util.ArrayList;

public class StudentService {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student == null || student.getName() == null
                || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student");
        }

        students.add(student);
    }

    public int getStudentCount() {
        return students.size();
    }
}