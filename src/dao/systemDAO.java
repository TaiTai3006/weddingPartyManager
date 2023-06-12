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
    
    public void Update(int tinhTrang){
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE `system` SET `tinhTrang`= ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, tinhTrang);
            System.out.println(st);
            st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   
}
