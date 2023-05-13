/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.CaDAO;
import dao.LoaiSanhDAO;
import database.JDBCUtil;
import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import model.Ca;
import model.LoaiSanh;
import model.Sanh;

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

        Ca ca = new Ca("CA001", "Ca sáng", Time.valueOf("07:00:00"), Time.valueOf("15:00:00"));
        CaDAO.getInstance().Insert(ca);
        ArrayList<Ca> lstCa = CaDAO.getInstance().SelectAll();
        for (Ca x : lstCa) {
            System.out.println(x.toString());
        }
    }
}