package com.example.demospringboot.restful;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiAuth {

    /**
     * 测试auth
     *
     * @param request
     * @param authKey
     * @param sign
     * @param info
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/auth/test", method = RequestMethod.POST)
    @ResponseBody
    public String testAuth(HttpServletRequest request, String authKey, String sign,
                           String info) throws Exception {

        String reqTime = request.getHeader( "ReqTime-Time" );

        //这里的token应该是根据客户端那边传来的authKey从redis里面将token取出来，我这里直接写死了
        String token = "lijieauthtest01";

        String check = check( reqTime, info, token, sign );

        System.out.println( "服务端:" + check );

        //do service

        return check;
    }

    /**
     * 校验参数和签名
     *
     * @param reqTime
     * @param info
     * @param token
     * @param sign
     * @return
     * @throws Exception
     */
    private String check(String reqTime, String info, String token, String sign) throws Exception {

        if (StringUtils.isEmpty( reqTime )) {
            return "头部时间为空";
        }
        if (StringUtils.isEmpty( info )) {
            return "信息为空";
        }
        if (StringUtils.isEmpty( token )) {
            return "没有授权";
        }
        if (StringUtils.isEmpty( sign )) {
            return "签名为空";
        }

        long serverTime = Calendar.getInstance().getTimeInMillis();

        long clientTime = Long.parseLong( reqTime );

        long flag = serverTime - clientTime;

        if (flag < 0 || flag > 5000) {
            return "时间错误";
        }

        String allStr = info + reqTime + token;

        String md5 = Signature.md5( allStr );

        if (sign.equals( md5 )) {

            System.out.println( "服务端未签名时为:" + allStr );

            System.out.println( "服务端签名之后为:" + md5 );

            return "签名成功";
        }

        return "签名错误";
    }

}
