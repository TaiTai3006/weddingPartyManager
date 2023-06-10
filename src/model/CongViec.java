/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class CongViec {
    private String maCongViec;
    private String tenCongViec;
    private String maDichVu;

    public CongViec() {
    }
      public CongViec(String maCongViec) {
        this.maCongViec = maCongViec;

    }

    public CongViec(String maCongViec, String tenCongViec) {
        this.maCongViec = maCongViec;
        this.tenCongViec = tenCongViec;
    }

    public CongViec(String maCongViec, String tenCongViec, String maDichVu) {
        this.maCongViec = maCongViec;
        this.tenCongViec = tenCongViec;
        this.maDichVu = maDichVu;
    }
    
    
    public String getMaCongViec() {
        return maCongViec;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public String getMaDichVu() {
        return maDichVu;
    }
    
    
}
