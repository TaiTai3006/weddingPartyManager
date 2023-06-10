/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChucVu;
import model.CongViec;

/**
 *
 * @author Asus
 */
public class CongViecDAO implements DAOInterface<CongViec> {
    public static CongViecDAO getInstance() {
        return new CongViecDAO();
    }

    @Override
    public int Insert(CongViec t) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO CongViec (maCongViec, tenCongViec, maDichVu) VALUES (?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);


            st.setString(1, t.getMaCongViec());
            st.setString(2, t.getTenCongViec());
            st.setString(3, t.getMaDichVu());

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
    public int Update(CongViec t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE CongViec SET tenCongViec = ?, maDichVu = ? WHERE maCongViec = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getTenCongViec());
            st.setString(2, t.getMaDichVu());
            st.setString(3, t.getMaCongViec());

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Cap nhat du lieu thanh cong!");
            } else {
                System.out.println("Cap nhat du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(CongViec t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM CongViec WHERE maCongViec = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaCongViec());

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Xoa du lieu thanh cong!");
            } else {
                System.out.println("Xoa du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<CongViec> SelectAll() {
        ArrayList<CongViec> lstCongViec = new ArrayList<CongViec>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CongViec";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                String maCongViec = kq.getString("maCongViec");
                String tenCongViec = kq.getString("tenCongViec");
                String maDichVu = kq.getString("maDichVu");

                lstCongViec.add(new CongViec(maCongViec, tenCongViec, maDichVu));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstCongViec;
    }
    
    public ArrayList<CongViec> getCongViecPhanCong(String maTC){
         ArrayList<CongViec> congViecs = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT maCongViec, `tenCongViec` FROM `CongViec` WHERE maDichVu IS null OR maDichVu IN (SELECT maDichVu FROM PhieuDatTiecCuoi, ChiTietDichVu "
                    + "WHERE PhieuDatTiecCuoi.maTiecCuoi = ChiTietDichVu.maTiecCuoi AND PhieuDatTiecCuoi.maTiecCuoi = ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maTC);
            ResultSet kq = st.executeQuery();
            while (kq.next()) {
                congViecs.add(new CongViec(kq.getString(1), kq.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongViecDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return congViecs;
    }
    public int getSoLuongCongViec(String maCV, String maTC){
        int sl = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT SUM(ChiTietDichVu.soLuong) FROM CongViec, ChiTietDichVu "
                    + "WHERE CongViec.maDichVu = ChiTietDichVu.maDichVu AND CongViec.maCongViec = ? AND ChiTietDichVu.maTiecCuoi = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maCV);
             st.setString(2, maTC);
             System.out.println(st);
            ResultSet kq = st.executeQuery();
            while (kq.next()) {
                sl = kq.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongViecDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }
    @Override
    public CongViec SelectById(CongViec t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CongViec> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
