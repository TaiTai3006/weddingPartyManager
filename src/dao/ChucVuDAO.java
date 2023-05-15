/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCUtil;
import java.util.ArrayList;
import model.ChucVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Asus
 */
public class ChucVuDAO implements DAOInterface<ChucVu> {

    public static ChucVuDAO getInstance() {
        return new ChucVuDAO();
    }

    @Override
    public int Insert(ChucVu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Update(ChucVu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Delete(ChucVu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChucVu SelectById(ChucVu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChucVu> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<ChucVu> SelectAll() {
        ArrayList<ChucVu> lstChucVu = new ArrayList<ChucVu>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM ChucVu";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                String maChucVu = kq.getString("maChucVu");
                String tenChucVu = kq.getString("tenChucVu");

                lstChucVu.add(new ChucVu(maChucVu, tenChucVu));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstChucVu;
    }

}
