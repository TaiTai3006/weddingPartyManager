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
import model.Employee;
import model.NhanVien;

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
            st.setInt(5, Integer.parseInt(t.getSdt() + ""));
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
            st.setInt(3, Integer.parseInt(t.getSdt() + ""));

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
                lstNhanVien.add(new Employee(kq.getString("maNhanVien"), kq.getString("maCongViec"), kq.getString("tenNhanVien"), kq.getString("gioiTinh"), kq.getInt("sdt"), kq.getString("loaiNhanVien"), kq.getString("tenCongViec")));
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
