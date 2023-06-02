/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class ChiTietMonAn extends MonAn {
    private String maTiecCuoi;
    private String maMonAn;
    private long donGiaMonAn;
    private int soLuong;
    private double thanhTien;
    private String ghiChu;

    public ChiTietMonAn() {
    }

    public ChiTietMonAn(String maTiecCuoi, String maMonAn, long donGiaMonAn, int soLuong, String ghiChu, String tenMonAn, String maLoaiMonAn) {
        super(maMonAn, tenMonAn, donGiaMonAn, maLoaiMonAn);
        this.maTiecCuoi = maTiecCuoi;
        this.maMonAn = maMonAn;
        this.donGiaMonAn = donGiaMonAn;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public ChiTietMonAn(String maTiecCuoi, String maMonAn, long donGiaMonAn, int soLuong, String ghiChu) {
        this.maTiecCuoi = maTiecCuoi;
        this.maMonAn = maMonAn;
        this.donGiaMonAn = donGiaMonAn;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public String getMaMonAn() {
        return maMonAn;
    }

    public long getDonGiaMonAn() {
        return donGiaMonAn;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public String getMaTiecCuoi() {
        return maTiecCuoi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }
}
