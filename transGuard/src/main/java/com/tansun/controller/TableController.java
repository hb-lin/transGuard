package com.tansun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tansun.vos.SSOUserVo;
import com.tansun.vos.TenantVo;
import org.jsoup.select.Evaluator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linhb
 * @create 2019-08-07
 */
@Controller
@RequestMapping(value = "dmgMdQuery")
public class TableController {
    private int count = 1;

    @RequestMapping(value = "/query/table/list/brief/{id}", method = RequestMethod.POST)
    public void getTableList(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String cookie = request.getHeader("Cookie");
        System.out.println("gettableColumn请求" + count + "次");
        int code = 200;
        if (count % 3 == 0) {
            code = 500;
        }
        count++;
        JSONObject column1 = new JSONObject().fluentPut("tblName","T_TEST1");
        JSONObject column2 = new JSONObject().fluentPut("tblName","T_TEST2");
        JSONObject column3 = new JSONObject().fluentPut("tblName","T_TEST3");
        List list = new ArrayList();
        list.add(column1);
        list.add(column2);
        list.add(column3);
        JSONObject jsonObject = new JSONObject().fluentPut("status", code)
                .fluentPut("message", "success")
                .fluentPut("result", list);
        response.setContentType("text/plain; charset=UTF-8");
        try {
            response.getWriter().print(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/query/tableColumn/list/brief/{id}", method = RequestMethod.POST)
    public void getTableClumn(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String cookie = request.getHeader("Cookie");
        System.out.println("gettableColumn请求" + count + "次");
        int code = 200;
        if (count % 3 == 0) {
            code = 500;
        }
        count++;
        JSONObject column1 = new JSONObject().fluentPut("colName","guid").fluentPut("type","String");
        JSONObject column2 = new JSONObject().fluentPut("colName","createUser").fluentPut("type","int");
        JSONObject column3 = new JSONObject().fluentPut("colName","createTime").fluentPut("type","date");
        List list = new ArrayList();
        list.add(column1);
        list.add(column2);
        list.add(column3);
        JSONObject jsonObject = new JSONObject().fluentPut("status", code)
                .fluentPut("message", "success")
                .fluentPut("result", list);
        response.setContentType("text/plain; charset=UTF-8");
        try {
            response.getWriter().print(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String a = "aa";
        System.out.println("a.hashcode:"+a.hashCode());
        change(a);
        System.out.println("a.hashcode:"+a.hashCode());
        System.out.println(a);
    }
    public static void change(String num){
        System.out.println("num in:"+num.hashCode());
        num = num+100;
        System.out.println("num out:"+num.hashCode());
    }
}
