/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCUtil;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class NhanVienDAO implements DAOInterface<TaiKhoan> {

    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    public static byte[] hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedPassword = password.getBytes(StandardCharsets.UTF_8);
        byte[] hashedPassword = digest.digest(encodedPassword);
        return hashedPassword;
    }

    @Override
    public int Insert(TaiKhoan t) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO TaiKhoan (userName, passWord, maChucVu) VALUES (?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);

            String password = "uit123";
            byte[] hashedPassword = hashPassword(password);

            st.setString(1, t.getUserName());
            st.setString(2, new String(hashedPassword));
            st.setString(3, t.getMaChucVu());

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
    public int Delete(TaiKhoan t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM TaiKhoan WHERE userName = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getUserName());

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
    public int Update(TaiKhoan t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE TaiKhoan SET maChucVu = ? WHERE userName = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaChucVu());
            st.setString(2, t.getUserName());

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

    public int UpdatePassword(TaiKhoan t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE TaiKhoan SET password = ? WHERE userName = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getPassWord());
            st.setString(2, t.getUserName());

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
    public ArrayList<TaiKhoan> SelectAll() {
        ArrayList<TaiKhoan> lstNhanVien = new ArrayList<TaiKhoan>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM TaiKhoan, ChucVu WHERE TaiKhoan.maChucVu = ChucVu.maChucVu";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstNhanVien.add(new TaiKhoan(kq.getString("userName"), kq.getString("passWord"), kq.getString("maChucVu"), kq.getString("tenChucVu")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstNhanVien;
    }
    
    public String getMaCV(String user){
        String macv = "";
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT  `maChucVu` FROM `TaiKhoan` WHERE `userName` = ?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user);
            ResultSet kq = st.executeQuery();

            while (kq.next()) {
               macv = kq.getString(1);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return macv;
    }

    @Override
    public TaiKhoan SelectById(TaiKhoan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<TaiKhoan> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
