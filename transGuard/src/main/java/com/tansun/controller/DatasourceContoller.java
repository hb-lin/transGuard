package com.tansun.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tansun.util.FileUtil;
import com.tansun.util.ResponseResult;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author linhb
 * @create 2019-11-07
 */
@Controller
@RequestMapping(value = "")
public class DatasourceContoller {


    @RequestMapping(value = "/tp/{id}/flushAllCache", method = RequestMethod.GET)
    @ResponseBody
    public String flushAllCache() {
//
        return "success";
    }

    @RequestMapping(value = "/dmgMdQuery/ds/sourceList/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String ds(@RequestBody JSONObject jsonParam) {
//        jsonParam.getJSONObject("dataType");
        String result = FileUtil.readJson("http/datasourceList.json");
        return result;
    }

    /**
     * 数据源列表
     *
     * @throws Exception
     */
    @RequestMapping(value = "/dmgMdQuery/ds/sourceList/simply/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String simply(@RequestBody JSONObject jsonParam) {
        System.out.println("获取数据源列表——request参数：" + jsonParam.toJSONString());
        //kafka数据源
        JSONArray dataTypes = jsonParam.getJSONArray("dataTypes");
        if ("kafka".equals(dataTypes.getString(0))) {
            return FileUtil.readJson("kafka/ds.json");
        }
        //es数据源
        String result = FileUtil.readJson("es/datasourceList.json");

        return result;
    }

    /**
     * 数据源信息
     *
     * @throws Exception
     */
    @RequestMapping(value = "/dmgMdQuery/ds/source/dsInfo/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult dsInfo(@RequestBody JSONObject jsonParam) {

        JSONArray dsNames = jsonParam.getJSONArray("dsNames");
        if (dsNames.get(0) != null) {
            String a = FileUtil.readJson("http/details.json");
            JSONArray array = JSONObject.parseObject(a).getJSONArray("result");
            for (Object object : array) {
                JSONObject json = (JSONObject) object;
                if (dsNames.get(0).toString().equalsIgnoreCase(json.get("dsName").toString())) {

                    return ResponseResult.createInfo(Arrays.asList(json));
                }
            }
        }
        return ResponseResult.createInfo(new ArrayList());
    }

    @RequestMapping(value = "/testds", method = RequestMethod.GET)
    @ResponseBody
    public String testDs() {
        String result = FileUtil.readJson("baidu/address.json");
//        try {
//            result =  DesUtil.encrypt(result, "qwer1111");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("result：" + result);
        return result;
    }

    /**
     * 数据源索引
     *
     * @throws Exception
     */
    @RequestMapping(value = "/dmgMdQuery/query/db/list/min/granted/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String dbList(@RequestBody JSONObject jsonParam) {
        String result = FileUtil.readJson("es/dbList.json");
        return result;
    }

    /**
     * 数据表
     *
     * @throws Exception
     */
    @RequestMapping(value = "/dmgMdQuery/query/table/list/min/granted/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String tableList(@RequestBody JSONObject jsonParam) throws IOException {
        System.out.println("获取数据表列表——request参数：" + jsonParam.toJSONString());
        //kafka数据源
        String tblType = jsonParam.getString("tblType");
        String env = jsonParam.getString("env");
        String dsName = jsonParam.getString("dsName");
        if ("TOPIC".equals(tblType)) {
            ClassPathResource classPathResource = new ClassPathResource("kafka/topic");
            File dir = classPathResource.getFile();
            Optional optional = FileUtils.listFiles(dir, null, false)
                    .stream()
                    .filter(element -> element.getName().equalsIgnoreCase(dsName+".json")).findAny();
            if(!optional.isPresent()){
                throw new RuntimeException("没有对应topic");
            }
            return FileUtil.readJson("kafka/topic/"+dsName+".json");
        }
        //es数据源
        String result;
        if ("test".equalsIgnoreCase(env)) {
            result = FileUtil.readJson("es/tableList.json");
        } else {
            result = FileUtil.readJson("es/prodTableList.json");
            ;
        }
        return result;
    }

    /**
     * 数据表字段
     *
     * @throws Exception
     */
    @RequestMapping(value = "/dmgMdQuery/query/tableColumn/list/brief/granted/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String column(@RequestBody JSONObject jsonParam) {
        String tblName = jsonParam.getString("tblName");
        String result = null;
//        if ("linhb_test8".equals(tblName)) {
        result = FileUtil.readJson("es/" + tblName + "_column.json");
//        }else if ("linhb_test5".equals(tblName)) {
//            result =  FileUtil.readJson("es/lhb_test5_column.json");
//        }else{
//            result = FileUtil.readJson("es/mixColumn.json");
//        }
        System.out.println(jsonParam.toJSONString());
        return result;
    }

}
