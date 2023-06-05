/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.ArrayList;
import model.ChiTietBaoCao;
import java.sql.Connection;
import database.JDBCUtil;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


/**
 *
 * @author ACER
 */
public class ChiTietBaoCaoDAO implements DAOInterface<ChiTietBaoCao>{
    public static ChiTietBaoCaoDAO getInstance() {
        return new ChiTietBaoCaoDAO();
    }
    
    public int Insert(ChiTietBaoCao t) {
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO `ChiTietBaoCao`(`maBaoCao`, `ngay`, `soLuongTiec`, `doanhThu`, `tiLe`) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getMaBaoCao());
            st.setString(2, t.getNgay());
            st.setInt(3, t.getSoLuongTiec());
            st.setDouble(4, t.getDoanhThu());
            st.setDouble(5, t.getTiLe());
        
            
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
    public int Update(ChiTietBaoCao t) {
         try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "UPDATE `ChiTietBaoCao` SET `soLuongTiec`= ?,`doanhThu`= ?, `tiLe` = ? "
                    + " WHERE `maBaoCao`= ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, t.getSoLuongTiec());
            st.setDouble(2, t.getDoanhThu());
            st.setDouble(3, t.getTiLe());
            st.setString(4, t.getMaBaoCao());
            
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
    public int Delete(ChiTietBaoCao t) {
         try {
            Connection con = JDBCUtil.getConnection(); 
            
            String sql = "DELETE FROM `ChiTietBaoCao` WHERE maBaoCao = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
             st.setString(1, t.getMaBaoCao());
            
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
    
    public ArrayList<ChiTietBaoCao> SelectByMonth(int month, int year) {
         ArrayList<ChiTietBaoCao> lstChiTietBaoCao = new ArrayList<ChiTietBaoCao>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM ChiTietBaoCao WHERE MONTH(`ngay`) = ? and YEAR(`ngay`) = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, month);
            st.setInt(2, year);
            
            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstChiTietBaoCao.add(new ChiTietBaoCao(kq.getString("maBaoCao"), kq.getString("ngay"), kq.getInt("soLuongTiec"), 
                        kq.getDouble("doanhThu"), kq.getDouble("tiLe")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstChiTietBaoCao;
    }

    @Override
    public ArrayList<ChiTietBaoCao> SelectAll() {
         ArrayList<ChiTietBaoCao> lstChiTietBaoCao = new ArrayList<ChiTietBaoCao>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM ChiTietBaoCao";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstChiTietBaoCao.add(new ChiTietBaoCao(kq.getString("maBaoCao"), kq.getString("ngay"), kq.getInt("soLuongTiec"), 
                        kq.getDouble("doanhThu"), kq.getDouble("tiLe")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstChiTietBaoCao;
    }

    @Override
    public ChiTietBaoCao SelectById(ChiTietBaoCao t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChiTietBaoCao> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
