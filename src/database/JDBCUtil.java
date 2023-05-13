/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author macbookpro
 */
public class JDBCUtil {
    public static Connection getConnection() {
        Connection c = null;
   
        try {
            DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
            
            String url = "jdbc:mySQL://localhost:3306/qltc";
            String username = "root";
            String password = "";
            
            c = DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
    
    public static void closeConnection(Connection c){
        try{
            if(c != null){
                c.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
