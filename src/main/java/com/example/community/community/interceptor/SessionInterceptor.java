package com.example.community.community.interceptor;

import com.example.community.community.dao.User;
import com.example.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: community
 * @author: FvngGumHun
 * @create: 2019-11-16 16:53
 **/
@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    /*
     * 程序处理前执行
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //通过 token 的查找,验证该用户信息是否在数据库中
                    User user = userService.findByToken(token);
                    if (user != null) {
                        //将 userInfo 命名为user 绑定到 Session
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
