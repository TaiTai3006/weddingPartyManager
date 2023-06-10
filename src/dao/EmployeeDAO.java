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
import java.util.Date;
import model.Employee;
import model.EmployeePC;
import model.TaiKhoan;

/**
 *
 * @author Asus
 */
public class EmployeeDAO implements DAOInterface<Employee> {

    public static EmployeeDAO getInstance() {
        return new EmployeeDAO();
    }

    @Override
    public int Insert(Employee t) {
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO NhanVien (maNhanVien, maCongViec, tenNhanVien, gioiTinh, sdt, loaiNhanVien) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaNhanVien());
            st.setString(2, t.getMaCongViec());
            st.setString(3, t.getTenNhanVien());
            st.setString(4, t.getGioiTinh());
            st.setString(5, t.getSdt());
            st.setString(6, t.getLoaiNhanVien());

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
    public int Update(Employee t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE NhanVien SET tenNhanVien = ?, gioiTinh = ?, sdt = ?, loaiNhanVien = ? WHERE maNhanVien = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getTenNhanVien());
            st.setString(2, t.getGioiTinh());
            st.setString(3, t.getSdt());
            st.setString(4, t.getLoaiNhanVien());
            st.setString(5, t.getMaNhanVien());

            int kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Cap nhat du lieu thanh cong!");
            } else {
                System.out.println("Cap nhat du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);
            return kq;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(Employee t) {
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM NhanVien WHERE maNhanVien = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getMaNhanVien());

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

    @Override
    public ArrayList<Employee> SelectAll() {
        ArrayList<Employee> lstNhanVien = new ArrayList<Employee>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM NhanVien, CongViec WHERE NhanVien.maCongViec = CongViec.maCongViec";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstNhanVien.add(new Employee(kq.getString("maNhanVien"), kq.getString("maCongViec"), kq.getString("tenNhanVien"), kq.getString("gioiTinh"), kq.getString("sdt"), kq.getString("loaiNhanVien"), kq.getString("tenCongViec")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstNhanVien;
    }

    public boolean checkPC(String maNV, String maTC) {
        boolean flag = false;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT COUNT(*) FROM NhanVien WHERE NhanVien.maNhanVien = ? "
                    + "AND  NhanVien.maNhanVien IN (SELECT maNhanVien FROM PhieuDatTiecCuoi, PhanCong "
                    + "WHERE PhieuDatTiecCuoi.maTiecCuoi = PhanCong.maTiecCuoi AND PhieuDatTiecCuoi.maTiecCuoi = ?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maNV);
            st.setString(2, maTC);
            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                int res = kq.getInt(1);
                if(res > 0) flag = true;
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    public ArrayList<EmployeePC> getNhanVienPC(Date ngayDT, String maCa, String maTC) {
        ArrayList<EmployeePC> lstNhanVien = new ArrayList<EmployeePC>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM NhanVien WHERE NhanVien.maNhanVien NOT IN "
                    + "(SELECT maNhanVien FROM PhieuDatTiecCuoi, PhanCong WHERE PhieuDatTiecCuoi.maTiecCuoi = PhanCong.maTiecCuoi AND PhieuDatTiecCuoi.ngayDaiTiec = ? AND PhieuDatTiecCuoi.maCa = ?) "
                    + "OR NhanVien.maNhanVien IN "
                    + "(SELECT maNhanVien FROM PhieuDatTiecCuoi, PhanCong WHERE PhieuDatTiecCuoi.maTiecCuoi = PhanCong.maTiecCuoi AND PhieuDatTiecCuoi.maTiecCuoi = ?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(ngayDT.getTime()));
            st.setString(2, maCa);
            st.setString(3, maTC);
            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstNhanVien.add(new EmployeePC(checkPC(kq.getString("maNhanVien"), maTC),kq.getString("maNhanVien"),
                        kq.getString("maCongViec"), kq.getString("tenNhanVien"),
                        kq.getString("gioiTinh"), kq.getString("sdt"),
                        kq.getString("loaiNhanVien")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstNhanVien;
    }

    @Override
    public Employee SelectById(Employee t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Employee> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
