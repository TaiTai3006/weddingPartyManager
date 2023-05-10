/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author macbookpro
 */
public class Sanh {
    private String maSanh;
    private String maLoaiSanh;
    private String tenSanh;
    private int soLuongBanToiDa;

    public Sanh() {
    }

    public Sanh(String maSanh, String maLoaiSanh, String tenSanh, int soLuongBanToiDa) {
        this.maSanh = maSanh;
        this.maLoaiSanh = maLoaiSanh;
        this.tenSanh = tenSanh;
        this.soLuongBanToiDa = soLuongBanToiDa;
    }

    public String getMaSanh() {
        return maSanh;
    }

    public String getMaLoaiSanh() {
        return maLoaiSanh;
    }

    public String getTenSanh() {
        return tenSanh;
    }

    public int getSoLuongBanToiDa() {
        return soLuongBanToiDa;
    }

    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }

    public void setMaLoaiSanh(String maLoaiSanh) {
        this.maLoaiSanh = maLoaiSanh;
    }

    public void setTenSanh(String tenSanh) {
        this.tenSanh = tenSanh;
    }

    public void setSoLuongBanToiDa(int soLuongBanToiDa) {
        this.soLuongBanToiDa = soLuongBanToiDa;
    }
    
}
