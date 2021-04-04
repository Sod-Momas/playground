package cc.momas.jee.webapi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于测试 cookie的Servlet
 * 它会把url传来的参数放进cookie里，例如
 * http://localhost/cookie?name=value&key=value&age=13
 * 会新建三个cookie并存储到浏览器，每次访问的时候会打印到控制台
 *
 * @author Sod-Momas
 * @since 2021-03-16
 */
@WebServlet(urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printCookie(req.getCookies());
        final var ps = req.getParameterNames();
        while (ps.hasMoreElements()) {
            final var key = ps.nextElement();
            final var value = req.getParameter(key);
            final var cookie = new Cookie(key, value);
            cookie.setComment("the comment");
            cookie.setDomain(req.getContextPath());
            cookie.setHttpOnly(false);
            cookie.setMaxAge(1000 * 60 * 60);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setVersion(1);

            resp.addCookie(cookie);
        }
    }

    private void printCookie(Cookie[] cookies) {
        if (cookies == null || cookies.length == 0) {
            return;
        }
        System.out.println("cookies: \r\n=======================");
        for (Cookie cookie : cookies) {
            System.out.printf("%s\t%s%n", cookie.getName(), cookie.getValue());
        }
        System.out.println("=======================");
    }
}
