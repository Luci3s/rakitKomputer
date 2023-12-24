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

/**
 *
 * @author LIKMI
 */
public class DBJual {
    private JualModel dt=new JualModel();   
    private HashMap<String,JualdetilModel>
            dt2=new HashMap<String,JualdetilModel>();
        
    public JualModel getJualModel(){ return(dt);}
    public void setJualModel(JualModel s){ dt=s;}
    
    public HashMap<String,JualdetilModel>
        getJualdetilModel(){ return(dt2);}
    public void setJualdetilModel(JualdetilModel d)
       { dt2.put(d.getKodebrg(), d);}
    
    public ObservableList<JualModel>  Load() {
        try {
            ObservableList<JualModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select nojual,tanggal,idmember from jual");
            int i = 1;
            while (rs.next()) {
                JualModel d=new JualModel();
                d.setNojual(rs.getString("nojual"));                
                d.setTgl(rs.getDate("tanggal"));
                d.setId(rs.getString("idmember"));                
                             
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from jual where nojual = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public int cariMax() {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select max(nojual) as idmax from jual");
            while (rs.next()) {                
                val = rs.getInt("idmax");            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual (nojual, tanggal,idmember) values (?,?,?)");
            con.preparedStatement.setString(1, getJualModel().getNojual());           
            con.preparedStatement.setDate(2, getJualModel().getTgl());
            con.preparedStatement.setString(3, getJualModel().getId());            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jual where nojual  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update jual set tanggal = ?, idmember = ?  where  nojual= ? ");
            con.preparedStatement.setDate(1, getJualModel().getTgl());
            con.preparedStatement.setString(2, getJualModel().getId());
            con.preparedStatement.setString(3, getJualModel().getNojual());
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
    
    public void CetakReportJual(){
        Koneksi con = new Koneksi();        
        String is = 
        "./src/uas2021130029/ReportJualMaster.jasper";   
        Map map = new HashMap(); 
        map.put("judul", "Report Transaksi Penjualan");
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
    
    public String autonum(String tahun){         
    String tmp="";
    try {          
	Koneksi con = new Koneksi();            
	con.bukaKoneksi();
        con.statement = con.dbKoneksi.createStatement();
        ResultSet rs = con.statement.executeQuery(
              "select max(nojual) as n from jual where nojual like '"+tahun+"%'");
            while (rs.next()) {              
	    tmp=tahun+
                String.format("%03d",
                Integer.parseInt(rs.getString("n").substring(4))+1 
            );            		}
            con.tutupKoneksi();
            return tmp;
        } catch (Exception e) {            
            e.printStackTrace();            
            return tmp;        
        }    
    }
    
    public boolean saveall() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.dbKoneksi.setAutoCommit(false); // membuat semua perintah menjadi 1 transaksi
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jual where nojual=?");
            con.preparedStatement.setString(1, getJualModel().getNojual());
            con.preparedStatement.executeUpdate();           
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual (nojual,tanggal,idmember) values (?,?,?)");
            con.preparedStatement.setString(1, getJualModel().getNojual());
            con.preparedStatement.setDate(2, getJualModel().getTgl());
            con.preparedStatement.setString(3, getJualModel().getId());
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from jual_detil where nojual=?");
            con.preparedStatement.setString(1, getJualModel().getNojual());
            con.preparedStatement.executeUpdate();           
            for(JualdetilModel sm:dt2.values()){
               con.preparedStatement = con.dbKoneksi.prepareStatement("insert into jual_detil (nojual,kodebrg, jumlah) values (?,?,?)");
               con.preparedStatement.setString(1, sm.getNojual());
               con.preparedStatement.setString(2, sm.getKodebrg());
               con.preparedStatement.setInt(3, sm.getJumlah());
               con.preparedStatement.executeUpdate();
            }
            con.dbKoneksi.commit();//semua perintah di transaksi dikerjakan
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
}
