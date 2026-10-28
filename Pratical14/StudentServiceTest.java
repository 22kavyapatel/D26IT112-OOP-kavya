import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    public void testAddStudentIncreasesCount() {
        StudentService service = new StudentService();

        service.addStudent(new Student("Kavya"));

        assertEquals(1, service.getStudentCount());
    }

    @Test
    public void testAddingTwoStudents() {
        StudentService service = new StudentService();

        service.addStudent(new Student("Kavya"));
        service.addStudent(new Student("Rahul"));

        assertEquals(2, service.getStudentCount());
    }

    @Test
    public void testInvalidStudentThrowsException() {
        StudentService service = new StudentService();

        assertThrows(IllegalArgumentException.class,
                () -> service.addStudent(null));
    }
}