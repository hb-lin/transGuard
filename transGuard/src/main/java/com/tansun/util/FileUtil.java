package com.tansun.util;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import sun.rmi.runtime.Log;

import java.io.*;
import java.util.logging.Logger;

/**
 * @author linhb
 * @create 2019-11-07
 */
public class FileUtil {
    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(FileUtil.class);


    public static String readJson(String path){
        ClassPathResource classPathResource =  new ClassPathResource(path);
        logger.info(classPathResource.getPath()+"||"+classPathResource.getFilename());
        //从给定位置获取文件
       InputStream inputStream = null;
        File file = null;
        try {
            inputStream = classPathResource.getInputStream();
//            file = ResourceUtils.getFile("classpath:"+path);
//            file = classPathResource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            //每次读取文件的缓存
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭文件流
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
}
