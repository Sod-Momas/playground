/**
 * @author Sod-Momas
 * @since 2021-02-21
 */
public class Student {
    public final int age;
    public final String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
