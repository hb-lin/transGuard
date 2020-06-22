package com.tansun.util;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * httpclient工具类
 *
 * @author linhb
 * @create 2019-08-08
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    /**
     * 最大连接数
     */
    public static final int MAX_HTTPCLIENT_CONN = 200;
    /**
     * 最大路由数
     */
    public static final int MAX_HTTPCLIENT_ROUTE = 2;

    /**
     * 线程超时（10s）
     */
    public static final int SOCKET_TIMEOUT_10S = 10 * 1000;

    /**
     * 连接主机超时（10s）
     */
    public static final int CONN_TIMEOUT_10S = 10 * 1000;

    /**
     * 从主机读取数据超时（30s）
     */
    public static final int CONN_REQUEST_TIMEOUT_10S = 30 * 1000;

    /**
     * utf-8字符编码
     */
    public static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * HTTP内容类型。
     */
    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

    /**
     * HTTP内容类型。相当于form表单的形式，提交数据
     */
    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

    /**
     * HTTP内容类型。相当于form表单的形式，提交数据
     */
    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=UTF-8";

    /**
     * 连接管理器
     */
    private static PoolingHttpClientConnectionManager pool;

    /**
     * 请求配置
     */
    private static RequestConfig requestConfig;

    /**
     * 初始化
     *
     */
    static {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(builder.build());
            //配置同时支持http和https
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register(
                    "http", PlainConnectionSocketFactory.getSocketFactory()).register(
                    "https", factory).build();
            // 初始化连接管理器
            pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 将最大连接数增加到200
            pool.setMaxTotal(MAX_HTTPCLIENT_CONN);
            // 设置最大路由
            pool.setDefaultMaxPerRoute(MAX_HTTPCLIENT_ROUTE);
            // 根据默认超时限制初始化requestConfig
            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
                    CONN_REQUEST_TIMEOUT_10S).setSocketTimeout(SOCKET_TIMEOUT_10S).setConnectTimeout(
                    CONN_TIMEOUT_10S).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取httpclient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(pool)
                // 设置请求配置
                .setDefaultRequestConfig(requestConfig)
                // 设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();

        return httpClient;
    }

    /**
     * get请求
     * @param url 目标地址
     * @param params 请求参数（通过uri拼接）
     * @param cookie
     * @return
     */
    public static String doGet(String url, Map<String, String> params, String cookie) {
        String resultString = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            //创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http Get
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            httpGet.setHeader("Cookie", cookie);
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity());
            }else{
                throw new NullPointerException();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.error("调用云服务异常****",e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("调用云服务异常****",e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            logger.error("调用云服务异常****",e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用云服务异常****",e);
        }finally {
            finallize(httpClient, response);
        }
        return resultString;
    }

    /**
     * json方式
     * @param url
     * @param json
     * @param cookies
     * @return
     */
    public static String doPost(String url, String json, String cookies) {
        String resultString = null;
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
        CloseableHttpResponse response = null;
        try {
            // 创建http Post
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            httpPost.setHeader("Content-Type", "application/json");
//            httpPost.setHeader("Cookie",cookies);
            // 创建参数队列
            ByteArrayEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            finallize(httpClient, response);
        }
        return resultString;
    }

    /**
     * 释放关闭httpclient和响应流
     * @param httpClient
     * @param response
     */
    private static void finallize(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
