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
public class ChiTietMonAnDAO implements DAOInterface<ChiTietMonAn>{
    public static ChiTietMonAnDAO getInstance() {
        return new ChiTietMonAnDAO();
    }

    @Override
    public int Insert(ChiTietMonAn t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Update(ChiTietMonAn t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Delete(ChiTietMonAn t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                lstDetailFoods.add(new ChiTietMonAn(kq.getString("maTiecCuoi"), kq.getString("maMonAn"), kq.getLong("donGiaMonAn"),kq.getInt("soLuong"), 
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
