/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCUtil;
import java.util.ArrayList;
import model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Asus
 */
public class NhanVienDAO implements DAOInterface<NhanVien> {

    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    @Override
    public int Insert(NhanVien t) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO NhanVien (userName, passWord, maChucVu) VALUES (?, ?, ?)";
            

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getUserName());
            st.setString(2, t.getPassWord());
            st.setString(3, t.getMaChucVu());

            System.out.println(st);

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Them du lieu thanh cong!");
            } else {
                System.out.println("Them du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(NhanVien t) {
        try {
            Connection con = JDBCUtil.getConnection();
 
            
            String sql = "DELETE FROM NhanVien WHERE userName = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
             st.setString(1, t.getUserName());
            
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Xoa du lieu thanh cong!");
            } else{
                System.out.println("Xoa du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;
    }

    @Override
    public int Update(NhanVien t) {
         try {
            Connection con = JDBCUtil.getConnection();
 
            
            String sql = "UPDATE FROM NhanVien SET maChucVu = ? WHERE userName = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getMaChucVu());
            st.setString(2, t.getUserName());
            
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Cap nhat du lieu thanh cong!");
            } else{
                System.out.println("Cap nhat du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;
    }

    @Override
    public ArrayList<NhanVien> SelectAll() {
        ArrayList<NhanVien> lstNhanVien = new ArrayList<NhanVien>();
       try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM NhanVien";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            ResultSet kq = st.executeQuery();
            
            while(kq.next()){
                lstNhanVien.add(new NhanVien(kq.getString("userName"),kq.getString("passWord"), kq.getString("maChucVu")));
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstNhanVien;
    }

    @Override
    public NhanVien SelectById(NhanVien t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<NhanVien> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}