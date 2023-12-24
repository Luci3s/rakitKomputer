/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas2021130029;

/**
 *
 * @author LIKMI
 */
public class CustModel {
    private String idmember,nama,alamat,status;
    private double total;
    
    public String getId() {
        return idmember;    }
    
    public void setId(String idmember) {        
        this.idmember = idmember;        }
    
    public String getNama() {
        return nama;        }
    
    public void setNama(String nama) {        
        this.nama = nama;        }
    
    public String getAlamat() {
        return alamat;    
    }
    
    public void setAlamat(String alamat) {        
        this.alamat = alamat;    
    }
    
    public double getTotal() {        
        return total;    
    }
    
    public void setTotal(double total) {        
        this.total = total;    
    }
    
    public String getStatus() {
        return status;    
    }
    
    public void setStatus(String status) {        
        this.status = status;    
    }
    
}
