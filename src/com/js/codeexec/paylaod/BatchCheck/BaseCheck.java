/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.codeexec.paylaod.BatchCheck;


import com.js.codeexec.frame.Main;
import com.js.codeexec.paylaod.BasePayload;
import com.js.codeexec.paylaod.CVE_2017_10271_12;
import java.awt.EventQueue;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shack2
 */
public class BaseCheck implements Runnable {
    public int index=0;
     public String url="";
     public JTable table=null;
     public BasePayload bp=null;
     public void init(BasePayload bp){
         this.bp=bp;
     }
    @Override
    public void run() {
        try {
            boolean isOk=bp.checkVUL(url);
            if(isOk){
                 EventQueue.invokeLater(new Runnable() {
                        @Override
			public void run() {
                            DefaultTableModel dtm=(DefaultTableModel)table.getModel();
                            dtm.addRow(new Object[]{index,"Weblogic",url,"Yes"});
                        }
                 });
            }
        } catch (Exception ex) {
            
        }
        Main.count.incrementAndGet();
    }
   
    
}
