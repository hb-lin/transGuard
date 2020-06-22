package com.tansun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tansun.vos.SSOUserVo;
import com.tansun.vos.TenantVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author linhb
 * @create 2019-08-07
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private int count = 1;

    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("进入登陆——");
        Cookie cookie  = new Cookie("sso-token","123");
        response.addHeader("Set-Cookie","sso-token=123");
//        try {
//            response.sendRedirect("http://localhost:9109/#/dealIndex");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @RequestMapping(value = "/api/re", method = RequestMethod.GET)
    public void re(HttpServletRequest request, HttpServletResponse response)  {
        System.out.println("重定向——");
//        Cookie cookie  = new Cookie("sso-token","123");
//        response.addHeader("Cookie","sso-token=123");
        try {
            response.sendRedirect("http://localhost:9109/#/dealIndex");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.GET)
    @ResponseBody
    public String reciveTrans(HttpServletRequest request, HttpServletResponse response) {
        int code = 200;
        Cookie[] cookieStore = request.getCookies();
        String msg = "token校验失败";
        for (Cookie cookie : request.getCookies()) {
            if("sso-token".equals(cookie.getName())&&"123".equals(cookie.getValue())){
                code = 200;
                msg = "token校验成功";
                break;
            }
            if("sso-token".equals(cookie.getName())&&"testsession".equals(cookie.getValue())){
                code = 200;
                msg = "token校验成功";
                break;
            }
            System.out.println("******----------cookie:name-"+cookie.getName()+"||content"+cookie.getComment()+"||domain"+cookie.getDomain());
        }
        System.out.println("sso_token校验请求" + count + "次");
        count++;
        TenantVo tenantVo = TenantVo.builder().id("123").build();
        SSOUserVo ssoUserVo = SSOUserVo.builder()
                .id(001).name("zhangsan").alias("张三").email("zhagnsan@tansun.com.cn").isTenant(false).tenant(tenantVo)
                .build();
        JSONObject jsonObject = new JSONObject().fluentPut("status", code).fluentPut("message", msg).fluentPut("result", ssoUserVo);
        return jsonObject.toString();
    }




}
