/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class ChiTiet_DV_ThanhToan extends DichVu{
    private String maHoaDon; 
    private String maDichVu;
    private int soLuong;
    private int donGiaDichVu;
    private double thanhTien;
    private String ghichu;

    public ChiTiet_DV_ThanhToan() {
    }

    public ChiTiet_DV_ThanhToan(String maHoaDon, String maDichVu, int soLuong, int donGiaDichVu, double thanhTien, String ghichu) {
        this.maHoaDon = maHoaDon;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.donGiaDichVu = donGiaDichVu;
        this.thanhTien = thanhTien;
        this.ghichu = ghichu;
    }
    
    public ChiTiet_DV_ThanhToan(String maHoaDon, String maDichVu, int soLuong, int donGiaDichVu, double thanhTien, String ghichu, String tenDichVu) {
        super(maDichVu, tenDichVu, donGiaDichVu);
        this.maHoaDon = maHoaDon;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.donGiaDichVu = donGiaDichVu;
        this.thanhTien = thanhTien;
        this.ghichu = ghichu;
    }
    
    

    public String getMaHoaDon() {
        return maHoaDon;
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

    public String getGhichu() {
        return ghichu;
    }
}
