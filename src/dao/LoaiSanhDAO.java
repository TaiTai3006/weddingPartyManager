/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.LoaiSanh;
import java.sql.Connection;
import database.JDBCUtil;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author macbookpro
 */
public class LoaiSanhDAO implements DAOInterface<LoaiSanh>{
    
    public static LoaiSanhDAO getInstance(){
        return new LoaiSanhDAO();
    }

    @Override
    public int Insert(LoaiSanh t) {
        
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO loaisanh (maloaisanh, tenloaisanh, soluongbantoida) VALUES (?, ?, ?)";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getMaLoaiSanh());
            st.setString(2, t.getTenLoaiSanh());
            st.setInt(3, t.getDonGiaBanToiThieu());
            
            System.out.println(st);
            
            int kq = st.executeUpdate();
            
            if(kq > 0){
                System.out.println("Them du lieu thanh cong!");
            } else{
                System.out.println("Them du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0 ;
    }

    @Override
    public int Update(LoaiSanh t) {
        
     try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "UPDATE loaisanh SET " +
                    "tenloaisanh =?, soluongbantoida =? " +
                    "WHERE maloaisanh =?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getTenLoaiSanh());
            st.setInt(2, t.getDonGiaBanToiThieu());
            st.setString(3, t.getMaLoaiSanh());
            
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
    public int Delete(LoaiSanh t) {
    try {
            Connection con = JDBCUtil.getConnection();
 
            
            String sql = "DELETE FROM loaisanh " +
                    "WHERE maloaisanh = ?";
            
            PreparedStatement st = con.prepareStatement(sql);
            
             st.setString(1, t.getMaLoaiSanh());
            
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
        return 0 ;
    }

    @Override
    public ArrayList<LoaiSanh> SelectAll() {
        ArrayList<LoaiSanh> loaiSanhs = new ArrayList<LoaiSanh>();
       try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM loaisanh";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            ResultSet kq = st.executeQuery();
            
            while(kq.next()){
                loaiSanhs.add(new LoaiSanh(kq.getString("maloaisanh"),kq.getString("tenloaisanh"), kq.getInt("soluongbantoida")));
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loaiSanhs;
    }

    @Override
    public LoaiSanh SelectById(LoaiSanh t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<LoaiSanh> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
