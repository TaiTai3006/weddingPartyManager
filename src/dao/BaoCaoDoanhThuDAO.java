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
    
    public int MaxValueYear()
    {
        int max = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT MAX(nam) as Max_Nam FROM BaoCaoDoanhThu";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            ResultSet kq = st.executeQuery();
            while (kq.next()) {
                max = kq.getInt("Max_Nam");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return max;
    }
    
    public int MinValueYear()
    {
        int min = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT MIN(nam) as Min_Nam FROM BaoCaoDoanhThu";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            ResultSet kq = st.executeQuery();
            while (kq.next()) {
                min = kq.getInt("Min_Nam");
            }


            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return min;
    }
    
    public ArrayList<BaoCaoDoanhThu> SelectByYear(int year) {
         ArrayList<BaoCaoDoanhThu> lstChiTietBaoCao = new ArrayList<BaoCaoDoanhThu>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM BaoCaoDoanhThu WHERE nam = ? ORDER by thang ASC";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, year);
//            st.setInt(2, year);
            
            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstChiTietBaoCao.add(new BaoCaoDoanhThu(kq.getString("maBaoCao"), kq.getInt("thang"), kq.getInt("nam"), 
                        kq.getDouble("tongDoanhThu"),  0, 0));
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

            String sql = "SELECT bcdt.nam, SUM(bcdt.tongDoanhThu) as tongDoanhThuNam FROM BaoCaoDoanhThu as bcdt "
                    + "WHERE bcdt.nam >= ? and bcdt.nam <= ? GROUP BY bcdt.nam ORDER by bcdt.nam ASC";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, year1);
            st.setInt(2, year2);
//            st.setInt(3, year1);
//            st.setInt(4, year2);
            
            ResultSet kq = st.executeQuery();


            while (kq.next()) {
                lstChiTietBaoCao.add(new BaoCaoDoanhThu("", 0,  kq.getInt("nam"),
                        kq.getDouble("tongDoanhThuNam"), 0, kq.getDouble("tongDoanhThuNam")));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstChiTietBaoCao;
    }
}
