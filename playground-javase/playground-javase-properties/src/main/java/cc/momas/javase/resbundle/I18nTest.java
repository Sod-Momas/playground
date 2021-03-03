package cc.momas.javase.resbundle;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 测试i18n
 *
 * @author Sod-Momas
 * @since 2021.03.04
 */
public class I18nTest {

    public static final String CONFIG_BUNDLE_NAME = "i18n/i18n";

    public static final String FIELD_DATE = "Date";
    public static final String FIELD_SALARY = "Salary";

    public static void main(String[] args) {
        ResourceBundle bundle;

        bundle = ResourceBundle.getBundle(CONFIG_BUNDLE_NAME);  // 获取默认配置,JVM会根据系统区域设置读取系统所属的地区
        printBundle(bundle); // 输出
        bundle = ResourceBundle.getBundle(CONFIG_BUNDLE_NAME, Locale.US);  // 获取美国的配置
        printBundle(bundle); // 输出
        bundle = ResourceBundle.getBundle(CONFIG_BUNDLE_NAME, Locale.JAPAN);  // 获取日本的配置
        printBundle(bundle); // 输出
    }

    private static void printBundle(final ResourceBundle bundle) {
        System.out.println("当前地区为:" + bundle.getLocale());
        String date = bundle.getString(FIELD_DATE);
        String salary = bundle.getString(FIELD_SALARY);
//		String noExsist = bundle.getString("noExsist");// 获取一个不存在的值,会抛异常
        System.out.println(date);
        System.out.println(salary);
//		System.out.println(noExsist);
    }

    private static void printAllKeys(final ResourceBundle bundle) {
        Enumeration<String> set = bundle.getKeys();
        while (set.hasMoreElements()) {
            System.out.println(set.nextElement());
        }
    }
}
