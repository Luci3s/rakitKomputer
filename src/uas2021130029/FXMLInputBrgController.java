/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas2021130029;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LIKMI
 */
public class FXMLInputBrgController implements Initializable {

    boolean editdata=false;
    
    @FXML
    private Button btnclose;
    @FXML
    private Button btncancel;
    @FXML
    private Button btnsubmit;
    @FXML
    private TextField txtgambar;
    @FXML
    private TextField txttarif;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtkode;
    @FXML
    private TextField txtstok;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void execute(BrgModel d){
        if(!d.getKodebrg().isEmpty()){
          editdata=true;
          txtkode.setText(d.getKodebrg());
          txtnama.setText(d.getNamabrg());         
          txttarif.setText(String.valueOf(d.getTarif()));
          txtstok.setText(String.valueOf(d.getStok()));
          txtgambar.setText(d.getGambar());
          txtkode.setEditable(false);
          txtnama.requestFocus();         
        }
    }

    @FXML
    private void closeklik(ActionEvent event) {
        btnclose.getScene().getWindow().hide();
    }

    @FXML
    private void cancelklik(ActionEvent event) {
        txtkode.setText("");
        txtnama.setText("");
        txttarif.setText("");
        txtgambar.setText("");
        txtstok.setText("");
        txtkode.requestFocus();
    }

    @FXML
    private void submitklik(ActionEvent event) {
        BrgModel n=new BrgModel();        
        n.setKodebrg(txtkode.getText());
        n.setNamabrg(txtnama.getText());     
        n.setTarif(Double.parseDouble(txttarif.getText())); 
        n.setStok(Integer.parseInt(txtstok.getText()));
        n.setGambar(txtgambar.getText());
        
        FXMLDocumentController.dtbrg.setBrgModel(n);
        if(editdata){
            if(FXMLDocumentController.dtbrg.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtkode.setEditable(true);        
               cancelklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
              }
            }
        else if(FXMLDocumentController.dtbrg.validasi(n.getKodebrg())<=0){
            if(FXMLDocumentController.dtbrg.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               cancelklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
           }
           else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtkode.requestFocus();
        }
    }
    
}
