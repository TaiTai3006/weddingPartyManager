/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class PhieuDatTiecCuoi {
    private String maTiecCuoi;
    private String ngayDat;
    private String ngayDaiTiec;
    private int soLuongBan;
    private int soLuongBanDuTru;
    private long donGiaBan;
    private long tongTienBan;
    private long tongTienDichVu;
    private long tongTienDatTiec;
    private long tienDatCoc;
    private long conLai;
    private String tenCoDau;
    private String tenChuRe;
    private String sdt;
    private String maCa;
    private String maSanh;
    private String userName;
    public PhieuDatTiecCuoi() {
    }

    public PhieuDatTiecCuoi(String maTiecCuoi, String ngayDat, String ngayDaiTiec, 
            int soLuongBan, int soLuongBanDuTru, long donGiaBan, long tongTienBan, 
            long tongTienDichVu, long tongTienDatTiec, long tienDatCoc, long conLai, 
            String tenCoDau, String tenChuRe, String sdt, String maCa, String maSanh, String userName) {
        this.maTiecCuoi = maTiecCuoi;
        this.ngayDat = ngayDat;
        this.ngayDaiTiec = ngayDaiTiec;
        this.soLuongBan = soLuongBan;
        this.soLuongBanDuTru = soLuongBanDuTru;
        this.donGiaBan = donGiaBan;
        this.tongTienBan = tongTienBan;
        this.tongTienDichVu = tongTienDichVu;
        this.tongTienDatTiec = tongTienDatTiec;
        this.tienDatCoc = tienDatCoc;
        this.conLai = conLai;
        this.tenCoDau = tenCoDau;
        this.tenChuRe = tenChuRe;
        this.sdt = sdt;
        this.maCa = maCa;
        this.maSanh = maSanh;
        this.userName = userName;
    }

    public String getMaTiecCuoi() {
        return maTiecCuoi;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public String getNgayDaiTiec() {
        return ngayDaiTiec;
    }
    
    public int getSoLuongBan(){
        return soLuongBan;
    }
    public int getSoLuongBanDuTru(){
        return soLuongBanDuTru;
    }
    public long getDonGiaBan(){
        return donGiaBan;
    }
    public long getTongTienBan(){
        return tongTienBan;
    }
    public long getTongTienDichVu(){
        return tongTienDichVu;
    }
    public long getTongTienDatTiec(){
        return tongTienDatTiec;
    }
    public long getTienDatCoc(){
        return tienDatCoc;
    }
    public long getConLai(){
        return conLai;
    }
    public String getTenCoDau(){
        return tenCoDau;
    }
    public String getTenChuRe(){
        return tenChuRe;
    }
    public String getSdt(){
        return sdt;
    }
    public String getMaCa(){
        return maCa;
    }
    public String getMaSanh(){
        return maSanh;
    }
    public String getUserName(){
        return userName;
    }

    public void setMaTiecCuoi(String maTiecCuoi) {
        this.maTiecCuoi = maTiecCuoi;
    }
    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }
    public void setNgayDaiTiec(String ngayDaiTiec) {
        this.ngayDaiTiec = ngayDaiTiec;
    }
    public void setSoLuongBan (int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }
    public void setSoLuongBangayDatuTru (int soLuongBanDuTru) {
        this.soLuongBanDuTru = soLuongBanDuTru;
    }
    public void setDonGiaBan(long donGiaBan) {
        this.donGiaBan = donGiaBan;
    }
    public void setTongTienBan(long tongTienBan) {
        this.tongTienBan = tongTienBan;
    }
    public void setTongTiengayDatichVu(long tongTienDichVu) {
        this.tongTienDichVu = tongTienDichVu;
    }
    public void setTongTiengayDatatTiec(long tongTienDatTiec) {
        this.tongTienDatTiec = tongTienDatTiec;
    }
    public void setTiengayDatatCoc(long tienDatCoc) {
        this.tienDatCoc = tienDatCoc;
    }
    public void setConLai(long conLai) {
        this.conLai = conLai;
    }
    public void setTenCoDau(String tenCoDau) {
        this.tenCoDau = tenCoDau;
    }
    public void setTenChuRe(String tenChuRe) {
        this.tenChuRe = tenChuRe;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }
    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }
}

