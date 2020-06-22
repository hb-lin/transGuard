package com.tansun.util;

import com.tansun.vos.TenantVo;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 文件工具类
 * @Author linhb
 * @Date 2020/3/4
 **/
public class FileUtils {

    private final static int BUFFER = 1048576;

    /**
     * 输入流转文件
     * @param ins
     * @param file
     * @throws IOException
     */
    public static void inputstreamToFile(InputStream ins, File file)throws IOException{
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

    /**
     * 文件转json字符串
     * （仅支持.json文件）
     * @param file
     * @return
     */
    public static String FileToString(File file) throws IOException {
        String str = "";
        int index = file.getName().lastIndexOf(".");
        //通过文件后缀判断类型
        if (".json".equals(file.getName().substring(index))) {
            str = org.apache.commons.io.FileUtils.readFileToString(file);
        }
        return str;
    }
    public static File packTar(List<File> sources, File target){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(target);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);
        for (File file : sources) {
            try {
                os.putArchiveEntry(new TarArchiveEntry(file,file.getName()));
                IOUtils.copy(new FileInputStream(file), os);
                os.closeArchiveEntry();
                file.deleteOnExit();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(os != null) {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return target;
    }

    /**
     * 压缩成gz格式文件
     * @param source
     * @return
     */
    public static File compress(File source,String GzFileName) {
        File target = new File(GzFileName + ".gz");
        FileInputStream in = null;
        GZIPOutputStream out = null;
        try {
            in = new FileInputStream(source);
            out = new GZIPOutputStream(new FileOutputStream(target));
            byte[] array = new byte[1024];
            int number = -1;
            while((number = in.read(array, 0, array.length)) != -1) {
                out.write(array, 0, number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return target;
    }

    /**
     * 解压tar.gz文件
     * @param tar_gz
     * @param sourceFolder
     */
    public static List<File> decompress(File tar_gz,String sourceFolder){
        List<File> files = new ArrayList<>();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        GZIPInputStream gzis = null;
        TarArchiveInputStream tais = null;
        OutputStream out = null;
        try {
            fis = new FileInputStream(tar_gz);
            bis = new BufferedInputStream(fis);
            gzis = new GZIPInputStream(bis);
            tais = new TarArchiveInputStream(gzis);
            TarArchiveEntry tae = null;
            boolean flag = false;
            while((tae = tais.getNextTarEntry()) != null ){
                File tmpFile = new File(sourceFolder+"/"+tae.getName());
                if(! flag){
                    //使用 mkdirs 可避免因文件路径过多而导致的文件找不到的异常
                    new File(tmpFile.getParent()).mkdirs();
                    flag = true;
                }
                out = new FileOutputStream(tmpFile);
                int length = 0;
                byte[] b = new byte[BUFFER];
                while((length = tais.read(b)) != -1){
                    out.write(b, 0, length);
                }
                tmpFile.deleteOnExit();
                files.add(tmpFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(tais != null)  tais.close();
                if(gzis != null) gzis.close();
                if(bis != null) bis.close();
                if(fis != null) fis.close();
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return files;
    }



}
