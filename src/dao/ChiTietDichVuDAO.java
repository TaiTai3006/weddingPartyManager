/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.ChiTietDichVu;
import java.sql.Connection;
import database.JDBCUtil;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Asus
 */
public class ChiTietDichVuDAO implements DAOInterface<ChiTietDichVu>{
    public static ChiTietDichVuDAO getInstance() {
        return new ChiTietDichVuDAO();
    }
    
    @Override
    public int Insert(ChiTietDichVu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Update(ChiTietDichVu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Delete(ChiTietDichVu t) {
         try {
            Connection con = JDBCUtil.getConnection();
 
            
            String sql = "DELETE FROM `ChiTietDichVu` WHERE maDichVu = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
             st.setString(1, t.getMaDichVu());
            
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Xoa du lieu thanh cong!");
            } else{
                System.out.println("Xoa du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
            
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;
    }

    @Override
    public ArrayList<ChiTietDichVu> SelectAll() {
         ArrayList<ChiTietDichVu> lstDetailServices = new ArrayList<ChiTietDichVu>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuDatTiecCuoi, ChiTietDichVu, DichVu WHERE PhieuDatTiecCuoi.maTiecCuoi = ChiTietDichVu.maTiecCuoi"
                                                                                 + " AND ChiTietDichVu.maDichVu = DichVu.maDichVu ";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstDetailServices.add(new ChiTietDichVu(kq.getString("maTiecCuoi"), kq.getString("maDichVu"), kq.getInt("soLuong"), 
                        kq.getInt("donGiaDichVu"), kq.getDouble("thanhTien"), kq.getString("tenDichVu")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstDetailServices;
    }

    @Override
    public ChiTietDichVu SelectById(ChiTietDichVu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChiTietDichVu> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
