/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class ThamSo {
    private int kiemTraPhat; 
    private double tiLePhat; 
    private double tienDatCoc;
    private int tgPhatHuyTiec;

    public ThamSo() {
    }

    public ThamSo(int kiemTraPhat, double tiLePhat, double tienDatCoc, int tgPhatHuyTiec) {
        this.kiemTraPhat = kiemTraPhat;
        this.tiLePhat = tiLePhat;
        this.tienDatCoc = tienDatCoc;
        this.tgPhatHuyTiec = tgPhatHuyTiec;
    }

    public int getKiemTraPhat() {
        return kiemTraPhat;
    }
    
    public double getTiLePhat() {
        return tiLePhat;
    }

    public int getTgPhatHuyTiec() {
        return tgPhatHuyTiec;
    }

    public double getTienDatCoc() {
        return tienDatCoc;
    }
}
