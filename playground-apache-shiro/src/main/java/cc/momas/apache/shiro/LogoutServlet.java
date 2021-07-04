package cc.momas.apache.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sod-Momas
 * @since 2021/7/4
 */
@WebServlet(urlPatterns = "/account/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        resp.sendRedirect("/login.jsp");
    }
}
