/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author macbookpro
 */
public class Sanh extends LoaiSanh{
    private String maSanh;
    private String tenSanh;
    private int soLuongBanToiDa;
    private String ghiChu;

    public Sanh() {
    }

    @Override
    public String toString() {
       
        return "Sanh{" + "maSanh=" + maSanh + ", tenSanh=" + tenSanh + ", soLuongBanToiDa=" + soLuongBanToiDa + '}' +  super.toString();
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
    }
    
      public Sanh(String maSanh, String tenSanh) {
        this.maSanh = maSanh;
        this.tenSanh = tenSanh;
    }

    public Sanh(String maSanh, String maLoaiSanh, String tenSanh, int soLuongBanToiDa, String tenLoaiSanh, int donGiaBanToiThieu, String ghiChu) {
        super(maLoaiSanh, tenLoaiSanh, donGiaBanToiThieu);
        this.maSanh = maSanh;
        this.ghiChu = ghiChu;
        this.tenSanh = tenSanh;
        this.soLuongBanToiDa = soLuongBanToiDa;
    }

  
    

    public String getMaSanh() {
        return maSanh;
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

    public void setTenSanh(String tenSanh) {
        this.tenSanh = tenSanh;
    }

    public void setSoLuongBanToiDa(int soLuongBanToiDa) {
        this.soLuongBanToiDa = soLuongBanToiDa;
    }
    
}
