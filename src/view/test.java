/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import database.JDBCUtil;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author macbookpro
 */
public class test {

    public static void main(String[] args) {
        File file = new File("src/report/rptPhieuDatTiec.jasper");
        String absolutePath = file.getAbsolutePath();
        System.out.println("Absolute Path: " + absolutePath);
        try {
            
            
           HashMap<String, Object> map = new HashMap<>();
                Connection con = JDBCUtil.getConnection();
                map.put("maTiecCuoi", "TC0014");
                map.put("tienKhachTra", 125000.0);
                map.put("tienThua", 0.0);
                map.put("tyLePhat", 1);
                map.put("thoiGianPhat", 7);
               
                JasperPrint p = JasperFillManager.fillReport(absolutePath, map, con);
                JasperViewer v = new JasperViewer(p, false);
                v.setVisible(true);

        } catch (JRException ex) {
            System.out.println("test.main()");
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
