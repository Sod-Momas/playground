package cc.momas.spring.boot.multidatasource.student;

import java.io.Serializable;

/**
 * @author Sod-Momas
 * @since 2021/9/30
 */
public class Student implements Serializable {
    private Long id;
    private String studentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
