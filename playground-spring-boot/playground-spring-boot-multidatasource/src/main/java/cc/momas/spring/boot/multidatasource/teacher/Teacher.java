package cc.momas.spring.boot.multidatasource.teacher;

import java.io.Serializable;

/**
 * @author Sod-Momas
 * @since 2021/9/30
 */
public class Teacher implements Serializable {
    private Long id;
    private String teacherNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherNumber='" + teacherNumber + '\'' +
                '}';
    }
}
