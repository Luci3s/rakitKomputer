/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas2021130029;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LIKMI
 */
public class DBBrg {
    private BrgModel dt=new BrgModel(); 
    public BrgModel getBrgModel(){return(dt);}
    public void setBrgModel(BrgModel s){ dt=s;}
    
    public ObservableList<BrgModel>  Load() {
        try {
            ObservableList<BrgModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery
            ("Select kodebrg, namabrg, tarif, stok, gambar from barang");
            int i = 1;
            while (rs.next()) {
                BrgModel d=new BrgModel();
                d.setKodebrg(rs.getString("kodebrg"));                
                d.setNamabrg(rs.getString("namabrg"));
                d.setTarif(rs.getDouble("tarif"));     
                d.setStok(rs.getInt("stok"));  
                d.setGambar(rs.getString("gambar"));   
                
                double tarif1 = rs.getDouble("tarif");
                String jenis;
                
                if (tarif1>=100000) jenis = "Mahal";
                    else jenis = "Murah";
                d.setJenis(jenis);
                
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
          }
    }
    
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from barang where kodebrg = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement
            ("insert into barang (kodebrg,namabrg,tarif,gambar,stok) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getBrgModel().getKodebrg());           
            con.preparedStatement.setString(2, getBrgModel().getNamabrg());
            con.preparedStatement.setDouble(3, getBrgModel().getTarif());           
            con.preparedStatement.setString(4, getBrgModel().getGambar());
            con.preparedStatement.setInt(5, getBrgModel().getStok()); 
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from barang where kodebrg  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
   
   public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
            "update barang set namabrg = ?, tarif = ?, gambar = ?, stok = ? where  kodebrg = ? ");
            con.preparedStatement.setString(1, getBrgModel().getNamabrg());
            con.preparedStatement.setDouble(2, getBrgModel().getTarif());
            con.preparedStatement.setString(3, getBrgModel().getGambar());
            con.preparedStatement.setInt(4, getBrgModel().getStok());
            con.preparedStatement.setString(5, getBrgModel().getKodebrg());
             
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
        }
   
   
public boolean updatestok() {
    boolean berhasil = false; 
    Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
            "update barang set stok = ? where  kodebrg = ? ");
            con.preparedStatement.setInt(1, getBrgModel().getStok());
            con.preparedStatement.setString(2, getBrgModel().getKodebrg());
             
            con.preparedStatement.executeUpdate();            
            
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi(); 
            return berhasil; }    
        }
   
   public ObservableList<BrgModel>  CariBrg(String kode, String nama) {
        try {
            ObservableList<BrgModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select * from barang WHERE kodebrg LIKE '" + kode + "%' OR namabrg LIKE '" + nama + "%'");
            int i = 1;
            while (rs.next()) {
                BrgModel d=new BrgModel();
                d.setKodebrg(rs.getString("kodebrg"));                
                d.setNamabrg(rs.getString("namabrg"));
                d.setTarif(rs.getDouble("tarif"));                
                d.setGambar(rs.getString("gambar")); 
                d.setStok(rs.getInt("stok"));
                
                double tarif1 = rs.getDouble("tarif");
                String jenis;
                
                if (tarif1>=100000) jenis = "Mahal";
                    else jenis = "Murah";
                d.setJenis(jenis);
                
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
          }
    }
   
   public void CetakReportBarang(){
        Koneksi con = new Koneksi();        
        String is = "./src/uas2021130029/ReportBrg2.jasper";   
        Map map = new HashMap(); 
        map.put("judul", "Report Data Barang");
        con.bukaKoneksi();        
        try{
           JasperPrint jasperPrint = 
                   JasperFillManager.fillReport
                   (is, map,  con.dbKoneksi);
           JasperViewer.viewReport(jasperPrint, false);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }        con.tutupKoneksi();    }
}

