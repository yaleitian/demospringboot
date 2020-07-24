package com.example.demospringboot.token;

import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

/**
 * JWT的工具类
 */
@Component
@ConfigurationProperties(prefix = "jwt.config")  //读取配置文件中的配置
@Data
public class JwtUtil {
    private String secret;  //秘钥
    private long expire;  //过期时间

    /**
     * 生成token
     *
     * @param id      用户的Id
     * @param subject 用户名
     * @param role    角色，分为用户和后台管理员
     * @return
     */
    public String encoder(String id, String subject, String role) throws Exception {
        JwtBuilder builder = Jwts.builder()
                .setId( id )   //用户Id
                .setIssuedAt( new Date() )  //用户登录的日期
                .setSubject( subject )//用户名
                .setExpiration( new Date( new Date().getTime() + expire ) )  //设置过期时间为1小时
                .claim( "role", role )  //自定义属性，指定角色
                .signWith( SignatureAlgorithm.HS256, secret ); //指定签名的算法和秘钥（盐）

        return builder.compact();
    }

    /**
     * 对token进行解码
     *
     * @param token
     * @return 解码后的结果集，相当于Map
     * @throws Exception 如果解码失败会抛出异常
     */
    public Claims decoder(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey( secret )  //设置解析的秘钥
                .parseClaimsJws( token )  //解析的token
                .getBody();
    }
}