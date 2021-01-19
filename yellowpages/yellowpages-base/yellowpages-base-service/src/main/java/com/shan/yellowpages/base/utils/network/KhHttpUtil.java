package com.shan.yellowpages.base.utils.network;

import com.shan.yellowpages.base.exception.IKhErrorCode;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装的httpclient
 * @author bruce
 */
public class KhHttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(KhHttpUtil.class);

    /**
     * 默认请求超时时间，单位毫秒
     */
    private static final int DEFAULT_REQUEST_TIMEOUT = 6 * 1000;

    /**
     * 默认等待数据超时时间，单位毫秒
     */
    private static final int DEFAULT_SO_TIMEOUT = 6 * 1000;

    /**
     * 编码
     */
    private static final String CHARSET_UTF8 = "utf-8";

    /** httpstatus的错误code */
    public static final int HTTP_STATUS_ERROR_CODE = 400;

    private static HttpEntityStringProcessor httpEntityStringProcessor = new HttpEntityStringProcessor();

    private static HttpEntityBytesProcesor httpEntityBytesProcessor = new HttpEntityBytesProcesor();


    /**
     * 请求配置
     * 内部类
     */
    public static class KhHttpConfig {

        /**
         * 请求超时时间，单位毫秒
         */
        private int requestTimeOutMillis = DEFAULT_REQUEST_TIMEOUT;

        /**
         * 请求超时时间，单位毫秒
         */
        private int soTimeOutMillis = DEFAULT_SO_TIMEOUT;

        public KhHttpConfig(int requestTimeOutMillis, int soTimeOutMillis) {
            this.requestTimeOutMillis = requestTimeOutMillis;
            this.soTimeOutMillis = soTimeOutMillis;
        }

        public int getRequestTimeOutMillis() {
            return requestTimeOutMillis;
        }

        public void setRequestTimeOutMillis(int requestTimeOutMillis) {
            this.requestTimeOutMillis = requestTimeOutMillis;
        }

        public int getSoTimeOutMillis() {
            return soTimeOutMillis;
        }

        public void setSoTimeOutMillis(int soTimeOutMillis) {
            this.soTimeOutMillis = soTimeOutMillis;
        }

        @Override
        public String toString() {
            return "HlmyHttpConfig{" + "requestTimeOutMillis=" + requestTimeOutMillis + ", soTimeOutMillis=" + soTimeOutMillis + '}';
        }
    }






    /**
     * Httpclient get
     *
     * @param url 链接
     * @param params 参数
     * @return 字符串结果
     */
    public static final String getRequest(String url, Map<String, String> params) {
        return getRequest(url, params, null, null);
    }


    /**
     * Httpclient get
     * @param url 链接
     * @param params 参数
     * @param headerParams 请求头参数
     * @return 字符串结果
     */
    public static final String getRequest(String url,Map<String,String> params, Map<String,String> headerParams){
        return getRequest(url,params,null,headerParams);
    }

    /**
     * Httpclient get
     *
     * @param url 链接
     * @param params 参数
     * @param httpConfig hlmy请求配置
     * @return 字符串结果
     */
    public static final String getRequest(String url, Map<String, String> params, KhHttpConfig httpConfig) {

        logger.debug("[KhHttpUtil][getRequest]entering, url:{}, params:{}, httpConfig:{}", url, params, httpConfig);

        return getRequest(url,params,httpConfig, null);
    }



    /**
     * Httpclient get
     *
     * @param url 链接
     * @param params 参数
     * @param httpConfig http配置
     * @param headerParams 请求头参数
     * @return 字符串结果
     */
    public static final String getRequest(String url, Map<String, String> params, KhHttpConfig httpConfig, Map<String,String> headerParams) {

        String result = null;
        try{
            result = getRequestWithException(url, params, httpConfig, headerParams);
        }catch(Exception e){
            logger.warn("[KhHttpUtil][getRequest]error, url:{}, params:{}, httpConfig:{}", url, params, httpConfig);
            logger.warn("[KhHttpUtil][getRequest]error", e);
        }
        return result;
    }


    /**
     * Httpclient get
     *
     * @param url 链接
     * @param params 参数
     * @param httpConfig http配置
     * @param headerParams 请求头参数
     * @return 字符串结果
     */
    public static final String getRequestWithException(String url, Map<String, String> params, KhHttpConfig httpConfig, Map<String,String> headerParams) throws Exception  {

        logger.debug("[KhHttpUtil][getRequest]entering, url:{}, params:{}, httpConfig:{}", url, params, httpConfig);

        String result  = getRequestEntity(httpEntityStringProcessor, url, params, httpConfig, headerParams);

        logger.info("[KhHttpUtil][getRequest]result:{}, url:{}, httpConfig:{}", result, url, httpConfig);
        return result;
    }


    /**
     * 获取bytes数组（通常用于下载图片、资源等）
     * @param url 链接
     * @param params 数据参数
     * @param httpConfig http配置
     * @param headerParams 请求头参数
     * @return
     * @throws Exception
     */
    public static final byte[] getBytes(String url, Map<String, String> params, KhHttpConfig httpConfig, Map<String,String> headerParams) throws Exception  {
        logger.debug("[KhHttpUtil][getBytes]entering, url:{}, params:{}, httpConfig:{}", url, params, httpConfig);
//        HttpEntity httpEntity = getRequestEntity(url, params, httpConfig, headerParams);
//        return getBytesFromHttpEntity(httpEntity);

        byte[] bytes = getRequestEntity(httpEntityBytesProcessor, url, params, httpConfig, headerParams);
        return bytes;
    }

