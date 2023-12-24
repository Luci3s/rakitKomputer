/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uas2021130029;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
public class FXMLTransaksiController implements Initializable {

    @FXML
    private TextField txtnojual;
    @FXML
    private DatePicker dtptanggal;
    @FXML
    private TableView<CustModel> tbvcust;
    @FXML
    private TableView<BrgModel> tbvbrg;
    @FXML
    private TextField txtqty;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtkodebrg;
    @FXML
    private Button btntambahbrg;
    @FXML
    private Button btnhapusbrg;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsave;
    @FXML
    private TableView<JualdetilModel> tbvdetil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showdatacust();
       showdatabrg();
       tbvcust.getSelectionModel().selectFirst(); 
       tbvbrg.getSelectionModel().selectFirst(); 
    }    

    public void showdatacust(){
        ObservableList<CustModel> data=FXMLDocumentController.dtcust.Load();
        if(data!=null){            
            tbvcust.getColumns().clear();            
            tbvcust.getItems().clear();
            TableColumn col=new TableColumn("id");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("id"));
            tbvcust.getColumns().addAll(col);
            col=new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("nama"));
            tbvcust.getColumns().addAll(col);
            tbvcust.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcust.getScene().getWindow().hide();;
            }
        }
    
    public void showdatabrg(){
        ObservableList<BrgModel> data=FXMLDocumentController.dtbrg.Load();
        if(data!=null){            
            tbvbrg.getColumns().clear();            
            tbvbrg.getItems().clear();
            TableColumn
            //col=new TableColumn("kodebrg");
            //col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("kodebrg"));
            //tbvbrg.getColumns().addAll(col);
            col=new TableColumn("namabrg");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("namabrg"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("tarif");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("tarif"));
            tbvbrg.getColumns().addAll(col);
            col=new TableColumn("stok");
            col.setCellValueFactory(new PropertyValueFactory<BrgModel, String>("stok"));
            tbvbrg.getColumns().addAll(col);
            tbvbrg.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbrg.getScene().getWindow().hide();;

            }
        }
    
    public void showdatadetil(){   
        ObservableList<JualdetilModel> data=FXMLDocumentController.dtjualdetil.Load(txtnojual.getText());
        if(data!=null){           
            tbvdetil.getColumns().clear();
            tbvdetil.getItems().clear();
            TableColumn 
            col=new TableColumn("kode barang");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("kodebrg"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("nama barang");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("namabrg"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("jumlah");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("jumlah"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("tarif");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("tarif"));
            tbvdetil.getColumns().addAll(col);
            col=new TableColumn("total");
            col.setCellValueFactory(new PropertyValueFactory<JualModel, String>("total"));
            tbvdetil.getColumns().addAll(col);
            tbvdetil.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvdetil.getScene().getWindow().hide();;
        }     
    }
    
    @FXML
    private void saveklik(ActionEvent event) {
      //Integer no = FXMLDocumentController.dtjual.cariMax();
      //txtnojual.setText(String.valueOf(no));
      
        JualModel n=new JualModel();  
        n.setNojual(txtnojual.getText());
        n.setTgl(Date.valueOf(dtptanggal.getValue()));     
        n.setId(txtid.getText());  
                
        FXMLDocumentController.dtjual.setJualModel(n);
        if(FXMLDocumentController.dtjual.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,
                       "Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,
                       "Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
      tbvbrg.disableProperty().setValue(Boolean.FALSE);
      txtkodebrg.editableProperty().setValue(Boolean.TRUE);
      txtqty.editableProperty().setValue(Boolean.TRUE);
    }
    
    @FXML
    private void tambahbrgklik(ActionEvent event) {
        //detil
        JualdetilModel d=new JualdetilModel();        
        d.setNojual(txtnojual.getText());
        d.setKodebrg(txtkodebrg.getText());     
        d.setJumlah(Integer.parseInt(txtqty.getText())); 
        
        Integer jumlah1 = tbvbrg.getSelectionModel().getSelectedItem().getStok();
        Integer jumlah2 = Integer.parseInt(txtqty.getText());
        if (jumlah1>=jumlah2)
        {
            Integer hasil = jumlah1-jumlah2;
                
            BrgModel b = new BrgModel();
            b.setKodebrg(txtkodebrg.getText());
            b.setStok(hasil);
        
            FXMLDocumentController.dtbrg.setBrgModel(b);
            FXMLDocumentController.dtbrg.updatestok();
        
            FXMLDocumentController.dtjualdetil.setJualdetilModel(d);
            if(FXMLDocumentController.dtjualdetil.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,
                       "Barang berhasil ditambah",ButtonType.OK);
               a.showAndWait();            
                } else {
               Alert a=new Alert(Alert.AlertType.ERROR,
                       "Barang gagal ditambah",ButtonType.OK);
               a.showAndWait();            
               }
        
            showdatadetil();
            showdatabrg();
        }else
        {
          Alert a=new Alert(Alert.AlertType.ERROR,
          "Jumlah stok barang tidak mencukupi",ButtonType.OK);  
          a.showAndWait();
        }
    }

    @FXML
    private void hapusbrgklik(ActionEvent event) {
        
        Integer jumlah2 = tbvbrg.getSelectionModel().getSelectedItem().getStok();
        Integer jumlah1 = tbvdetil.getSelectionModel().getSelectedItem().getJumlah();
        Integer hasil = jumlah1 + jumlah2;
        
        BrgModel g = new BrgModel();
        g.setKodebrg(txtkodebrg.getText());
        g.setStok(hasil);
                
        FXMLDocumentController.dtbrg.setBrgModel(g);
        FXMLDocumentController.dtbrg.updatestok();
            
        JualdetilModel s= new JualdetilModel();       
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,
                "Barang akan dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtjualdetil.delete
                  (txtnojual.getText(),txtkodebrg.getText())){
                   Alert b=new Alert(Alert.AlertType.INFORMATION,
                   "Barang berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,
                       "Barang gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdatadetil();   
        }
    }


    @FXML
    private void tbvcustklik(MouseEvent event) {
      txtid.setText(tbvcust.getSelectionModel().getSelectedItem().getId());    
    }

    @FXML
    private void tbvbrgklik(MouseEvent event) {
     txtkodebrg.setText(tbvbrg.getSelectionModel().getSelectedItem().getKodebrg());
    }

   @FXML
    private void batalklik(ActionEvent event) {
    }

    @FXML
    private void pilihdetil(MouseEvent event) {
        txtkodebrg.setText(tbvdetil.getSelectionModel().getSelectedItem().getKodebrg());
        txtqty.setText(String.valueOf(tbvdetil.getSelectionModel().getSelectedItem().getJumlah()));
    
    }

    
    
}
