package com.tansun.util;

import org.apache.commons.lang.ArrayUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;

public class DesUtil {

    /**
     * Description 加密方法
     *
     * @param data 需要加密的参数（姓名、身份证、全称、组织机构代码）
     * @param key  密钥（找汇法技术提供）
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes("UTF-8"), key.getBytes("UTF-8"));
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }


    /**
     * Description 解密方法
     *
     * @param data 需要解密的查询结果字符串
     * @param key  密钥（找汇法技术提供）
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException, Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, key.getBytes("UTF-8"));
        return new String(bt, "UTF-8");
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) {

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks;
        byte[] str = null;
        try {
            dks = new DESKeySpec(key);
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = null;

            if (key.length > 8) {
                iv = new IvParameterSpec(ArrayUtils.subarray(key, 0, 8));
            } else {
                iv = new IvParameterSpec(key);
            }

            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
            str = cipher.doFinal(data);
        } catch (Exception e) {
//			logger.info(e);
        }


        return str;
    }


    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) {

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks;
        byte[] str = null;
        try {
            dks = new DESKeySpec(key);
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            IvParameterSpec iv = null;

            if (key.length > 8) {
                iv = new IvParameterSpec(ArrayUtils.subarray(key, 0, 8));
            } else {
                iv = new IvParameterSpec(key);
            }

            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);

            str = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
//			logger.info(e);

        }


        return str;
    }
}