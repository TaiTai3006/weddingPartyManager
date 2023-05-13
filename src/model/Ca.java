/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Time;

/**
 *
 * @author Asus
 */
public class Ca {
    private String maCa;
    private String tenCa;
    private Time gioBatDau;
    private Time gioKetThuc;

    public Ca() {
    }

    public Ca(String maCa, String tenCa, Time gioBatDau, Time gioKetThuc) {
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }
    
    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public String getMaCa() {
        return maCa;
    }

    public String getTenCa() {
        return tenCa;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }
}
