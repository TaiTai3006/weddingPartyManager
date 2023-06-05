/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.BaoCaoDoanhThu;

/**
 *
 * @author ACER
 */
public class BaoCaoDoanhThuDAO {
    public static BaoCaoDoanhThuDAO getInstance() {
        return new BaoCaoDoanhThuDAO();
    }
    
    public ArrayList<BaoCaoDoanhThu> SelectByYear(int year) {
         ArrayList<BaoCaoDoanhThu> lstChiTietBaoCao = new ArrayList<BaoCaoDoanhThu>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM BaoCaoDoanhThu as bcdt, (SELECT SUM(tongDoanhThu) as Sum_tongDoanhThu FROM BaoCaoDoanhThu WHERE nam = ?) as SumTong WHERE bcdt.nam = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, year);
            st.setInt(2, year);
            
            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstChiTietBaoCao.add(new BaoCaoDoanhThu(kq.getString("maBaoCao"), kq.getInt("thang"), kq.getInt("nam"), 
                        kq.getDouble("tongDoanhThu"),  kq.getDouble("Sum_tongDoanhThu"), 0));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstChiTietBaoCao;
    }
    
        public ArrayList<BaoCaoDoanhThu> SelectByFromY1toY2(int year1, int year2) {
         ArrayList<BaoCaoDoanhThu> lstChiTietBaoCao = new ArrayList<BaoCaoDoanhThu>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT bcdt.nam, SUM(bcdt.tongDoanhThu) as tongDoanhThuNam, abc.Sum_tongDoanhThu FROM BaoCaoDoanhThu as bcdt, "
                    + "(SELECT SUM(tongDoanhThu) as Sum_tongDoanhThu FROM BaoCaoDoanhThu WHERE nam >= ? and nam <= ?) as abc "
                    + "WHERE bcdt.nam >= ? and bcdt.nam <= ? GROUP BY bcdt.nam";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, year1);
            st.setInt(2, year2);
            st.setInt(3, year1);
            st.setInt(4, year2);
            
            ResultSet kq = st.executeQuery();


            while (kq.next()) {
                lstChiTietBaoCao.add(new BaoCaoDoanhThu("", 0,  kq.getInt("nam"),
                        kq.getDouble("tongDoanhThuNam"), 0, kq.getDouble("Sum_tongDoanhThu")));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstChiTietBaoCao;
    }
}
