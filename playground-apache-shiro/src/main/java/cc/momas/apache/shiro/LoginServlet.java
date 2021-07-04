package cc.momas.apache.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@WebServlet(urlPatterns = "/account/login")
public class LoginServlet extends HttpServlet {

    private Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        if ("sod".equals(username) && "123456".equals(password)) {

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {

            currentUser.login(token);
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            resp.setContentType("text/javascript");
            resp.getWriter().write("alert(" + e.getLocalizedMessage() + ")");
            return;
        }

            resp.sendRedirect("/index.jsp");
//            return;return
//        }return
//        req.getRequestDispatcher("/login.jsp").forward(req, resp);
//        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        resp.sendRedirect("/login.jsp?msg=username_or_incorrect.");
    }
}
