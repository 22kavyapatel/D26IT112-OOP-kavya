import java.io.*;

class Student implements Serializable {
    int rollNo;
    String name;
    transient String password;

    Student(int rollNo, String name, String password) {
        this.rollNo = rollNo;
        this.name = name;
        this.password = password;
    }

    void display() {
        System.out.println(rollNo + " " + name + " " + password);
    }
}

public class SerializableDemo {
    public static void main(String[] args) {

        Student[] students = {
            new Student(1, "Kavya", "abc123"),
            new Student(2, "Rahul", "xyz456")
        };

        try {
            ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream("students.dat"));

            out.writeObject(students);
            out.close();

            ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("students.dat"));

            Student[] loaded =
                (Student[]) in.readObject();

            in.close();

            for (Student s : loaded)
                s.display();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}