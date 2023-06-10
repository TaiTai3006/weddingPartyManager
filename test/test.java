/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.CaDAO;
import dao.EmployeeDAO;
import dao.LoaiSanhDAO;
import dao.NhanVienDAO;
import database.JDBCUtil;
import java.io.File;
import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ca;
import model.LoaiSanh;
import model.TaiKhoan;
import model.Sanh;
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
//        LoaiSanh loaiSanh = new LoaiSanh("T003", "haha", 5000);
//        LoaiSanhDAO.getInstance().Insert(loaiSanh);
//        ArrayList<LoaiSanh> loaiSanhs = LoaiSanhDAO.getInstance().SelectAll();
//        for (LoaiSanh x : loaiSanhs) {
//            System.out.println(x.toString());
//        }

//        Ca ca = new Ca("CA001", "Ca sáng", Time.valueOf("07:00:00"), Time.valueOf("15:00:00"));
//        CaDAO.getInstance().Insert(ca);
//        ArrayList<Ca> lstCa = CaDAO.getInstance().SelectAll();
//        for (Ca x : lstCa) {
//            System.out.println(x.toString());
//        }
        
//        TaiKhoan nv = new TaiKhoan("Sgg2003", "", "ST001", "Staff");
//        NhanVienDAO.getInstance().Insert(nv);
//        ArrayList<NhanVien> lstNhanVien = NhanVienDAO.getInstance().SelectAll();
//        for (TaiKhoan x : lstNhanVien) {
//            System.out.println(x.toString());
//        }

        
//        try {
//                HashMap<String, Object> map = new HashMap<>();
//                Connection con = JDBCUtil.getConnection();
//                map.put("maTiecCuoi", "TC0001");
//                
//                JasperPrint p = JasperFillManager.fillReport("src/report/rptPhieuDatTiec.jasper", map, con);
//                JasperViewer v = new JasperViewer(p, false);
//                v.setVisible(true);
//
//            } catch (JRException ex) {
//                System.out.println(ex);
//
//            }
//        try {
//                HashMap<String, Object> map = new HashMap<>();
//                Connection con = JDBCUtil.getConnection();
//                map.put("maHD", "HD0001");
//                
//                JasperPrint p = JasperFillManager.fillReport("src/report/rptThanhToanHDH.jasper", map, con);
//                JasperViewer v = new JasperViewer(p, false);
//                v.setVisible(true);
//
//            } catch (JRException ex) {
//                System.out.println(ex);
//
//         

    }
}
