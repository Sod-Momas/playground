package cc.momas.jee.beanvalidate;

public class AppMain {

    public static void main(String[] args) {
        User user = new User();
        user.setAge(120);
        user.setName("Sod-Momas");

        // 验证
        ValidationUtil.validate(user);
    }
}
