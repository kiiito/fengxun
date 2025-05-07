import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;


@WebServlet({"/login"})
public class servlet01 extends HttpServlet{
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        // 获取用户输入的信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(!validateUsername(username)) {
            out.println("<p>用户名需包含字母和数字，长度不小于8位</p>");
            out.println("<meta http-equiv='refresh' content='3;url=/ob/login.jsp'>");
//            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }else if(!validatePassword(password)) {
            out.println("<p>密码需是6位以上的数字</p>");
            out.println("<meta http-equiv='refresh' content='3;url=/ob/login.jsp'>");
           // resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }else{
            out.println("登录成功，3秒钟后跳转到系统页面");

             out.println("<meta http-equiv='refresh' content='3;url=/ob/welcome.jsp '>");
            //resp.sendRedirect(req.getContextPath()+"/welcome.jsp");
        }
    }

    public static boolean validateUsername(String username) {
        // 检查用户名是否包含字母和数字
        if (!Pattern.matches("^[a-zA-Z0-9]+$", username)) {
            return false;
        }
        // 检查用户名长度是否不小于 8 位
        if (username.length() < 8) {
            return false;
        }
        return true;
    }

    public static boolean validatePassword(String password) {
        // 检查密码是否是 6 位以上的数字
        return Pattern.matches("^\\d{6,}$", password   );
    }

}
