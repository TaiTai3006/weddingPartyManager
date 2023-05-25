/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.Sanh;
import java.sql.Connection;
import database.JDBCUtil;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Date;
import java.math.BigDecimal;


/**
 *
 * @author macbookpro
 */
public class SanhDAO implements DAOInterface<Sanh> {

    public static SanhDAO getInstance() {
        return new SanhDAO();
    }

    @Override
    public int Insert(Sanh t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO `Sanh`(`maSanh`, `tenSanh`, `soLuongBanToiDa`, `maLoaiSanh`, `ghiChu`) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaSanh());
            st.setString(2, t.getTenSanh());
            st.setInt(3, t.getSoLuongBanToiDa());
            st.setString(4, t.getMaLoaiSanh());
            st.setString(5, t.getGhiChu());

            System.out.println(st);

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Them du lieu thanh cong!");
            } else {
                System.out.println("Them du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);

            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Update(Sanh t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE `Sanh` SET `tenSanh`= ?,`soLuongBanToiDa`= ?,"
                    + "`maLoaiSanh`= ?,`ghiChu`= ? WHERE `maSanh`= ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(5, t.getMaSanh());
            st.setString(1, t.getTenSanh());
            st.setInt(2, t.getSoLuongBanToiDa());
            st.setString(3, t.getMaLoaiSanh());
            st.setString(4, t.getGhiChu());
            System.out.println(st);
            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Cap nhat du lieu thanh cong!");
            } else {
                System.out.println("cap nhat du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(Sanh t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM `Sanh` WHERE maSanh = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaSanh());

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Xoa du lieu thanh cong!");
            } else {
                System.out.println("Xoa du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);

            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int GetID() {
        int key = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM Sanh ORDER BY maSanh DESC LIMIT 1";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                if (kq.getString("maSanh").length() != 0) {
                    key = Integer.parseInt(kq.getString("maSanh").substring(2));
                }
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return key;
    }

    @Override
    public ArrayList<Sanh> SelectAll() {
        ArrayList<Sanh> sanhs = new ArrayList<Sanh>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM Sanh, LoaiSanh WHERE Sanh.maLoaiSanh = LoaiSanh.maLoaiSanh";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                String maSanh = kq.getString("maSanh");
                String tenSanh = kq.getString("tenSanh");
                String maLoaiSanh = kq.getString("maLoaiSanh");
                String tenLoaiSanh = kq.getString("tenLoaiSanh");
                int soLuongBanToiDa = kq.getInt("soLuongBanToiDa");
                int donGiaBanToiThieu = kq.getInt("donGiaBanToiThieu");
                String ghiChu = kq.getString("ghiChu");

                sanhs.add(new Sanh(maSanh, maLoaiSanh, tenSanh, soLuongBanToiDa, tenLoaiSanh, donGiaBanToiThieu, ghiChu));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sanhs;
    }
    
    public int GetSoLuongBanToiDa(String maSanh){
        int soLuongBanToiDa = 0;
        
             try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT  `soLuongBanToiDa`FROM `Sanh` WHERE maSanh = ?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maSanh);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
               soLuongBanToiDa = kq.getInt("soLuongBanToiDa");
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }  
           return soLuongBanToiDa;
    }

    public ArrayList<Sanh> SelectSanhDatTiec(String maCa, String day, String month, String year) {
        ArrayList<Sanh> sanhs = new ArrayList<>();
        
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT maSanh, tenSanh \n"
                    + "FROM Sanh\n"
                    + "WHERE maSanh NOT IN (SELECT maSanh \n"
                    + "                     FROM PhieuDatTiecCuoi, Ca\n"
                    + "                     WHERE PhieuDatTiecCuoi.maCa = Ca.maCa AND Ca.maCa = ? AND ngayDaiTiec = ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maCa);
            st.setString(2, year+"/"+month+"/"+day);
            
            ResultSet kq = st.executeQuery();
            
            while(kq.next()){
                String maSanh = kq.getString("maSanh");
                String tenSanh = kq.getString("tenSanh");
                sanhs.add(new Sanh(maSanh, tenSanh));
            }
            
            JDBCUtil.closeConnection(con);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sanhs;
    }
    
    public int GetDonBanToiThieu(String maSanh){
        int donBanToiThieu = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT donGiaBanToiThieu from Sanh, LoaiSanh WHERE Sanh.maLoaiSanh = LoaiSanh.maLoaiSanh AND  maSanh = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maSanh);
            
            
            ResultSet kq = st.executeQuery();
            
            while(kq.next()){
               donBanToiThieu = kq.getInt("donGiaBanToiThieu");
            }
            
            JDBCUtil.closeConnection(con);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return donBanToiThieu;
    }

    @Override
    public Sanh SelectById(Sanh t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Sanh> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
