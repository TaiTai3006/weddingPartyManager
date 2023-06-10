/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class HoaDon extends TaiKhoan{
    private String maHoaDon;
    private String maTiecCuoi;
    private Date ngayThanhToan;
    private double tongTienDichVu;
    private double tienPhat;
    private double tongTienHoaDon;
    private double conLai;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, String maTiecCuoi, Date ngayThanhToan, double tongTienDichVu, double tienPhat, double tongTienHoaDon, double conLai, String userName) {
        super(userName);
        this.maHoaDon = maHoaDon;
        this.maTiecCuoi = maTiecCuoi;
        this.ngayThanhToan = ngayThanhToan;
        this.tongTienDichVu = tongTienDichVu;
        this.tienPhat = tienPhat;
        this.tongTienHoaDon = tongTienHoaDon;
        this.conLai = conLai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getMaTiecCuoi() {
        return maTiecCuoi;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public double getTongTienDichVu() {
        return tongTienDichVu;
    }

    public double getTienPhat() {
        return tienPhat;
    }

    public double getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public double getConLai() {
        return conLai;
    }
}
