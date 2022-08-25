package com.project.bootboard.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 요청이 들어가기전
        // 예시
        // HttpSession session = request.getSession();
        // Member member = session.getAttribute("loginMember");
        // if(member ==null){
        // 로그인 안했는데 접근했을때
        // return false;
        // }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 요청이 들어가서 다 완료한 후
    }
}
