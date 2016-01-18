import DAO.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginField = req.getParameter("loginField");
        String passField = req.getParameter("passField");

        if (DBUtils.validate(loginField, passField)){
            HttpSession session = req.getSession(true);
            switch (DBUtils.getRole(loginField)){
                case "client":{
                    session.setAttribute("role","client");
                    req.getRequestDispatcher("jsp/client.jsp").forward(req,resp);
                    break;
                }case "manager":{
                    session.setAttribute("role","manager");
                    req.getRequestDispatcher("jsp/manager.jsp").forward(req,resp);
                    break;
                }case "admin":{
                    session.setAttribute("role","admin");
                    req.getRequestDispatcher("jsp/admin.jsp").forward(req,resp);
                    System.out.println("IN ADMIN'S PAGE");
                    break;
                }
            }
            System.out.println("ok");
        }else {
            System.out.println("ne ok");
            req.getRequestDispatcher("jsp/LoginError.jsp").forward(req, resp);
        }
    }

}
