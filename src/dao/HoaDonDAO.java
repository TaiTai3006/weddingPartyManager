/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.HoaDon;
import java.sql.Connection;
import database.JDBCUtil;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
/**
 *
 * @author Asus
 */
public class HoaDonDAO implements DAOInterface<HoaDon> {
    public static HoaDonDAO getInstance() {
        return new HoaDonDAO();
    }

    @Override
    public int Insert(HoaDon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Update(HoaDon t) {
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "UPDATE `HoaDon` SET `ngayThanhToan`= ? WHERE `maHoaDon`= ?";
            
            PreparedStatement st = con.prepareStatement(sql);
                                
            java.sql.Date sqlDate = new java.sql.Date(t.getNgayThanhToan().getTime());
            st.setDate(1, sqlDate);
            st.setString(2, t.getMaHoaDon());
            System.out.println(st);
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Cap nhat du lieu thanh cong!");
            } else{
                System.out.println("cap nhat du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;
    }

    @Override
    public int Delete(HoaDon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HoaDon> SelectAll() {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<HoaDon>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuDatTiecCuoi, HoaDon, NhanVien WHERE PhieuDatTiecCuoi.maTiecCuoi = HoaDon.maTiecCuoi"
                                                                                 + " AND NhanVien.userName = HoaDon.userName ";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstHoaDon.add(new HoaDon(kq.getString("maHoaDon"), kq.getString("maTiecCuoi"), kq.getDate("ngayThanhToan"), 
                        kq.getDouble("tongTienDichVu"), kq.getDouble("tienPhat"), kq.getDouble("tongTienHoaDon") ,kq.getDouble("conLai"), kq.getString("userName")));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstHoaDon;
    }

    @Override
    public HoaDon SelectById(HoaDon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HoaDon> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
