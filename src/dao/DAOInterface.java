/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public interface DAOInterface<T> {
    public int Insert(T t);
    
    public int Update(T t);
    
    public int Delete(T t);
    
    public ArrayList<T> SelectAll();
    
    public T SelectById(T t);
    
    public ArrayList<T> SelectByCondition(String condition);
}
