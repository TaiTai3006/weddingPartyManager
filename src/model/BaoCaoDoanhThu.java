/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class BaoCaoDoanhThu {
    private String maBaoCao;
    private int thang;
    private int nam;
    private double tongDoanhThu;
    private double tongThang;
    private double tongNam;
    
    public BaoCaoDoanhThu(){
        
    }
    
    public BaoCaoDoanhThu(String maBaoCao, int thang, int nam, double doanhThu, double tongThang, double tongNam) {
        this.maBaoCao = maBaoCao;
        this.thang = thang;
        this.nam = nam;
        this.tongDoanhThu = doanhThu;
        this.tongThang = tongThang;
        this.tongNam = tongNam;
    }
    
    
    public void setMaBaoCao(String maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }
    
    public void setNam(int nam) {
        this.nam = nam;
    }

    public void setTongDoanhThu(double doanhThu) {
        this.tongDoanhThu = doanhThu;
    }
    
    public void setTongNam(double tongNam) {
        this.tongNam = tongNam;
    }
    
    public void setTongThang(double tongThang) {
        this.tongThang = tongThang;
    }

    public String getMaBaoCao() {
        return maBaoCao;
    }

    public int getThang() {
        return thang;
    }
    
    public int getNam() {
        return nam;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }
    
    public double getTongNam() {
        return tongNam;
    }
    
    public double getTongThang() {
        return tongThang;
    }
}
