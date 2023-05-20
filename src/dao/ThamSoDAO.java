/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.ThamSo;

/**
 *
 * @author Asus
 */
public class ThamSoDAO implements DAOInterface<ThamSo> {
    public static ThamSoDAO getInstance() {
        return new ThamSoDAO();
    }

    @Override
    public int Insert(ThamSo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Update(ThamSo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Delete(ThamSo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ThamSo> SelectAll() {
        ArrayList<ThamSo> lstThamSo = new ArrayList<ThamSo>();
        try {
            java.sql.Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM ThamSo";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstThamSo.add(new ThamSo(kq.getBoolean("kiemTraPhat"), kq.getDouble("tiLePhat"), kq.getDouble("tienDatCoc"), 
                        kq.getInt("tgPhatHuyTiec")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstThamSo;
    }

    @Override
    public ThamSo SelectById(ThamSo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ThamSo> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
