package com.celine.controller;

import com.celine.Main;
import com.celine.entity.Barang;
import com.celine.entity.Category;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TampilanAwalController implements Initializable {

    @FXML
    public TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    public ComboBox<Category> comboCategory;
    @FXML
    public DatePicker expDate;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnReset;
    @FXML
    public Button btnUpdate;
    @FXML
    public TableColumn<Barang,String> colID;
    @FXML
    public TableColumn<Barang,String> colName;
    @FXML
    public TableColumn<Barang,String> colCategory;
    @FXML
    public TableColumn<Barang,String> colExpDate;
    public TableView<Barang> tableBarang;
    private Barang z;
    Alert error = new Alert(Alert.AlertType.ERROR);


    private ObservableList<Barang> barangs;
    private ObservableList<Category> categories;
//    private TampilanAwalController tampilanAwalController;

//    public void setTampilanAwalController(TampilanAwalController tampilanAwalController) {
//        this.tampilanAwalController = tampilanAwalController;
//        tableBarang.setItems(tampilanAwalController.getBarangs());
//    }

//    public ObservableList<Barang> getBarangs() {
//        if(barangs==null){
//            barangs= FXCollections.observableArrayList();
//        }
//        return barangs;
//    }

    public ObservableList<Category> getCategories() {
        if(categories==null){
            categories= FXCollections.observableArrayList();
        }
        return categories;
    }

    public void SaveAction(ActionEvent actionEvent) {
        Barang p = new Barang();
        Boolean found = false;
        if(!txtName.getText().isEmpty() && !txtID.getText().isEmpty() && comboCategory.getValue()!=null && expDate.getValue()!=null){
            p.setName(txtName.getText().trim());
            p.setID(Integer.valueOf(txtID.getText()));
            p.setCategory(comboCategory.getValue());
            p.setExpDate(String.valueOf(expDate.getValue()));

            for (Barang i:barangs){
                if(i.getName().equals(p.getName())){
                    found = true;
                    error.setContentText("ID is duplicate");
                    error.showAndWait();
                    break;
                }
            }
            if (!found) {
                barangs.add(p);
            }
            txtID.clear();
            txtName.clear();
            comboCategory.setValue(null);
            expDate.setValue(null);
        }
        else{
            error.setContentText("Please fill ID/Name/Category/ExpiredDate");
            error.show();
        }
    }

    public void ResetAction(ActionEvent actionEvent) {
        txtName.clear();
        txtID.clear();
        comboCategory.setValue(null);
        expDate.setValue(null);
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
    }

    public void UpdateAction(ActionEvent actionEvent) {
        int count =0 ;
        String tempName = z.getName();
        z.setID(Integer.valueOf(txtID.getText()));
        for (Barang i:barangs){
            if(i.getName().equals(txtID.getText())){
                count+=1;
            }
        }
        if(count > 1){
            error.setContentText("ID is duplicate");
            error.showAndWait();
            z.setName(tempName);
        }
        else{
            z.setName(txtName.getText());
            z.setCategory(comboCategory.getValue());
            z.setExpDate(String.valueOf(expDate.getValue()));
        }
        tableBarang.refresh();
    }

    public void showCategoryAction(ActionEvent actionEvent) {
        try {
//            Parent root = FXMLLoader.load(Main.class.getResource("view/FacultyForm.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TampilanCaregory.fxml"));
            BorderPane root = loader.load();
            TampilanCategoryController controller = loader.getController();
            controller.setTampilanAwalController(this);

            Stage mainStage = new Stage();
            mainStage.setTitle("Category Form");
            mainStage.setScene(new Scene(root));
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categories = FXCollections.observableArrayList();
        barangs = FXCollections.observableArrayList();
        comboCategory.setItems(categories);
        tableBarang.setItems(barangs);
        colID.setCellValueFactory(data-> {
            Barang p = data.getValue();

            return new SimpleStringProperty(String.valueOf(p.getID()));
        });
        colName.setCellValueFactory(data->{
            Barang p = data.getValue();

            return new SimpleStringProperty(p.getName());
        });
        colExpDate.setCellValueFactory(data->{
            Barang p = data.getValue();

            return new SimpleStringProperty(String.valueOf(p.getExpDate()));
        });
        colCategory.setCellValueFactory(data->{
            Barang p = data.getValue();

            return new SimpleStringProperty(p.getCategory().getName());
        });
    }

    public void tableClicked(MouseEvent mouseEvent) {
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        z = tableBarang.getSelectionModel().getSelectedItem();
        txtName.setText(z.getName());
        txtID.setText(String.valueOf(z.getID()));
        txtName.setText(z.getName());
        comboCategory.setValue(z.getCategory());
        expDate.setValue(LocalDate.parse(z.getExpDate()));
    }
}
