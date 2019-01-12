package com.superblock.sdk.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 描述：http工具类
 * 创建人：yoan
 * 创建日期：2018/10/8
 */
public class HttpTool {

    /**
     * 发送get请求
     *
     * @param httpUrl 请求url
     * @param param   请求参数
     * @param charset 请求字符编码
     * @return
     */
    public static String get(String httpUrl, Map<String, Object> param, String charset) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 连接参数
            String paramString = generateParamUrl(param, charset);
            // 创建远程url连接对象
            URL url = new URL(realHttpUrl(httpUrl + paramString));
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：30000毫秒
            connection.setConnectTimeout(30000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 关闭请求缓存
            connection.setUseCaches(false);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, charset));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    /**
     * 发送post请求
     *
     * @param httpUrl 请求url
     * @param body    请求参数
     * @param charset 请求字符编码
     * @return
     */
    public static String post(String httpUrl, String body, String charset) {
        DataOutputStream out = null;
        BufferedReader reader = null;
        StringBuilder resultBuilder = new StringBuilder();
        try {
            URL url = new URL(realHttpUrl(httpUrl));
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：post
            connection.setRequestMethod("POST");
            // 设置连接主机服务器的超时时间：30000毫秒
            connection.setConnectTimeout(30000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 关闭请求缓存
            connection.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 设置通用属性
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", charset);
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(body.length()));
            // 获取URLConnection对象对应的输出流
            out = new DataOutputStream(connection.getOutputStream());
            // 发送请求参数
            out.write(body.getBytes(charset));
            // flush输出流的缓冲
            out.flush();
            out.close();
            // 定义BufferedReader输入流来读取URL的响应
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    resultBuilder.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return resultBuilder.toString();
    }

    /**
     * 构造正确的http url
     *
     * @param url
     * @return
     */
    public static String realHttpUrl(String url) {
        if (url.startsWith("http://")) {
            return url;
        } else {
            return "http://" + url;
        }
    }

    /**
     * 构建请求参数字符串
     *
     * @param param   参数map
     * @param charset 参数编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String generateParamUrl(Map<String, Object> param, String charset) throws UnsupportedEncodingException {
        StringBuilder paramUrl = new StringBuilder();
        boolean isFirst = true;
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if (isFirst) {
//                paramUrl.append("?");
                isFirst = false;
            } else {
                paramUrl.append("&");
            }
            paramUrl.append(entry.getKey());
            paramUrl.append("=");
            paramUrl.append(URLEncoder.encode(String.valueOf(entry.getValue()), charset));
        }
        return paramUrl.toString();
    }

    /**
     * 从输入流获取字符数据
     *
     * @param inputStream
     * @return
     */
    public static String getStringFromInputSteam(InputStream inputStream) throws IOException {
        StringBuilder outBuilder = new StringBuilder();
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        int readSize = 0;
        char[] buffer = new char[2048];
        while ((readSize = reader.read(buffer, 0, buffer.length)) > 0) {
            outBuilder.append(buffer);
        }
        return outBuilder.toString();
    }
}
