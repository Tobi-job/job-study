package com.example.springpractice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        // 公開ページ・静的リソース・API はチェック対象外
        if (requestURI.equals("/")
                || requestURI.equals("/login")
                || requestURI.equals("/register")
                || requestURI.equals("/logout")
                || requestURI.startsWith("/api")
                || requestURI.startsWith("/css/")
                || requestURI.startsWith("/h2-console")
                || requestURI.startsWith("/error")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        String username = null;

        if (session != null) {
            username = (String) session.getAttribute("username");
        }

        // セッションにユーザー名がない場合はログイン画面にリダイレクト
        if (username == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
