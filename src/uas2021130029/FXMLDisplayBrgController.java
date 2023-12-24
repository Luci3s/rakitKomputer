/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas2021130029;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LIKMI
 */
public class FXMLDisplayBrgController implements Initializable {

    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal;
    private Button btnkeluar;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnkurang;
    @FXML
    private Button btntambah;
    @FXML
    private TableView<BrgModel> tbvbrg;
    @FXML
    private TextField txtcari;
    @FXML
    private ImageView imgbarang;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
        tbvbrg.getSelectionModel().selectFirst(); 
        showgambar();
    }    
    
    public void showdata(){
        ObservableList<BrgModel> data=FXMLDocumentController.dtbrg.Load();
        if(data!=null){            
            tbvbrg.getColumns().clear();            
            tbvbrg.getItems().clear();
            
            TableColumn col=new TableColumn("kodebrg");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("kodebrg"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("namabrg");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("namabrg"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("tarif");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("tarif"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("stok");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("stok"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("jenis");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("jenis"));
            tbvbrg.getColumns().addAll(col);
            tbvbrg.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbrg.getScene().getWindow().hide();;

            }
        }

    @FXML
    private void ubahklik(ActionEvent event) {
        BrgModel s= new BrgModel();
        s=tbvbrg.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBrg.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputBrgController isidt=(FXMLInputBrgController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
        awalklik(event);
    }

    @FXML
    private void kurangklik(ActionEvent event) {
        BrgModel s= new BrgModel();       
        s=tbvbrg.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtbrg.delete(s.getKodebrg())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           awalklik(event);       
        }    
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputBrg.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
        awalklik(event);
    }

    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvbrg.getSelectionModel().selectFirst();
        showgambar();
        tbvbrg.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvbrg.getSelectionModel().selectPrevious();
        showgambar();
        tbvbrg.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvbrg.getSelectionModel().selectNext();
        showgambar();
        tbvbrg.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvbrg.getSelectionModel().selectLast();
        showgambar();
        tbvbrg.requestFocus();
    }

    @FXML
    private void cariData(KeyEvent event) {
        BrgModel s = new BrgModel();
        String key = txtcari.getText();
        if(key!=""){
        ObservableList<BrgModel> data=FXMLDocumentController.dtbrg.CariBrg(key,key);
        if(data!=null){            
            tbvbrg.getColumns().clear();            
            tbvbrg.getItems().clear();
            
            TableColumn col=new TableColumn("kodebrg");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("kodebrg"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("namabrg");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("namabrg"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("tarif");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("tarif"));
            tbvbrg.getColumns().addAll(col);
            //col=new TableColumn("gambar");
            //col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("gambar"));
            //tbvbrg.getColumns().addAll(col);
            col=new TableColumn("jenis");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("jenis"));
            tbvbrg.getColumns().addAll(col);
            tbvbrg.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbrg.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }
    }
    
    public void showgambar(){
        Image gambar = null;
        try {
            gambar = new Image(new FileInputStream(tbvbrg.getSelectionModel().getSelectedItem().getGambar()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDisplayBrgController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        imgbarang.setImage(gambar);
    }

    @FXML
    private void tableklik(MouseEvent event) {
        showgambar();
    }
   
}
