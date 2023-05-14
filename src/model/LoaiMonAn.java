/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class LoaiMonAn {
    private String maLoaiMonAn;
    private String tenLoaiMonAn;

    
    public LoaiMonAn(String maLoaiMonAn, String tenLoaiMonAn) {
        this.maLoaiMonAn = maLoaiMonAn;
        this.tenLoaiMonAn = tenLoaiMonAn;
    }

    public LoaiMonAn() {
    }

    public String getMaLoaiMonAn() {
        return maLoaiMonAn;
    }

    public String getTenLoaiMonAn() {
        return tenLoaiMonAn;
    }

    public void setMaLoaiMonAn(String maLoaiMonAn) {
        this.maLoaiMonAn = maLoaiMonAn;
    }

    public void setTenLoaiMonAn(String tenLoaiMonAn) {
        this.tenLoaiMonAn = tenLoaiMonAn;
    }

    @Override
    public String toString() {
        return "LoaiMonAn{" + "maLoaiMonAn=" + maLoaiMonAn + ", tenLoaiMonAn=" + tenLoaiMonAn + '}';
    }
    
    
}
