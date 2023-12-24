/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas2021130029;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LIKMI
 */
public class FXMLJualController implements Initializable {

    @FXML
    private TableView<JualModel> tbvjual;
    @FXML
    private TableView<JualdetilModel> tbvdetil;
    @FXML
    private TextField txtnojual;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private TextField txttotal;
    @FXML
    private Button btnhapus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
        tbvjual.getSelectionModel().selectFirst();
        txtnojual.setText(tbvjual.getSelectionModel().getSelectedItem().getNojual());
        showdatadetil();
        
    }    
    
     public void showdata(){
        ObservableList<JualModel> data=FXMLDocumentController.dtjual.Load();
        if(data!=null){          
            tbvjual.getColumns().clear();
            tbvjual.getItems().clear();
            TableColumn col=new TableColumn("nojual");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("nojual"));
            tbvjual.getColumns().addAll(col);
            col=new TableColumn("tanggal");
            col.setCellValueFactory
            (new FormattedDateValueFactory<JualModel>("tgl", "dd-MMM-yyyy"));
            //col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("tgl"));
            tbvjual.getColumns().addAll(col);
            col=new TableColumn("idmember");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("id"));
            tbvjual.getColumns().addAll(col);
            tbvjual.setItems(data);
                                              
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvjual.getScene().getWindow().hide();;
        }                
    }
     
     public void showdatadetil(){
        ObservableList<JualdetilModel> data
                =FXMLDocumentController.dtjualdetil.Load(txtnojual.getText());
        if(data!=null){  
            
            tbvdetil.getColumns().clear();
            tbvdetil.getItems().clear();
            TableColumn
            //col=new TableColumn("nojual");
            //col.setCellValueFactory(new PropertyValueFactory<JualdetilModel, String>("nojual"));
            //tbvdetil.getColumns().addAll(col);
            col=new TableColumn("kode barang");
            col.setCellValueFactory(new PropertyValueFactory<JualdetilModel, String>("kodebrg"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("nama barang");
            col.setCellValueFactory(new PropertyValueFactory<JualdetilModel, String>("namabrg"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("jumlah");
            col.setCellValueFactory(new PropertyValueFactory<JualdetilModel, String>("jumlah"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("tarif");
            col.setCellValueFactory(new FormattedDouble<JualdetilModel>("tarif","#,###,##0"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("total");
            //col.setCellValueFactory(new FormattedDouble<JualdetilModel>("total","#,###,##0"));
            col.setCellValueFactory(new PropertyValueFactory<JualdetilModel, String>("total"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("totalall");
            col.setCellValueFactory(new PropertyValueFactory<JualdetilModel, String>("totalall"));
            tbvdetil.getColumns().addAll(col);
            tbvdetil.setItems(data);
                                            
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvdetil.getScene().getWindow().hide();
        }   
        tbvdetil.getSelectionModel().selectLast();
        txttotal.setText(Float.toString(tbvdetil.getSelectionModel().getSelectedItem().getTotalall()));
    }

    @FXML
    private void pilihdata(MouseEvent event) {
        txtnojual.setText(tbvjual.getSelectionModel().getSelectedItem().getNojual());
        showdatadetil();
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectFirst(); 
        tbvjual.requestFocus();  
        txtnojual.setText(tbvjual.getSelectionModel().getSelectedItem().getNojual());
        showdatadetil();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectAboveCell(); 
        tbvjual.requestFocus();  
        txtnojual.setText(tbvjual.getSelectionModel().getSelectedItem().getNojual());
        showdatadetil();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectBelowCell(); 
        tbvjual.requestFocus();  
        txtnojual.setText(tbvjual.getSelectionModel().getSelectedItem().getNojual());
        showdatadetil();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvjual.getSelectionModel().selectLast(); 
        tbvjual.requestFocus();  
        txtnojual.setText(tbvjual.getSelectionModel().getSelectedItem().getNojual());
        showdatadetil();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,
              "Data Penjualan akan dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        
        if(a.getResult()==ButtonType.YES){
            FXMLDocumentController.dtjualdetil.deleteall(txtnojual.getText());
            FXMLDocumentController.dtjual.delete(txtnojual.getText());
                      
           Alert b=new Alert(Alert.AlertType.INFORMATION,
                   "Data Penjualan berhasil dihapus", ButtonType.OK);
           b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,
                    "Data Penjualan gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();
           showdatadetil(); 
    }
}
