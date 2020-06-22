package com.tansun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tansun.util.FileUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author linhb
 * @Date 2020/3/20
 **/
@RestController
@RequestMapping(value = "")
public class ImpexpController {

    @RequestMapping(value = "/impexp-server/impexp/clients/register", method = RequestMethod.POST)
    public Object ds(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject= new JSONObject().fluentPut("data","qwe")
                .fluentPut("status",200)
                .fluentPut("message","asdf");
        return jsonObject;
    }
    @RequestMapping(value = "/impexp-test", method = RequestMethod.POST)
    public Object test(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject= new JSONObject().fluentPut("data","qwe")
                .fluentPut("status",200)
                .fluentPut("message","asdf");
        return jsonObject;
    }


    @RequestMapping(value = "/impexp-server/impexp/export/callback", method = RequestMethod.POST)
    public Object expCallback(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject= new JSONObject().fluentPut("data","qwe")
                .fluentPut("status",200)
                .fluentPut("message","asdf");
        return jsonObject;
    }
    @RequestMapping(value = "/impexp-server/impexp/import/callback", method = RequestMethod.POST)
    public Object impCallback(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject= new JSONObject().fluentPut("data","qwe")
                .fluentPut("status",200)
                .fluentPut("message","asdf");
        return jsonObject;
    }

    @RequestMapping(value = "/project/api/projects/getProjectDetail", method = RequestMethod.GET)
    public Object getProjectDetail(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject= new JSONObject().fluentPut("data","qwe")
                .fluentPut("status",200)
                .fluentPut("message","asdf")
                .fluentPut("result",new JSONObject().fluentPut("id",24));
        return jsonObject;
    }

    @RequestMapping(value = "/project/api/projects/hasPrivilege", method = RequestMethod.GET)
    public Object projectPrivilege(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject= new JSONObject().fluentPut("data","qwe")
                .fluentPut("status",200)
                .fluentPut("message","asdf")
                .fluentPut("result",true);
        return jsonObject;
    }
}
