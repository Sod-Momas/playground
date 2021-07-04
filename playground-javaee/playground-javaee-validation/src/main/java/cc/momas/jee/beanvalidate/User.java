package cc.momas.jee.beanvalidate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * 测试用的实体
 */
public class User {

    // 可以自定义消息
    @Size(min = 2, max = 4, message = "名字长度不可小于2且不大于4 (验证测试消息)")
    private String name;

    // 不写验证消息的时候会有默认的消息
    @Min(value = 0)
    @Max(value = 100)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
