package com.js.codeexec.tools;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate; 
import javax.net.ssl.X509TrustManager; 
public class MyCERT implements X509TrustManager { 
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException { 
    // TODO Auto-generated method stub 
    } 
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException { 
        // TODO Auto-generated method stub 
    } 
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub 
        return null;
    } 
}