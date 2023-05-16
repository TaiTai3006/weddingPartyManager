/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import database.JDBCUtil;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LoaiMonAn;

/**
 *
 * @author Admin
 */
public class LoaiMonAnDAO implements DAOInterface<LoaiMonAn> {
    public static LoaiMonAnDAO getInstance(){
        return new LoaiMonAnDAO();
    }
    @Override
    public int Insert(LoaiMonAn t) {
        int kq = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "INSERT INTO loaimonan(maLoaiMonAn,tenLoaiMonAn) VALUES ('"+t.getMaLoaiMonAn()+"','"+t.getTenLoaiMonAn()+"')";
            
            kq = st.executeUpdate(sql);
            
            if(kq > 0){
                System.out.println("Them du lieu thanh cong");
            }else{
                System.out.println("Them du lieu that bai");
            }
            
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiMonAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    @Override
    public int Update(LoaiMonAn t) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "UPDATE loaimonan "
                        + "SET tenLoaiMonAn = '"+t.getTenLoaiMonAn()+"'"
                        + "WHERE maLoaiMonAn = '"+t.getMaLoaiMonAn()+"'";
            
            int kq = st.executeUpdate(sql);
            
            if(kq > 0){
                System.out.println("Cap nhat du lieu thanh cong");
            }else{
                System.out.println("Cap nhat du lieu that bai");
            }
            
            JDBCUtil.closeConnection(con);
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(LoaiMonAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;    
    }

    @Override
    public int Delete(LoaiMonAn t) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "DELETE from loaimonan "
                        + "WHERE maLoaiMonAn = '"+t.getMaLoaiMonAn()+"'";
            
            int kq = st.executeUpdate(sql);
            
            if(kq > 0){
                System.out.println("Xoa du lieu thanh cong");
            }else{
                System.out.println("Xoa du lieu that bai");
            }
            
            JDBCUtil.closeConnection(con);
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(LoaiMonAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;        }

    @Override
    public ArrayList<LoaiMonAn> SelectAll() {
        ArrayList<LoaiMonAn> lm = new ArrayList<LoaiMonAn>();
       try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM loaimonan";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            ResultSet kq = st.executeQuery();
            
            while(kq.next()){
                lm.add(new LoaiMonAn(kq.getString("maLoaiMonAn"),kq.getString("tenLoaiMonAn")));
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lm;    
    }

    @Override
    public LoaiMonAn SelectById(LoaiMonAn t) {
        LoaiMonAn loaimon = null;
       try {
            Connection con = JDBCUtil.getConnection();
            
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM loaimonan WHERE maLoaiMonAn = '"+t.getMaLoaiMonAn()+"' ";
            

            System.out.println(sql);
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
    public LoaiMonAn SelectByName(String t) {
        LoaiMonAn loaimon = null;
       try {
            Connection con = JDBCUtil.getConnection();
            
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM loaimonan WHERE tenLoaiMonAn = '"+t+"' ";
            

            System.out.println(sql);
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
    public ArrayList<LoaiMonAn> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



    

