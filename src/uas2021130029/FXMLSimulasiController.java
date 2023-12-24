/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas2021130029;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Timer;
import java.util.TimerTask; 
import javafx.application.Platform;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * FXML Controller class
 *
 * @author LIKMI
 */
public class FXMLSimulasiController implements Initializable {

    @FXML
    private ListView<String> listcust;
    @FXML
    private ListView<String> listbrg;
    @FXML
    private TextArea txareajual;
    @FXML
    private TextField txtwaktu;
    @FXML
    private Button btnsimulasi;

    Random acak=new Random();
    Timer t=new Timer();
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     txareajual.setWrapText(true);
     listcust.getItems().clear();
     listbrg.getItems().clear();
     ObservableList<CustModel> data=FXMLDocumentController.dtcust.Load();
        if(data.isEmpty()){
         Alert a=new Alert(Alert.AlertType.ERROR,
                 "Data Pelanggan kosong",ButtonType.OK);
         a.showAndWait();
         txareajual.getScene().getWindow().hide();;
        }else {
          for(int i=0;i<data.size();i++){
            listcust.getItems().addAll(data.get(i).getId()+" - "+data.get(i).getNama());
           }
          ObservableList<BrgModel> data2=FXMLDocumentController.dtbrg.Load();
           if(data.isEmpty()){
             Alert a=new Alert(Alert.AlertType.ERROR,
                     "Data Barang kosong",ButtonType.OK);
             a.showAndWait();
             txareajual.getScene().getWindow().hide();;
            }else {
                for(int i=0;i<data2.size();i++){
              listbrg.getItems().addAll(data2.get(i).getKodebrg()+" - "+data2.get(i).getNamabrg());
                }
            }        }    
    }    

    @FXML
    private void simulasiklik(ActionEvent event) {
      int wkt=Integer.parseInt(txtwaktu.getText());        
      t.scheduleAtFixedRate
        (new TimerTask()
        {           
          @Override
          public void run(){
              Platform.runLater(() -> 
              {
                 int p=acak.nextInt(listcust.getItems().size());
                 int b=acak.nextInt(listbrg.getItems().size());
                 int jml=acak.nextInt(10)+1;
                 
                 JualModel j=new JualModel();
                 j.setNojual(FXMLDocumentController.dtjual.autonum
                   (sdf.format(new java.util.Date())) );        
                 j.setId(listcust.getItems().get(p).substring(0, 6));
                 j.setTgl(Date.valueOf(LocalDate.now().format
                    (DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                 
                JualdetilModel s=new JualdetilModel();        
                s.setNojual(j.getNojual());
                s.setKodebrg(listbrg.getItems().get(b).substring(0, 6));        
                s.setJumlah(jml);
                FXMLDocumentController.dtjual.setJualModel(j);
                FXMLDocumentController.dtjual.setJualdetilModel(s);
                if (FXMLDocumentController.dtjual.saveall()){
                    txareajual.setText(txareajual.getText()+
                j.getNojual()+" - "+    listcust.getItems().get(p)+" : "+
                listbrg.getItems().get(b)+" "+String.format("%d\n", jml) );
         txareajual.setScrollTop(Double.MAX_VALUE);        }
           });}        }, 0, 60000*wkt);           
    }
    
}
