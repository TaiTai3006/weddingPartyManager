/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dao.LoaiSanhDAO;
import database.JDBCUtil;
import java.sql.Connection;
import model.LoaiSanh;
import model.Sanh;
/**
 *
 * @author macbookpro
 */
public class test {
    public static void main(String[] args) {
        LoaiSanh loaiSanh = new LoaiSanh("T001", "HIHI", 9000);
        LoaiSanhDAO.getInstance().Delete(loaiSanh);
        
    }
}
