package top.aidan.interceptor;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import top.aidan.bean.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Aidan on 2021/9/1 8:04
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

public class PermitInterceptor implements HandlerInterceptor {
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if ("/".equals(requestURI) || "/doLogin".equals(requestURI) || "/register".equals(requestURI) || "/doRegister".equals(requestURI)) {
            return true;
        }
        HttpSession session = request.getSession(true);
        Employee currentUser = (Employee) session.getAttribute("currentUser");
        if (pathMatcher.match("/admin/**", requestURI)) {
            if (currentUser != null && currentUser.getRole() == 2) {
                return true;
            } else {
                //可以使用封装类简写Content-Type，使用该方法则无需使用setCharacterEncoding
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("禁止访问");
                return false;
            }
        } else {
            if (currentUser != null) {
                return true;
            }
        }
        response.sendRedirect("/");
        return false;

    }
}
