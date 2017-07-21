package com.heshan.framework.common.http;


import com.heshan.framework.common.constant.IBaseConstant;
import com.heshan.framework.common.entity.Entry;
import com.heshan.framework.log.LogUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtils {

    private static final String AJAX_REQUEST_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_REQUEST_HEADER_VALUE = "XMLHttpRequest";
    private static final String AJAX_REQUEST_URI_PREFIX = "ajax-";
    private static final int HTTPCLIENT_TIMEOUT = 90000;

    public static boolean isAjax(HttpServletRequest request) {
        String uri = request.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/") + 1);
        return AJAX_REQUEST_HEADER_VALUE.equals(request.getHeader(AJAX_REQUEST_HEADER_NAME))
                || uri.startsWith(AJAX_REQUEST_URI_PREFIX);
    }

    public static String getLocalIP() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }

    /**
     * 
     * @【方法名称】 post
     * @【方法描述】 post 发送请求
     * @【适用条件】 返回数据中为 字符串
     * @【执行流程】
     * @【注意事项】
     * @param sendURL 发送路径 params发送参数 无序 key=value
     * @return Entry 属性 code=SUCCESS 则 msg=返回字符串
     * @Exception
     */
    public static Entry<String, String> post(String sendURL, Map<String, String> params) {
        return post(sendURL, params, IBaseConstant.DEFAULT_CHARSET);
    }

    /**
     * 
     * @【方法名称】 post
     * @【方法描述】 post 发送请求
     * @【适用条件】 返回数据中为 字符串
     * @【执行流程】
     * @【注意事项】
     * @param sendURL 发送路径 params发送参数 无序 key=value charset post请求与返回字符类型
     * @return ReturnInfoAdapter 属性 returnCode=SUCCESS 则 returnMsg= 返回字符串
     * @Exception
     */
    public static Entry<String, String> post(String sendURL, Map<String, String> params, Charset charset) {
        return post(sendURL, params, charset, null);
    }

    /**
     * 
     * @Title: postReturnByte
     * @param sendURL
     * @param encoding
     * @param contentType
     * @return Entry<String,byte[]> 返回类型
     * @throws
     */
    public static Entry<String, String> post(String sendURL, Map<String, String> params, Charset encoding,
            String contentType) {
        return post(sendURL, getNameValueList(params), encoding, contentType);
    }

    /**
     * 
     * @Title: postReturnByte
     * @param sendURL
     * @param nameValueList
     * @param encoding
     * @param contentType
     * @return Entry<String,byte[]> 返回类型
     * @throws
     */
    public static Entry<String, String> post(String sendURL, List<NameValuePair> nameValueList, Charset encoding,
            String contentType) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        return post(sendURL, nameValueList, encoding, contentType, httpclient, false);
    }

    /**
     * 
     * @Title: postReturnByte
     * @param sendURL
     * @param nameValueList
     * @param encoding
     * @param contentType
     * @return Entry<String,byte[]> 返回类型
     * @throws
     */
    public static Entry<String, String> post(String sendURL, List<NameValuePair> nameValueList, Charset encoding,
            String contentType, CloseableHttpClient httpclient, boolean isPool) {

        Entry<String, String> result = new Entry<String, String>(Entry.ERROR, "");

        Long longTime = System.currentTimeMillis();
        result.setExt(longTime);

        LogUtils.serviceApiLog("HttpReq: [time =" + longTime + ", reqUrl=" + sendURL + ",params=" + nameValueList + "]");

        /** time totals */
        StopWatch watch = new StopWatch();
        watch.start();

        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        HttpPost httpPost = null;

        try {

            httpPost = new HttpPost(sendURL);
            httpPost.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-cn");

            RequestConfig config = RequestConfig.custom().setSocketTimeout(HTTPCLIENT_TIMEOUT)
                    .setConnectionRequestTimeout(HTTPCLIENT_TIMEOUT).setConnectTimeout(HTTPCLIENT_TIMEOUT).build();
            httpPost.setConfig(config);
            httpPost.setEntity(getUrlEncoded(nameValueList, encoding, contentType));

            response = httpclient.execute(httpPost);
            if (response == null) {
                return result;
            }

            Integer respStatus = response.getStatusLine().getStatusCode();

            try {

                if (HttpStatus.SC_OK == respStatus) {

                    result.setCode(Entry.SUCCESS);
                    entity = response.getEntity();
                    if (entity != null) {
                        byte[] byteStr = IOUtils.toByteArray(entity.getContent());
                        if (null != byteStr) {
                            result.setMsg(new String(byteStr, encoding));
                        }

                    }
                } else {
                    result.put(Entry.ERROR, response.getStatusLine().toString());
                }

            } finally {
                try {
                    if (entity != null) {
                        IOUtils.closeQuietly(entity.getContent());
                        EntityUtils.consume(entity);
                    }
                    response.close();
                } catch (IOException e) {
                    LogUtils.error("close(" + longTime + ")", new Throwable(e));
                }
            }

        } catch (Exception e) {
            LogUtils.error("send(" + longTime + ")", new Throwable(e));
            // 异常返回
            result.put(Entry.ERROR, e.getMessage());
        } finally {
            if (null != httpPost) {
                httpPost.releaseConnection();
                httpPost = null;
            }
            if (!isPool) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    LogUtils.error("close(" + longTime + ")", new Throwable(e));
                }
            }
        }

        LogUtils.serviceApiLog("HttpResp: [time =" + longTime + ",totalTime=" + watch.getTime() + ", reqUrl="
                + sendURL + ",response=" + result + "]");
        return result;

    }

    public static Entry<String, String> doGet(String reqUrl, List<NameValuePair> nameValueList, Charset encoding,CloseableHttpClient httpclient,
            boolean isPool) {

        Entry<String, String> result = new Entry<String, String>(Entry.ERROR, "");

        String apiUrl = reqUrl;
        StringBuffer param = new StringBuffer();
        if (nameValueList != null && nameValueList.size() > 0) {
            int i = 0;
            for (NameValuePair  tmpPair: nameValueList) {
                if (i == 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }
                param.append(tmpPair.getName()).append("=").append(tmpPair.getValue());
                i++;
            }
            apiUrl += param;
        }

        Long longTime = System.currentTimeMillis();
        result.setExt(longTime);

        LogUtils.serviceApiLog("HttpReq: [time =" + longTime + ", reqUrl=" + reqUrl + ",params=" + param + "]");

        /** time totals */
        StopWatch watch = new StopWatch();
        watch.start();

        HttpEntity entity = null;
        HttpResponse response = null;
        HttpGet httpPost = null;

        try {

            httpPost = new HttpGet(apiUrl);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(HTTPCLIENT_TIMEOUT)
                    .setConnectionRequestTimeout(HTTPCLIENT_TIMEOUT).setConnectTimeout(HTTPCLIENT_TIMEOUT).build();
            httpPost.setConfig(config);
            response = httpclient.execute(httpPost);

            if (response == null) {
                return result;
            }

            Integer respStatus = response.getStatusLine().getStatusCode();

            try {

                if (HttpStatus.SC_OK == respStatus) {

                    result.setCode(Entry.SUCCESS);
                    entity = response.getEntity();
                    if (entity != null) {
                        byte[] byteStr = IOUtils.toByteArray(entity.getContent());
                        if (null != byteStr) {
                            result.setMsg(new String(byteStr, encoding));
                        }

                    }
                } else {
                    result.put(Entry.ERROR, response.getStatusLine().toString());
                }

            } finally {
                try {
                    if (entity != null) {
                        IOUtils.closeQuietly(entity.getContent());
                        EntityUtils.consume(entity);
                    }
                } catch (IOException e) {
                    LogUtils.error("close(" + longTime + ")", new Throwable(e));
                }
            }
        } catch (Exception e) {
            LogUtils.error("send(" + longTime + ")", new Throwable(e));
            // 异常返回
            result.put(Entry.ERROR, e.getMessage());
        } finally {
            if (null != httpPost) {
                httpPost.releaseConnection();
                httpPost = null;
            }
            if (!isPool) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    LogUtils.error("close(" + longTime + ")", new Throwable(e));
                }
            }
        }

        LogUtils.serviceApiLog("HttpGetResp: [time =" + longTime + ",totalTime=" + watch.getTime() + ", reqUrl=" + apiUrl
                + ",response=" + result + "]");
        return result;
    }
    /**
     * @Title: getNameValueList
     * @param params
     * @return List<NameValuePair> 返回类型
     * @throws
     */
    private static List<NameValuePair> getNameValueList(Map<String, String> params) {

        List<NameValuePair> result = new ArrayList<NameValuePair>();

        if (null != params && !params.isEmpty()) {

            Set<Map.Entry<String, String>> paramsSet = params.entrySet();

            for (Map.Entry<String, String> entry : paramsSet) {
                result.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return result;
    }

    /**
     * 
     * @Title: GetField
     * @param @param params
     * @param @param encoding
     * @param @param contentType
     * @param @return 设定文件
     * @return UrlEncodedFormEntity 返回类型
     * @throws
     */
    private static UrlEncodedFormEntity getUrlEncoded(List<NameValuePair> nvps, Charset encoding, String contentType) {

        if (nvps == null) {
            nvps = new ArrayList<NameValuePair>();
        }

        UrlEncodedFormEntity result = new UrlEncodedFormEntity(nvps, encoding);
        if (null != contentType && !contentType.isEmpty()) {
            result.setContentType(contentType);
        }
        return result;
    }
}
