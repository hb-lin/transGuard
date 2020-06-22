package com.tansun.controller;

import com.alibaba.fastjson.JSON;
import com.tansun.util.DesUtil;
import com.tansun.util.FileUtil;
import com.tansun.util.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author linhb
 * @create 2019-11-07
 */
@Controller
@RequestMapping(value = "")
public class BaiduContoller {

    @RequestMapping(value = "/geocoding/v3", method = RequestMethod.GET)
    @ResponseBody
    public String ds() {
//        jsonParam.getJSONObject("dataType");
        String str ;
        String result = FileUtil.readJson("baidu/address.json");
        return result;
    }


    @RequestMapping(value = "/geocoding/v3", method = RequestMethod.POST)
    @ResponseBody
    public String dsPost() {
//        jsonParam.getJSONObject("dataType");
        String result = FileUtil.readJson("baidu/address.json");
        System.out.println("result：" + result);
        return result;
    }
    @RequestMapping(value = "/testFile", method = RequestMethod.POST)
    @ResponseBody
    public void getTransFile(String transids)  {
        try {
            String[] ids = transids.split(",");
//            File[] files= new File[ids.length];
            List<File> fileList = new ArrayList<>();
            for (int i = 0;i<Arrays.asList(ids).size();i++) {
                File tempFile = File.createTempFile("temp"+Arrays.asList(ids).get(i),".json");
    //            List<String> transData = this.getTransAllMsgByGuids(new String[]{id});
                InputStream stream = IOUtils.toInputStream(JSON.toJSONString("qwerqwerqwerqwer"), StandardCharsets.UTF_8.name());
                FileUtils.inputstreamToFile(stream,tempFile);
                stream.close();
                fileList.add(tempFile);
//                files[i] = tempFile;
            }
            //生成临时文件
            File tempTar = File.createTempFile("AAAAA",".tar");
            //压缩tar
            FileUtils.packTar(fileList,tempTar);
            File tarGzFile = FileUtils.compress(tempTar,tempTar.getName());

//            List<File> fileList = FileUtils.decompress(tarGzFile,"E:/temp");
//            List<String> list = new ArrayList<>();
//            for (File file : fileList) {
//                list.add(FileUtils.FileToString(file));
//                file = null;
//            }
//            FileUtils.compress("E:/temp","E:/temp/a.tar.gz");
            for (File file : fileList) {
                file = null;
            }
//            for(int i = 0 ;i<files.length;i++){
//                files[i].deleteOnExit();
//                //此处置空（若还有引用，会出现临时文件的资源被占用无法删除）
//                files[i] = null;
//            }
            tempTar.deleteOnExit();
//            System.out.println(JSON.toJSONString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
