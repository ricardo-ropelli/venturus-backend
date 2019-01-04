package com.venturus.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<Void> loginUser(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestHeader(value = "userName") String userName,
                                          @RequestHeader(value = "password") String password) {

        response.addCookie(buildToken(userName, password));
        return ResponseEntity.ok().build();
    }

    private Cookie buildToken(final String userName, final String password) {
        final String clearToken = userName.concat(password);
        Cookie tokenCookie = new Cookie("token", new String(Base64.encodeBase64(clearToken.getBytes())));
        tokenCookie.setPath("/");
        return tokenCookie;
    }
}
