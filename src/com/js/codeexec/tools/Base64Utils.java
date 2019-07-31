/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.codeexec.tools;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shack2
 */
public class Base64Utils {
    public static String encode(String txt){
        try {
            return Base64.getEncoder().encodeToString(txt.getBytes("UTF-8"));
        } catch (Exception ex) {
            Logger.getLogger(Base64Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
