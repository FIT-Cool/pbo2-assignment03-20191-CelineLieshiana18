package com.celine.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TampilanAwalController implements Initializable {

    @FXML
    public TextField txtID;
    @FXML
    public ComboBox comboCategory;
    @FXML
    public DatePicker expDate;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnReset;
    @FXML
    public Button btnUpdate;
    @FXML
    public TableView tableDepartment;
    @FXML
    public TableColumn colID;
    @FXML
    public TableColumn colName;
    @FXML
    public TableColumn colCategory;
    @FXML
    public TableColumn colExpDate;
    @FXML
    private TextField txtName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void SaveAction(ActionEvent actionEvent) {
    }

    public void ResetAction(ActionEvent actionEvent) {
    }

    public void UpdateAction(ActionEvent actionEvent) {
    }

    public void showCategoryAction(ActionEvent actionEvent) {
    }

    public void closeAction(ActionEvent actionEvent) {
    }
}
