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
import model.Ca;

/**
 *
 * @author macbookpro
 */
public class systemDAO {
    public static systemDAO  getInstance() {
        return new systemDAO();
    }
    public boolean getTinhTrang(){
        boolean tinhTrang = false;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT tinhTrang FROM `system`";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                 tinhTrang = kq.getBoolean(1);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tinhTrang;
    }
    
    public void Update(int tinhTrang, String user, String macv){
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE `system` SET `userName`= ?,`maChucVu`= ?,`tinhTrang`= ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, macv);
            st.setInt(3, tinhTrang);
            System.out.println(st);
            st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String getChucVu(){
        String maCV = "";
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT  `maChucVu` FROM `system`";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            System.out.println(st);
            ResultSet kq = st.executeQuery();
            
            while (kq.next()) {
                
                maCV = kq.getString(1);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return maCV;
    }
    
    public String getUser(){
        String user = "";
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT  `userName` FROM `system`";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            System.out.println(st);
            ResultSet kq = st.executeQuery();
            
            while (kq.next()) {
                
               user = kq.getString(1);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
   
}
