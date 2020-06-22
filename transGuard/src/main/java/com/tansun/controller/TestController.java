package com.tansun.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author linhb
 * @create 2019-08-22
 */
@Controller
@RequestMapping(value = "")
public class TestController {
    private int count = 1;
    /**
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/test/test1", method = RequestMethod.GET)
    public void registe(HttpServletRequest request, HttpServletResponse response//,
                        /*@RequestParam() Map<String, Object> params*/) throws IOException {
       /* for(String key : params.keySet()){
            System.out.println(key+":"+params.get(key));
        }*/
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // 将资料解码
        String reqBody = sb.toString();
        System.out.println("——注册api请求参数：" + reqBody);
        System.out.println("注册api请求" + count + "次");
        int code = 200;
//        if (count % 3 == 0) {
//            code = 500;
//        }
        count++;
        JSONObject jsonObject = new JSONObject()
                .fluentPut("status", code)
                .fluentPut("message", "success")
                .fluentPut("result", new JSONObject().fluentPut("apiId", "online"+count));
        response.setContentType("text/plain; charset=UTF-8");
        try {
            response.getWriter().print(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