//    /**
//     * 获取文本
//     * @param httpEntity
//     * @return
//     * @throws Exception
//     */
//    private static final String getTextFromHttpEntity(HttpEntity httpEntity) throws Exception  {
//        logger.debug("[KhHttpUtil][getTextFromHttpEntity]entering" );
//        String result = EntityUtils.toString(httpEntity, CHARSET_UTF8);
//        logger.info("[KhHttpUtil][getTextFromHttpEntity]result:{}", result );
//        return result;
//    }

//    /**
//     * 获取bytes数组（通常用于下载图片等）
//     * @param httpEntity
//     * @return
//     * @throws Exception
//     */
//    private static final byte[] getBytesFromHttpEntity(HttpEntity httpEntity) throws Exception  {
//        logger.debug("[KhHttpUtil][getBytesFromHttpEntity]entering" );
//        InputStream is = httpEntity.getContent();
//        int i;
//        byte[] buffer = new byte[1024];
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        while ((i = is.read(buffer)) != -1) {
//            bos.write(buffer, 0, i);
//        }
//        byte[] result = bos.toByteArray();
//        return result;
//    }


    /**
     * Httpclient get
     *
     * @param url 链接
     * @param params 参数
     * @param httpConfig http配置
     * @param headerParams 请求头参数
     * @return HttpEntity
     */
    private static final <T> T getRequestEntity(IKhHttpEntityProcessor<T> processor, String url, Map<String, String> params, KhHttpConfig httpConfig, Map<String,String> headerParams) throws Exception  {

        logger.debug("[KhHttpUtil][getRequestEntity]entering, url:{}, params:{}, httpConfig:{}", url, params, httpConfig);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        if (MapUtils.isNotEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url = UrlUtil.addParameter(url, entry.getKey(), entry.getValue());
            }
        }

        HttpGet httpGet = new HttpGet(url);

        //设置请求和传输超时时间
        int requestTimeout = httpConfig == null || httpConfig.getRequestTimeOutMillis() <= 0 ? DEFAULT_REQUEST_TIMEOUT : httpConfig.getRequestTimeOutMillis();
        int soTimeout = httpConfig == null || httpConfig.getSoTimeOutMillis() <= 0 ? DEFAULT_SO_TIMEOUT : httpConfig.getSoTimeOutMillis();

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(requestTimeout).setSocketTimeout(soTimeout).build();
        httpGet.setConfig(requestConfig);

        if (MapUtils.isNotEmpty(headerParams)) {
            //设置请求头
            for(Map.Entry<String, String> entry: headerParams.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                httpGet.setHeader(key, value);
            }
        }

        HttpEntity httpEntity = null;
        //发送请求

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            //有错误
            if (statusCode >= HTTP_STATUS_ERROR_CODE) {
                throw new KhRuntimeException(IKhErrorCode.SYSTEM_NETWORK_REQUEST_ERROR, "网络请求异常", null);
            } else {
                // 获取返回数据
                httpEntity = httpResponse.getEntity();

                T result =  processor.processHttpEntity(httpEntity);
                logger.info("[KhHttpUtil][getRequest]result:{}, url:{}, httpConfig:{}", result, url, httpConfig);
                return result;
            }
        } finally {
            try {
                if(httpClient!=null){
                    httpClient.close();
                }
                if(httpResponse!=null){
                    httpResponse.close();
                }
            } catch (IOException e) {
                logger.error("[KhHttpUtil][getRequestEntity]close error", e);
            }
        }
    }


    /**
     * Httpclient Post
     *
     * @param url 链接
     * @param params 参数
     * @param data requestBody参数
     * @return 字符串结果
     */
    public static final String postRequest(String url, Map<String, String> params, String data) {
        return postRequest(url, params, data, null, null);
    }


    /**
     * Httpclient Post
     *
     * @param url 链接
     * @param params 参数
     * @param data requestBody参数
     * @param httpConfig http配置
     * @return 字符串结果
     */
    public static final String postRequest(String url, Map<String, String> params, String data, KhHttpConfig httpConfig, Map<String, String> headerParams) {

        logger.debug("[KhHttpUtil][postRequest]entering, url:{}, params:{}, data:{}, httpConfig:{}", url, params, data, httpConfig);

        String result = null;
        try {
            result = postRequestWithException(url, params, data, httpConfig, headerParams);
        } catch (Exception e) {
            logger.error("[KhHttpUtil][getRequest]error:{}, url:{}, data:{}, httpConfig:{}", e, url, data, httpConfig);
        }
        logger.info("[KhHttpUtil][postRequest]result:{}, url:{},  data:{}, httpConfig:{}", result, url, data, httpConfig);
        return result;
    }

    /**
     * Httpclient Post
     *
     * @param url 链接
     * @param params 参数
     * @param data requestBody参数
     * @param httpConfig http配置
     * @return 字符串结果
     */
    public static final String postRequestWithException(String url, Map<String, String> params, String data, KhHttpConfig httpConfig, Map<String, String> headerParams) throws Exception {
        logger.debug("[KhHttpUtil][postRequestWithException]entering, url:{}, params:{}, httpConfig:{}", url, params, httpConfig);

        String result =  postHttpEntity(httpEntityStringProcessor, url, params, data, httpConfig, headerParams);

        logger.info("[KhHttpUtil][postRequestWithException]result:{}, url:{}, httpConfig:{}", result, url, httpConfig);
        return result;
    }

    /**
     * Httpclient Post
     *
     * @param url 链接
     * @param params 参数
     * @param data requestBody参数
     * @param httpConfig http配置
     * @return 字符串结果
     */
    private static final <T> T postHttpEntity(IKhHttpEntityProcessor<T> processor,  String url, Map<String, String> params, String data, KhHttpConfig httpConfig, Map<String, String> headerParams) throws Exception {

        logger.debug("[KhHttpUtil][postRequestWithException]entering, url:{}, params:{}, data:{}, httpConfig:{}", url, params, data, httpConfig);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> formParams = new ArrayList<>();

        CloseableHttpResponse httpResponse = null;
        try {
            HttpPost httpPost = new HttpPost(url);

            if (StringUtils.isNotBlank(data)) {
                //requestBody有内容
                StringEntity dataEntity = new StringEntity(data, CHARSET_UTF8);
                httpPost.addHeader("Content-Type", "text/plain");
                httpPost.setEntity(dataEntity);
                //此时参数需要加入到链接中
                if (params != null) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        url = UrlUtil.addParameter(url, entry.getKey(), entry.getValue());
                    }
                    httpPost.setURI(new URI(url));
                }
            } else {
                //requestBody无内容，则使用标准的form表单提交
                UrlEncodedFormEntity formEntity = null;
                if (params != null && params.size() > 0) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                    formEntity = new UrlEncodedFormEntity(formParams, CHARSET_UTF8);
                    httpPost.setEntity(formEntity);
                }
            }

            if (MapUtils.isNotEmpty(headerParams)) {
                //设置请求头
                for(Map.Entry<String, String> entry: headerParams.entrySet()){
                    String key = entry.getKey();
                    String value = entry.getValue();
                    httpPost.setHeader(key, value);
                }
            }

            //设置请求和传输超时时间
            int requestTimeout = httpConfig == null || httpConfig.getRequestTimeOutMillis() <= 0 ? DEFAULT_REQUEST_TIMEOUT : httpConfig.getRequestTimeOutMillis();
            int soTimeout = httpConfig == null || httpConfig.getSoTimeOutMillis() <= 0 ? DEFAULT_SO_TIMEOUT : httpConfig.getSoTimeOutMillis();

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(requestTimeout).setSocketTimeout(soTimeout).build();
            httpPost.setConfig(requestConfig);

            httpResponse = httpClient.execute(httpPost);

            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode >= HTTP_STATUS_ERROR_CODE) {
                //有错误
                throw new KhRuntimeException(IKhErrorCode.SYSTEM_NETWORK_REQUEST_ERROR, "网络请求异常", null);
            } else {
                // 获取返回数据
                HttpEntity httpEntity = httpResponse.getEntity();
                T result =  processor.processHttpEntity(httpEntity);
                logger.info("[KhHttpUtil][postRequest]result:{}, url:{}, httpConfig:{}", result, url, httpConfig);
                return result;
            }
        }finally {
            try {
                if(httpClient!=null) {
                    httpClient.close();
                }
                if(httpResponse!=null){
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * httpEntity处理器接口
     * 供扩展，产出需要的数据结果
     * @param <T>
     */
    interface IKhHttpEntityProcessor<T> {
        /**
         * 处理响应
         * @param httpEntity
         * @return
         * @throws IOException
         */
        T processHttpEntity(HttpEntity httpEntity) throws IOException;
    }

    /**
     * string类的处理器
     */
    static class HttpEntityStringProcessor implements IKhHttpEntityProcessor<String> {

        @Override public String processHttpEntity(HttpEntity httpEntity) throws IOException {
            String result = EntityUtils.toString(httpEntity);
            return result;
        }
    }

    /**
     * bytes类的处理器
     */
    static class HttpEntityBytesProcesor implements IKhHttpEntityProcessor<byte[]> {

        @Override public byte[] processHttpEntity(HttpEntity httpEntity) throws IOException {
            InputStream is = httpEntity.getContent();
            int i;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((i = is.read(buffer)) != -1) {
                bos.write(buffer, 0, i);
            }
            byte[] result = bos.toByteArray();
            return result;
        }
    }


}
