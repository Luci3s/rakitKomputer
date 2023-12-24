/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas2021130029;

/**
 *
 * @author LIKMI
 */
public class JualdetilModel {
    private String nojual;
    private String kodebrg, namabrg;
    private int jumlah;
    private double tarif;
    private float total, totalall;
    
    public String getNojual() {
        return nojual;    }
    
    public void setNojual(String nojual) {        
        this.nojual = nojual;        }
    
    public String getKodebrg(){
        return this.kodebrg;
    }
    public void setKodebrg(String kodebrg){
        this.kodebrg = kodebrg;
    }
    
    public String getNamabrg(){
        return this.namabrg;
    }
    public void setNamabrg(String namabrg){
        this.namabrg = namabrg;
    }
    
    public int getJumlah(){
        return this.jumlah;
    }
    public void setJumlah(int jumlah){
        this.jumlah = jumlah;
    }
    
    public Double getTarif(){
        return this.tarif;
    }
    public void setTarif(Double tarif){
        this.tarif = tarif;
    }
    
    public float getTotal(){
        return this.total;
    }
    public void setTotal(float total){
        this.total = total;
    }
    
    public float getTotalall(){
        return this.totalall;
    }
    public void setTotalall(float totalall){
        this.totalall = totalall;
    }
}
