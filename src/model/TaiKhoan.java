/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class TaiKhoan extends ChucVu{
    private String userName;
    private String passWord;
    private String maChucVu;

    public TaiKhoan() {
    }
    public TaiKhoan(String userName) {
        this.userName = userName;
    }
    public TaiKhoan(String userName, String passWord, String maChucVu, String tenChucVu) {
        super(maChucVu, tenChucVu);
        this.userName = userName;
        this.passWord = passWord;
        this.maChucVu = maChucVu;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    
}
