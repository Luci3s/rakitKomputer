/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package uas2021130029;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask; 
import javafx.application.Platform;
import java.text.SimpleDateFormat;
import javafx.scene.control.Button;
import java.util.Random;

/**
 *
 * @author LIKMI
 */
public class FXMLDocumentController implements Initializable {
    
    public static DBCust dtcust=new DBCust();
    public static DBBrg dtbrg=new DBBrg();
    public static DBJual dtjual=new DBJual();
    public static DBJualdetil dtjualdetil=new DBJualdetil();
    
    @FXML
    private MenuItem DisplayCust;
    @FXML
    private MenuItem DisplayBrg;
    @FXML
    private MenuItem InputCust;
    @FXML
    private MenuItem InputBrg;
    @FXML
    private MenuItem datajual;
    @FXML
    private MenuItem transaksi;
    @FXML
    private MenuItem reportbrg;
    @FXML
    private MenuItem reportcust;
    @FXML
    private MenuItem reportjual;
    @FXML
    private Label txtwaktu;
    
    @FXML
    private Button btnrandom;
    @FXML
    private Label lbangkaa;
    @FXML
    private Label lbangkab;
    
    Timer t=new Timer();        
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy,hh:mm:ss");
    
    Random acak=new Random();
    @FXML
    private MenuItem simulasi;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      t.scheduleAtFixedRate(new TimerTask(){           
      @Override           
      public void run(){
       Platform.runLater(() -> {
                 txtwaktu.setText(sdf.format(new java.util.Date()));
           });}
        }, 0, 1000);            

    }    

    @FXML
    private void DisplayCustClick(ActionEvent event) {
    try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDisplayCust.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();       
    }
    
}

    @FXML
    private void DisplayBrgClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDisplayBrg.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();       
    }
    
    }

    @FXML
    private void InputCustCLick(ActionEvent event) {
    try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputCust.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();       
    }
    }

    @FXML
    private void InputBrgClick(ActionEvent event) {
    try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBrg.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();       
    }
    }

    @FXML
    private void datajualklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLJual.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();  
    }
    }

    @FXML
    private void transaksiklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader
            (getClass().getResource("FXMLTransaksi.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();  
    }
    }

    @FXML
    private void reportbrgklik(ActionEvent event) {
        dtbrg.CetakReportBarang();
    }

    @FXML
    private void reportcustklik(ActionEvent event) {
        dtcust.CetakReportCust();
    }

    @FXML
    private void reportjualklik(ActionEvent event) {
        dtjual.CetakReportJual();
    }

    @FXML
    private void btnrandomklik(ActionEvent event) {
        int a = acak.nextInt(100);
        int b = (int)(Math.random()*100);
        lbangkaa.setText(String.valueOf(a));
        lbangkab.setText(String.valueOf(b));
    }

    @FXML
    private void simulasiklik(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLSimulasi.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();       
    }
    }
}
