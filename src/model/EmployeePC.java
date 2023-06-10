/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author macbookpro
 */
public class EmployeePC extends Employee{
    private boolean chon;

    public EmployeePC(boolean chon, String maNhanVien, String maCongViec, String tenNhanVien, String gioiTinh, String sdt, String loaiNhanVien) {
        super(maNhanVien, maCongViec, tenNhanVien, gioiTinh, sdt, loaiNhanVien);
        this.chon = chon;
    }

    public boolean isChon() {
        return chon;
    }

    public void setChon(boolean chon) {
        this.chon = chon;
    }
    
}
