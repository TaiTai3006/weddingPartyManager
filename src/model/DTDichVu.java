/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author macbookpro
 */
public class DTDichVu extends DichVu{
    
    private int soLuong;
    private boolean chon;

   
    public DTDichVu( String maDichVu, String tenDichVu, int donGia) {
        super(maDichVu, tenDichVu, donGia);
        this.soLuong = 0;
        this.chon = false;
    }

    public DTDichVu(int soLuong, boolean chon, String maDichVu, String tenDichVu, int donGia) {
        super(maDichVu, tenDichVu, donGia);
        this.soLuong = soLuong;
        this.chon = chon;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setChon(boolean chon) {
        this.chon = chon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public boolean isChon() {
        return chon;
    }
    
    
}
