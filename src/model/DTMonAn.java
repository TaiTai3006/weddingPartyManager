/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author macbookpro
 */
public class DTMonAn extends MonAn{
    private String ghiChu;
    private boolean chon;


    public DTMonAn( String maMonAn, String tenMonAn, long donGia, String maLoaiMonAn) {
        super(maMonAn, tenMonAn, donGia, maLoaiMonAn);
        this.ghiChu = "";
        this.chon = false;
    }

    public DTMonAn( String maMonAn, String tenMonAn, long donGia, String maLoaiMonAn, String ghiChu) {
        super(maMonAn, tenMonAn, donGia, maLoaiMonAn);
        this.ghiChu = ghiChu;
        this.chon = false;
    }
    
    public String getGhiChu() {
        return ghiChu;
    }

    public boolean getChon() {
        return chon;
    }

    @Override
    public String toString() {
        return "DTMonAn{" + super.getMaMonAn() + " ghiChu=" + ghiChu + ", chon=" + chon + '}';
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setChon(boolean chon) {
        this.chon = chon;
    }
    
    
}
