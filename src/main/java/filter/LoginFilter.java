package filter;

import manager.CookiesManager;
import manager.UserManager;
import utils.MD5Utils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dasun on 2017/7/27.
 */
public class LoginFilter implements Filter {
    private String loginProcess;
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if(uri.equals(loginProcess)){
            chain.doFilter(req, resp);
            return;
        }
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            chain.doFilter(req, resp);
            return;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                String ckName = cookie.getName();
                if(ckName.equals("principal")){
                    String value = cookie.getValue();
                    String[] parts = value.split(":");
                    String username=parts[0];
                    String md5Password=parts[1];
                    String dbpsd = UserManager.getInstance().findByName(username);
                    if(dbpsd!=null){
                        String md5DBpassword = CookiesManager.generatorMD5CookieValue(dbpsd);
                        if(md5DBpassword!=null&&md5Password.equals(md5DBpassword)){
                            session.setAttribute("user",username);
                        }
                    }
                    break;
                }
            }
        }
        chain.doFilter(req, resp);

    }
    public void init(FilterConfig config) throws ServletException {
        loginProcess=config.getInitParameter("loginProcess");
        if(loginProcess==null){
            throw  new RuntimeException("no filter config");
        }
    }

}
