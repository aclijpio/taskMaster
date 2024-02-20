package ru.pio.aclij.taskmanagement.security.services.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public final class TokenAuthenticationHelper {
    public static final String COOKIE_BEARER = "COOKIE_BEARER";

    public static void addTokenToCookie(HttpServletResponse res, String token){
        Cookie cookie = new Cookie(COOKIE_BEARER, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        res.addCookie(cookie);
    }
    public static String getTokenFromRequest(HttpServletRequest req){
        Cookie cookie = WebUtils.getCookie(req, COOKIE_BEARER);
        return cookie != null ? cookie.getValue() : null;
    }
}
