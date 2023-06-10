/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class NhanVien {
    private String maNhanVien;
    private String maCongViec;
    private String tenNhanVien;
    private String gioiTinh;
    private String sdt;
    private String loaiNhanVien;

    public NhanVien() {
    }
    
    public NhanVien(String maNV, String maCV, String ten, String gioiTinh, String sdt, String loainv) {
        this.maNhanVien = maNV;
        this.tenNhanVien = ten;
        this.maCongViec = maCV;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
    }
    
    public void setMaNV(String maNV)
    {
        this.maNhanVien = maNV;
    }
    
    public void setMaCV(String maCV)
    {
        this.maCongViec = maCV;
    }
    
    public void setTenNV(String tenNV)
    {
        this.tenNhanVien = tenNV;
    }
    public void setGoiTinh(String gt)
    {
        this.gioiTinh = gt;
    }
    public void setSDT(String sdt)
    {
        this.sdt = sdt;
    }
    public void setLoaiNV(String loai)
    {
        this.loaiNhanVien = loai;
    }
    
    public String getMaNV()
    {
        return this.maNhanVien;
    }
    public String getTenNV()
    {
        return this.tenNhanVien;
    }
    public String getMaCV()
    {
        return this.maCongViec;
    }
    public String getGT()
    {
        return this.gioiTinh;
    }
    public String getSDT()
    {
        return this.sdt;
    }
    public String getLoaiNV()
    {
        return this.loaiNhanVien;
    }
//    public void setMaNV(String maNV) {
//        this.maNhanVien = maNV;
//    }
//
//    public void setPassWord(String passWord) {
//        this.passWord = passWord;
//    }
//
//    public void setMaChucVu(String maChucVu) {
//        this.maChucVu = maChucVu;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getPassWord() {
//        return passWord;
//    }
//
//    public String getMaChucVu() {
//        return maChucVu;
//    }

    
}
