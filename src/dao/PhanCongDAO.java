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
import model.CongViec;
import model.TaiKhoan;
import model.PhanCong;
import model.Sanh;
/**
 *
 * @author ACER
 */
public class PhanCongDAO implements DAOInterface<PhanCong> {
    public static PhanCongDAO getInstance() {
        return new PhanCongDAO();
    }
//SELECT nv.tenNhanVien, pc.ngayLam, ca.tenCa, sanh.maSanh FROM phancong pc, nhanvien nv, ca, sanh, congviec cv WHERE pc.maNhanVien = nv.maNhanVien and cv.maCongViec = nv.maCongViec and ca.maCa = pc.maCa and sanh.maSanh = pc.maSanh
    @Override
    public int Insert(PhanCong t) {

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO `PhanCong`(`maNhanVien`, `maTiecCuoi`) VALUES (?, ?)";            
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaNhanVien());
            st.setString(2, t.getMaTC());
//            st.setString(3, t.getCa());
//            st.setString(4, t.getMaSanh());
            
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
    public int Update(PhanCong t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Delete(PhanCong t) {
      try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM `PhanCong` WHERE `maNhanVien`= ? AND `maTiecCuoi` = ?";            
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaNhanVien());
            st.setString(2, t.getMaTC());
//            st.setString(3, t.getCa());
//            st.setString(4, t.getMaSanh());
            
            System.out.println(st);
            
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

    public ArrayList<PhanCong> SelectAll() {
        ArrayList<PhanCong> lstPhanCong = new ArrayList<PhanCong>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhanCong";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                String maNhanVien = kq.getString("maNhanVien");
                String maTC = kq.getString("maTiecCuoi");
//                String maCa = kq.getString("maCa");
//                String maSanh = kq.getString("maSanh");

                lstPhanCong.add(new PhanCong(maNhanVien, maTC));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstPhanCong;
    }
    
    public ArrayList<PhanCong> SelectByWeek(String monDay, String sunDay) {
        ArrayList<PhanCong> lstPhanCong = new ArrayList<PhanCong>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhanCong WHERE ngayLam >= ? and ngayLam <= ?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, monDay);
            st.setString(2, sunDay);


            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                String maNhanVien = kq.getString("maNhanVien");
                String maTiecCuoi = kq.getString("maTiecCuoi");
//                String maCa = kq.getString("maCa");
//                String maSanh = kq.getString("maSanh");
                lstPhanCong.add(new PhanCong(maNhanVien, maTiecCuoi));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstPhanCong;
    }
    
    public ArrayList<PhanCong> SelectByMonth(int month) {
        ArrayList<PhanCong> lstPhanCong = new ArrayList<PhanCong>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhanCong WHERE YEAR(ngayLam) = ?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, month);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                String maNhanVien = kq.getString("maNhanVien");
                String maTiecCuoi = kq.getString("maTiecCuoi");
//                String maCa = kq.getString("maCa");
//                String maSanh = kq.getString("maSanh");
                lstPhanCong.add(new PhanCong(maNhanVien, maTiecCuoi));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstPhanCong;
    }

    @Override
    public PhanCong SelectById(PhanCong t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PhanCong> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}