/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.codeexec.tools;

import com.js.codeexec.paylaod.BasePayload;
import com.js.codeexec.paylaod.CVE_2019_2725_10_bypass;
import com.js.codeexec.paylaod.CVE_2019_2725_12;
import com.js.codeexec.paylaod.CVE_2017_10271_10;
import com.js.codeexec.paylaod.CVE_2017_10271_12;
import com.js.codeexec.paylaod.CVE_2019_2725_10;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     
     public static String reverse(String data){
        StringBuilder sb=new StringBuilder(data);
        return sb.reverse().toString(); 
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
     
     
      public static BasePayload getPayload(String select){
        BasePayload bp=null;
        if(select.startsWith("CVE-2017-10271 Weblogic10")){
            bp=new CVE_2017_10271_10();
        }
        else if(select.startsWith("CVE-2017-10271 Weblogic12")){
            bp=new CVE_2017_10271_12();
        }
        else if(select.startsWith("CVE-2019-2725 Weblogic10")){
            bp=new CVE_2019_2725_10();
        }
        else if(select.startsWith("CVE-2019-2725 Weblogic12")){
            bp=new CVE_2019_2725_12();
        }
        else if(select.startsWith("CVE-2019-2725-Bypass Weblogic10")){
            bp=new CVE_2019_2725_10_bypass();
        }
       
        return bp;
      }
      
      public static String str2Hex(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }
      
      /**
    * 16进制直接转换成为字符串(无需Unicode解码)
    * @param hexStr
    * @return
    */
   public static String hex2Str(String hexStr) {
       String str = "0123456789ABCDEF";
       char[] hexs = hexStr.toCharArray();
       byte[] bytes = new byte[hexStr.length() / 2];
       int n;
       for (int i = 0; i < bytes.length; i++) {
           n = str.indexOf(hexs[2 * i]) * 16;
           n += str.indexOf(hexs[2 * i + 1]);
           bytes[i] = (byte) (n & 0xff);
       }
       return new String(bytes);
   }
   
    public static String loadExp(String path){
        try {
            Properties pro = new Properties();
            FileInputStream in = new FileInputStream(path);
          pro.load(in);
            String exp=(String) pro.get("exp");
            return exp;
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
   }
    
  
    
   
   

}
