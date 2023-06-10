/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class Employee extends CongViec {

    private String maNhanVien;
    private String maCongViec;
    private String tenNhanVien;
    private String gioiTinh;
    private String sdt;
    private String loaiNhanVien;

    public Employee() {
    }

    public Employee(String maNhanVien, String maCongViec, String tenNhanVien, String gioiTinh, String sdt, String loaiNhanVien) {
        super(maCongViec);
        this.maNhanVien = maNhanVien;
        this.maCongViec = maCongViec;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.loaiNhanVien = loaiNhanVien;
    }

    public Employee(String maNhanVien, String maCongViec, String tenNhanVien, String gioiTinh, String sdt, String loaiNhanVien, String tenCongViec) {
        super(maCongViec, tenCongViec);
        this.maNhanVien = maNhanVien;
        this.maCongViec = maCongViec;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.loaiNhanVien = loaiNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getMaCongViec() {
        return maCongViec;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public String getLoaiNhanVien() {
        return loaiNhanVien;
    }
}
