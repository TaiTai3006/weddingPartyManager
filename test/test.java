/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.SanhDAO;
import database.JDBCUtil;
import java.sql.Connection;
import java.util.ArrayList;
import model.LoaiSanh;
import model.Sanh;
/**
 *
 * @author macbookpro
 */
public class test {
    public static void main(String[] args) {
//        LoaiSanh loaiSanh =  new LoaiSanh("T003", "haha", 5000);
//      LoaiSanhDAO.getInstance().Insert(loaiSanh);
//      ArrayList<Sanh> loaiSanhs =  SanhDAO.getInstance().SelectAll();
//      for(LoaiSanh x : loaiSanhs){
//          System.out.println(x.toString());
//      }
 int a = SanhDAO.getInstance().GetID();
    System.out.print(a);
    }   
}
