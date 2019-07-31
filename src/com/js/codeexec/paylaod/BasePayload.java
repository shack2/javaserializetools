/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.codeexec.paylaod;

/**
 *
 * @author shack2
 */
public interface BasePayload {
    
    boolean  checkVUL(String url) throws Exception;
    String exeCMD(String url,String cmd,String encoding) throws Exception;
    String getWebPath(String url) throws Exception;
    String uploadFile(String url,String fileContent,String fileName,boolean useUserPath) throws Exception;

}
