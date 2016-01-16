import DAO.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String loginField = req.getParameter("loginField");
        String passField = req.getParameter("passField");

        if (DBUtils.validate(loginField, passField)){
            System.out.println("ok");
        }else {
            System.out.println("ne ok");
        }


    }

}
