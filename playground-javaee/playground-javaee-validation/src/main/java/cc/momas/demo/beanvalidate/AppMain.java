package cc.momas.demo.beanvalidate;

public class AppMain {

    public static void main(String[] args) {
        User user = new User();
        user.setAge(120);
        user.setName("aaa");

        try {
            // 验证
            ValidationUtil.validate(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
