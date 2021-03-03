package cc.momas.javase.reflect;

/**
 * @author Sod-Momas
 * @since 2021-02-27
 */
import java.util.Date;

public class User {

    private int age;
    private String name;
    private Date birthday;

    /**
     * 无参构造器
     */
    public User() {
    }

    /**
     * 全参构造器
     */
    public User(int age, String name, Date birthday) {
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
