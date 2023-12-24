/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas2021130029;

import java.sql.Date;

/**
 *
 * @author LIKMI
 */
public class JualModel {
    
    private String nojual;
    private Date tgl;
    private String idmember;
    
     public String getNojual() {
        return nojual;    }
    
    public void setNojual(String nojual) {        
        this.nojual = nojual;        }
    
    public Date getTgl() {
        return tgl;    }
    
    public void setTgl(Date tgl) {        
        this.tgl = tgl;        }
    
    public String getId() {
        return idmember;    }
    
    public void setId(String idmember) {        
        this.idmember = idmember;        }
}
