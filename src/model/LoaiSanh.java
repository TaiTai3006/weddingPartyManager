/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author macbookpro
 */
public class LoaiSanh {
    private String maLoaiSanh;
    private String tenLoaiSanh;
    private int donGiaBanToiThieu;

    public LoaiSanh() {
    }

    public LoaiSanh(String maLoaiSanh, String tenLoaiSanh, int donGiaBanToiThieu) {
        this.maLoaiSanh = maLoaiSanh;
        this.tenLoaiSanh = tenLoaiSanh;
        this.donGiaBanToiThieu = donGiaBanToiThieu;
    }
    

    public String getMaLoaiSanh() {
        return maLoaiSanh;
    }

    public String getTenLoaiSanh() {
        return tenLoaiSanh;
    }

    public int getDonGiaBanToiThieu() {
        return donGiaBanToiThieu;
    }

    @Override
    public String toString() {
        return "LoaiSanh{" + "maLoaiSanh=" + maLoaiSanh + ", tenLoaiSanh=" + tenLoaiSanh + ", donGiaBanToiThieu=" + donGiaBanToiThieu + '}';
    }

    public void setMaLoaiSanh(String maLoaiSanh) {
        this.maLoaiSanh = maLoaiSanh;
    }

    public void setTenLoaiSanh(String tenLoaiSanh) {
        this.tenLoaiSanh = tenLoaiSanh;
    }

    public void setDonGiaBanToiThieu(int donGiaBanToiThieu) {
        this.donGiaBanToiThieu = donGiaBanToiThieu;
    }
    
}
