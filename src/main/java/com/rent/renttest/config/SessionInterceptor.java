package com.rent.renttest.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //重定向
        Object guanli = request.getSession().getAttribute("guanli");
        Object own = request.getSession().getAttribute("own");
        Object tenant = request.getSession().getAttribute("tenant");
        if (null == guanli || null == own || null == tenant){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
