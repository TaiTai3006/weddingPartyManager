package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import database.JDBCUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ThamSo;

/**
 *
 * @author Asus
 */
public class ThamSoDAO implements DAOInterface<ThamSo> {
    public static ThamSoDAO getInstance() {
        return new ThamSoDAO();
    }

    @Override
    public int Insert(ThamSo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public int Update(boolean checkPhat, double tilePhat, double tileCoc, int tgPhatHuyTiec, int tgDatTiec) {
        Connection con = JDBCUtil.getConnection();
        
        
        try {
            String sql = "UPDATE THAMSO SET KIEMTRAPHAT = ?, TILEPHAT = ?, TILEDATCOC = ?, TGPHATHUYTIEC = ?, thoiGianDatTiec = ?";
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setBoolean(1, checkPhat);
            st.setDouble(2, tilePhat);
            st.setDouble(3, tileCoc);
            st.setInt(4,tgPhatHuyTiec);
            st.setInt(5, tgDatTiec);
            int kq = st.executeUpdate();
            System.out.println(st);
            if(kq > 0){
                System.out.println("Cap nhat du lieu thanh cong!");
            } else{
                System.out.println("cap nhat du lieu that bai!");
            }
            
            JDBCUtil.closeConnection(con);            
        } catch (SQLException ex) {
            Logger.getLogger(ThamSoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return 0;
    }

    @Override
    public int Delete(ThamSo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ThamSo> SelectAll() {
        ArrayList<ThamSo> lstThamSo = new ArrayList<ThamSo>();
        try {
            java.sql.Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM thamso";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                lstThamSo.add(new ThamSo(kq.getInt("kiemTraPhat"), kq.getDouble("tiLePhat"), kq.getDouble("tiLeDatCoc"), 
                        kq.getInt("tgPhatHuyTiec")));
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lstThamSo;
    }

    @Override
    public ThamSo SelectById(ThamSo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ThamSo> SelectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int GetTyLePhat(){
        try{
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql  = "SELECT  `TiLePhat` FROM `ThamSo`";
            
            ResultSet kq = st.executeQuery(sql);
            
            while(kq.next()){
                return kq.getInt("TiLePhat");
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int GetTyLeCoc(){
        try{
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql  = "SELECT  `TiLeDatCoc` FROM `ThamSo`";
            
            ResultSet kq = st.executeQuery(sql);
            
            while(kq.next()){
                return kq.getInt("TiLeDatCoc");
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;

    }
    
    public int GetThoiGianPhat(){
        try{
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql  = "SELECT TGPhatHuyTiec FROM `ThamSo`";
            
            ResultSet kq = st.executeQuery(sql);
            
            while(kq.next()){
                return kq.getInt(1);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int GetThoiGianDatTiec(){
        try{
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql  = "SELECT `thoiGianDatTiec` FROM `ThamSo` ";
            
            ResultSet kq = st.executeQuery(sql);
            
            while(kq.next()){
                return kq.getInt(1);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
        public boolean isPhat(){
        try{
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql  = "SELECT `KiemTraPhat` FROM `ThamSo` ";
            
            ResultSet kq = st.executeQuery(sql);
            
            while(kq.next()){
                return kq.getBoolean(1);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int Update(ThamSo t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
