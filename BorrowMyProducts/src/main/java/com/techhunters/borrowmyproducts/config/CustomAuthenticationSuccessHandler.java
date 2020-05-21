package com.techhunters.borrowmyproducts.config;

import com.techhunters.borrowmyproducts.dto.UserDTO;
import com.techhunters.borrowmyproducts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler, LogoutSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String username = authentication.getName();
        log.info("username:" + username);
        UserDTO theUser = userService.findByEmail(username);

        HttpSession session = request.getSession();
        log.info("user:" + theUser);
        session.setAttribute("user", theUser);
        log.info("session id" + session.getId());
        log.info("" + session.getAttribute("user"));
        response.sendRedirect(request.getContextPath() + "/mainPage");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("user");
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }

}