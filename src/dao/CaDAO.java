/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCUtil;
import model.Ca;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Time;

/**
 *
 * @author Asus
 */
public class CaDAO implements DAOInterface<Ca> {

    public static CaDAO getInstance() {
        return new CaDAO();
    }

    public int Insert(Ca t) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO Ca (maCa, tenCa, gioBatDau, gioKetThuc) VALUES (?, ?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaCa());
            st.setString(2, t.getTenCa());
            st.setTime(3, t.getGioBatDau());
            st.setTime(4, t.getGioKetThuc());

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
    public int Delete(Ca t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM Ca WHERE maCa = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaCa());

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
    public int Update(Ca t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE Ca SET tenCa = ?, gioBatDau = ?, gioKetThuc = ? WHERE maCa = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getTenCa());
            st.setTime(2, t.getGioBatDau());
            st.setTime(3, t.getGioKetThuc());
            st.setString(4, t.getMaCa());

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
    public ArrayList<Ca> SelectAll() {
        ArrayList<Ca> lstCa = new ArrayList<Ca>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM Ca";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstCa.add(new Ca(kq.getString("maCa"), kq.getString("tenCa"), kq.getTime("gioBatDau"), kq.getTime("gioKetThuc")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstCa;
    }
    public Ca SelectBy_Id(String id) {
//        System.out.println("ID Ca:" + id);
        Ca ca0 = new Ca();
        try {
            Connection con = JDBCUtil.getConnection();
//            Statement a = con.createStatement();
            String sql = "SELECT * FROM Ca WHERE Ca.maCa='"+id+"'" ;
            
            PreparedStatement st = con.prepareStatement(sql);
            
            ResultSet kq = st.executeQuery();
            
            while(kq.next()){
                String maCa = kq.getString("maCa");
                String tenCa = kq.getString("tenCa");
                Time gioBatDau = kq.getTime("gioBatDau");
                Time gioKetThuc = kq.getTime("gioKetThuc");
                ca0.setMaCa(maCa);
                ca0.setTenCa(tenCa);
                ca0.setGioBatDau(gioBatDau);
                ca0.setGioKetThuc(gioKetThuc);
//                System.out.println(ca0.getGioBatDau());
//                ca0 = new Ca(maCa, tenCa, gioBatDau, gioKetThuc);
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ca0;
    }

    @Override
    public Ca SelectById(Ca t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Ca> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
