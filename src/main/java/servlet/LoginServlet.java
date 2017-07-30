package servlet;

import manager.CookiesManager;
import manager.UserManager;
import utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by dasun on 2017/7/26.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + ";" + password + ";" + action);
        HttpSession session = request.getSession();
        if (action != null && action.equals("logout")) {
            session.invalidate();
            Cookie cookie=new Cookie("principal",username+":"+password);
            cookie.setMaxAge(0);
            cookie.setPath("/");
//          cookie.setDomain(".com");
            response.addCookie(cookie);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        String psd = UserManager.getInstance().findByName(username);
        String path = "/login.jsp";
        if (password != null && password.equals(psd)) {
            session.setAttribute("user", username);
            if (request.getParameter("autoLogin") != null) {
                String md5Psd= CookiesManager.generatorMD5CookieValue(password);
                Cookie cookie=new Cookie("principal",username+":"+md5Psd);
                cookie.setMaxAge(3600*24*7);
                cookie.setPath("/");
//                cookie.setDomain(".com");
                response.addCookie(cookie);
            }
            path = "/success.jsp";
        }
            request.getRequestDispatcher(path).forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
