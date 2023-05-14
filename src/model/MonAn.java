/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class MonAn {
    private String maMonAn;
    private String tenMonAn;
    private long donGia;
    private String maLoaiMonAn;

    public MonAn() {
    }
    
    
    public MonAn(String maMonAn, String tenMonAn, long donGia, String maLoaiMonAn) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.maLoaiMonAn = maLoaiMonAn;
    }

    public String getMaMonAn() {
        return maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public long getDonGia() {
        return donGia;
    }

    public String getMaLoaiMonAn() {
        return maLoaiMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        this.maMonAn = maMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public void setMaLoaiMon(String maLoaiMonAn) {
        this.maLoaiMonAn = maLoaiMonAn;
    }

    @Override
    public String toString() {
        return "MonAn{" + "maMonAn=" + maMonAn + ", tenMonAn=" + tenMonAn + ", donGia=" + donGia + ", maLoaiMon=" + maLoaiMonAn + '}';
    }
    
}
