/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas2021130029;

/**
 *
 * @author LIKMI
 */
public class BrgModel {
    private String kodebrg, namabrg, gambar, jenis;
    private Double tarif;
    private Integer stok;
    
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
    
    public Double getTarif(){
        return this.tarif;
    }
    public void setTarif(Double tarif){
        this.tarif = tarif;
    }
    
    public String getGambar(){
        return this.gambar;
    }
    public void setGambar(String gambar){
        this.gambar = gambar;
    }
    
    public String getJenis(){
        return this.jenis;
    }
    public void setJenis(String jenis){
        this.jenis = jenis;
    }
    
    public Integer getStok(){
        return this.stok;
    }
    public void setStok(Integer stok){
        this.stok = stok;
    }
    
}
