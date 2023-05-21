/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.DichVu;
import java.sql.Connection;
import database.JDBCUtil;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author macbookpro
 */
public class DichVuDAO implements DAOInterface<DichVu> {

    public static DichVuDAO getInstance() {
        return new DichVuDAO();
    }

    @Override
    public int Insert(DichVu t) {

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO DichVu (maDichVu, tenDichVu, donGia) VALUES (?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaDichVu());
            st.setString(2, t.getTenDichVu());
            st.setInt(3, t.getDonGia());

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
    public int Update(DichVu t) {

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE DichVu SET "
                    + "tenDichVu =?, donGia =? "
                    + "WHERE maDichVu =?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getTenDichVu());
            st.setInt(2, t.getDonGia());
            st.setString(3, t.getMaDichVu());

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Cap nhat du lieu thanh cong!");
            } else {
                System.out.println("cap nhat du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(DichVu t) {

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM DichVu "
                    + "WHERE maDichVu = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaDichVu());

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
    public ArrayList<DichVu> SelectAll() {
        ArrayList<DichVu> dichVus = new ArrayList<DichVu>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM DichVu";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                dichVus.add(new DichVu(kq.getString("maDichVu"), kq.getString("tenDichVu"), kq.getInt("donGia")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dichVus;
    }

    @Override
    public DichVu SelectById(DichVu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DichVu> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
