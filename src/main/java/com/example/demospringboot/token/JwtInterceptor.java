package com.example.demospringboot.token;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import io.jsonwebtoken.Claims;

/**
 * JWT验证token的拦截器
 * 改进： 如果没有权限，那么可以跳转到一个指定的错误页面护.......
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtUtil jwtUtil;    //注入JwtUtil

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //获取请求头中的token
        String token = request.getHeader( "token" );
        if (StringUtils.isEmpty( token )) {
            response.setStatus( HttpStatus.UNAUTHORIZED.value() );  //设置401响应信息，没有权限
            System.err.println( "没有权限" );
            return false;  //直接拦截，不继续进行
        }

        //如果有token，需要解码
        Claims claims = null;
        try {
            System.err.println( token );
            claims = jwtUtil.decoder( token );
            if (claims != null) {
                request.setAttribute( "claims", claims );  //放置在request中，后续的接口可能还需使用
            }
        } catch (Exception e) {
            response.setStatus( HttpStatus.UNAUTHORIZED.value() );  //设置401响应信息，没有权限
            return false;
        }

        return true;
    }
}