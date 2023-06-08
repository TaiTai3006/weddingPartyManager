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

    public CongViec() {
    }

    public CongViec(String maCongViec, String tenCongViec) {
        this.maCongViec = maCongViec;
        this.tenCongViec = tenCongViec;
    }
    
    public String getMaCongViec() {
        return maCongViec;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }
}
