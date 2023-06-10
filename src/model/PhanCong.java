/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class PhanCong{
    private String maNhanVien;
//    private String tenCongViec;
    private String maTiec;

    public PhanCong() {
        
    }

    public PhanCong(String tenNhanVien, String maTC) {
        this.maNhanVien = tenNhanVien;
        this.maTiec = maTC;
    }

    public String getMaNhanVien() {
        return this.maNhanVien;
    }

    public String getMaTC() {
        return this.maTiec;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setMaTC(String maTC) {
        this.maTiec = maTC;
    }
}




//public class PhanCong{
//    private String maNhanVien;
////    private String tenCongViec;
//    private String ngayLam;
//    private String maCa;
//    private String maSanh;
//
//    public PhanCong() {
//        
//    }
//
//    public PhanCong(String tenNhanVien, String ngayLam, String ca, String maSanh) {
//        this.maNhanVien = tenNhanVien;
////        this.tenCongViec = tenCongViec;
//        this.ngayLam = ngayLam;
//        this.maCa = ca;
//        this.maSanh = maSanh;
//    }
//
//    public String getMaNhanVien() {
//        return this.maNhanVien;
//    }
//
////    public String getTenCongViec() {
////        return this.tenCongViec;
////    }
//
//    public String getNgayLam() {
//        return this.ngayLam;
//    }
//
//    public String getCa() {
//        return this.maCa;
//    }
//
//    public String getMaSanh() {
//        return this.maSanh;
//    }
//
//    public void setMaNhanVien(String maNhanVien) {
//        this.maNhanVien = maNhanVien;
//    }
//
//    public void setNgayLam(String ngayLam) {
//        this.ngayLam = ngayLam;
//    }
//
//    public void setCa(String ca) {
//        this.maCa = ca;
//    }
//
//    public void setMaSanh(String maSanh) {
//        this.maSanh = maSanh;
//    }
//}
