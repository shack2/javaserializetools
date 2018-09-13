/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.codeexec.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shack2
 */
public class HttpTool {
    private static int Timeout=10000;
    private static String DefalutEncoding="UTF-8";
    public static String httpRequest(String requestUrl,int timeOut,String requestMethod,String contentType, String postString,String encoding) throws Exception{
        if("".equals(encoding)||encoding==null){
            encoding=DefalutEncoding;
        }
        StringBuilder buffer = new StringBuilder();
        HttpURLConnection httpUrlConn=null;
        InputStream inputStream=null;
        BufferedInputStream bis=null;
        ByteArrayOutputStream baos=null;
        try {    
            URL url = new URL(requestUrl);    
            httpUrlConn = (HttpURLConnection) url.openConnection();    
            httpUrlConn.setReadTimeout(timeOut);
            if(contentType!=null&&!"".equals(contentType)){
                 httpUrlConn.setRequestProperty("Content-Type", contentType);
            }
            httpUrlConn.setRequestProperty("User-Agent","Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html）");
            httpUrlConn.setDoOutput(true);    
            httpUrlConn.setDoInput(true);        
            // 设置请求方式（GET/POST）    
            httpUrlConn.setRequestMethod(requestMethod);    
    
            httpUrlConn.connect();    
    
            // 当有数据需要提交时    
            if (null != postString&&!"".equals(postString)) {    
                OutputStream outputStream = httpUrlConn.getOutputStream();    
                // 注意编码格式，防止中文乱码    
                outputStream.write(postString.getBytes(encoding));    
                outputStream.close();    
            }    
    
            // 将返回的输入流转换成字符串    
            inputStream = httpUrlConn.getInputStream();    
            bis = new BufferedInputStream(inputStream);
            baos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[4096];
            while((len=bis.read(arr))!= -1){
                baos.write(arr,0,len);
                baos.flush();
            }
            return baos.toString(encoding);
        } catch (IOException e) {    
            // 将返回的输入流转换成字符串    
            inputStream = httpUrlConn.getErrorStream();    
            bis = new BufferedInputStream(inputStream);
            baos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[4096];
            while((len=bis.read(arr))!= -1){
                baos.write(arr,0,len);
                baos.flush();
            }
            return baos.toString(encoding);
        }catch (Exception e) {    
            throw e;
        }finally{
            if(baos!=null){
                baos.close();
            }
            if(bis!=null){
                bis.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            if(httpUrlConn!=null){
                httpUrlConn.disconnect();
            }
        } 
        
    }
    
    /**
     *
     * @param requestUrl
     * @param timeOut
     * @param requestMethod
     * @param contentType
     * @param postString
     * @param encoding
     * @return
     * @throws Exception
     */
    public static int codeByHttpRequest(String requestUrl,int timeOut,String requestMethod,String contentType, String postString,String encoding) throws Exception{
        if("".equals(encoding)||encoding==null){
            encoding=DefalutEncoding;
        }
        StringBuilder buffer = new StringBuilder();
        HttpURLConnection httpUrlConn=null;
        InputStream inputStream=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        try {    
            URL url = new URL(requestUrl);    
            httpUrlConn = (HttpURLConnection) url.openConnection();    
            httpUrlConn.setReadTimeout(timeOut);
            if(contentType!=null&&!"".equals(contentType)){
                 httpUrlConn.setRequestProperty("Content-Type", contentType);
            }
           
            httpUrlConn.setRequestProperty("User-Agent","Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html）");
            httpUrlConn.setDoOutput(true);    
            httpUrlConn.setDoInput(true);    
            httpUrlConn.setUseCaches(false);    
            // 设置请求方式（GET/POST）    
            httpUrlConn.setRequestMethod(requestMethod);    
    
            httpUrlConn.connect();    
    
            // 当有数据需要提交时    
            if (null != postString&&!"".equals(postString)) {    
                OutputStream outputStream = httpUrlConn.getOutputStream();    
                // 注意编码格式，防止中文乱码    
                outputStream.write(postString.getBytes(encoding));    
                outputStream.close();    
            }    
            return httpUrlConn.getResponseCode();

        } catch (Exception e) {    
            throw e;
        }finally{
            if(br!=null){
                br.close();
            }
            if(isr!=null){
                isr.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            if(httpUrlConn!=null){
                httpUrlConn.disconnect();
            }
        } 
       
    }
   
    public static String httpReuest(String requestUrl,String method,String contentType,String postString,String encoding) throws Exception{  
        return httpRequest(requestUrl,Timeout,method,contentType,postString,encoding);
    }
 
    public static String postHttpReuest(String requestUrl,int timeOut,String contentType,String postString,String encoding) throws Exception{  
        return httpRequest(requestUrl,timeOut,"POST",contentType,postString,encoding);
    }
    public static String postHttpReuest(String requestUrl,String contentType,String postString,String encoding) throws Exception{  
        return httpRequest(requestUrl,Timeout,"POST",contentType,postString,encoding);
    }
    
    public static String postHttpReuest(String requestUrl,int timeOut,String postString,String encoding) throws Exception{  
        return httpRequest(requestUrl,timeOut,"POST","application/x-www-form-urlencoded",postString,encoding);
    }
    public static String postHttpReuest(String requestUrl,String postString,String encoding) throws Exception{  
        return httpRequest(requestUrl,Timeout,"POST","application/x-www-form-urlencoded",postString,encoding);
    }
    
    public static String postHttpReuestByXML(String requestUrl,int timeOut,String postString,String encoding) throws Exception{  
        return httpRequest(requestUrl,timeOut,"POST","text/xml",postString,encoding);
    }
    public static String postHttpReuestByXML(String requestUrl,String postString,String encoding) throws Exception{  
        return httpRequest(requestUrl,Timeout,"POST","text/xml",postString,encoding);
    }
    
    public static int codeByHttpRequest(String requestUrl,String method,String contentType,String postString,String encoding) throws Exception{  
        return codeByHttpRequest(requestUrl,Timeout,method,contentType,postString,encoding);
    }
     
    public static int getCodeByHttpRequest(String requestUrl,String encoding) throws Exception{  
        return codeByHttpRequest(requestUrl,"GET",null,"",encoding);
    }
    public static int getCodeByHttpRequest(String requestUrl,int timeout,String encoding) throws Exception{  
        return codeByHttpRequest(requestUrl,timeout,"GET",null,"",encoding);
    }
    public static int postCodeByHttpRequest(String requestUrl,String contentType,String postString,String encoding) throws Exception{  
        return codeByHttpRequest(requestUrl,Timeout,"POST",contentType,postString,encoding);
    }
    public static int postCodeByHttpRequestWithNoContenType(String requestUrl,String postString,String encoding) throws Exception{  
        return codeByHttpRequest(requestUrl,Timeout,"POST",null,postString,encoding);
    }
     public static int postCodeByHttpRequest(String requestUrl,String encoding) throws Exception{  
        return codeByHttpRequest(requestUrl,Timeout,"POST",null,null,encoding);
    }
    public static int postCodeByHttpRequest(String requestUrl,String postString,String encoding) throws Exception{  
        return codeByHttpRequest(requestUrl,Timeout,"POST","application/x-www-form-urlencoded",postString,encoding);
    }
    public static int postCodeByHttpRequestXML(String requestUrl,String postString,String encoding) throws Exception{  
        return codeByHttpRequest(requestUrl,Timeout,"POST","text/xml",postString,encoding);
    }
    
    public static boolean downloadFile(String downURL, File file) throws Exception{
        HttpURLConnection httpURLConnection=null;
        BufferedInputStream bin=null;
        OutputStream out=null;
        try {
            // 统一资源
            URL url = new URL(downURL);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();

            bin = new BufferedInputStream(httpURLConnection.getInputStream());

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);                
            }
           
           
        } catch (Exception e) {
           throw e;
        }finally {
            if(bin!=null){
                
               bin.close();
            }
            if(out!=null){
                out.flush();
                out.close();
            }
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
        }
        return true;
    }
 public static boolean downloadFile(String downURL, String path) throws Exception{
       return downloadFile(downURL,new File(path));
    }
    
    public static void main(String args[]){
        try {
            String apiUrl="https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_13?SERVLET_NAME=B2CMainPlat_13&CCB_IBSVersion=V6&PT_STYLE=1&CUSTYPE=0&TXCODE=O10100";
            int code=postCodeByHttpRequest(apiUrl,"xx=aa","");
            System.out.println(code);
        } catch (Exception ex) {
            Logger.getLogger(HttpTool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
