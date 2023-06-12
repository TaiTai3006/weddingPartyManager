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
import model.ChiTietDichVu;
import model.ChiTietMonAn;

/**
 *
 * @author Asus
 */
public class ChiTietMonAnDAO implements DAOInterface<ChiTietMonAn> {

    public static ChiTietMonAnDAO getInstance() {
        return new ChiTietMonAnDAO();
    }

    @Override
    public int Insert(ChiTietMonAn t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO `ChiTietMonAn`(`maMonAn`, `maTiecCuoi`, `donGiaMonAn`, `soLuong`, `ghiChu`) VALUES (?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaMonAn());
            st.setString(2, t.getMaTiecCuoi());
            st.setLong(3, t.getDonGiaMonAn());
            st.setInt(4, t.getSoLuong());
            st.setString(5, t.getGhiChu());

            System.out.println(st);

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Them du lieu thanh cong!");
            } else {
                System.out.println("Them du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Update(ChiTietMonAn t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Delete(ChiTietMonAn t) {
         try {
            Connection con = JDBCUtil.getConnection();
 
            
            String sql = "DELETE FROM `ChiTietMonAn` WHERE maMonAn = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
             st.setString(1, t.getMaMonAn());
            
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Xoa du lieu thanh cong!");
            } else{
                System.out.println("Xoa du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
            
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;
    }

    @Override
    public ArrayList<ChiTietMonAn> SelectAll() {
        ArrayList<ChiTietMonAn> lstDetailFoods = new ArrayList<ChiTietMonAn>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuDatTiecCuoi pd, ChiTietMonAn ctma, MonAn ma"
                    + " WHERE pd.maTiecCuoi = ctma.maTiecCuoi AND ctma.maMonAn = ma.maMonAn";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstDetailFoods.add(new ChiTietMonAn(kq.getString("maTiecCuoi"), kq.getString("maMonAn"), kq.getLong("donGiaMonAn"), kq.getInt("soLuong"),
                        kq.getString("ghiChu"), kq.getString("tenMonAn"), kq.getString("maLoaiMonAn")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstDetailFoods;
    }
    
    public ArrayList<ChiTietMonAn> SelectAllMA() {
        ArrayList<ChiTietMonAn> lstDetailFoods = new ArrayList<ChiTietMonAn>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM ChiTietMonAn CTMA, MonAn MA WHERE CTMA.maMonAn = MA.maMonAn";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstDetailFoods.add(new ChiTietMonAn(kq.getString("maTiecCuoi"), kq.getString("maMonAn"), kq.getLong("donGiaMonAn"), kq.getInt("soLuong"),
                        kq.getString("ghiChu"), kq.getString("tenMonAn"), kq.getString("maLoaiMonAn")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstDetailFoods;
    }

    @Override
    public ChiTietMonAn SelectById(ChiTietMonAn t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChiTietMonAn> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
