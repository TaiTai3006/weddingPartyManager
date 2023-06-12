/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.JDBCUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.ChiTiet_DV_ThanhToan;
import model.DichVu;
import java.sql.Connection;

/**
 *
 * @author Asus
 */
public class ChiTiet_DV_ThanhToanDAO implements DAOInterface<ChiTiet_DV_ThanhToan>{
    public static ChiTiet_DV_ThanhToanDAO getInstance() {
        return new ChiTiet_DV_ThanhToanDAO();
    }


    public int InsertData(String MaHD, String maDichVu, String soLuong, long donGiaDichVu) {
        long sl = Long.parseLong(soLuong);
//        long donGia = Long.parseLong(donGiaDichVu);
        try {
            java.sql.Connection con = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO `chitiet_dv_thanhtoan`(`maHoaDon`, `maDichVu`, `soLuong`, `donGiaDichVu`, `thanhTien`, `ghiChu`) VALUES (? , ?, ?, ?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, MaHD);
            st.setString(2, maDichVu);
            st.setLong(3, sl);
            st.setLong(4, donGiaDichVu);
            st.setLong(5, sl*donGiaDichVu);
            st.setString(6, "");
        
            
            System.out.println(st);
            
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Them du lieu thanh cong!");
            } else{
                System.out.println("Them du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;
    }
    
    @Override
    public int Insert(ChiTiet_DV_ThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Update(ChiTiet_DV_ThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Delete(ChiTiet_DV_ThanhToan t) {
        try {
            Connection con = JDBCUtil.getConnection();
 
            
            String sql = "DELETE FROM `chitiet_dv_thanhtoan` WHERE maDichVu = ?";
            
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
    public ArrayList<ChiTiet_DV_ThanhToan> SelectAll() {
        ArrayList<ChiTiet_DV_ThanhToan> lstCTDVTT = new ArrayList<ChiTiet_DV_ThanhToan>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM chitiet_dv_thanhtoan";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstCTDVTT.add(new ChiTiet_DV_ThanhToan(kq.getString("maHoaDon"), kq.getString("maDichVu"), kq.getInt("soLuong"), kq.getInt("donGiaDichVu"), kq.getDouble("thanhTien"), kq.getString("ghiChu")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstCTDVTT;
    }

    @Override
    public ChiTiet_DV_ThanhToan SelectById(ChiTiet_DV_ThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChiTiet_DV_ThanhToan> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
