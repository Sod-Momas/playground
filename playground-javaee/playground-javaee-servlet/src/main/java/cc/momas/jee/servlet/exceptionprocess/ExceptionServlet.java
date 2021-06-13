package cc.momas.jee.servlet.exceptionprocess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * 测试异常的servlet
 *
 * @author Sod-Momas
 * @since 2021.06.13
 */
@WebServlet(value = {"/exception"})
public class ExceptionServlet extends HttpServlet {
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("ExceptionServlet 初始化完成");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("exceptionType");
        PrintWriter writer = resp.getWriter();
        // 系统异常
        if ("rt".equals(type)) {
            throw new RuntimeException("test runtime");
        }
        // 检验的业务异常
        if ("biz".equals(type)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "没有登录，请登录后再操作");
        }
        // 正常响应
        writer.write(toJson(0, "ok", "data"));
    }

    private String toJson(int code, String msg, String data) {
        // 格式化成 json
        return "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "\",\"data\":\"" + data + "\"}";
    }
}
