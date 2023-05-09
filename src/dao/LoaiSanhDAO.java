/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.LoaiSanh;
import java.sql.Connection;
import database.JDBCUtil;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            Statement st = con.createStatement();
            
            String sql = "INSERT INTO loaisanh (maloaisanh, tenloaisanh, soluongbantoida) VALUES " +
                    "('"+t.getMaLoaiSanh()+"' , '"+ t.getTenLoaiSanh() + "' , "+ t.getDonGiaBanToiThieu()+")";
            
            int kq = st.executeUpdate(sql);
            
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
            
            Statement st = con.createStatement();
            
            String sql = "UPDATE loaisanh SET " +
                    "tenloaisanh = '" + t.getTenLoaiSanh() + "', soluongbantoida = " + t.getDonGiaBanToiThieu() + 
                    " WHERE maloaisanh = '" + t.getMaLoaiSanh()+"\'";
            
            int kq = st.executeUpdate(sql);
            
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
            
            Statement st = con.createStatement();
            
            String sql = "DELETE FROM loaisanh " +
                    "WHERE maloaisanh = '" + t.getMaLoaiSanh()+"\'";
            
            int kq = st.executeUpdate(sql);
            
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
