package cc.momas.jee.webapi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sod-Momas
 * @since 2021-04-04
 */
@WebServlet(urlPatterns = "/cleanCookie")
public class CleanCookieServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getCookies() == null) {
            resp.getWriter().write("no books.<br/> <a href=\"books.jsp\">back<a/> ");
            return;
        }
        for (Cookie cookie : req.getCookies()) {
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
        resp.getWriter().write("clean cookie done <br/> <a href=\"books.jsp\">back<a/> ");
    }
}
