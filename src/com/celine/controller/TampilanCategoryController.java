package com.celine.controller;

import com.celine.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TampilanCategoryController implements Initializable {
    public TextField txtID;
    public TextField txtName;
    public Button btnSave;
    public TableColumn<Category,String> colID;
    public TableColumn<Category,String> colName;
    public TableView<Category> tableCategory;
    private TampilanAwalController tampilanAwalController;
    private Integer count;
    Alert error = new Alert(Alert.AlertType.ERROR);
    private ObservableList<Category> aa;

    public void setTampilanAwalController(TampilanAwalController tampilanAwalController) {
        this.tampilanAwalController = tampilanAwalController;
        tableCategory.setItems(tampilanAwalController.getCategories());
    }

    public void SaveAction(ActionEvent actionEvent) {
        aa = tampilanAwalController.getCategories();
        Category c = new Category();
        boolean found = false;
        c.setID(Integer.valueOf(txtID.getText()));
        c.setName(txtName.getText());

        if(txtID.getText().isEmpty() && txtName.getText().isEmpty() ){
            error.setContentText("Please fill ID/Name category");
            error.show();
        }
        else {
            for (Category i : aa) {
                if (i.getID().equals(c.getID())) {
                    found = true;
                    error.setContentText("ID is duplicate");
                    error.showAndWait();
                    break;
                }
            }
            if (!found) {
                aa.add(c);
            }
            txtID.clear();
            txtName.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(data-> {
            Category c = data.getValue();

            return new SimpleStringProperty(String.valueOf(c.getID()));
        });
        colName.setCellValueFactory(data-> {
            Category c = data.getValue();

            return new SimpleStringProperty(c.getName());
        });

    }
}
