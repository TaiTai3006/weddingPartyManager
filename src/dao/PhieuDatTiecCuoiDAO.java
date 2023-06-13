/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.PhieuDatTiecCuoi;
import java.sql.Connection;
import database.JDBCUtil;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookpro
 */
public class PhieuDatTiecCuoiDAO implements DAOInterface<PhieuDatTiecCuoi> {

    public static PhieuDatTiecCuoiDAO getInstance() {
        return new PhieuDatTiecCuoiDAO();
    }

    @Override
    public int Insert(PhieuDatTiecCuoi t) {

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO PhieuDatTiecCuoi (maTiecCuoi, ngayDat, ngayDaiTiec, soLuongBan, soLuongBanDuTru,"
                    + "donGiaBan, tongTienBan, tongTienDichVu, tongTienDatTiec, tienDatCoc, conLai, tenCoDau, tenChuRe,"
                    + "sdt, maCa, maSanh, userName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaTiecCuoi());
            st.setString(2, t.getNgayDat());
            st.setString(3, t.getNgayDaiTiec());
            st.setInt(4, t.getSoLuongBan());
            st.setInt(5, t.getSoLuongBanDuTru());
            st.setLong(6, t.getDonGiaBan());
            st.setLong(7, t.getTongTienBan());
            st.setLong(8, t.getTongTienDichVu());
            st.setLong(9, t.getTongTienDatTiec());
            st.setLong(10, t.getTienDatCoc());
            st.setLong(11, t.getConLai());
            st.setString(12, t.getTenCoDau());
            st.setString(13, t.getTenChuRe());
            st.setString(14, t.getSdt());
            st.setString(15, t.getMaCa());
            st.setString(16, t.getMaSanh());
            st.setString(17, t.getUserName());

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
    public int Update(PhieuDatTiecCuoi t) {

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE PhieuDatTiecCuoi SET "
                    + "ngayDaiTiec=?, soLuongBan=?, soLuongBanDuTru=?,"
                    + "donGiaBan=?, tongTienBan=?, tongTienDichVu=?, tongTienDatTiec=?,"
                    + "tienDatCoc=?, conLai=?, tenCoDau=?, tenChuRe=?,"
                    + "sdt=?, maCa=?, maSanh=? "
                    + "WHERE maTiecCuoi =?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getNgayDaiTiec());
            st.setInt(2, t.getSoLuongBan());
            st.setInt(3, t.getSoLuongBanDuTru());
            st.setLong(4, t.getDonGiaBan());
            st.setLong(5, t.getTongTienBan());
            st.setLong(6, t.getTongTienDichVu());
            st.setLong(7, t.getTongTienDatTiec());
            st.setLong(8, t.getTienDatCoc());
            st.setLong(9, t.getConLai());
            st.setString(10, t.getTenCoDau());
            st.setString(11, t.getTenChuRe());
            st.setString(12, t.getSdt());
            st.setString(13, t.getMaCa());
            st.setString(14, t.getMaSanh());
            st.setString(15, t.getMaTiecCuoi());

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
    
    public void UpdateTinhTrang(String maTC){
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE PhieuDatTiecCuoi SET `tinhTrangPhanCong` = 1 WHERE maTiecCuoi =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maTC);
           

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Cap nhat du lieu tinh trang thanh cong!");
            } else {
                System.out.println("cap nhat du lieu tinh trang that bai!");
            }

            JDBCUtil.closeConnection(con);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
    }

    public ArrayList<PhieuDatTiecCuoi> SelectById(String id) {
        ArrayList<PhieuDatTiecCuoi> lstTiecCuoi = new ArrayList<PhieuDatTiecCuoi>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuDatTiecCuoi WHERE PhieuDatTiecCuoi.maTiecCuoi = ?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstTiecCuoi.add(new PhieuDatTiecCuoi(kq.getString("maTiecCuoi"), kq.getString("ngayDat"), kq.getString("ngayDaiTiec"),
                        kq.getInt("soLuongBan"), kq.getInt("soLuongBanDuTru"), kq.getLong("donGiaBan"), kq.getLong("tongTienBan"),
                        kq.getLong("tongTienDichVu"), kq.getLong("tongTienDatTiec"), kq.getLong("tienDatCoc"), kq.getLong("conLai"),
                        kq.getString("tenCoDau"), kq.getString("tenChuRe"), kq.getString("sdt"), kq.getString("maCa"), kq.getString("maSanh"), kq.getString("userName")));
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstTiecCuoi;
    }

    @Override
    public int Delete(PhieuDatTiecCuoi t) {

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM PhieuDatTiecCuoi "
                    + "WHERE maTiecCuoi = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaTiecCuoi());

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

            String sql = "SELECT * FROM PhieuDatTiecCuoi ORDER BY maTiecCuoi DESC LIMIT 1";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                if (kq.getString("maTiecCuoi").length() != 0) {
                    key = Integer.parseInt(kq.getString("maTiecCuoi").substring(2));
                }
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return key;
    }

    @Override
    public ArrayList<PhieuDatTiecCuoi> SelectAll() {
        ArrayList<PhieuDatTiecCuoi> phieuDatTiecCuois = new ArrayList<PhieuDatTiecCuoi>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuDatTiecCuoi WHERE maTiecCuoi not in (SELECT hoadon.maTiecCuoi FROM hoadon) ORDER BY ngayDaiTiec ASC";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                phieuDatTiecCuois.add(new PhieuDatTiecCuoi(kq.getString("maTiecCuoi"), kq.getString("ngayDat"), kq.getString("ngayDaiTiec"),
                        kq.getInt("soLuongBan"), kq.getInt("soLuongBanDuTru"), kq.getLong("donGiaBan"), kq.getLong("tongTienBan"),
                        kq.getLong("tongTienDichVu"), kq.getLong("tongTienDatTiec"), kq.getLong("tienDatCoc"), kq.getLong("conLai"),
                        kq.getString("tenCoDau"), kq.getString("tenChuRe"), kq.getString("sdt"), kq.getString("maCa"), kq.getString("maSanh"), kq.getString("userName")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return phieuDatTiecCuois;
    }
    
    public ArrayList<PhieuDatTiecCuoi> SelectAllTC() {
        ArrayList<PhieuDatTiecCuoi> phieuDatTiecCuois = new ArrayList<PhieuDatTiecCuoi>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuDatTiecCuoi";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                phieuDatTiecCuois.add(new PhieuDatTiecCuoi(kq.getString("maTiecCuoi"), kq.getString("ngayDat"), kq.getString("ngayDaiTiec"),
                        kq.getInt("soLuongBan"), kq.getInt("soLuongBanDuTru"), kq.getLong("donGiaBan"), kq.getLong("tongTienBan"),
                        kq.getLong("tongTienDichVu"), kq.getLong("tongTienDatTiec"), kq.getLong("tienDatCoc"), kq.getLong("conLai"),
                        kq.getString("tenCoDau"), kq.getString("tenChuRe"), kq.getString("sdt"), kq.getString("maCa"), kq.getString("maSanh"), kq.getString("userName")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return phieuDatTiecCuois;
    }

    public ArrayList<PhieuDatTiecCuoi> SelectDesc() {
        ArrayList<PhieuDatTiecCuoi> phieuDatTiecCuois = new ArrayList<PhieuDatTiecCuoi>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuDatTiecCuoi WHERE maTiecCuoi not in (SELECT hoadon.maTiecCuoi FROM hoadon) ORDER by soLuongBan DESC";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                phieuDatTiecCuois.add(new PhieuDatTiecCuoi(kq.getString("maTiecCuoi"), kq.getString("ngayDat"), kq.getString("ngayDaiTiec"),
                        kq.getInt("soLuongBan"), kq.getInt("soLuongBanDuTru"), kq.getLong("donGiaBan"), kq.getLong("tongTienBan"),
                        kq.getLong("tongTienDichVu"), kq.getLong("tongTienDatTiec"), kq.getLong("tienDatCoc"), kq.getLong("conLai"),
                        kq.getString("tenCoDau"), kq.getString("tenChuRe"), kq.getString("sdt"), kq.getString("maCa"), kq.getString("maSanh"), kq.getString("userName")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return phieuDatTiecCuois;
    }

    public ArrayList<PhieuDatTiecCuoi> SelectAsc() {
        ArrayList<PhieuDatTiecCuoi> phieuDatTiecCuois = new ArrayList<PhieuDatTiecCuoi>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PhieuDatTiecCuoi WHERE maTiecCuoi not in (SELECT hoadon.maTiecCuoi FROM hoadon) ORDER by soLuongBan ASC";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                phieuDatTiecCuois.add(new PhieuDatTiecCuoi(kq.getString("maTiecCuoi"), kq.getString("ngayDat"), kq.getString("ngayDaiTiec"),
                        kq.getInt("soLuongBan"), kq.getInt("soLuongBanDuTru"), kq.getLong("donGiaBan"), kq.getLong("tongTienBan"),
                        kq.getLong("tongTienDichVu"), kq.getLong("tongTienDatTiec"), kq.getLong("tienDatCoc"), kq.getLong("conLai"),
                        kq.getString("tenCoDau"), kq.getString("tenChuRe"), kq.getString("sdt"), kq.getString("maCa"), kq.getString("maSanh"), kq.getString("userName")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return phieuDatTiecCuois;
    }

    public void getPhanCong(Date ngayBD, Date ngayKT, DefaultTableModel modelPC) {

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT ngayDaiTiec, tenCa, maTiecCuoi, maSanh, (soLuongBan+soLuongBanDuTru) as SLBan, tinhTrangPhanCong  \n"
                    + "FROM `PhieuDatTiecCuoi`, Ca \n"
                    + "WHERE PhieuDatTiecCuoi.maCa = ca.maCa AND (ngayDaiTiec BETWEEN ? AND ?) AND maTiecCuoi NOT IN (SELECT maTiecCuoi FROM HoaDon)  \n"
                    + "ORDER BY ngayDaiTiec ASC, tenCa ASC";

            PreparedStatement st = con.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(ngayBD.getTime()));
            st.setDate(2, new java.sql.Date(ngayKT.getTime()));
            System.out.println(st);

            ResultSet kq = st.executeQuery();
            int i = 0;
            String row[] = new String[7];
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            while (kq.next()) {
                row[0] = String.valueOf(++i);
                row[1] = dateFormat.format(kq.getDate(1));
                row[2] = kq.getString(2);
                row[3] = kq.getString(3);
                row[4] = kq.getString(4);
                row[5] = kq.getString(5);
                if (kq.getInt(6) == 0) {
                    row[6] = "Chưa phân công";
                } else {
                    row[6] = "Đã phân công";
                }
                modelPC.addRow(row);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            System.out.print("lỗi");
            ex.printStackTrace();
        }

    }

    @Override
    public PhieuDatTiecCuoi SelectById(PhieuDatTiecCuoi t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PhieuDatTiecCuoi> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
