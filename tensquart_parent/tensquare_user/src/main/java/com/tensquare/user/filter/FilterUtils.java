package com.tensquare.user.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FilterUtils extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String authorization = request.getHeader("Authorization");
        if (authorization!=null && authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims !=null){
                if (claims.get("role").equals("admin")){
                    request.setAttribute("admin_claims",claims );
                }else {
                    request.setAttribute("user_claims",claims );
                }
            }
        }
        return true;
    }

}
