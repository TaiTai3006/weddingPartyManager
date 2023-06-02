/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class ChiTietDichVu extends DichVu{
    private String maTiecCuoi; 
    private String maDichVu;
    private int soLuong;
    private int donGiaDichVu;
    private double thanhTien;

    public ChiTietDichVu() {
    }

    public ChiTietDichVu(String maTiecCuoi, String maDichVu, int soLuong, int donGiaDichVu, double thanhTien, String tenDichVu) {
        super(maDichVu, tenDichVu, donGiaDichVu);
        this.maTiecCuoi = maTiecCuoi;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.donGiaDichVu = donGiaDichVu;
        this.thanhTien = thanhTien;
    }

    public ChiTietDichVu(String maTiecCuoi, String maDichVu, int soLuong, int donGiaDichVu, double thanhTien) {
        this.maTiecCuoi = maTiecCuoi;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.donGiaDichVu = donGiaDichVu;
        this.thanhTien = thanhTien;
    }
    
    

    public void setMaTiecCuoi(String maTiecCuoi) {
        this.maTiecCuoi = maTiecCuoi;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGiaDichVu(int donGiaDichVu) {
        this.donGiaDichVu = donGiaDichVu;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaTiecCuoi() {
        return maTiecCuoi;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDonGiaDichVu() {
        return donGiaDichVu;
    }

    public double getThanhTien() {
        return thanhTien;
    }
}
