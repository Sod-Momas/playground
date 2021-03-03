package cc.momas.spring.spel;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class PlaygroundSpringFrameworkSpelApplicationTests {

    /**
     * 连接字符串
     */
    @Test
    public void concat() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String msg = (String) exp.getValue();
        System.out.println(msg);
    }

    /**
     * 用点符号调用方法
     */
    @Test
    public void invokeObjMethod() {
        ExpressionParser parser = new SpelExpressionParser();
        // 调用'getBytes()'方法
        Expression exp = parser.parseExpression("'Hello World'.bytes");
        byte[] msg = (byte[]) exp.getValue();
        System.out.println(new String(msg));
    }


    /**
     * 用点符号嵌套调用
     */
    @Test
    public void methodMethod() {
        String str = "'Hello World'.bytes.length";
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(str);
        Integer len = (Integer) exp.getValue();
        System.out.println(len);
    }


    /**
     * 调用构造函数
     */
    @Test
    public void constructor() {
        String str = "new String('Hello World').toUpperCase()";
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(str);
        String msg = exp.getValue(String.class);
        System.out.println(msg);
    }
}
