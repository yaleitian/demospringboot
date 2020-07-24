package com.example.demospringboot;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class TestToken {
    /**
     * 生成token
     */
    @Test
    public void test1() {
        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTQ1NTc0ODE1LCJzdWIiOiLpmYjliqDlhbUifQ.WF0VoGSP5oH0XRsCraJ9lRjtVFRs6I0KJpkhFngpwgk
        JwtBuilder builder = Jwts.builder()
                .setId( "1" )   //用户Id
                .setIssuedAt( new Date() )  //用户登录的日期
                .setSubject( "lisi" )//用户名
                .signWith( SignatureAlgorithm.HS256, "sercet" );  //指定签名的算法和秘钥（盐）
        String token = builder.compact();  //获取生成的token
        System.out.println( token );
    }

    /*
     * 	解析token
     */
    @Test
    public void test2() {
        Claims claims = Jwts.parser()
                .setSigningKey( "sercet" )  //设置解析的秘钥
                .parseClaimsJws( "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTQ1NTc0ODE1LCJzdWIiOiLpmYjliqDlhbUifQ.WF0VoGSP5oH0XRsCraJ9lRjtVFRs6I0KJpkhFngpwgk" )
                .getBody();

        System.out.println( "用户Id：" + claims.getId() );
        System.out.println( "用户名：" + claims.getSubject() );
        System.out.println( "登录时间：" + claims.getIssuedAt() );


    }

    JwtBuilder builder = Jwts.builder()
            .setId( "1" )   //用户Id
            .setIssuedAt( new Date() )  //用户登录的日期
            .setSubject( "lisi" )//用户名
            .setExpiration( new Date( new Date().getTime() + 1000 * 60 * 60 ) )  //设置过期时间为1小时
            .signWith( SignatureAlgorithm.HS256, "sercet" );  //指定签名的算法和秘钥（盐）


    /**
     * 生成token
     */
    @Test
    public void test3() {
        JwtBuilder builder = Jwts.builder()
                .setId( "1" )   //用户Id
                .setIssuedAt( new Date() )  //用户登录的日期
                .setSubject( "lisi" )//用户名
                .setExpiration( new Date( new Date().getTime() + 1000 * 60 * 60 ) )  //设置过期时间为1小时
                .signWith( SignatureAlgorithm.HS256, "sercet" ) //指定签名的算法和秘钥（盐）
                .claim( "age", 22 )    //自定义内容
                .claim( "address", "江苏省" ); //自定义内容
        String token = builder.compact();  //获取生成的token
        System.out.println( token );
    }


    /*
     * 	解析token
     */
    @Test
    public void test4() {
        Claims claims = Jwts.parser()
                .setSigningKey( "sercet" )  //设置解析的秘钥
                .parseClaimsJws( "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTQ1NTc1ODQzLCJzdWIiOiLpmYjliqDlhbUiLCJleHAiOjE1NDU1Nzk0NDMsImFnZSI6MjIsImFkZHJlc3MiOiLmsZ_oi4_nnIEifQ.uRhzSnsWl5IO-K6SA3zHsqGacZzkOOsFlD8lvqYDleY" )
                .getBody();

        System.out.println( "用户Id：" + claims.getId() );
        System.out.println( "用户名：" + claims.getSubject() );
        System.out.println( "登录时间：" + claims.getIssuedAt() );
        System.out.println( "过期时间：" + claims.getExpiration() );
        System.out.println( "address：" + claims.get( "address" ) );
    }
    
}
