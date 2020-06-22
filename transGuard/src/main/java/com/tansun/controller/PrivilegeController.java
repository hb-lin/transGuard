package com.tansun.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linhb
 * @create 2019-09-04
 */
@Controller
@RequestMapping(value = "privilege")
public class PrivilegeController {

    private int count = 0;

    @RequestMapping(value = "/api/roles/product_roles", method = RequestMethod.POST)
    public void getRoles(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>(16);
        map1.put("name", "admin");
        map1.put("description", "管理员");
        list.add(map1);
        Map<String, String> map2 = new HashMap<>(16);
        map2.put("name", "DBA");
        map2.put("description", "数据库管理员");
        list.add(map2);
        Map<String, String> map3 = new HashMap<>(16);
        map3.put("name", "Operater");
        map3.put("description", "运维人员");
//        if(count%2 ==0){
        list.add(map3);
//        }
        JSONObject jsonObject = new JSONObject().fluentPut("status", 200).fluentPut("message", "success").fluentPut("result", list);
//        JSONObject jsonObject = new JSONObject().fluentPut("status", 201).fluentPut("message", "当前用户没有角色").fluentPut("result", list);
        response.setContentType("text/plain; charset=UTF-8");
        try {
            response.getWriter().print(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        count++;
    }

    @RequestMapping(value = "/api/roles/in_project", method = RequestMethod.GET)
    public void getRolesInprojecct(HttpServletRequest request, HttpServletResponse response) {
        String jsonstr = "{\n" +
                "  \"status\": 200,\n" +
                "  \"message\": \"成功\",\n" +
                "  \"result\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"code\": \"GLOBAL_DATA_ADMIN\",\n" +
                "      \"name\": \"全局数据管理员\",\n" +
                "      \"description\": \"全局数据管理员可以从全局维度对企业数据进行管理，拥有最高的数据管理权限\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"code\": \"PROJECT_ADMIN\",\n" +
                "      \"name\": \"项目管理员\",\n" +
                "      \"description\": \"项目管理员拥有具体项目的管理权限，包括项目里的配置、项目成员管理、项目作业上线审批\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"code\": \"DEVELOPER\",\n" +
                "      \"name\": \"开发人员\",\n" +
                "      \"description\": \"开发人员可以使用平台资源进行各类大数据任务开发\"\n" +
                "    },{\"code\": \"OPERATOR\",\"name\": \"OPERATOR\"}]\n" +
                "}";
//        String jsonstr = "{\n" +
//                "  \"status\": 200,\n" +
//                "  \"message\": \"成功\",\n" +
//                "  \"result\": [\n" +
//                "    {\n" +
//                "      \"id\": 1,\n" +
//                "      \"code\": \"GLOBAL_DATA_ADMIN\",\n" +
//                "      \"name\": \"全局数据管理员\",\n" +
//                "      \"description\": \"全局数据管理员可以从全局维度对企业数据进行管理，拥有最高的数据管理权限\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"id\": 4,\n" +
//                "      \"code\": \"PROJECT_ADMIN\",\n" +
//                "      \"name\": \"项目管理员\",\n" +
//                "      \"description\": \"项目管理员拥有具体项目的管理权限，包括项目里的配置、项目成员管理、项目作业上线审批\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"id\": 5,\n" +
//                "      \"code\": \"DEVELOPER\",\n" +
//                "      \"name\": \"开发人员\",\n" +
//                "      \"description\": \"开发人员可以使用平台资源进行各类大数据任务开发\"\n" +
//                "    } ]\n" +
//                "}";
        Object o = JSONObject.parse(jsonstr);
        response.setContentType("text/plain; charset=UTF-8");
        try {
            response.getWriter().print(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
