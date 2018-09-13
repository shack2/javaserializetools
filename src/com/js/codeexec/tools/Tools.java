/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.codeexec.tools;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author shack2
 */
public class Tools {
    public static void setToScreenCenter(Window window){
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     Dimension windowSize = window.getSize();
     if (windowSize.height > screenSize.height)
          windowSize.height = screenSize.height;
     if (windowSize.width > screenSize.width)
          windowSize.width = screenSize.width;
     window.setLocation((screenSize.width-windowSize.width)/2,(screenSize.height-windowSize.height)/2);
    }
     //验证并修改域名格式
     public static String checkTheDomain(String weburl){
           if ("".equals(weburl.trim())){
               return "";
           }
           if (!weburl.startsWith("http")){
              weburl="http://"+weburl;
           }
           if (!weburl.endsWith("/")){
               //移除最后一个/
               weburl = weburl+"/";
           }
           return weburl;
     }
     //验证并修改域名格式
     public static boolean checkTheURL(String weburl){
           if ("".equals(weburl.trim())){
               return false;
           }
           if(!weburl.startsWith("http")){
             return false;
           } 
           return true;
     }
     
     private static boolean match( String regex ,String str ){
        Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher( str );
        return matcher.matches();
    }
     
     public static String getDate(){
        Date d = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return sdf.format(d); 
     }
     
     public static HashSet<String> read(String path,String encode){
		
		HashSet<String> list=new HashSet<String>();
		
		FileInputStream fs;
		
		try {
			
			fs = new FileInputStream(new File(path));
			
			InputStreamReader isr=null;
			
			if(encode.equals("")){
				isr=new InputStreamReader(fs);
			}
			else{
				isr=new InputStreamReader(fs,encode);
			}
			
			BufferedReader br=new BufferedReader(isr);
		
			String tem=null;
			
			while((tem=br.readLine())!=null){
                                tem=checkTheDomain(tem);
				if(!list.contains(tem)){
                                    list.add(tem);
                                }
			}
		
			br.close();
			isr.close();
		
		} catch (Exception e) {
			
		}
		
		return list;
	}

}
