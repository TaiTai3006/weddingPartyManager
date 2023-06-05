/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class ChiTietBaoCao {
    private String maBaoCao;
    private String ngay;
    private int soLuongTiec;
    private double doanhThu;
    private double tiLe;
    
    public ChiTietBaoCao(){
        
    }
    
    public ChiTietBaoCao(String maBaoCao, String ngay, int soLuongTiec, double doanhThu, double tiLe) {
        this.maBaoCao = maBaoCao;
        this.ngay = ngay;
        this.soLuongTiec = soLuongTiec;
        this.doanhThu = doanhThu;
        this.tiLe = tiLe;
    }
    
    
    public void setMaBaoCao(String maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setSoLuongTiec(int soLuongTiec) {
        this.soLuongTiec = soLuongTiec;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public void setTiLe(double tiLe) {
        this.tiLe = tiLe;
    }

    public String getMaBaoCao() {
        return maBaoCao;
    }

    public String getNgay() {
        return ngay;
    }

    public int getSoLuongTiec() {
        return soLuongTiec;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public double getTiLe() {
        return tiLe;
    }
    
    
}
