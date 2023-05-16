/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import database.JDBCUtil;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.LoaiMonAn;
import model.MonAn;

/**
 *
 * @author Admin
 */
public class MonAnDAO implements DAOInterface<MonAn> {
    
    public static MonAnDAO getInstance(){
        return new MonAnDAO();
    }

    @Override
    public int Insert(MonAn t) {
        int kq = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO monan (maMonAn, tenMonAn, donGia, maLoaiMonAn) VALUES (?, ?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaMonAn());
            st.setString(2, t.getTenMonAn());
            st.setLong(3, t.getDonGia());
            st.setString(4, t.getMaLoaiMonAn());

            System.out.println(st);

            kq = st.executeUpdate();

            if (kq > 0) {
                System.out.println("Them du lieu thanh cong!");
            } else {
                System.out.println("Them du lieu that bai!");
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kq;

    }

    @Override
    public int Update(MonAn t) {
     try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "UPDATE monan SET " +
                    "tenMonAn =? , maLoaiMonAn =? , donGia =? " + 
                    "WHERE maMonAn =?";
            
            PreparedStatement st = con.prepareStatement(sql);
            System.out.println(sql);
            st.setString(1, t.getTenMonAn());
            st.setString(2, t.getMaLoaiMonAn());
            st.setLong(3, t.getDonGia());
            st.setString(4, t.getMaMonAn());
            System.out.println(st);
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Cap nhat du lieu thanh cong!");
            } else{
                System.out.println("cap nhat du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;
    }

    @Override
    public int Delete(MonAn t) {
    try {
            Connection con = JDBCUtil.getConnection();
 
            
            String sql = "DELETE FROM monan " +
                    "WHERE maMonAn = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getMaMonAn());
            
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Xoa du lieu thanh cong!");
            } else{
                System.out.println("Xoa du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;    }

    @Override
    public ArrayList<MonAn> SelectAll() {
        ArrayList<MonAn> monan = new ArrayList<MonAn>();
       try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM monan";
            
            PreparedStatement st = con.prepareStatement(sql);
            

            ResultSet kq = st.executeQuery();
            
            while(kq.next()){
                monan.add(new MonAn(kq.getString("maMonAn"), kq.getString("tenMonan"),kq.getLong("donGia"), kq.getString("maLoaiMonAn")));
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return monan;    
    }

    @Override
    public MonAn SelectById(MonAn t) {
        MonAn monan = null;
       try {
            Connection con = JDBCUtil.getConnection();
            
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM monan WHERE maMonAn = '"+t.getMaMonAn()+"' ";
            

            ResultSet kq = st.executeQuery(sql);
            
            while(kq.next()){
                monan = new MonAn(kq.getString("maMonAn"), kq.getString("tenMonan"),kq.getLong("donGia"), kq.getString("maLoaiMonAn"));
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return monan;      
}
    public LoaiMonAn SelectedNameByID(String t){
       LoaiMonAn loaimon = null;
       try {
            Connection con = JDBCUtil.getConnection();
            
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM loaimonan WHERE maLoaiMonAn = '"+t+"' ";
            
            ResultSet kq = st.executeQuery(sql);
            
            while(kq.next()){
                loaimon = new LoaiMonAn(kq.getString("maLoaiMonAn"), kq.getString("tenLoaiMonan"));
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loaimon;    
    }
    @Override
    public ArrayList<MonAn> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
